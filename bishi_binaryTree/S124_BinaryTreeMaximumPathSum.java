package bishi_binaryTree;

import org.junit.Test;



/**
 * 
 * @author chengcheng
 * @time 2016年8月31日 上午8:47:12
 *
 */
public class S124_BinaryTreeMaximumPathSum {
	
	 int maxValue;
	public int maxPathSum(TreeNode root) {
		maxValue = Integer.MIN_VALUE;
//        System.out.println(maxPathDown(root));
		maxPathDown(root);
        return maxValue;
	}

	private int maxPathDown(TreeNode node) {
        if (node == null) return 0;
        int left = Math.max(0, maxPathDown(node.left));
        int right = Math.max(0, maxPathDown(node.right));
        //下面都是回溯过程中要执行的
        maxValue = Math.max(maxValue, left + right + node.val);//我们认为最大值从root+root.left+root.right中产生。
        return Math.max(left, right) + node.val;//每次返回的时候，必须选择较大的那个值，然后加上当前节点的值。
    }
	@Test
	public void test() {
		
		TreeNode node1 = new TreeNode(5);
		TreeNode node2 = node1.left = new TreeNode(4);
		TreeNode node3 = node1.right = new TreeNode(8);
		TreeNode node4 = node2.left = new TreeNode(11);
		node4.left = new TreeNode(7);
		node4.right = new TreeNode(2);
		node3.left = new TreeNode(13);
		TreeNode node5 = node3.right = new TreeNode(4);
		node5.left = new TreeNode(5);
		node5.right = new TreeNode(1);
		
		System.out.println(maxPathSum(node1));
		
	}
}
