# 一、Arrays数组操作

## 1.equals 比较相等

`Arrays.equals(X[],Y[])`长度和内容包括顺序完全一样

底层其规则为**先比较长度，再逐个比较**

## 2. toString 逐个输出

`Arrays.toString(X[])`将数组逐个输出，并加上[]

## 3.fill 填充

`Arrays.fill(X[],N)`将数组全部填充为指定的N

## 4.sort排序

`Arrays.sort(X[])` 对X进行排序，将调用`Comparator`接口

**底层为快速排序`DualPivotQuicksort `**

## 5.binarySearch查找

`Arrays.binarySearch(X[],K)`在数组X中查找K的位置，不存在返回负数。

**底层为二分查找**

## 6.arraycopy 复制

​	在`System.arraycopy`包下

参数为（源，源起始下标，目标，目标起始下标，长度）

