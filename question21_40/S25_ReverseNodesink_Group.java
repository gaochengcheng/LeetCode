package question21_40;

import org.junit.Test;

/**
 * 
 * @author chengcheng
 * @time 2016年8月17日 上午8:43:24
 *
 */
public class S25_ReverseNodesink_Group {
public ListNode reverseKGroup(ListNode head, int k) {
        if(head == null || head.next == null){
        	return head;
        }
        
        ListNode fakenode = new ListNode(-1);
        fakenode.next = head;
        ListNode startPoint = fakenode;
        ListNode node1 = null;
        ListNode node2 = null;
        
        
    }
	@Test
	public void test(){
		
	}

}
