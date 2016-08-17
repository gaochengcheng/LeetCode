package question21_40;

import org.junit.Test;



/**
 * 
 * @author chengcheng
 * @time 2016年8月17日 上午7:51:17
 *
 */
public class S24_SwapNodesinPairs {
	public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null)
        	return head;
        
        int value = 0;
        ListNode fakenode = new ListNode(-1);
        fakenode.next = head;
        ListNode ptr1 = head;
        ListNode ptr2 = head.next;
        while(ptr1 != null && ptr2 != null){
        	value = ptr1.val;
        	ptr1.val = ptr2.val;
        	ptr2.val = value;
        	if(ptr2.next != null){
        		ptr1 = ptr2.next;
        		if(ptr1.next != null){
        			ptr2 = ptr1.next;
        		}
        		else
        			break;
        	}else
        		break;
        }
        return fakenode.next;
    }
	@Test
	public void test(){
		ListNode node1 = new ListNode(1);
		ListNode node2 = node1.next = new ListNode(2);
		ListNode node3 = node2.next = new ListNode(3);
		ListNode node4 = node3.next = new ListNode(4);
		ListNode node5 = node4.next = new ListNode(5);
		ListNode temp = swapPairs(node1);
		while(temp != null){
			System.out.println(temp.val);
			temp = temp.next;
			
		}
	}
}
