package bishi_linkedList;

import org.junit.Test;


/**
 * 
 * @author chengcheng
 * @time 2016年8月17日 上午8:43:24
 *
 */
public class S25_ReverseNodesink_Group {
		public ListNode reverse(ListNode pre, ListNode next){
		//需要维护的一共三个指针，pre指向待交换的前一个、last指向待交换的第一个、cur指向待交换的第二个。	
			ListNode last = pre.next;
			ListNode cur = last.next;
			while(cur != next){
				last.next = cur.next;
				cur.next = pre.next;
				pre.next = cur;
				cur = last.next;
			}
			return last;   //last作为下一次交换时候的pre
		}
	
public ListNode reverseKGroup(ListNode head, int k) {
        if(head != null && k == 1){
        	return head;
        }
        
        ListNode fakenode = new ListNode(-1);
        fakenode.next = head;
        
        ListNode pre = fakenode;
        ListNode cur = head;
        int count = 0;
        
        //pre指向待交换的前一个，next指向下一轮待交换的头一个
        while(cur != null){
        	count++;
        	ListNode next = cur.next; // 下一轮待交换的第一个
        	if(count == k){
        		pre = reverse(pre,next);
        		count = 0;
        	}
        	cur = next; //不懂
        }
        return fakenode.next;
    }
	@Test
	public void test(){
		
	}

}
