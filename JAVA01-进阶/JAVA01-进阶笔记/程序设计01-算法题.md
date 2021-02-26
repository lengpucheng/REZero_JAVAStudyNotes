# T1.两数之和

给定一个整数数组 `nums` 和一个目标值 `target`，请你在该数组中找出和为目标值的那 **两个** 整数，并返回他们的数组下标。

```java
class Solution {
    public int[] twoSum(int[] nums, int target) {}
    
    }
```



## 知识点：HashMap检验

1. `map.containsKey`：用来检验一个Key是否存在于HashMap表中；
2. `map.containsValue`:用来检验一个值是否被包含在HashMap表中；



## 解法一：暴力解法

遍历每个合，对比，与结果相同就返回

时间复杂度：o（n^2)

```java
 public int[] twoSum(int[] nums, int target) {
        for(int i=0;i<nums.length;i++)
            for(int j=i+1;j<nums.length;j++)
                if(nums[i]+nums[j]==target)
                    return new int[] {i,j};
    	throw new RuntimeException("not sum");
    	}
```



## 解法二：Hash表映射

利用将每个值作为key加入到hash中，判断与结果的余数是否存在于hash表中，存在即返回，否则加入表中。

时间复杂度最低位 o(n)

```java
 public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> map=new HashMap<>();
        for(int i=0;i<nums.length;i++){
            int key=target-nums[i];
            if(map.containsKey(key))
                return new int[] {map.get(key),i};
            map.put(nums[i],i);
        }
        throw new RuntimeException("error");
    }
```



# T2.两数相加

给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。

如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。

您可以假设除了数字 0 之外，这两个数都不会以 0 开头。

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    
}
```



## 知识点：链表

1. 直接使用（temp.next=x)赋值,可以避免引用被更改。
2. 在直接赋值 tmep=temp.next，使得引用向后移动。
3. **不可以用先转换位int再相加会溢出**
4. **只有当尾节点为null时才不会关闭引用**

## 解题：同位相加

```java
 public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head=new ListNode(0);//头节点
        ListNode rest=head;//尾节点
        int nextNumber=0;//进位
        
        while(l1!=null||l2!=null){
            int n1=l1==null?0:l1.val;//取出当前节点的数，节点为空取0
            int n2=l2==null?0:l2.val;
            int sum=n1+n2+nextNumber;//当前位和上一位的进位相加;
            
            if(sum>9){
                nextNumber=1;
                sum=sum-10;//将当前位设置为进位后的余数
            }else
                nextNumber=0;//恢复为0
            rest.next=new ListNode(sum);//在尾部插入
            rest=rest.next;//移动到尾部
            //向后移动
            l1=l1==null?null:l1.next;//如果当前节点为空就赋空值否则向后移动
            l2=l2==null?null:l2.next;
        }
        //如果进位不为0，继续添一位
        if(nextNumber!=0)
            rest.next=new ListNode(nextNumber);

        return head.next;
    }
```



# T3. 重复字符串

给定一个字符串，请你找出其中不含有重复字符的 **最长子串** 的长度。

```java
class Solution {
    public int lengthOfLongestSubstring(String s) {}
```



## 知识点：滑动窗口合HashSet

1. `HashSet`初始化 `Set<T>=new HashSet<T>();`，set不允许出现重复
2. `Char`包装对象是`Character`
3. `set.contains()`检查是否包含
4. `set.add()`当值重复时候会返回`false`，`set.put()`则会直接覆盖
5. 滑动窗口：**从当前位置开始，向后检验并设定停止条件**,类似于向后滑动，即已经**滑动的中间是一定符合条件的**；

## 解题：滑动窗口

```java
public int lengthOfLongestSubstring(String s) {
    Set<Character> set=new HashSet<Character>();//右指针，同时记录字符串中的字符
    int len=s.length();//字符串长度
    int left=-1;//开始时的左指针(注意在最开始的前边)
  	int max=0;//最大长度
    for(int i=0;i<len;i++){
        if(i!=0)
            set.remove(s.charAt(i-1));//让右指针向前移一位
        while(left+1<len&&set.contains(s.charAt(left+1))){
            //左指针没有到最后并且后一项不与前面重复
            left++;//左指针右移
            set.add(s.charAt(left));//右指针右移动
        }
        max=Math.max(max,left-i+1);//最大长度和当前滑动窗口覆盖长度取最大值
    }
    return max;
    
}
```



# T4.寻找两个数组中位数

给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。

请你找出这两个正序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。

你可以假设 nums1 和 nums2 不会同时为空。

```java
class Solution {
    }
```

## 知识点：二分查找

1. `中位数`是`将一个集合划分为两个长度相等的子集，其中一个子集中的元素总是大于另一个子集中的元素。`

2. 因此只需要判断奇偶性，找到n/2或 n/2和n/2+1的数即可**(对应下标为n/2-1 和n/2)**
3. 故将两个数组归并，比较入栈，直到n/2+1个数为止；（因为非空，故最小位2，对应下标为n/2）
4. int除法，记得加f，否则会变为int强转

## 解法一：二分归并

```java
 public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        int length = len1 + len2;
        int end=length/2;//中间位
        List<Integer> list = new ArrayList<>();
        int rk1 = 0;//指针
        int rk2 = 0;
        //入栈
        for (int i = 0; i <=length / 2; i++) {
            if (rk1 == len1) {//n1溢出
                list.add(nums2[rk2]);
                rk2++;
            } else if (rk2 == len2) {//n2溢出
                list.add(nums1[rk1]);
                rk1++;
            } else {
                if (nums1[rk1] < nums2[rk2]) {
                    list.add(nums1[rk1]);
                    rk1++;
                } else {
                    list.add(nums2[rk2]);
                    rk2++;
                }
            }
        }
        //弹出
        if(length%2==0)
            return (list.get(end)+list.get(end-1))/2f;
        else
            return list.get(length/2);
    }
```



# T42.连续子数组的最大和*

输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。

要求时间复杂度为O(n)。

```java
class Solution {}
```

## 题目分析

1. 求最大子串和，则一定是从左往右，因此必须有一个从左往右的扫描
2. 如果说左边前n项和是一个负数，那么一定是越来越小，因此可以直接舍弃
3. 如果右边后n项合全部是负数，那么显然左边前n项最大

因此可以有如下推论：

​	1. Max=max(左边前N-m项合，左边前N-m-x项合)；

 2. 如果前m-1项合为负数，那么从第m项单独起的合 n-m项一定大于前m项。

## 解题：逻辑分析

```java
public int maxSubArray(int[] nums) {
	int rest=nums[0];//最开始前N项目即为单一0项
    int sum=0;//n-m项目合暂时为0
    for(int i:nums){//扫描
        if(sum>=0)//如果大于前m项合大于0就累加
            sum+=i;
        else//否则从m项开始
            sum=i;
        rest=Math.max(rest,sum);//取前n-m合n-m-x的最大合
    }
    return rest;
}
```


​    



# T0.最长回文子串*

给定一个字符串 `s`，找到 `s` 中最长的回文子串。你可以假设 `s` 的最大长度为 1000。

```java
class Solution {
    public String longestPalindrome(String s) {}
}
```

```
输入: "babad"
输出: "bab"
注意: "aba" 也是一个有效答案。
```

## 特别说明

使用`str.toCharArray()`将字符串转换为字符数组，可以简化`时间复杂度`

**`charAt`每一次都会检查越界，时间复杂度为o(n)**

`subsring(s,e)`为**左闭右开**,即包含s下标的字符，不包含e下标的字符



## 解法一：暴力枚举

### 检测回文

1. 比较两头字符是否相同，如果是则左右向中间收拢再次比较，否则一定不是回文

2. 如果当左指针大于或等于右指针时，检验结束

   ```java
      public boolean isHuiWenStr(char[] charArray,int start,int end){
           while(start<end){
               if(charArray[start]!=charArray[end])
                   return false;
               start++;
               end--;
           }
           return true;
       }
   ```

### 暴力枚举

1. 小于2个长度是，本身就是回文即返回本身
2. 左指针`i`从左向右开始遍历到字符串长度减一结束（小于2个时候本身就是长度为1的回文串，无需遍历）
3. 遍历指针`j`每次从左指针比当前最大子串大的位置（`j=i+max`)开始遍历，并检验是否是回文字符串，如果是就记录起始位置`start=j`和最大长度`max=j-i+1`;
4. 最后返回`start`到`start+max`的字符串

```java
 public String longestPalindrome(String s) {
        if(s.length()<2)
            return s;
        
        int start=0;
        int max=1;
        char[] chars=s.toCharArray();//转换为array提高效率
        int size=chars.length;
        for(int i=0;i<size-1;i++){//左指针左移
            for(int j=i+max;j<size;j++)//遍历
                if(isHuiWenStr(chars,i,j)){//是否是回文字串
                    start=i;
                    max=j-i+1;//记录其实位置和长度
                }
        }
        return s.substring(start,start+max);//subString是左闭右开
    }
```



## 解法二：中心扩散

每一次遍历时，把当作回文

### 检验回文

1. 判断中间两个是否相等（如果是奇数其实左右指针相同），相同这左指针左移，右指针右移，否则跳出
2. 当左右有一个触碰边界时停止
3. 返回为回文长度，跳出循环时候左右一定不相对减去两边2再加上端点1，故长度为 左-右-2+1=左-右-1

```java
/**
     * 返回当前中心开始回文字符串的长度，奇数时左右相等
     * @param charArray 字符串数组
     * @param left  左指针
     * @param right 右指针
     * @return 最大回文字符串长度
     */
    public static int isHuiWenStr(char[] charArray,int left,int right){
        int size=charArray.length;//字符串长度
        while(left>=0&&right<size){
            if(charArray[left]!=charArray[right])
                break;
            left--;
            right++;
        }
        return right-left-1;
    }
```

### 中心扩散

1. 当字符串长度小于`2`时，本身就是回文，返回本身

2. 否则以 第下标`0`开始向右一直到下标`n-1`结束，作为回文中心

3. 分别检验字符串为奇数和偶数时的回文串长度，取最大的哪个为当前回文中心的最大长度`theMax`

4. 如果`Max`小于`theMax`则赋值为其，并记录开始为`start=i-(theMax-1)/2`

5. 遍历结束后，截取`star`到`star+max`的字符串

   

```java
 public static String longestPalindrome(String s) {
       if(s.length()<2)
            return s;
        int start=1;
        int max=1;
        char[] chars=s.toCharArray();
        int size=chars.length;
        for(int i=1;i<size;i++){
            //奇数情况
            int js=isHuiWenStr(chars,i,i);
            //偶数情况
            int os=isHuiWenStr(chars,i,i+1);
            //比较大小,如果大记录最大的长度和当前中心
            if(js>max||os>max){
                max=Math.max(js,os);
                start=i-(max-1)/2;
            }
        }

            return s.substring(start,start+max);

    }
```



## 解法三、动态规划

1. 因为回文字符串长度大于3时候，去掉首位中间依然是回文
2. 当中间是偶数时候，判断两个是否相等，中间是奇数1时候直接成立，因此对角线一定是回文

故根据1、2可以得出长度为n的字符共有n*n个方格包含对角线的字符串（n(n+1)/2，相当于插入两块分割线)。

则有

​	`dp[l][r]= (n==m)&&dp[l+1][r-1]`，即一个字符串是否回文取决去其两端是否相等，以及中间是否回文

```java
     public static String longestPalindrome(String s) {
        if (s.length() < 2)
            return s;
        char[] chars = s.toCharArray();
        int len = chars.length;
        boolean[][] dp = new boolean[len][len];
        int max = 1;//最大长度
        int start = 0;//起始位置
        for (int i = 0; i < len; i++)
            dp[i][i] = true;//对角线即单个都回文


        for (int right = 1; right < len; right++) {//右指针右移
            for (int left = 0; left < right; left++) {//左指针左移
                if(chars[left]!=chars[right])
                    dp[left][right]=false;//首位不相等一定不是回文
                else {
                    if(right-left<3)
                        dp[left][right]=true;//如果长度小于3就一定是回文
                    else
                        dp[left][right]=dp[left+1][right-1];//否则取决于中间
                }

                if(dp[left][right]&&(right-left)+1>max){
                    //如果当前是回文并且长度比最大还有大
                    max=right-left+1;
                    start=left;
                }
            }
        }
        return s.substring(start, start + max);
    }
```

