package question1_20;

import org.junit.Test;


/**
 * 
 * @author chengcheng
 * @time 2016年8月16日 上午10:24:39
 *
 */
public class S19_RemoveNthNodeFromEndofList {
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

	@Test
	public void test() {
		ListNode node1 = new ListNode(1);
		ListNode node2 = node1.next = new ListNode(2);
		ListNode node3 = node2.next = new ListNode(3);
		ListNode node4 = node3.next = new ListNode(4);
		ListNode node5 = node4.next = new ListNode(5);
		ListNode temp = removeNthFromEnd(node1,3);
		while(temp != null){
			System.out.println(temp.val);
			temp = temp.next;
			
		}
	}
}
