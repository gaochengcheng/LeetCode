package question101_120;

import org.junit.Test;

/**
 * 
 * @author chengcheng
 * @time 2016年8月27日 下午2:13:27
 *
 */
public class S111_MinimumDepthofBinaryTree {
	public int minDepth(TreeNode root) {
		return Depth(root);
		
	}

	
	public int Depth(TreeNode root){
		if(root == null)
			return 0;
		if(root.left != null && root.right != null)
			return Depth(root.left) > Depth(root.right) ? Depth(root.right)+1 : Depth(root.left)+1;
		else if(root.left != null)
			return Depth(root.left) +1;
		else
			return Depth(root.right) +1;
			
	}
	@Test
	public void test() {
		TreeNode root = new TreeNode(3);
		root.left = new TreeNode(9);
		root.right = new TreeNode(20);
		root.right.left = new TreeNode(15);
		root.right.right = new TreeNode(7);
		System.out.println(Depth(root));
	}
}
