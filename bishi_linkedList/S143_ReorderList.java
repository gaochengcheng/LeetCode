package bishi_linkedList;

import org.junit.Test;


/**
 * 
 * @author chengcheng
 * @time 2016年8月19日 上午11:31:52
 *
 */
public class S143_ReorderList {
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

	@Test
	public void test() {
		ListNode node1 = new ListNode(1);
		ListNode node2 = node1.next = new ListNode(2);
		node2.next = new ListNode(3);
		reorderList(node1);
	}
}
