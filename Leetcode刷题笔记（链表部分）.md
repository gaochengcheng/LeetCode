# Leetcode刷题笔记（链表部分）

[TOC]

## S.2_Add Two Numbers

原题地址：https://leetcode.com/problems/add-two-numbers/

思路：

​	(2 -> 4 -> 3) + (5 -> 6 -> 4) Output: 7 -> 0 -> 8。其中，对243而言，2是个位，4是十位，3是百位。对564而言，5是个位，6是十位，4是百位。

​	按照做加法的方式写，对应为相加然后判断是否需要进位。

代码：

```java
public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(0);
		ListNode lastResult = result; // 保存链表的头结点，因为对一个链表而言，只要知道头结点，就可以通过next访问到所有元素
		while(l1 != null && l2 != null){
			int val1 = l1.val;
			int val2 = l2.val;
			int tempsum = result.val + val1 + val2;
			result.val = (result.val + val1 + val2) % 10 ;
			if(tempsum >= 10){   //产生进位，向前进1.
				result.next = new ListNode(1);   
			}else{
				if(l1.next != null || l2.next != null){   //判断是否到了最高位，如果不是，则产生
					result.next = new ListNode(0);        //下一位的节点，并且初始化为0.
				}
			}
			result = result.next;
			l1 = l1.next;
			l2 = l2.next;
		}
		while(l1 != null){
			int val = l1.val;
			int tempsum = result.val + val;
			result.val = (result.val + val) % 10;
			if(tempsum >= 10 ){
				result.next = new ListNode(1);
			}else{
				if(l1.next != null){
					result.next = new ListNode(0);
				}
			}
			l1 = l1.next;
			result = result.next;
		}
		while(l2 != null){
			int val = l2.val;
			int tempsum = result.val + val;
			result.val = (result.val + val) % 10;
			if(tempsum >= 10 ){
				result.next = new ListNode(1);
			}else{
				if(l2.next != null){
					result.next = new ListNode(0);
				}
			}
			l2 = l2.next;
			result = result.next;
		}
		
		return lastResult;
    }
```

## S.92_Reverse Linked List II（思路巧妙）

原题地址：https://leetcode.com/problems/reverse-linked-list-ii/

思路：

​	本质上是一道链表逆序题目啦！只不过在这道题目中指定了逆序的两端m和n。方法和经典问题的从头到尾全部逆序的方法是一样的。

​	维护3个指针，startpoint，node1和node2。

​	startpoint永远指向需要开始reverse的点的前一个位置。

​	node1指向正序中第一个需要rever的node，node2指向正序中第二个需要reverse的node。 

​	交换后，node1 在后，node2在前。这样整个链表就逆序好了。

代码：

```java
public ListNode reverseBetween(ListNode head, int m, int n) {
		ListNode newHead = new ListNode(-1);      //给链表增加了一个头节点，当只有两个节点时，startpoint指向头结点。
		newHead.next = head;
		
		if(head == null || head.next == null){
			return newHead.next;
		}
		
		//维护以下3个node，startPoint是m的前一个，node1是两个相邻的需要reverse节点的前一个，node2是后一个
		ListNode startPoint = newHead;
		ListNode node1 = null;
		ListNode node2 = null;
		//node1的最后位置是n-1.node2的最后位置是n
		for(int i = 0; i < n; i++){
			if(i < m-1){
				startPoint = startPoint.next;
			}
			else if( i == m-1){
				node1 = startPoint.next;
				node2 = node1.next;
			}
			else{
				node1.next = node2.next;
				node2.next = startPoint.next;
				startPoint.next = node2;
				node2 = node1.next;
			}
		}
        return newHead.next;
    }
```

startPoint、node1、node2之间的变换过程如如图：

![链表逆序过程](pics\/链表逆序过程.PNG)

这种方法是使用头插法逆序一个链表：

​	new一个伪头结点，dummy或者fakenode，令fakenode.next = head;

​	把遍历的每个元素放到fakenode.next 的位置，当遍历完成时，链表就逆序了。

​	过程是：

​	1--2--3--4--5

​	2--1--3--4--5

​	3--2--1--4--5

​	4--3--2--1--5

​	5--4--3--2--1

```java
ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode cur = head.next;
        ListNode last = head;
        while(cur != null){
            last.next = cur.next;
            cur.next = pre.next;
            pre.next = cur;
            cur = last.next;
        }
        head = dummy.next;

reverse a linked list with a head node
```

## S.86_Partition List

原题地址：https://leetcode.com/problems/partition-list/

思路：

​	这道题的意思就是说，给定一个值x，小于x的都放到大于等于x的前面。并且不改变链表之间node原始的相对位置。每次看这道题我老是绕晕，纠结为什么4在3的前面。。其实还是得理解题意，4->3->5都是大于等3的数，而且这保持了他们原来的相对位置。

1. new两个新链表，一个用来管理所有大于等于x的数，一个用来管理小于x的数。遍历原来的链表时，当当前node.val大于等于x就接在大链表上，当当前node.val小于x，就接在小链表上。
2. 最后把小链表的最后一个接上大链表的第一个，大链表最后一个元数的next设置为null。

代码：

```java
public ListNode partition(ListNode head, int x) {
        
		//1.先判断异常条件
		if(head == null || head.next == null)
			return head;
		
		//2.再判断正常条件
		ListNode smallNode = new ListNode(-1);
		ListNode bigNode = new ListNode(-1);
		ListNode originLessHead = smallNode;
		ListNode originGreatHead = bigNode;
		while(head != null){
			if(head.val < x){
				smallNode.next = head;
				smallNode = smallNode.next;
			}
			else{
				bigNode.next = head;
				bigNode = bigNode.next; 
			}
			head = head.next;
		}
		//让小的队列链接上大的队列，同时让大的队列末尾是null
		smallNode.next = originGreatHead.next;
		bigNode.next = null;
		return originLessHead.next;
	
    }
```

## S.83_Remove Duplicates from Sorted List

原题地址：https://leetcode.com/problems/remove-duplicates-from-sorted-list/

思路：

​	这道题目吧，本质上就是在一个已经排过序的链表中，对元素进行遍历操作，当发现链表中有重复元素的时候，通过修改链表指针达到删除重复元素的效果。

>这道题是经典的双指针问题，用两个指针一前一后指向链表。如果两个指针指向的值相等，那么就让第二个指针一直往后挪，挪到与第一个指针不同为止。然后让第一个指针的next指向第二个指针，两个指针同时往后挪，进行下面的操作。
>
>需要注意，当list的结尾几个node是重复的时候，例如`1->2->3->3，那么ptr2会指向null，需要特殊处理，令ptr1.next = null，这样list尾部就不会丢。`
>
>其他情况就不用特殊处理结尾了，因为结尾没有重复值，只须遍历就够了，不用特殊处理尾部。 



1. 维护两个指针，pre和cur，cur指向当前遍历到的元素，pre指向前面的一个。
2. 当pre.val == cur.val时，cur = cur.next; 需要注意的是当cur是null的时候，需要特殊处理，令pre.next = null。剩下的情况只需遍历就行，不需要重复处理。


代码：

```java
 public ListNode deleteDuplicates(ListNode head) {
        
        ListNode pre = new ListNode(-1);
        ListNode cur = null;
       
        if(head == null || head.next == null)
        	return head;
   
        pre.next = head;
        cur = head;
        while(cur != null){
        	if(pre.val == cur.val){
        		cur = cur.next;
        		if(cur == null)    //特殊处理
        			pre.next = cur;
        	}
        	else{
        		pre.next = cur;
        		pre = pre.next;
        		cur = cur.next;
        	}
        }
        return head;
    }
```

## S.82_Remove Duplicates from Sorted List II

原题地址：https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/

思路：

​	这个题目和上面一个题目很相似，不同之处在于，一旦遇到重复元素，就把该元素全部删除掉。因此要维护3个指针（pre、cur、post），并且曾加一个伪头节点。

​	最开始建立头结点，并将其指向head。

```java
	ListNode fakehead = new ListNode(-1);
	fakehead.next = head;
```

​	建立三个指针，分别代表pre、cur、post

```java
	ListNode ptr0 = fakehead;  //pre
	ListNode ptr1 = fakehead.next;  //cur
	ListNode ptr2 = fakehead.next.next;  //post
```

​	设置一个flag变量，通过这个变量判断是否需要删除元素。

​	当没有遇到重复（flag == false）元素，三个指针同时向后移动。

```java
	ptr0 = ptr1;
	ptr1 = ptr2;
	ptr2 = ptr2.next;
```

​	如果遇到重复元素，设置flag为true，并让ptr2一直往后找找到第一个与ptr1值不等的位置时停止，这时，ptr1指向的node的值是一个重复值，需要删除，所以这时就需要让ptr0的next连上当前的ptr2，这样就把所有重复值略过了。然后，让ptr1和ptr2往后挪动继续查找。

​	这里还需要注意的是，当ptr2一直往后找的过程中，是有可能ptr2==null（这种情况就是list的最后几个元素是重复的，例如1->2->3->3->null)，这时ptr1指向的值肯定是需要被删除的，所以要特殊处理，令ptr0的next等于null，把重复值删掉。其他情况说明最后几个元素不重复，不需要处理结尾，遍历就够了。

代码：

```java
public ListNode deleteDuplicates(ListNode head) {
        
		if(head == null || head.next == null){
			return head;
		}

		ListNode fakehead = new ListNode(-1);
		fakehead.next = head;
		ListNode ptr0 = fakehead;  //pre
		ListNode ptr1 = fakehead.next;  //cur
		ListNode ptr2 = fakehead.next.next;  //post
		
		boolean flag = false;
		
		while(ptr2 != null){
			if(ptr1.val == ptr2.val){
				flag = true;
				ptr2 = ptr2.next;
				if(ptr2 == null){
					ptr0.next = null;
				}
			}else{
				if(flag){
					ptr0.next = ptr2;
					flag = false;
				}
				else{
					ptr0 = ptr1;
				}
				ptr1 = ptr2;
				ptr2 = ptr2.next;
			}
		}
		return fakehead.next;
    }
```

## S.61_Rotate List

原题地址：https://leetcode.com/problems/rotate-list/

思路：

​	这道题主要先理解题意，就是倒着数k个node，从那开始到结尾和之前那部分对调，那个例子就是，4->5拿前面来，1->2->3拿后面去。

​	几个特殊：

​	k是可以大于整个list的长度的，所以这时要对k对len取模

​	如果取模之后得0，相当于不用rotate，直接返回

	>看到这种倒着数数的直接使用**双指针**，ptr1和ptr2一前一后同时向后移动。两个指针之间的间隔为k，当ptr2到达最后一ige元素后，ptr1就是倒数第k个的位置。

​	移动结束后，newhead = ptr1.next

​	ptr2.next = head;

​	ptr1.next = null;

代码：

```java
public ListNode rotateRight(ListNode head, int k) {
		if(head == null || head.next == null){
			return head;
		}
		
		ListNode fakehead = new ListNode(-1);
		fakehead.next = head;
		
		ListNode ptr1 = head;
		ListNode ptr2 = head;
		int length = 0;
		//计算链表长度
		while(head != null){
			length++;
			head = head.next;
		}

		k = k % length;
		int i = 0;
		
		//ptr2到达指定位置
		while(i < k){
			i++;
			ptr2 = ptr2.next;
		}

		//ptr1、ptr2同时向后移动，直到ptr2到达最后
		while(ptr2.next != null){
			ptr1 = ptr1.next;
			ptr2 = ptr2.next;
		}
		fakehead.next = ptr1.next;
		ptr2.next = fakehead.next;
		ptr1.next = null;
		
		return fakehead.next;
	}
```

## S.19_Remove Nth Node From End of List

原题地址：https://leetcode.com/problems/remove-nth-node-from-end-of-list/

思路；

​	这道题目的意思是要删除从末尾开始数起，第n个位置的值。采用的方法和上一题很相似，也是使用**双指针**ptr1和ptr2。当第二个指针指向末尾的时候，第一个指针的next就是待删除的元素。这时ptr1.next = ptr2.next.next。返回fakenode.next;

​	需要处理的特殊情况，当删除第一个点（n的值和链表长度值相同时），fakenode.next = fakenode.next.next;返回fakenode.next;

代码：

```java
public ListNode removeNthFromEnd(ListNode head, int n) {
		if(head == null )
			return head;
		
		ListNode fakenode = new ListNode(-1);
		fakenode.next = head;
		int i = 0;
		ListNode ptr1 = fakenode;
		ListNode ptr2 = fakenode;
		
		int length = 0;
		while(head != null){
			length++;
			head = head.next;
		}
		//处理length == n时的情况
		if(n == length){
			fakenode.next = fakenode.next.next;
			return fakenode.next;
		}
		
		//让ptr2和ptr1之间差别n
		while(i < n){
			i++;
			ptr2 = ptr2.next;
		}
		//让ptr1和ptr2同时向后移动，直到ptr2.next == null
		while(ptr2 != null){
			ptr1 = ptr1.next;
			ptr2 = ptr2.next;
			if(ptr2.next == null)
				break;
		}
		//ptr1.next 就是要删除的元素
		ptr1.next = ptr1.next.next;
		
		return fakenode.next;
	}
```

## S.24_Swap Nodes in Pairs

原题地址：https://leetcode.com/problems/swap-nodes-in-pairs/

思路：

​	使用两个指针，分别指向相邻的两个元素，然后交换两个元素的值。

​	交换元素之后，两个指针分别同时向后移动，重复之前的操作。

代码：

```java
public ListNode swapPairs(ListNode head) {
       
        if(head == null || head.next == null)
        	return head;
        
        int value = 0;
        ListNode fakenode = new ListNode(-1);
        fakenode.next = head;
        ListNode ptr1 = head;
        ListNode ptr2 = head.next;
        while(ptr1 != null && ptr2 != null){
        	value = ptr1.val;
        	ptr1.val = ptr2.val;
        	ptr2.val = value;
        	if(ptr2.next != null){
        		ptr1 = ptr2.next;
        		if(ptr1.next != null){
        			ptr2 = ptr1.next;
        		}
        		else
        			break;
        	}else
        		break;
        }
        return fakenode.next;
    }
```

## S.25_Reverse Nodes in k-Group（hard）

原题地址：https://leetcode.com/problems/reverse-nodes-in-k-group/

思路：

​	凡是涉及链表逆序，就是维护好3个指针，pre指向待交换元素的前一个元素，last指向待交换元素的第一个，cur指向待交换元素的第二个。

代码：

```java
	public ListNode reverse(ListNode pre, ListNode next){
		//需要维护的一共三个指针，pre指向待交换的前一个、last指向待交换的第一个、cur指向待交换的第二个。	
			ListNode last = pre.next;
			ListNode cur = last.next;
			while(cur != next){
				last.next = cur.next;
				cur.next = pre.next;
				pre.next = cur;
				cur = last.next;
			}
			return last;   //last作为下一次交换时候的pre
		}
    public ListNode reverseKGroup(ListNode head, int k) {
         if(head != null && k == 1){
        	return head;
        }
        
        ListNode fakenode = new ListNode(-1);
        fakenode.next = head;
        
        ListNode pre = fakenode;
        ListNode cur = head;
        int count = 0;
        
        //pre指向待交换的前一个，next指向下一轮待交换的头一个
        while(cur != null){
        	count++;
        	ListNode next = cur.next; // next指向下一轮待交换的头一个
        	if(count == k){
        		pre = reverse(pre,next);
        		count = 0;
        	}
        	cur = next; //不懂
        }
        return fakenode.next;
    }
```

## S.138_Copy List with Random Pointer（hard）

原题地址：https://leetcode.com/problems/copy-list-with-random-pointer/

思路：

​	两步走：

​	1.第一步通过next指针遍历所有元素，先把所有节点创建出来，使用next指针把他们之间的关系连接上。并且再此过程中，把值相同的节点放到map中，key是旧链接的节点，value是新链接的节点。他们的lable值相同。

​	2.第二步，通过next指针再次遍历元素，依次把每个元素的random指向的节点取出来，赋给新链接的random。

​	最后返回新链接的头结点newhead，就OK啦。

​	空间复杂度：使用了hashmap，O（n）。

​	时间复杂度：遍历了两边原来的链接，是O（2n）= O（n）。

代码：

```java
// 使用hashmap，key存储原始链接的RandomListNode，value存储新链接的RandomListNode。
	public RandomListNode copyRandomList(RandomListNode head) {
		if (head == null)
			return null;
		// map中key和value的节点值是相同的，不过是不同的对象。
		HashMap<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();
		RandomListNode newhead = new RandomListNode(head.label);
		map.put(head, newhead);

		RandomListNode oldp = head.next;
		RandomListNode newp = newhead;

		while (oldp != null) {
			RandomListNode newnode = new RandomListNode(oldp.label);
			map.put(oldp, newnode);
			newp.next = newnode;

			oldp = oldp.next;
			newp = newp.next;

		}
		oldp = head;
		newp = newhead;
		while (oldp != null) {
			// 等号右边是一个新的节点,因为oldp的值和newp的值相同，现在取出oldp.random对应的value给了newp.random。
			newp.random = map.get(oldp.random);
			oldp = oldp.next;
			newp = newp.next;

		}

		return newhead;
	}
```

## S.141_Linked List Cycle

原题地址：https://leetcode.com/problems/linked-list-cycle/

思路：

​	判断一个链表中是否有环存在。

​	遍历这个链表，如果可以遍历到null，则说明这个链表中没有环，那么如果有环要如何判断呢？

​	设置两个指针slow和faster，slow指针每次向后移动一个位置，faster每次向后移动两个指针。如果这个链表中存在环，那么这两个指针肯定会有相遇的时候。

> 为什么他俩肯定能相遇呢？万一一个把一个超了但是没相遇咋办？
>
> 直觉和生活经验告诉我，他俩肯定能相遇，比如在操场跑圈，一个快的一个慢的同时开始跑，一直跑，快的肯定能跟慢的相遇。不过有更严谨的说法就更有说服力了。
>
> 下面我就引用一下CC150里面外加我的完善来说明怎么证明的这个问题：
>
> 假设Faster确实把Slower超了而且他俩还没相遇（类似Faster一下迈了2步，Slower一下迈了一步，Faster超了Slower，但是俩人并没遇上）。那么就假设Faster现在在 i+1 位置而Slower在 i 位置。那么前一时刻，Slower肯定是在 i-1 位置，而Faster肯定在(i+1)-2位置，所以前一时刻，俩人都在 i-1 位置，相遇了。
>
> 还有一种情况就是Faster在i+2位置而slower在i位置，那么前一时刻，Faster在i位置，而Slower在 i-1位置。这样问题又回归到上面那种情况了（再往前一时刻，Faster在i-2位置，Slower在i-1-1位置，相遇）。
>
> 所以，这就证明Runner和Faster在有环的链表中肯定会相遇。



代码：

```java
public boolean hasCycle(ListNode head) {
		if(head == null || head.next == null)
			return false;
		
		ListNode slow = head;
		ListNode faster = head;
		//注意这里不是slow.next != null && faster.next.next != null
		//应该是faster.next != null && faster.next.next != null
		//因为只有faster.next != null 才会有faster.next.next,如果faster.next == null,那么faster.next.next会报
		//nullpointerException的。
		while(faster.next != null && faster.next.next != null){
			slow = slow.next;
			faster = faster.next.next;
			
			if(slow == faster)
				return true;
		}
		
		return false;
		
	}
```

## S.142_Linked List Cycle II（medium）

原题地址：https://leetcode.com/problems/linked-list-cycle-ii/

思路：

​	使用双指针，slower和faster，反复论证清楚这两者之间的关系，找到相遇点，然后找到相遇点和环入口的关系，找到环入口的位置。证明如下：

​	  ![环入口](pics\/环入口.PNG)

> 如上图所示，12为带环链表的入口点，简单分析可知243是fast和slow的相遇点（鹊桥相会啊==！），a为环入口点到头结点的路程，x为相遇点到环入口点的路程。我们假设slow指针走过的路程为s，那么fast指针走过的路程则为2s（如果理解有困难，自己可以举例），假设环长为c。且有

>2s = s + nc
>
>s = a + x

通过这个式子得到：

>a + x = nc
>
>a = nc – x
>
>a = (n – 1)c + c -x
>
>式子化来化去，到这儿，我们是不是感觉有点不对经，好想如果在头结点位置和相遇点位置分别再派出两名跑步选手，并且他们都每次只跑一步，好像会在环的入口点相遇啊！！！恭喜你，答对啦！具体原因我们再分析分析最后一个式子就能明白了。



1. 设置两个指针，slower和faster，当两者相遇的时候，faster走过的路程是slower的2倍。通过这个关系找到相遇点。

2. 再设置两个指针，分别从头结点和相遇点出发，当他们相遇的时候就是环入口的位置。依据的公式是：

    a = kc + (c-x)

代码：

```java

	public ListNode detectCycle(ListNode head) {
		if(head == null || head.next == null){
			return null; //因为不存在环么
		}
		
		ListNode slower = head;
		ListNode faster = head;
		
		while(true){
			if(faster.next == null || faster.next.next == null)
				return null; //不存在环的情况  
			
			slower = slower.next;
			faster = faster.next.next;
			
			if(slower == faster)
				break;    //slower和faster相遇
			
		}
		slower = head;
		while(slower != faster){   //slower和faster分别从头节点的位置和相遇节点的位置往后移动
			slower = slower.next;
			faster = faster.next;
		}
		return slower;
		
	}

```

## S.143_Reorder List（medium，重点）

原题地址：https://leetcode.com/problems/reorder-list/

思路：

	>把【1，2，3，4】分成两部分，【1，2】和【3，4】，第二部分逆序，成为【4，3】，然后顺次连接两个链表，最后结果：【1，4，2，3】。

**技巧**：在求中间元素的时候，不需要先遍历一遍求出长度，然后再次遍历到中间找到中间元素。只需要搞两个指针，slower和faster，slower每次走一步，faster每次走两步，当faster到达末尾的时候，slower走过的路正好是faster走过的一半，所以slower.next就是第二部分的第一个元素。

1. 第一个链表和第二个链表分开.
2. 把第二部分元素逆序了.
3. 把两个链表按序接起来.

积累：

​	链表逆序的方法有两种，上面插图的是一种方法。哪种方法叫做头插法。遍历到的元素往前面放。

​	第二种方法是如下，仅仅修改前后两个元素的指针即可。很简单。

​	

代码：

```java

    public ListNode reverseList(ListNode head){
		if(head == null || head.next == null){
			return head;
		}
		
		ListNode pre = head;
		ListNode cur = head.next;
		
		//链表逆序的另外一种写法,也很巧妙。原先的写法是头插法。
		while(cur != null){
			ListNode temp = cur.next;
			cur.next = pre;
			pre = cur;
			cur = temp;
		}
		
		head.next = null;//把head.next修改为null。
		
		return pre;
		
	}
    public void reorderList(ListNode head) {
		if(head == null || head.next == null)
			return ;
		
		ListNode slower = head;
		ListNode faster = head;
		ListNode firstHalf = head;
		while(faster.next != null && faster.next.next != null){
			slower = slower.next;
			faster = faster.next.next;
		}
		
		ListNode secondHalf = slower.next;
		//第一个链表和第二个链表分开
		slower.next = null; 
		
		//把第二部分元素逆序了
		secondHalf = reverseList(secondHalf);
		
		//把两个链表按序接起来
		while(firstHalf != null && secondHalf != null){
			ListNode node1 = firstHalf.next;
			ListNode node2 = secondHalf.next;
			
			firstHalf.next = secondHalf;
			secondHalf.next = node1;
			
			firstHalf = node1;
			secondHalf = node2;
			
		}
		
		
	}
```

## S.146_LRU Cache

原题地址：https://leetcode.com/problems/lru-cache/

思路：

​	在所有LeetCode中，这是一道设计数据结构的题目。可以好好看看！

> 这道题要求设计实现LRU cache的数据结构，实现set和get功能。学习过操作系统的都应该知道，cache作为缓存可以帮助快速存取数据，但是确定是容量较小。这道题要求实现的cache类型是LRU，LRU的基本思想就是“最近用到的数据被重用的概率比较早用到的大的多”，是一种更加高效的cache类型。

> 解决这道题的方法是：**双向链表+HashMap**。双向链表的顺序表明了在cache满的情况下，把那个元素删除，HashMap是为了能在O（1）的复杂度内访问到一个元素。

> “为了能够快速删除最久没有访问的数据项和插入最新的数据项，我们将双向链表连接Cache中的数据项，并且保证链表维持数据项从**最近访问到最旧访问的顺序**。 每次数据项被查询到时，都将此数据项移动到链表头部（O(1)的时间复杂度）。这样，在进行过多次查找操作后，最近被使用过的内容就向链表的头移动，而没 有被使用的内容就向链表的后面移动。当需要替换时，链表最后的位置就是最近最少被使用的数据项，我们只需要将最新的数据项放在链表头部，当Cache满 时，淘汰链表最后的位置就是了。 ”

 “注： 对于双向链表的使用，基于两个考虑。

            首先是Cache中块的命中可能是随机的，和Load进来的顺序无关。

         其次，双向链表插入、删除很快，可以灵活的调整相互间的次序，时间复杂度为O(1)。”

解决了LRU的特性，现在考虑下算法的时间复杂度。为了能减少整个数据结构的时间复杂度，就要减少查找的时间复杂度，所以这里利用HashMap来做，这样时间复杂度就是O(1)。

 所以对于本题来说：

get(key): 如果cache中不存在要get的值，返回-1；如果cache中存在要找的值，返回其值并将其在原链表中删除，然后将其作为头结点。

set(key,value)：当要set的key值已经存在，就更新其value， 将其在原链表中删除，然后将其作为头结点；当药set的key值不存在，就新建一个node，如果当前len<capacity,就将其加入hashmap中，并将其作为头结点，更新len长度，否则，删除链表最后一个node，再将其放入hashmap并作为头结点，但len不更新。

 

原则就是：**对链表有访问，就要更新链表顺序。 **

代码：

```java
public class LRUCache {
    private HashMap<Integer,DoubleLinkedListNode> map = 
			new HashMap<Integer,DoubleLinkedListNode>();
	private DoubleLinkedListNode head;
	private DoubleLinkedListNode end;
	private int capacity;
	private int len;
    public LRUCache(int capacity) {
        		this.capacity = capacity;

    }
    
    public int get(int key) {
        
		if(map.containsKey(key)){
			DoubleLinkedListNode latest = map.get(key);
			removeNode(latest);
			setHead(latest);
			return latest.val;
		}else{
			return -1;
		}
	
    }
    public void setHead(DoubleLinkedListNode node){
		node.next = head;
		node.pre = null;   //缺少这一行，答案不对
		if(head != null){
			head.pre = node;
		}
		head = node;  //head指向node
		
		if(end == null){
			end = node;
		}
	}
	
	public void removeNode(DoubleLinkedListNode node){
		DoubleLinkedListNode cur = node;
		DoubleLinkedListNode pre = cur.pre;
		DoubleLinkedListNode post = cur.next;
		
		if(pre != null){
			pre.next = post;
		}else{
			head = post;
		}
		
		if(post != null){
			post.pre = pre;
		}else{
			end = pre;
		}
	}
    public void set(int key, int value) {
        
		if(map.containsKey(key)){//key值存在，只需要更新
			DoubleLinkedListNode oldNode = map.get(key);
			oldNode.val = value;
			removeNode(oldNode);
			setHead(oldNode);
		}else{
			//key不存在，分两种情况，一种是length == capacity，一种是length < capacity
			DoubleLinkedListNode newNode = new DoubleLinkedListNode(key,value);
			if(len < capacity){
				setHead(newNode);
				map.put(key, newNode);
				len++;
			}else{
				map.remove(end.key);
				end = end.pre;
				if(end != null){
					end.next = null;
				}
				
				setHead(newNode);
				map.put(key, newNode);
			}
		}
	
    }
}

class DoubleLinkedListNode{
	public int val;
	public int key;
	public DoubleLinkedListNode pre;
	public DoubleLinkedListNode next;
	
	public DoubleLinkedListNode(int key,int value){
		this.key = key;
		val = value;
	}
}
```

​	

