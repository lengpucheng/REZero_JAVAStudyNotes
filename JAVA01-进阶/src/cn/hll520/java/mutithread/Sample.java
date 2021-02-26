package cn.hll520.java.mutithread;

/**
 * 描述：生产者和消费者
 *
 * @author lpc lpc@hll520.cn
 * @version 1.0  2021-02-25-16:21
 * @since 2021-02-25-16:21
 */
public class Sample {

    public static void main(String[] args) {
        Goods goods = new Goods();
        new Thread(new Producer(goods)).start();
        new Thread(new Consumer(goods)).start();
    }
}

class Consumer implements Runnable {
    private final Goods goods;

    public Consumer(Goods goods) {
        this.goods = goods;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (goods) {
                if (goods.sum == 0) {
                    try {
                        goods.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "消费商品N" + goods.id + " 剩余" + goods.sum);
                goods.sum--;
                goods.notifyAll();
            }

        }
    }
}


class Producer implements Runnable {
    private final Goods goods;

    public Producer(Goods goods) {
        this.goods = goods;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (goods) {
                if (goods.sum == 20) {
                    try {
                        goods.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                goods.sum++;
                goods.id++;
                System.out.println(Thread.currentThread().getName() + "生产商品N" + goods.id + " 剩余" + goods.sum);
                goods.notifyAll();
            }

        }
    }
}

class Goods {
    int id = 1001;
    int sum = 0;
}


