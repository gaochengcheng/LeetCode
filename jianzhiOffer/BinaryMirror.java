package jianzhiOffer;
/**
 * 二叉树镜像问题
 * @author chengcheng
 * @time 2017年8月10日 下午3:28:57
 *
 */
public class BinaryMirror {
	// 1.先序的方式遍历这棵二叉树
	// 2.遍历的每个节点，只有存在左孩子或者右孩子，就可以做交换操作
	public TreeNode solution(TreeNode root){
		if(root == null)
			return null;
		
		if(root.left == null && root.right == null) //交换完所有非叶子结点
			return null;
		
		//交换root结点的左右两个孩子，左右孩子只要有一个非空，就可以交换。
		TreeNode temp = root.left;
		root.left = root.right;
		root.right = temp;
		
		if(root.left != null)
			solution(root.left);
		if(root.right != null)
			solution(root.right);
		
		return root;
	}
}
