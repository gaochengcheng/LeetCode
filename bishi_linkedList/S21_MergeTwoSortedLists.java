package bishi_linkedList;

import org.junit.Test;


/**
 * 
 * @author chengcheng
 * @time 2016年9月1日 上午8:40:54
 *
 */
public class S21_MergeTwoSortedLists {
	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null)
        	return l2;
        if(l2 == null)
        	return l1;
        
        ListNode newhead = new ListNode(-1);
        ListNode head = newhead;
        
        while(l1 != null && l2 != null){
        	if(l1.val < l2.val){
        		newhead.next = l1;
        		l1 = l1.next;
        		newhead = newhead.next;
        	}
        	else{
        		newhead.next = l2;
        		l2 = l2.next;
        		newhead = newhead.next;
        	}
        }

        if(l1 != null)
        	newhead.next = l1;
	
        if(l2 != null)
        	newhead.next = l2;
	
        return head.next;
	}
	
	public ListNode merge(ListNode l1, ListNode l2){
		if(l1 == null)
			return l2;
		if(l2 == null)
			return l1;
		
		ListNode mergeHead = null;
		
		if(l1.val < l2.val){
			mergeHead = l1;
			mergeHead.next = merge(l1.next, l2);
		}
		else{
			mergeHead = l2;
			mergeHead.next = merge(l1, l2.next);
		}
		
		return mergeHead;
	}
	@Test
	public void test(){
		
	}
}
