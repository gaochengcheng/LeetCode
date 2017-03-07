package bishi_linkedList;

import org.junit.Test;


/**
 * 
 * @author chengcheng
 * @time 2016年8月15日 上午10:17:17
 *
 */
public class S82_RemoveDuplicatesfromSortedListII {
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

	@Test
	public void test() {

	}
}
