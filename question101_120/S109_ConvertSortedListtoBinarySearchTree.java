package question101_120;

import org.junit.Test;

/**
 * 
 * @author chengcheng
 * @time 2016年8月26日 下午1:37:20
 *
 */
public class S109_ConvertSortedListtoBinarySearchTree {
	public TreeNode sortedListToBST(ListNode head) {
		
		if(head == null)
			return null;
		int length = 0;
		ListNode cur = head;
		while(cur != null){
			length++;
			cur = cur.next;
		}
		System.out.println(length);
		return sortedListToBST(head, 0, length-1);
    }
	
	public TreeNode sortedListToBST(ListNode list, int start, int end){
		if(start > end)
			return null;
		
		int mid = (start+end)/2;
		TreeNode leftChild = sortedListToBST(list, start, mid-1);
		TreeNode parent = new TreeNode(list.val);
		parent.left = leftChild;
		list = list.next;
		parent.right = sortedListToBST(list, mid+1, end);
		
		return parent;
	}
	@Test
	public void test(){
		ListNode node1 = new ListNode(3);
		node1.next = new ListNode(5);
		node1.next.next = new ListNode(8);
		
		TreeNode temp = sortedListToBST(node1);
		
		System.out.println(S102_LevelOrder.LevelOrder_1(temp));
	}
}
