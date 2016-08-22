package question101_120;

import org.junit.Test;

/**
 * 
 * Given a binary tree, determine if it is height-balanced.
 * 
 * For this problem, a height-balanced binary tree is defined as a binary tree
 * in which the depth of the two subtrees of every node never differ by more
 * than 1.
 * 递归程序的写法：
 * 		1.写上递归终止的条件。
 * 		2.写上在每一次递归中要做什么事情。就是当把问题分解为最小规模后，是做什么事情。
 * 
 * 首先要会采用递归的形式求这棵树的高度
 * 思路：
 * 	1.递归，针对每个节点做递归。
 * 	2.在回溯的过程中，每个节点记录当前是第几层（从底往上数）。
 * 	3.并且针对每个节点，计算当前左右子树的高度差，如果>1，直接return -1。（表示这个树不是高度平衡的）
 * @author chengcheng
 *
 */
public class S110_BalancedBinaryTree {
	public static void main(String[] args) {

	}
	@Test
	public void test(){
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.left.left = new TreeNode(4);
		int height = getHeight(root);
		System.out.println(height);
		boolean flag = isBalanced(root);
		System.out.println(flag);
		
	}
	public boolean isBalanced(TreeNode root) {
		
		if(root == null)
			return true;
		if(getHeight(root) == -1)
			return false;
		
		return true;
	}
	public int getHeight(TreeNode root){
		if(root == null)
			return 0;
		
		int left = getHeight(root.left);
		int right = getHeight(root.right);
		if(left == -1 || right == -1)
			return -1;
		if(Math.abs(left - right) > 1)
			return -1;
		
		return Math.max(left, right)+1;
	}
	
}
