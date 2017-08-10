package jianzhiOffer;


public class ReverseList {
	
	public ListNode solution(ListNode head){
		if(head == null)
			return null;
		
		ListNode reverseHead = null;
		ListNode pNode = head;
		ListNode pPrev = null;
		while(pNode != null){
			
			ListNode pNext = pNode.next;
			if(pNext != null)  //当pNext==null时，reverseHead不变。
				reverseHead = pNext;
			
			pNode.next = pPrev;
			
			pPrev = pNode;
			pNode = pNext;
			
			
		}
		
		return reverseHead;
	}
}
