package question101_120;

import org.junit.Test;

/**
 * 
 * @author chengcheng
 * @time 2016年8月27日 下午2:45:25
 *
 */
public class S104_MaximumDepthofBinaryTree {
	public int maxDepth(TreeNode root) {
		return Depth(root);
	}

	public int Depth(TreeNode root) {
		if(root == null)
			return 0;
		return Integer.max(Depth(root.left)+1, Depth(root.right)+1);
	}

	@Test
	public void test() {

	}
}
