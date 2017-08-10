package jianzhiOffer;
/**
 * 返回链表中倒数第 K 个结点
 * @author chengcheng
 * @time 2017年8月10日 上午10:08:57
 *
 */
public class FindkthToTail {

	public ListNode solution(ListNode head, int k){
		if(head == null || k <= 0 )
			return null;
		
		//找到第K个结点
		ListNode pAhead = head;
		ListNode pBehind = head;
		for(int i = 1; i<=k-1 ;i++){
			if(pAhead.next != null){
				pAhead = pAhead.next;
			}
			//如果列表长度小于K，程序中断返回
			else
				return null;
		}
		
		while(pAhead.next != null){
			pAhead = pAhead.next;
			pBehind = pBehind.next;
		}
		return pBehind;
		
	}
}
