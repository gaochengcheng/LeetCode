package question81_100;

import org.junit.Test;

/**
 * 
 * @author chengcheng
 * @time 2016年8月14日 上午10:21:13
 *
 */
public class S86_PartitionList {
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

	@Test
	public void test() {
		ListNode node1 = new ListNode(1);
		ListNode node2 = node1.next = new ListNode(4);
		ListNode node3 = node2.next = new ListNode(3);
		ListNode node4 = node3.next = new ListNode(2);
		ListNode node5 = node4.next = new ListNode(5);
		ListNode node6 = node5.next = new ListNode(6);
		node6.next = new ListNode(7);
		ListNode temp = partition(node1,3);
		while(temp != null){
			System.out.println(temp.val);
			temp = temp.next;
			
		}
	}
}
