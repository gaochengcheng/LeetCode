package contest21;

import java.util.Stack;

import org.junit.Test;

public class S530_MinimumAbsoluteDifferenceinBST {
	public int getMinimumDifference(TreeNode root) {
		int dis = 1000;
		TreeNode p = root;
		int cur=1000,next = 2000;
		Stack<TreeNode> stack = new Stack<TreeNode>();
		while(!stack.isEmpty() || p!= null){
			if(p != null){
				stack.push(p);
				p = p.left;
			}
			else{
				next = cur;
				TreeNode node = stack.pop();
				cur = node.val;
				p = node.right;
				System.out.println("next "+next+", cur "+cur);
			}
			dis = Math.min(Math.abs(cur-next),dis);
		}
		return dis;
	}
	
	@Test
	public void test(){
		TreeNode node1 = new TreeNode(5);
		node1.left = new TreeNode(4);
		node1.right = new TreeNode(7);
		System.out.println(getMinimumDifference(node1));
	}
}
