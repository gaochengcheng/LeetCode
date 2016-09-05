package question141_160;

import org.junit.Test;

/**
 * 
 * @author chengcheng
 * @time 2016年9月2日 下午3:40:44
 *
 */
public class S147_InsertionSortList {
	public ListNode insertionSortList(ListNode head) {
		if(head == null || head.next == null)
			return head;
		
		ListNode sortedListHead = new ListNode(0);//fakenode
		ListNode cur = head;
		
		int times = 1;
		
		while(cur != null){
			System.out.println("times : "+ times++);
			ListNode next = cur.next;
			ListNode pre = sortedListHead;
			System.out.println("sortedListHead.next : "+sortedListHead.next);
			while(pre.next != null && pre.next.val < cur.val){
				pre = pre.next;
			}
			//下面看不懂
			
			System.out.println("pre.next: "+pre.next);
			cur.next = pre.next;   //真是没想到，pre.next 会经常是null.
			System.out.println("cur: "+cur + "cur.val" + cur.val);
			pre.next = cur;
			cur = next;
			System.out.println("----------------------");
		}
        return sortedListHead.next;
    }
	@Test
	public void test(){
		ListNode node = new ListNode(1);
		node.next = new ListNode(3);
		node.next.next = new ListNode(2);
		node.next.next.next = new ListNode(5);
		node.next.next.next.next = new ListNode(4);
		ListNode result = insertionSortList(node);
		while(result != null){
			System.out.println(result.val);
			result = result.next;
		}
	}
//	@Test
//	public void test_2(){
//		ListNode node = new ListNode(1);
//		node.next = ;
//		while(node != null){
//			System.out.println(node.val);
//			node = node.next;
//		}
//			
//	}
}
