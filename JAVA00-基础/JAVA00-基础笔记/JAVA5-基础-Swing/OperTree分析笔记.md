# 基本类型

1. `OperTreeModel`继承于`DefaultTreeModel`作为基本的命名存储节点，其中构造函数实例化一个`OCommandGroupNode`作为对象，作为根节点传入，根节点默认图片，名称为”“；
2. 其中`OCommandGroupNode`是一个继承自`DefaultDualTreeNode`封装的一个基本节点样式，包含默认图标，其构造函数传入一个 id 和 当前操作的名称，调用父类构造，**其中，如果ID==某一个常量收藏，则设置为星号图标**
3. `DefaultDualTreeNode`继承自`TristateTreeNode`是封装好的一个包含三种基本状态的基本节点对象，并设置相应的界面图标，可以设置 其 id，名称，选中状态，图标。
   1. `TristateTreeNode`是包含三种状态的节点对象，继承于`MyNode`，包含一个节点`id`用来进行唯一标识，可以设置该节点是否是有效（valid）、是否又选择框（checkbox）、并统计子节点的选中个数（如有）。
   2. `MyNode`是直接继承于`DefultMutableTreeNode`的最基本节点，重写了`getChildAt`，**保证为null时候返回null而不是抛出异常。**
4. `DualTreeUtils`，是一个用来构造不同类型的树的工具类（static），其中`createEmptyTree`可以构造一个不显示工具栏的空树，并隐藏了根节点



# 创建操作树

在`OtnmNeTreePanelProvider`中创建操作树

# 实现

实现操作 操作数面板的类是 `OperTreePanel`类，直接继承于`JPanel`

### 属性

| 名             | 类             | 释意           | 来源 |
| -------------- | -------------- | -------------- | ---- |
| bundle         | ResourceBundle |                |      |
| keepSelection  | boolean        | 是否保存选项   |      |
| operTree       | JTree          | 组件树         |      |
| treeScrollpane |                | 滚动窗口       |      |
| treeModel      | OperTreeModel  | 基本存储根节点 |      |
| neBean       | NEBean           | 当前网元对象               |      |
| currentSelectBean | AbstractBean |  |      |
| lstTreeProvider | List<> | 提供操作树的数据接口 |      |
| searchCom | BusinessConfigSearchCom |  |  |
| defaultFunclist | Set<String> | 可能是默认的功能列表| |
| commonFunclist | list<GlobaTempIateBean> | 全局命名的模板 | |
| lock | obj | 对象锁 | |
| templateHelper | IGIobalTemplateHelper | CRUD接口 | 获取的类调用接口 |
| deleteSuc | boolean | | 默认true|
| addSuc | boolean | | 默认true|
| nEProperties | String | 网元配置类名 | class.getName|
| selObjKey | obj | 默认选中的类| |
| MAXSIZE | int | 最大的常用功能树 | 10 |
| cacheFunctionMap | map<int,list<sting>> | 缓存当前的网元的操作树功能，key网元，value 操作数功能 | |
| isOpticlNe | boolean | 是否是光网元 | false |



### 构造函数

构造函数需传入参数 ne  ，即传入一个`final`网元，并存储当前网元

***(`final`修饰引用参数，仅仅只是不能更改引用地址)***

1. 用一个ArrarList来实例化`lstTreeProvider`
2. 将配置网元类`nEproperties`添加到默认的网元操作列表 `defaultFuncList`中
3. `LookUp`查找所有的的实现`IOperTreeProvider`接口的类，放入到`lstTreeProvider`，并把接口中的默认列表全部添加到，默认操作树列表`defaultFuncList`中
4. 为`operTree`使用工具类`DualTreeUtils`获取一个空的不显示标题和根的空树
5. 为`treeModel`实例化一个`OperTreeModel`根节点
6. 将根节点添加到空树,并添加选择监听器
7. 为树添加 鼠标事件 监听器
   1. 选中后，把`keepSelection`取反
8. `treeScrollPane`使用滑动面板工厂传入参数 `operTree`，并在顶部显示一个黑分界线
9. 用`operTree`实例化一个`BusinessConfigSearchCom`对象赋值给`srarchCom`
10. 设置样式为`BorderLayout`，把搜索框设置在顶部（north），操作数的滑动面板放在中部（center）



### 获取单盘操作树

在方法`getAllNeAndBoardFunctions`中传入 当前打开的类型，类型状态参数，选中的节点`selectdBean`，判断`type`，针对不同类型加载不同的列表

1. 如果是网元，先重缓存中读取列表，如果list不为空，直接使用当前的`selectedBean`来更新列表，并返回
2. 否则构造一个`RpcTransHelper`对象helper（调用底层的ice接口）
3. 实例化一个 `NInt2intSeqMapHolder`对象inHolder，用来保存输入数据，并把当前对象的状态参数赋值给inHolder
4. 实例化一个outHolder对象，来保存输出数据

构造一个`SwingWorker`线程池，在其中实例化一个 List<GlobalTemplateBean>全局模板对象集 beans，和step记录当前执行的步骤，重写`doinBackGround`方法：

1. 如果`commonFuncList`命名列表不大于零，即命名列表为空，先获取当前用户的ID，取一个标记`isFirstOpenNemanage`来表明是否是第一次打开
2. 使用`queryTemplates`传入用户id和beans进行，进行查询，并返回错误信息，将查询结果保存在beans中
3. 如果beans大于0 就清空命名列表，并将beans放入其中，否则返回错误信息
4. step+1，如果是第一次打开（isFirstOpen=ture），将 默认列表中的每一条传入 命名模板，并调用ICE执行，如果成功就将其加入到 命名列表中，否则返回错误代码
5. step+1，创建一个连接信息对象，并将用户连接信息转换为ICE对象，执行`helper.call`,参数包括 inHolder和outHolder，对其更新

重写`done`方法，如果前面执行成功，取出所有的 outHolder中的值，并存入缓存列表中，否则弹窗并工具执行的步骤提示使用 `selectedBean`更新界面
