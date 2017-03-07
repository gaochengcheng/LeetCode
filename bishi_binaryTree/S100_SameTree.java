package bishi_binaryTree;

import java.util.Stack;

import org.junit.Test;

public class S100_SameTree {
	public static void main(String[] args) {
		
	}
	@Test
	public void test(){
		TreeNode root = new TreeNode(0);
    	root.left = new TreeNode(1);
    	
    	
    	TreeNode root2 = new TreeNode(0);
    	root2.left = new TreeNode(1);
    	
    	Boolean flag = isSameTree2(root, root2);
    	System.out.println(flag);
	}
	public boolean isSameTree2(TreeNode p, TreeNode q){
		Stack<TreeNode> stack = new Stack<TreeNode>();
		stack.push(p);
		stack.push(q);
		while(!stack.isEmpty()){
			TreeNode node1 = stack.pop();
			TreeNode node2 = stack.pop();
			if(node1 == null && node2 == null)
				continue;
			if(node1 == null || node2 == null)
				return false;
			if(node1.val != node2.val)
				return false;
			stack.push(node1.left);
			stack.push(node2.left);
			
			stack.push(node1.right);
			stack.push(node2.right);
		}
		return true;
	}
	
	
	public boolean isSameTree(TreeNode p, TreeNode q){
		Stack<TreeNode> stack1 = new Stack<TreeNode>();
		Stack<TreeNode> stack2 = new Stack<TreeNode>();
		if(p == null && q == null)
			return true;
		stack1.push(p);
		stack2.push(q);
		while(!stack1.isEmpty() || !stack2.isEmpty()){
			TreeNode node1 = stack1.pop();
			TreeNode node2 = stack2.pop();
			if(node1 == null && node2 != null)
				return false;
			if(node1 != null && node2 == null)
				return false;
			if(node1.val != node2.val)
				return false;
			if(node1.left == null && node2.left != null)
				return false;
			if(node1.right != null && node2.right == null)
				return false;
			
			if(node2.right != null && node2.right != null){
				stack1.push(node1.right);
				stack2.push(node2.right);
			}
			if(node1.left != null && node2.left != null){
				stack1.push(node1.left);
				stack2.push(node2.left);
			}	
		}
		return true;
	}
}
