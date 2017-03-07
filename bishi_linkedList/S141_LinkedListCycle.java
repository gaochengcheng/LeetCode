package bishi_linkedList;

import org.junit.Test;


/**
 * 
 * @author chengcheng
 * @time 2016年8月18日 下午8:00:40
 *
 */
public class S141_LinkedListCycle {
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
	
	@Test
	public void test(){
		ListNode node1 = new ListNode(3);
		ListNode node2 = node1.next = new ListNode(2);
		ListNode node3 = node2.next = new ListNode(0);
//		ListNode node4 = node3.next = new ListNode(-4);
//		node3.next = node1;
		System.out.println(hasCycle(node1));
	}
}
