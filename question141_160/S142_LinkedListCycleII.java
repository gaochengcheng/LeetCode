package question141_160;

import org.junit.Test;
/**
 * 
 * @author chengcheng
 * @time 2016年8月19日 上午10:11:30
 *
 */
public class S142_LinkedListCycleII {
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

	@Test
	public void test() {
		ListNode node1 = new ListNode(1);
//		ListNode node2 = node1.next = new ListNode(2);
//		ListNode node3 = node2.next = new ListNode(3);
//		ListNode node4 = node3.next = new ListNode(4);
////		ListNode node5 = node4.next = new ListNode(5);
		ListNode temp = detectCycle(node1);
		System.out.println(temp);
	}
}
