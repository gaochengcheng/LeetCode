package bishi_array;

import org.junit.Test;

public class PathSum {
	public int solution(TreeNode root, int sum){
		
		if(root == null)
			return sum;
		
		sum += root.val;
		return solution(root.left, sum)+solution(root.right, sum);
	}
	
	@Test
	public void test(){
		TreeNode root = new TreeNode(5);
		root.left = new TreeNode(3);
		root.right = new TreeNode(6);
		root.left.left = new TreeNode(9);
		root.left.right = new TreeNode(12);
		System.out.println(solution(root, 0)/2);
	}
}
