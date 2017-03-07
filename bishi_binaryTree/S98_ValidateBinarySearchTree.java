package bishi_binaryTree;

import java.util.ArrayList;
import java.util.Stack;

import org.junit.Test;

/**
 * 
 * @author chengcheng
 * @time 2016年8月26日 上午8:55:09
 *
 */
public class S98_ValidateBinarySearchTree {
	public boolean isValidBST_2(TreeNode root) {
		if(root == null)
			return true;
		
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode p = root;
		ArrayList<Integer> list = new ArrayList<Integer>();
		while(!stack.isEmpty() || p != null){
			if(p != null){
				stack.push(p);
				p = p.left;
			}
			else{
				TreeNode node = stack.pop();
				list.add(node.val);
				p = node.right;
			}
			
		}
		
		int length = list.size();
		for(int i = 0; i < length-1; i++){
			if(list.get(i) >= list.get(i+1))
				return false;
		}
		return true;
	}
	
	public boolean isValidBST(TreeNode root){
		
		return isBST(root,Integer.MIN_VALUE,Integer.MAX_VALUE);
	}
	public boolean isBST(TreeNode node, int low, int high){
		if(node == null)
			return true;
		if(node.val > low && node.val < high)
			//往左边递归的时候，所有节点要小于root.val，往右边递归的时候，所有节点要大于root.val
			return isBST(node.left, low, node.val) && isBST(node.right, node.val, high);
		else
			return false;
	}
	
	@Test
	public void test(){
		//[-2147483648,null,2147483647,-2147483648]
		TreeNode node1 = new TreeNode(-2147483648);
//		TreeNode node2 = node1.left = new TreeNode(1);
		TreeNode node3 = node1.right = new TreeNode(2147483647);
		node3.left = new TreeNode(-2147483648);
//		node3.right = new TreeNode(20);
		System.out.println(isValidBST(node1));
	}
}
