# Swing中的EDT事件派发线程

当Swing程序被运行时会触发三个线程的启动：

1. main线程，即程序的入口主线程
2. `Toolkit`，仅用于捕捉系统的各种状态和事件，包括鼠标移动、点击、键盘等，不会执行其他任何操作
3. `EventDispatchThread`线程，即EDT线程，负责接收 `Toolkit`捕获的事件添加监视器，以及绘制界面

EDT线程底层是`EventQueue`，其本质上是一个队列，将接收的各种事件和绘图，放置在一个队列中按先后顺序依次执行，因此**如果某一个线程操作耗时过长会导致其他线程长时间等待，并且无法更新UI，造成界面未响应状态**。

各种事件在EDT中都是一个`paint`对象（绘制）用于更新界面，因此相同的连续事件或操作会同一合并为一个操作或事件放入队尾用于快速更新，故**当一系列连续非UI操作在EDT线程中执行时，可能只执行一次**



因为EDT是串行执行，因此需要：

1. **所有的耗时操作应在非EDT中执行**
2. **所有的更新UI操作应该在EDT中执行**



> 例如：期望每点击按钮后2秒内进度条冲0到满

## 错误：在EDT中执行耗时操作

```java
 public static void main(String[] args) {
        JFrame f = new JFrame();
        JButton button = new JButton("GO");
        JProgressBar bar = new JProgressBar() {
            @Override
            public void paint(Graphics g) {// 复写绘制方法，打印绘制的内容
                super.paint(g);
                System.out.println(g);
            }
        };
        f.setLayout(new FlowLayout());
        f.add(bar);
        f.add(button);
        f.pack();
        f.setVisible(true);
        button.addActionListener(e -> {
            for (int i = 0; i < 100; i++) {
                try {
                    Thread.sleep(20);//每0.02秒进度条加1%
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
                bar.setValue(i);
            }

        });
    }
```

实际情况是，点击GO后，整个界面卡主不动，2秒后进度条一瞬间满了，并仅仅只是打印了一条绘制。

原因是EDT执行UI界面更新，但执行了耗时操作，而且更新进度条是一个连续的操作因此被打包为一次绘制事件，排在了最后一次的绘制进度条，从而导致sleep先于绘制执行，导致未响应两秒后一致性执行。



## 修改：耗时操作应该异步执行

```java
public static void main(String[] args) {
    JFrame f = new JFrame();
    JButton button = new JButton("GO");
    JProgressBar bar = new JProgressBar() {
        @Override
        public void paint(Graphics g) {// 复写绘制方法，打印绘制的内容
            super.paint(g);
            System.out.println(g);
        }
    };
    f.setLayout(new FlowLayout());
    f.add(bar);
    f.add(button);
    f.pack();
    f.setVisible(true);
    button.addActionListener(e -> new Thread(() -> {// 新建应该匿名线程异步执行耗时操作
        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(20);//每0.02秒进度条加1%
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
            bar.setValue(i);
        }
    }).start());
}
```

将耗时操作移动到一个新的线程中，EDT仅仅负责界面更新，界面不会卡顿，因为在异步中，绘制事件是非连续的因此不会被合并，因此实习了需求。






# SwingUtilities

主要实现类Swing的一些常用接口和工具，其中：

1. `SwingUtilities.invokeAndWait()` 用于同步调用EDT，即阻塞当前EDT并同步执行该方法
2. `SwingUtilities.invokeLater()`用于在EDT队列尾部插入当前线程，并等待异步执行
3. `SwingUtilities.isEventDispatchThread()`用于判断当前线程是否是EDT线程

**通常启动Swing程序，应该在`SwingUtilities.invokeAndWait()`中打开窗口**

**在其他线程中更新UI时，应该将更新UI的操作加入到EDT中，即使用**`SwingUtilities.invokeLater()`



## 修改：把异步的UI更新移动到EDT

之前虽然把耗时操作放到了异步线程中，但是对进度条的更新却不在EDT线程当中，因为Swing是非线程安全，可能会导致EDT和异步线程同时进行UI界面更新的问题导致界面显示异常，因此需要将进度条更新放入到EDT中，只需要将之前的操作用`SwingUtilities.invokeLater()`包装接口，即将更新放入到EDT队尾。

```java
public static void main(String[] args) {
        JFrame f = new JFrame();
        JButton button = new JButton("GO");
        JProgressBar bar = new JProgressBar() {
            @Override
            public void paint(Graphics g) {// 复写绘制方法，打印绘制的内容
                super.paint(g);
                System.out.println(g);
            }
        };
        f.setLayout(new FlowLayout());
        f.add(bar);
        f.add(button);
        f.pack();
        f.setVisible(true);
        button.addActionListener(e -> new Thread(() -> {// 新建应该匿名线程异步执行耗时操作
            for (int i = 0; i < 100; i++) {
                try {
                    Thread.sleep(20);//每0.02秒进度条加1%
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
                int finalI = i;
                SwingUtilities.invokeLater(() -> bar.setValue(finalI));
            }
        }).start());
    }
```





# SwingWorker

抽象方法`T doInBackground`，执行耗时操作，并返回结果为T 的对象

结果显示方法 ` void done()`,当线程执行完毕后，执行done方法进行界面更新

传递中间数据 ` publish(V ...)` 向耗时操作传递中间数据，可以在`doInBackground`中进行调用

1. **EDT线程仅仅只是用来执行 界面的绘制与更新  其他耗时操作均要在工作线程中执行**
2. **不要在其他线程中更新界面**

因此，在`doInBackground`中执行耗时操作，在`done`中更新UI界面



| 方法                                          |                             功能                             |
| --------------------------------------------- | :----------------------------------------------------------: |
| boolean cancel(boolean mayInterruptIfRunning) |                     取消正在进行的工作。                     |
| T get()                                       | 获取类型为T的结果值。该方法将一直处于阻塞状态，直到结果可用。 |
| T get(long timeout,TimeUnit unit)             |    获取类型为T的结果值，将会一直阻塞直到结果可用或超时。     |
| boolean isCancelled()                         |                   判断任务线程是否被取消。                   |
| boolean isDone()                              |                    判断任务线程是否完成。                    |
| abstract T doInBackground()                   | 该方法作为线程的一部分执行，负责完成线程的基本任务，并以返回值（类型为T）作为线程的执行结果。继承类必须覆盖该方法并确保包含或代理任务线程的基本任务。不要直接调用该方法，应使任务对象的execute方法来调度执行。 |
| protected void done()                         | 在doInBackground()方法完成之后，SwingWorker在EDT上激活done方法。如果任务需要在完成后使用线程结果更新GUI组件或者做些清理工作，可覆盖该方法来完成. |
| void publish(V…data)                          |   传递中间进度数据到EDT。从doInBankground方法调用该方法。    |
| void process(Listdata)                        |             覆盖该方法处理任务线程的中间结果数据             |
| void execute()                                |       为SwingWorker线程的执行预订这个SwingWorker对象。       |



## 修改：使用SwingWorker修改

```java
public static void main(String[] args) {
    JFrame f = new JFrame();
    JButton button = new JButton("GO");
    JProgressBar bar = new JProgressBar() {
        @Override
        public void paint(Graphics g) {// 复写绘制方法，打印绘制的内容
            super.paint(g);
            System.out.println(g);
        }
    };
    f.setLayout(new FlowLayout());
    f.add(bar);
    f.add(button);
    f.pack();
    f.setVisible(true);


    button.addActionListener(e -> {
        SwingWorker<String, Integer> worker = new SwingWorker<>() {
            @Override
            protected String doInBackground() throws Exception {
                for (int i = 0; i < 100; i++) {
                    Thread.sleep(20);
                    setProgress(i);
                }
                return "完成";
            }

            //执行结束后最后绘制
            @Override
            protected void done() {
                bar.setValue(100);
                try {
                    button.setText(get());// 获取当前的结果并显示在按钮上
                } catch (InterruptedException | ExecutionException interruptedException) {
                    interruptedException.printStackTrace();
                }
            }
        };

        worker.execute();
        // 绑定worker属性改变时候的监听事件
        worker.addPropertyChangeListener(evt -> {
            if ("progress".equals(evt.getPropertyName()))
                bar.setValue((Integer) evt.getNewValue());
        });
    });
}
```