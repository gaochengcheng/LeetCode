package question81_100;

import org.junit.Test;

import question81_100.ListNode;

/**
 * 
 * @author chengcheng
 * @time 2016年8月13日 下午1:55:44
 *
 */
public class S92_ReverseLinkedListII {
	public ListNode reverseBetween(ListNode head, int m, int n) {
		ListNode newHead = new ListNode(-1);      //给链表增加了一个头节点,当只有两个节点时，startpoint指向头结点。
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
	@Test
	public void test(){
		ListNode node1 = new ListNode(3);
		ListNode node2 = node1.next = new ListNode(5);
//		ListNode node3 = node2.next = new ListNode(3);
//		ListNode node4 = node3.next = new ListNode(4);
//		node4.next = new ListNode(5);
//		while(node1 != null){
//			System.out.println(node1.val);
//			node1 = node1.next;
//		}
		ListNode newnode = reverseBetween(node1,1,2);
		System.out.println("------------");
		while(newnode != null){
			System.out.println(newnode.val);
			newnode = newnode.next;
		}
	}
}
