# LeetCode刷题笔记（排序部分）

[TOC]

## S.88_Merge Sorted Array

原题地址：https://leetcode.com/problems/merge-sorted-array/

思路：

​	给定nums1和nums2两个数组，其中包含的元素分别是m和和n个。要求把nums2中的树放到num1中，并且让num1是有序的。num1的大小可以容纳下两个数组的所有元素。

​	知识一道easy的题目，因为不允许使用多余的数组空间，就不好对两个数组从前往后进行比较（插入位置比较麻烦）。方法是从后往前比，然后处理剩下的元素。

代码：

```java
public void merge(int[] nums1, int m, int[] nums2, int n) {
		
		while(m > 0 && n > 0){
			if(nums1[m-1] > nums2[n-1]){
				nums1[m+n-1] = nums1[m-1];
				m--;
			}
			else{
				nums1[m+n-1] = nums2[n-1];
				n--;
			}
			
		}
		while(n>0){
			nums1[m+n-1] = nums2[n-1];
			n--;
		}
	}
```

### 复习：

对两个数组进行merge 排序：

```java
public int[] mergeList(int[] a, int[] b){
		int[] c = new int[a.length+b.length];
		int i = 0; //visit a
		int j = 0; //visit b
		int k = 0; //visit c
		while(i < a.length && j < b.length){
			if(a[i] < b [j])
				c[k++] = a[i++];
			else
				c[k++] = b[j++];
		}
		while(i < a.length)
			c[k++] = a[i++];
		while(j < b.length)
			c[k++] = b[j++];
		return c;
	}
```

对两个LinkedList进行merge 排序：

```java
public ListNode mergeList(ListNode list1, ListNode list2){
		if(list1 == null)
			return list2;
		if(list2 == null)
			return list1;
		
		ListNode newhead = new ListNode(-1);
		ListNode head = newhead;
		
		while(list1 != null && list2 != null){
			if(list1.val < list2.val){
				newhead.next = list1;
				list1 = list1.next;
				newhead = newhead.next;
			}
			else{
				newhead.next = list2;
				list2 = list2.next;
				newhead = newhead.next;
			}
		}
		
//		while(list1 != null){
//			newhead.next = list1;
//			list1 = list1.next;
//			newhead = newhead.next;
//		}
		//替换上面的while循环
		if(list1 != null){
			newhead.next = list1;
		}
//		while(list2 != null){
//			newhead.next = list2;
//			list2 = list2.next;
//			newhead = newhead.next;
//		}
		//替换上面的while循环
		if(list2 != null){	
			newhead.next = list2;
		}
		//使用if这里就不要再赋予额外的null指针了
//		newhead.next = null;
		return head.next;
	}
```

## S.21_Merge Two Sorted Lists

原题地址：https://leetcode.com/problems/merge-two-sorted-lists/

思路；

​	因为是对有序的链表排序，就从头开始，分别比较两个链表的每个元素。较小元素放前面，然后一次往后走。

代码：

```java
public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        
        if(l1 == null)
        	return l2;
        if(l2 == null)
        	return l1;
        
        ListNode newhead = new ListNode(-1);
        ListNode head = newhead;
        
        while(l1 != null && l2 != null){
        	if(l1.val < l2.val){
        		newhead.next = l1;
        		l1 = l1.next;
        		newhead = newhead.next;
        	}
        	else{
        		newhead.next = l2;
        		l2 = l2.next;
        		newhead = newhead.next;
        	}
        }
        if(l1 != null)
        	newhead.next = l1;
	
        if(l2 != null)
        	newhead.next = l2;
	
        return head.next;
	
    }
```

## S.23_Merge k Sorted Lists

原题地址：https://leetcode.com/problems/merge-k-sorted-lists/

思路：

​	这道题目是让对K个sorted list进行排序。可以在对两个sorted list排序的基础上进行扩展，比如说每次都调用两两排序的方法，但是这样做，时间复杂度会很高。



### 回顾一下什么是归并排序：

​	归并排序是对一个无序的数组进行排序。最差时间复杂度是O（nlgn）。

参考链接：http://www.java2novice.com/java-sorting-algorithms/merge-sort/

> 1) Divide the unsorted array into n partitions, each partition contains 1 element. Here the one element is considered as sorted. 
>
> 2) Repeatedly merge partitioned units to produce new sublists until there is only 1 sublist remaining. This will be the sorted list at the end.

 ![MergeSort](pics\MergeSort.PNG)

代码：

```java
public class Merge {
	private static int[] aux;
	
	public static void sort(int[] a){
		aux = new int[a.length];
		sort(a, 0, a.length-1);
	}
	private static void sort(int[] a, int lo, int hi){
		if(hi <= lo)
			return ;
		int mid = (hi+lo)/2;
		sort(a, lo, mid);
		sort(a, mid+1,hi);
		merge(a, lo, mid, hi);
	}
	
	public static void merge(int[] a, int lo, int mid, int hi){
		int i = lo, j = mid+1;
		for(int k = lo; k <= hi; k++)
			aux[k] = a[k];
		
		for(int k = lo; k <= hi; k++)
			if(i > mid)  //mid左边的全部排序结束，右边的接着放就行
				a[k] = aux[j++];
			else if(j > hi)   //mid右边的全部排序结束，左边的直接放即可
				a[k] = aux[i++];
			else if(aux[j] < aux[i])  //mid两边逐个比较，
				a[k] = aux[j++];
			else 
				a[k] = aux[i++];
		
	}
	
	public static void main(String[] args) {
//		int[] a = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16};
		int[] a = {16,15,14,13,12,11,10,9,8,7,6,5,4,3,2,1};
		for(int ele : a)
			System.out.print(ele+" ");
		sort(a);
		System.out.println();
		for(int ele : a)
			System.out.print(ele+" ");
		
	}
	
}
```

思路：

​	借鉴归并排序的思想，针对这个lists进行排序。不同于传统归并排序的地方在于：lists数组中的元素不是基础类型，而是自定义类型的ListNode。假如lists中有4个元素，先分别归并前两个和后两个，然后再并归并后的结果再次进行归并，得到结果。

代码：（这道题目困了我一天）

```java
public ListNode mergeList(ListNode l1, ListNode l2) {
		if (l1 == null)

			return l2;
		if (l2 == null)
			return l1;

		ListNode newhead = new ListNode(-1);
		ListNode head = newhead;

		while (l1 != null && l2 != null) {
			if (l1.val < l2.val) {
				newhead.next = l1;
				l1 = l1.next;
				newhead = newhead.next;
			} else {
				newhead.next = l2;
				l2 = l2.next;
				newhead = newhead.next;
			}
		}
		if (l1 != null)
			newhead.next = l1;
		if (l2 != null)
			newhead.next = l2;

		return head.next;
	}

	
	public ListNode mergeKLists(ListNode[] lists) {
		if(lists == null || lists.length == 0)
			return null;
		
		if(lists.length == 1)
			return lists[0];
		
		int length = lists.length;
		return mergeKLists(lists, 0, length-1);
	}
	
	public ListNode mergeKLists(ListNode[] lists, int startIndex, int endIndex){
			
		if(startIndex < endIndex){
			int mid = (startIndex + endIndex)/2;
			ListNode l1 = mergeKLists(lists, startIndex, mid);
			ListNode l2 = mergeKLists(lists, mid+1, endIndex);
			return mergeList(l1,l2);
		}
		else{
			return lists[startIndex];
		}
	}

	//下面是测试用例：
	public void test() {
		ListNode l1 = new ListNode(1);
		l1.next = new ListNode(5);
		l1.next.next = new ListNode(10);
		
		ListNode l2 = new ListNode(2);
		l2.next = new ListNode(4);
		l2.next.next = new ListNode(8);
		
		ListNode l3 = new ListNode(3);
		l3.next = new ListNode(7);
		l3.next.next = new ListNode(11);
		
		ListNode l4 = new ListNode(6);
		l4.next = new ListNode(12);
		l4.next.next = new ListNode(15);
		
		ListNode[] lists = {l1,l2,l3,l4};
		ListNode newlist = mergeKLists(lists);
		while(newlist != null){
			System.out.println(newlist.val);
			newlist = newlist.next;
		}
 	}
```

