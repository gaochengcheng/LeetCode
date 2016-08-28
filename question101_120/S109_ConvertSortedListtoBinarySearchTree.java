package question101_120;

import org.junit.Test;

/**
 * 
 * @author chengcheng
 * @time 2016年8月26日 下午1:37:20
 *
 */
public class S109_ConvertSortedListtoBinarySearchTree {
	
	static ListNode h;  //为了解决java中没有引用传递，只有值传递的情况，我们在这里搞了一个全局变量。
	public TreeNode sortedListToBST(ListNode head) {
		
		if(head == null)
			return null;
		int length = 0;
		h = head;
		ListNode cur = head;
		while(cur != null){
			length++;
			cur = cur.next;
		}
		System.out.println(length);
		return sortedListToBST(0, length-1);
    }
	
	public TreeNode sortedListToBST(int start, int end){
		if(start > end)
			return null;
		
		int mid = (start+end)/2;
		TreeNode leftChild = sortedListToBST(start, mid-1);
		TreeNode parent = new TreeNode(h.val);
		parent.left = leftChild;
		h = h.next;
		parent.right = sortedListToBST(mid+1, end);
		
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
