package bishi_linkedList;

import org.junit.Test;


/**
 * 
 * @author chengcheng
 * @time 2016年9月1日 下午3:23:48
 *
 */
public class S23_MergekSortedLists {
	//方法超时
//	public ListNode mergeKLists(ListNode[] lists) {
//
//		int length = lists.length;
//		if (length == 0)
//			return null;
//
//		ListNode head = lists[0];
//		for (int i = 1; i < length; i++) {
//			head = mergeList(head, lists[i]);
//		}
//
//		return head;
//	}
//
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
	
	
	@Test
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
}
