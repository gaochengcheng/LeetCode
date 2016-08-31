package question81_100;

import org.junit.Test;

/**
 * 
 * @author chengcheng
 * @time 2016年8月31日 下午1:22:51
 *
 */
public class S88_MergeSortedArray {
	
	//回顾一下如何把两个数组类型的list合并到一个新的数组中
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
	
	@Test
	public void testmergeList(){
		int[] a = {1,3,5,7,9};
		int[] b = {2,4,6,8};
//		int[] c = new int[a.length+b.length];
//		c = mergeList(a,b);
		int[] c = mergeList(a,b);
		for(int ele:c)
			System.out.println(ele);
	}
	
	//回顾一下，如何把两个链表类型的list放到同一个list中
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
	
	@Test
	public void testmergeLinkedList(){
		ListNode node1 = new ListNode(1);
		node1.next = new ListNode(3);
		node1.next.next = new ListNode(5);
		
		ListNode node2 = new ListNode(2);
		node2.next = new ListNode(4);
		node2.next.next = new ListNode(6);
		node2.next.next.next = new ListNode(8);
		
		ListNode node = mergeList(node1,node2);
		
		while(node != null){
			System.out.println(node.val);
			node = node.next;
		}
	}
	
	
	//
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
			while(n>0){
				nums1[m+n-1] = nums2[n-1];
				n--;
			}
		}
	}
}
