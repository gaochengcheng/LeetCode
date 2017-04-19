package bishi_binaryTree;

import org.junit.Test;
/**
 * 二叉树镜像
 * @author chengcheng
 * @time 2017年4月10日 上午11:29:41
 *
 */
public class MirrorOfBinaryTree {
	public void mirrorOfBinaryTree(TreeNode root){
	  if(root == null)
	    return ;
	  if(root.left == null && root.right == null)
	    return ;
	  
	  //exchange left,right child
	  TreeNode tmp = root.left;
	  root.left = root.right;
	  root.right = tmp;
	  
	  
	  if(root.left != null)
	 	mirrorOfBinaryTree(root.left);
	  if(root.right != null)
	  	mirrorOfBinaryTree(root.right);
	  
	}
	
}

