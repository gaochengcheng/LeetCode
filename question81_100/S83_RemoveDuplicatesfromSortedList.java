package question81_100;

import org.junit.Test;

/**
 * 
 * @author chengcheng
 * @time 2016年8月15日 上午8:20:29
 *
 */
public class S83_RemoveDuplicatesfromSortedList {
	public ListNode deleteDuplicates(ListNode head) {
        ListNode pre = new ListNode(-1);
        ListNode cur = null;
        
        if(head == null || head.next == null)
        	return head;
        
        pre.next = head;
        cur = head;
        while(cur != null){
        	while(cur != null && pre.val == cur.val){
        		cur = cur.next;
        	}
        	if(cur != null){
        		pre.next = cur;
        		pre = pre.next;
        		cur = cur.next;
        	}else
        	{
        		pre.next = cur;
        	}
        }
        return head;
    }
	@Test
	public void test(){

		ListNode node1 = new ListNode(1);
		ListNode node2 = node1.next = new ListNode(2);
		ListNode node3 = node2.next = new ListNode(3);
		ListNode node4 = node3.next = new ListNode(3);
		ListNode node5 = node4.next = new ListNode(3);
		ListNode node6 = node5.next = new ListNode(4);
		node6.next = new ListNode(4);
		ListNode temp = deleteDuplicates(node1);
		while(temp != null){
			System.out.println(temp.val);
			temp = temp.next;
			
		}
	
	}
}
