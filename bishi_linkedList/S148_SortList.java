package bishi_linkedList;

import org.junit.Test;


/**
 * Question : Sort a linked list in O(n log n) time using constant space complexity.
 * @author chengcheng
 * @time 2016年9月5日 上午10:15:57
 *
 */
public class S148_SortList {
	ListNode newhead;
	public ListNode sortList(ListNode head){
         if(head == null || head.next == null)
        	 return head;
         
         newhead = head;
         return recursiveSort(newhead);
    }
	
	public ListNode recursiveSort(ListNode head){
		
		ListNode slower = head;
        ListNode faster = head;
        while(faster.next != null && faster.next.next != null){
        	
       	 	slower = slower.next;
       	 	faster = faster.next.next;
        }
        if(head != faster && head.next != faster){
        	ListNode part2 = slower.next;
        	slower.next = null;
        	ListNode first = recursiveSort(head);
        	ListNode second = recursiveSort(part2);
        	return mergeListNode(first, second);
        }
        else{
        	
        	ListNode part2 = head.next;
        	head.next = null;
        	return mergeListNode(head,part2);
        }
        
	}
	
	public ListNode mergeListNode(ListNode first, ListNode second){
		
		ListNode node = new ListNode(-1);
		ListNode result = node;
		while(first != null && second != null){
			if(first.val < second.val){
				
				node.next = first;
				first = first.next;
				node = node.next;
			}
			else{
				node.next = second;
				second = second.next;
				node = node.next;
			}
		}
		while(first != null){
			node.next = first;
			first = first.next;
			node = node.next;
		}
		if(second != null){
			node.next = second;
			second = second.next;
			node = node.next;
		}
		
		return result.next;
	}
	@Test
	public void test(){
		ListNode node = new ListNode(2);
		node.next = new ListNode(1);
		ListNode result = sortList(node);
		while(result != null){
			System.out.println(result.val);
			result = result.next;
		}
	}
	@Test
	public void test2(){
		ListNode node1 = new ListNode(2);
		ListNode node2 = new ListNode(1);
	}
}
