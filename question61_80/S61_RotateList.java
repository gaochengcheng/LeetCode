package question61_80;

import org.junit.Test;



/**
 * 
 * @author chengcheng
 * @time 2016年8月16日 上午8:49:06
 *
 */
public class S61_RotateList {
	public ListNode rotateRight(ListNode head, int k) {
		if(head == null || head.next == null){
			return head;
		}
		
		ListNode fakehead = new ListNode(-1);
		fakehead.next = head;
		
		ListNode ptr1 = head;
		ListNode ptr2 = head;
		int length = 0;
		
		while(head != null){
			length++;
			head = head.next;
		}
//		System.out.println("length is : "+ length);
		k = k % length;
		int i = 0;
		
		//ptr2到达指定位置
		while(i < k){
			i++;
			ptr2 = ptr2.next;
		}
//		System.out.println("ptr2 is : "+ptr2.val);
		//ptr1、ptr2同时向后移动，直到ptr2到达最后
		while(ptr2.next != null){
			ptr1 = ptr1.next;
			ptr2 = ptr2.next;
		}
		ptr2.next = fakehead.next;
		fakehead.next = ptr1.next;
		ptr1.next = null;
		
		
		return fakehead.next;
	}

	@Test
	public void test() {
		ListNode node1 = new ListNode(1);
		ListNode node2 = node1.next = new ListNode(2);
		ListNode node3 = node2.next = new ListNode(3);
		ListNode node4 = node3.next = new ListNode(4);
		ListNode node5 = node4.next = new ListNode(5);
		ListNode temp = rotateRight(node1,2);
		while(temp != null){
			System.out.println(temp.val);
			temp = temp.next;
			
		}
	}
}
