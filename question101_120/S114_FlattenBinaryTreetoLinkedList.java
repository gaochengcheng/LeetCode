package question101_120;

import java.util.Stack;

import org.junit.Test;
/**
 * 
 * @author chengcheng
 *
 */
public class S114_FlattenBinaryTreetoLinkedList {
	@Test
	public void test(){
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(5);
		root.left.left = new TreeNode(3);
		root.left.right = new TreeNode(4);
		root.right = new TreeNode(5);
		root.right.right = new TreeNode(6);
		flatten0(root);
	}
	
	
	public void flatten0(TreeNode root){
		Stack<TreeNode> stack = new Stack<TreeNode>();
		
		if(root == null)
			return ;
		stack.push(root);
		TreeNode newroot = new TreeNode(0);
		TreeNode newroot2 = newroot;
		while(!stack.isEmpty()){
			TreeNode node = stack.pop();
			System.out.println(node.val);
			TreeNode temp = new TreeNode(node.val);
			temp.left = null;
			newroot.right = temp;
			
			newroot = temp;
			
			
			if(node.right != null)
				stack.push(node.right);
			if(node.left != null)
				stack.push(node.left);
			
		}
		root = newroot2.right;
		while(root != null){
			System.out.println(root.val);
			System.out.println(root.left);
			root = root.right;
		}
	}
	/**
	 * 对整颗树一直向右子树方向遍历。当遍历的节点有右孩子的时候，就将其入栈。
	 * 有左孩子时，将其更新为当前节点的右孩子，左孩子置空。
	 * 当左孩子为空而栈不为空事，就弹出栈。
	 * @param root
	 */
	public void flatten2(TreeNode root) {
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode p = root;

		while (p != null || !stack.empty()) {

			if (p.right != null) {
				stack.push(p.right);
			}

			if (p.left != null) {
				p.right = p.left;
				p.left = null;
			} else if (!stack.empty()) {
				TreeNode temp = stack.pop();
				p.right = temp;
			}
			p = p.right;
		}
	}
	 public void flatten(TreeNode root) {
		 if( root == null){
			 return;
		 }
		 Stack<TreeNode> stack = new Stack<TreeNode>();
		 stack.push(root);
		 
		 while(!stack.isEmpty()){
			 TreeNode node = stack.peek();
			 stack.pop();
			 
			 if(node.right != null)
				 stack.push(node.right);
			 if(node.left != null)
				 stack.push(node.left);
			 
			 node.left = null;
			 
			 if(!stack.isEmpty()){
				 node.right = stack.pop();
			 }
		 }
		 
	 }
	
	
}
