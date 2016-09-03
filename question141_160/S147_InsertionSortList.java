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
		while(cur != null){
			ListNode next = cur.next;
			ListNode pre = sortedListHead;
			while(pre.next != null && pre.next.val < cur.val){
				pre = pre.next;
			}
			//下面看不懂
			cur.next = pre.next;
			pre.next = cur;
			cur = next;
		}
        return sortedListHead;
    }
	@Test
	public void test(){
		
	}
}
