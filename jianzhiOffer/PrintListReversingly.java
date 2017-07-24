package jianzhiOffer;

import java.util.Stack;

/**
 * 
 * @author chengcheng
 * @time 2017年7月23日 下午5:33:15
 *
 */

// 定义一个ListNode类
class ListNode{
	int val;
	ListNode next;
}

public class PrintListReversingly {
	
	public void solution_PrintListReversingly_Iterately(ListNode pHead){
		Stack<ListNode> stack = new Stack<>();
		
		while(pHead != null){
			stack.push(pHead);
			pHead = pHead.next;
		}
		
		while(! stack.isEmpty()){
			ListNode node = stack.peek();
			System.out.println(node.val);
			stack.pop();
		}
	}
	
	public void solution_PrintListReversingly_Recursively(ListNode pHead){
		if(pHead != null){
			if(pHead.next != null){
				solution_PrintListReversingly_Recursively(pHead.next);
			}
			//遇到最后一个节点，输出值
			System.out.println(pHead.val);
		}
	}
	
}
