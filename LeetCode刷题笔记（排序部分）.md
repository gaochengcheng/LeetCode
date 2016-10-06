# LeetCode刷题笔记（排序部分和查找部分）

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

超时的代码及复杂度分析：

​	假设每条链表平均有n个元素，此种时间复杂度是O(2n+3n+…+kn), 为O(nk²)。因此会超时。	

```java
public ListNode mergeKLists(ListNode[] lists) {

		int length = lists.length;
		if (length == 0)
			return null;

		ListNode head = lists[0];
		for (int i = 1; i < length; i++) {
			head = mergeList(head, lists[i]);
		}

		return head;
	}
```

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

正确思路：

​	借鉴归并排序的思想，针对这个lists进行排序。不同于传统归并排序的地方在于：lists数组中的元素不是基础类型，而是自定义类型的ListNode。假如lists中有4个元素，先分别归并前两个和后两个，然后再并归并后的结果再次进行归并，得到结果。

代码：（这道题目困了我一天，难点在于不知道如何写递归）

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
	//把这里的边界条件控制好
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

	public ListNode mergeKLists(ListNode[] lists) {
		if(lists == null || lists.length == 0)
			return null;
		
		if(lists.length == 1)
			return lists[0];
		
		int length = lists.length;
		return mergeKLists(lists, 0, length-1);
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

## S.147_Insertion Sort List

原题地址：https://leetcode.com/problems/insertion-sort-list/

思路：

​	思路很简单，就是把一个一个元素往已排好序的list中插入的过程。

​	初始时，sorted list是空，把一个元素插入sorted list中。然后，在每一次插入过程中，都是找到最合适位置进行插入。

​	 因为是链表的插入操作，需要维护pre，cur和next3个指针。

​	 pre始终指向sorted list的fakehead，cur指向当前需要被插入的元素，next指向下一个需要被插入的元素。

​	 当sortedlist为空以及pre.next所指向的元素比cur指向的元素值要大时，需要把cur元素插入到pre.next所指向元素之前。否则，pre指针后移。最后返回fakehead的next即可。

  ![链表实现插入排序](pics\链表实现插入排序.jpg)

代码：

```java
public ListNode insertionSortList(ListNode head) {
		if(head == null || head.next == null)
			return head;
		
		ListNode sortedListHead = new ListNode(0);//fakenode
		ListNode cur = head;
		while(cur != null){
			ListNode next = cur.next;
			ListNode pre = sortedListHead;
			while(pre.next != null && pre.next.val < cur.val){
				pre = pre.next;
			}
			//下面看不懂
			cur.next = pre.next;
			pre.next = cur;
			cur = next;
		}
        return sortedListHead;
    }
```

## S.148_Sort List

原题地址：https://leetcode.com/problems/sort-list/

思路：

​	对这个LinkedList进行归并排序，时间复杂度是O（nlgn）。此前实现的归并排序都是基于数组这样的数据结构的，现在做归并排序要使用list这样的数据结构。其核心是如何找到这个链表的中间位置，对其进行二分吧。

代码：

```java
public ListNode sortList(ListNode head) {
        if(head == null || head.next == null)
        	 return head;
         
         return recursiveSort(head);
    }
	//不断对list进行二分部分
   public ListNode recursiveSort(ListNode head){
		
		ListNode slower = head;
        ListNode faster = head;
     //在找中点的时候使用到快慢指针的技巧
        while(faster.next != null && faster.next.next != null){
        	
       	 	slower = slower.next;
       	 	faster = faster.next.next;
        }
        if(head != faster && head.next != faster){
        	ListNode part2 = slower.next;
        	slower.next = null;
        	ListNode first = recursiveSort(head);
        	ListNode second = recursiveSort(part2);
        	return mergeListNode(first, second);
        }
        else{
        	ListNode part2 = head.next;
        	head.next = null;
        	return mergeListNode(head,part2);
        }  
	}
	//真正做归并的部分
	public ListNode mergeListNode(ListNode first, ListNode second){
		
		ListNode node = new ListNode(-1);
		ListNode result = node;
		while(first != null && second != null){
			if(first.val < second.val){		
				node.next = first;
				first = first.next;
				node = node.next;
			}
			else{
				node.next = second;
				second = second.next;
				node = node.next;
			}
		}
		if(first != null){
			node.next = first;
		}
		if(second != null){
			node.next = second;	
		}
		return result.next;
	}
```

## S.41_First Missing Positive（妈啊，我都看不懂这个题目的意思）

原题地址：https://leetcode.com/problems/first-missing-positive/

思路：

​	在一个未排序的数组中，返回第一个本应该出现，但是却没有出现的正整数。

>Given [1,2,0] return 3, 分析：0，1，2之后应该出现3.
>
>and [3,4,-1,1] return 2. 分析：-1，1，3，4。1之后应该出现2的。但是没有出现。

代码：

```java
public int firstMissingPositive(int[] A) {
        
		if(A==null || A.length==0)  
              return 1;  
              
          for(int i=0;i<A.length;i++){  
              if(A[i]<=A.length && A[i]>0 && A[A[i]-1]!=A[i]){  
                  int temp = A[A[i]-1];  
                  A[A[i]-1] = A[i];  
                  A[i] = temp;  
                 i--;  
             }  
         }  
         
         for(int i=0;i<A.length;i++){  
             if(A[i]!=i+1)  
                 return i+1;  
         } 
         return A.length+1;  
    }
```

## S.75_Sort Colors(方法二很巧妙啊)

原题地址：https://leetcode.com/problems/sort-colors/

思路：

​	这道题目抽象出来后的意思就是：数组中有0，1，2共3种不同的数字，他们之间是乱序的，然后对这个数组进行排序，使得所有相同的数字是相邻在一起的。并且顺序按照0，1，2。

​	思路一：

​	直接调用系统的方法：Arrays.sort(nums)就可以对数组进行排序。

代码：

```java
	public void sortColors(int[] nums) {
		if(nums.length == 0 || nums.length == 1)
			return ;
		
		Arrays.sort(nums);
		
    }
```

​	思路二：

​	由于题目明确要求不能使用库函数，所以要使用其他方法。

>这道题就是运用指针来解决了，可以说叫3指针吧。
>
>一个指针notred从左开始找，指向第一个不是0（红色）的位置；一个指针notblue从右开始往左找，指向第一个不是2（蓝色）的位置。
>
>然后另一个新的指针i指向notred指向的位置，往后遍历，遍历到notred的位置。
>
>这途中需要判断：
>
>当i指向的位置等于0的时候，说明是红色，把他交换到notred指向的位置，然后notred++，i++。
>
>当i指向的位置等于2的时候，说明是蓝色，把他交换到notblue指向的位置，然后notred--。
>
>当i指向的位置等于1的时候，说明是白色，不需要交换，i++即可。

代码：

```java
public void sortColors(int[] nums) {
		if(nums.length == 0 || nums.length == 1)
			return ;
			
		int notred = 0;
		int notblue = nums.length-1;
		
		while(notred < nums.length && nums[notred] == 0)
			notred ++;
		while(notblue >= 0 && nums[notblue] == 2)
			notblue --;
		
		int i = notred;
		while(i <= notblue){
			//i指向的位置是red，所以和notred交换
			if(nums[i] == 0){
				swap(nums,i,notred);
				i++;
				notred++;
			}
			else if(nums[i] == 2){
				swap(nums,i,notblue);
				notblue--;
				//这里不需要再进行i++,因为交换完成后，不知道这个notblue的值究竟是多少，还需要再次进行判断这个位置
				//i++;
			}
			else{
				i++;
			}	
		}
    }
    public void swap(int[] A, int i, int j){
		int temp = A[i];
		A[i] = A[j];
		A[j] = temp;
	}
```

## S.34_Search for a Range

原题地址：https://leetcode.com/problems/search-for-a-range/

思路：

>Given a sorted array of integers, find the starting and ending position of a given target value.
>
>Your algorithm's runtime complexity must be in the order of *O*(log *n*).
>
>If the target is not found in the array, return `[-1, -1]`.
>
>For example,
>Given `[5, 7, 7, 8, 8, 10]` and target value 8,
>return `[3, 4]`.

​	这道题目最直接的写法就是顺序查找，时间复杂度O(n).但是题目中明确说明了复杂度得是O（log n）,所以一定要使用二分的思想。通过二分的方法找到target的值在哪里。然后再基于该位置向数组的左右两边进行扩展，就得到的最后的答案。

代码：

```java
public int[] searchRange(int[] nums, int target) {
		int[] result = {-1,-1};
		if(nums == null || nums.length == 0)
			return result;
        
		int low = 0, high = nums.length-1;
		int mid = 0;
		while(low <= high){
			mid = (low+high)/2;
			if(nums[mid] == target)
				break;
			if(nums[mid] < target){
				low = mid + 1;
			}
			if(nums[mid] > target){
				high = mid - 1;
			}
		}
		int pos = mid;
		while(mid >=0 && nums[mid] == target){
			result[0] = mid;
			mid--;
		}
		while(pos <= high && nums[pos] == target){
			result[1] = pos;
			pos++;
		}
		
        return result;
	}
```

## S.35_Search Insert Position

原题地址：https://leetcode.com/problems/search-insert-position/

### 思路1：

> Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.
>
> You may assume no duplicates in the array.
>
> [1,3,5,6], 5 → 2
> [1,3,5,6], 2 → 1
> [1,3,5,6], 7 → 4
> [1,3,5,6], 0 → 0
>

​	遍历数组，如果找到target，就返回target的下标。否则就不断更新position的值，position的含义是比target小的值的下标。

代码：

```java
public int searchInsert(int[] nums, int target) {
        
		if(nums == null || nums.length == 0)
			return 0;
		int length = nums.length;
		int position = -1;
		for(int i = 0; i < length; i++){
			if(nums[i] == target)
				return i;
			if(nums[i] < target)
				position = i;
		}
		return position+1;
    }
```

### 思路2：二分思想

这道题目可以很好的学习二分思想。

>本题是基本考察二分查找的题目，与基本二分查找方法不同的地方是，二分查找法当查找的target不在list中存在时返回-1，而本题则需要返回该target应在此list中插入的位置。 
>
>当循环结束时，如果没有找到target，那么low一定停target应该插入的位置上，high一定停在恰好比target小的index上。 
>
>可以举例：
>
>**[1,3,5,6], 7**
>
>low = 0, high = 3
>
>step1: mid = 1
>
>          A[mid] = 3, 3<7
>
>          low = mid + 1 = 2
>
> 
>
>low = 2, high = 3
>
>step2: mid = 2
>
>          A[mid] = 5, 5<7
>
>         low = mid + 1 = 3
>
> 
>
>low = 3, high = 3
>
>step3: mid = 3
>
>          A[mid] = 6, 6<7
>
>          low = mid + 1 = 4 
>
>low = 4, high = 3
>
>return low = 4; 
>
>**[1,3,5,6], 0 **
>
>low = 0, high = 3
>
>step1: mid = 1
>
>          A[mid] = 3, 3>0
>
>          high = mid - 1 = 0
>
> 
>
>low = 0, high = 0
>
>step2: mid = 0
>
>          A[mid] = 1, 1>0
>
>          high = mid - 1 = -1
>
> 
>
>low = 0, high = -1
>
>return 0 

代码：

```java
public int searchInsert(int[] nums, int target) {
		if(nums == null || nums.length == 0)
			return 0;
		
		int high = nums.length-1;
		int low = 0;
		while(low <= high){
			int mid = (low+high)/2;
			if(nums[mid] < target)
				low = mid + 1;
			else if(nums[mid] > target)
				high = mid -1;
			else
				return mid;
		}
		return low;
	}
```

## S.74_Search a 2D Matrix

原题地址：https://leetcode.com/problems/search-a-2d-matrix/

思路：

>Write an efficient algorithm that searches for a value in an *m* x *n* matrix. This matrix has the following properties:
>
>- Integers in each row are sorted from left to right.
>- The first integer of each row is greater than the last integer of the previous row.
>
>For example,
>
>Consider the following matrix:
>
>```
>[
>  [1,   3,  5,  7],
>  [10, 11, 16, 20],
>  [23, 30, 34, 50]
>]
>```
>Given target = 3, return true.

​	这道题目抽象出来之后，就是在2维数组中进行二分查找。思路是把二维数组转变为一维数组，然后仍然使用针对一维数组进行二分查找的思路，对其进行二分查找。

代码：

```java
public boolean searchMatrix(int[][] matrix, int target) {
        
		if(matrix == null || matrix[0].length == 0)
			return false;
		int minLength = 0;
		int maxLength = matrix.length * matrix[0].length - 1;
		int len = matrix[0].length;
		int x = 0;
		int y = 0;
		int cur = 0;
		while(minLength <= maxLength){
			cur = (minLength+maxLength)/2;
			//第几行用y表示，第几列用x表示
			y = cur/len ;
			x = cur%len ;
			if(matrix[y][x] > target)
				maxLength = cur -1 ;
			else if(matrix[y][x] < target)
				minLength = cur + 1;
			else
				return true;
		}
			
		return false;
	
    }
```

## Selection Sort

思想：

- 从`i`下标的位置开始（i下标从0开始增长），从整个数组中选出一个值最小的数。
- 把这个最小的数和下标`i`位置的元素交换。这样最小的数就被交换到前面。
- 然后从`i+1`的位置开始，再从后面的数组（即从`i+1`到数组末尾）中找出最小的数，把这个数交换到下标是`i+1`的位置。
- 重复上面过程，直到所有元素都比较完，此时数组是有序的。

代码：

```java
public static void sort(Comparable[] a){
		int N = a.length;
		for(int i = 0; i < N; i++){
			int min = i;
			for(int j = i + 1; j < N; j++)
				if(less(a[j],a[min]))    //if a[j] < a[min] return true.
					min = j;
			exch(a, i, min);  //a is a array.
		}
	}
```

## Insertion Sort

思想：

- 整个排序过程分成两个部分，前面一部分是有序的，后面一部分是无序的。最开始的时候，下标是0的位置是有序的，0之后的部分是需要进行排序的。换言之，0之后无序部分需要插入到前面的有序部分中。
- 每次到达一个位置后，先和其前面的一个位置比较，如果比前面的小，就和前面一个交换，然后继续和前面一个交换，直到不比前面一个元素小为止。

代码：

```java
public static void sort(Comparable[] a){
		//Sort a[] into increasing order.
		int N = a.length;
		for(int i = 1; i < N; i++){
			//Insert a[i] among a[i-1],a[i-2],a[i-3].....
			for(int j = i; j > 0 && less(a[j], a[j-1]); j--){
				exch(a, j, j-1);
			}
		}
	}
```

分析：

​	最好情况：数组的初始状态是有序的，然后一共需要比较的次数是`n-1`，

​	最差情况，数组的初始状态是逆序的，此时，在比较次数上和选择排序是相同的，但是显然在交换次数上插入排序用的更多。比较次数是`O(N^2)`，交换次数是`O(N^2)`。

## Shell Sort

思想：

- 希尔排序在本质上是插入排序的一种，确切讲可以称之为缩小增量（间隔）的插入排序。
- 增量一般来讲记作h，h满足一定的条件，比如在`h  < N /3`的情况下，`h = 3*h+1`。然后使用这个h值作为间隔的大小做插入排序。所相应位置的元素做完插入排序之后，缩小`h`的值，然后继续做插入排序，知道`h`的值缩小为1.

代码：

```java
public static void sort(Comparable[] a){
		//Sort a[] into increasing order.
		int N = a.length;
		int h = 1;
		while(h < N/3) 
			h = 3*h+1;  //首先找到间隔是多大
		System.out.println("h is : "+h);
		while(h >=1){
			// h-sort the array.
			for(int i = h; i < N; i++){
				//Insert a[i] among a[i-h], a[i-2h], a[i-3h]...
				for(int j = i; j >= h && less(a[j],a[j-h]); j= j-h)
					exch(a, j, j-h);
			}
			h = h / 3;
		}
	}
```

### 小结：

#### 选择排序 VS 插入排序

​	当数据的大小关系较为随机一般的时候，插入排序比选择排序快大约1.7倍。原因很简单，因为选择是排序是绝对的O(n^2)的复杂度，但是插入排序并不是严格的O(n^2)。所以插入排序更快。

​	当数据的大小关系完全逆序的时候，选择排序更快。因为此时比较的次数都是O（n^2），但是插入排序移动的次数多余选择排序，所以插入排序更慢。

### 希尔排序 VS 选择和插入排序

​	希尔排序的时间复杂度是O(n^(3/2))，小于n^2级别。所以在所有的elementary sort算法中，希尔排序是最快的。

## Quick Sort（不稳定的）

思想：

- 对整个数组进行partitioning操作。具体做法是选一个元素作为partition item，小于这个值的元素全放到这个该值的左边，大于这个值的元素全都放到这个值的右边。返回partition的下标。
- 对该下标的左部分进行partitioning操作。
- 对该下标的右部分进行partitioning操作。

代码：

```java
private static void sort(Comparable[] a, int lo, int hi)
{
	if (hi <= lo) return;
	int j = partition(a, lo, hi);
	sort(a, lo, j-1);
	sort(a, j+1, hi);
}

private static int partition(Comparable[] a, int lo, int hi)
{
	int i = lo, j = hi+1;
	while (true)
{
	while (less(a[++i], a[lo]))  // find item on left to swap
		if (i == hi) break;                   
	while (less(a[lo], a[--j])) //  find item on right to swap
		if (j == lo) break;
	if (i >= j) break;   //   check if pointers cross
	exch(a, i, j);   //swap
}
	exch(a, lo, j);  //swap with partitioning item
	return j;   //return index of item now known to be in place
}
```

 返回的j的值，将原先的数组分成两个部分，左边部分小于j处的值，右边部分大于j处的值。![QuickSort](pics\QuickSort.PNG)

partition 循环中`while(true)`部分的执行过程：

![QuickSort_whileloop](pics\QuickSort_whileloop.PNG)

## 优先队列和堆

参考链接：http://www.yeolar.com/note/2012/05/26/ds-heap/

### 优先队列

优先队列应该包含插入操作和删除最小元素操作，前者等价于队列中的入队操作，后者则对应出队操作，但它找到并删除优先队列中的最小元素。

可以使用链表实现优先队列，但这将花费` O(N) `时间进行删除，另一种方法是使用二叉查找树，插入和删除都平均花费 `O(logN) `时间，但将二叉查找树用于优先队列过于复杂了。

一般使用二叉堆来实现优先队列，它不需要指针，插入和删除的最坏运行时间为` O(logN) `，插入的平均运行时间为`O(1) `，因此它可以以线性时间建立有`N` 项的优先队列。

### 二叉堆

二叉堆也称为堆，它是一颗完全二叉树，即除底层外完全被填满的二叉树，底层的元素从左到右填入。堆具有结构性和堆序性。

![/media/note/2012/05/26/ds-heap/fig1.png](http://www.yeolar.com/media/note/2012/05/26/ds-heap/fig1.png)

高为 h 的完全二叉树有$$ 2^h$$ 到$$ 2^{h+1}-1 $$个节点，因此完全二叉树的高为$$ ⌊logN⌋$$ ，它是$$ O(logN)$$ 。

**完全二叉树的规律性使它可以用数组表示**。数组中元素从位置1开始，对于数组中任意位置 i 上的元素，其左儿子在位置 2i 上，右儿子在位置 2i+1 上，父亲在位置 ⌊i/2⌋ 上。

堆具有堆性，其堆性的含义是一个堆总是有序的，对于大堆而言，堆顶元素是最大的，且父节点总是大于孩子节点。对于小堆而言，则相反。

**我自己的理解：**优先队列是一种抽象数据类型，具体实现的时候可以使用链表、二叉排序树、二叉堆等结构来实现。我们最终使用二叉堆来实现。二叉堆也称为堆，它是一颗完全二叉树，即除底层外完全被填满的二叉树，底层的元素从左到右填入。堆具有结构性和堆序性。

至此，由于是一颗完全二叉树，所以所以进一步可以使用数组的方式来表示。

由下到上调整堆的过程：

```java
private static void sink(Comparable[] a, int k, int N){
		while(2*k <= N){
			int j = 2*k;
			if(j < N && less(a, j, j+1))
				j++;

			if(!less(a, k, j))
				break;
			exch(a, k, j);
			k = j;
		}
	}
```



### 完整的堆排序代码：下标从0开始，不同于书本上的下标从1开始。

```java
public static void sort(Comparable[] a){
		int N = a.length-1;
		for(int k = N/2; k >=0; k--){
			sink(a, k, N);
		}
		while(N > 0 ){
			System.out.println("..."+a[0]);   //a[0]处代表的元素是最大元素
			exch(a, 0, N--);
			sink(a, 0,N);
		}
	}
	private static boolean less(Comparable[] a, int i, int j){
		return a[i].compareTo(a[j]) < 0;
	}
	private static void exch(Comparable[] a, int i, int j){
//		System.out.println("j is "+j);
		Comparable temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	
	//a[0]...a[a.length-1]
	private static void sink(Comparable[] a, int k, int N){
		while(2*k <= N){
			int j = 2*k;
			if(j < N && less(a, j, j+1))
				j++;

			if(!less(a, k, j))
				break;
			exch(a, k, j);
			k = j;
		}
	}
	
	public static void main(String[] args) {
		Comparable[] a = {1,5,3,2,7,6,8};
		sort(a);
		for(Comparable ele : a)
			System.out.print(ele + " ");   //1，2，3，5，6，7，8
	}
```



