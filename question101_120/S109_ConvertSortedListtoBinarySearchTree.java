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
		return sortedListToBST(head, 0, length);
    }
	
	public TreeNode sortedListToBST(ListNode head, int start, int end){
		if(start > end)
			return null;
		
		int mid = (start+end)/2;
		TreeNode leftChild = sortedListToBST(head, start, mid-1);
		TreeNode parent = new TreeNode(head.val);
		parent.left = leftChild;
		head = head.next;
		parent.right = sortedListToBST(head, mid+1, end);
		
		return parent;
	}
	@Test
	public void test(){
		ListNode node1 = new ListNode(0);
//		node1.next = new ListNode(3);
		System.out.println(node1);
	}
}
