package bishi_array;

import org.junit.Test;

import bishi_binaryTree.S102_LevelOrder;

/**
 * 
 * @author chengcheng
 * @time 2016年8月24日 上午8:17:56
 *
 */
public class S106_ConstructBinaryTreefromInorderandPostorderTraversal {
public TreeNode buildTree(int[] inorder, int[] postorder) {
		int inLength = inorder.length;
		int postLength = postorder.length;
		
		return buildTree(inorder, 0, inLength-1, postorder, 0, postLength-1);
    }
	public TreeNode buildTree(int[] inorder, int inStart, int inEnd,int[] postorder, int postStart, int postEnd){
		
		if(inStart > inEnd || postStart > postEnd){
			return null;
		}
		
		int rootVal = postorder[postEnd];
		int rootIndex = 0;
		for(int i = inStart; i <= inEnd; i++){
			if(inorder[i] == rootVal){
				rootIndex = i;
				break;
			}
		}
		int len = rootIndex - inStart;
		TreeNode root = new TreeNode(rootVal);
		root.left = buildTree(inorder,inStart,rootIndex-1,postorder,postStart,postStart+len-1);
		root.right = buildTree(inorder,rootIndex+1,inEnd,postorder,postStart+len,postEnd-1);
		
		return root;
	}
	@Test
	public void test() {
		int[] inOrder = {4,2,5,1,6,3,7};
		int[] postOrder = {4,5,2,6,7,3,1};
		TreeNode root = buildTree(inOrder,postOrder);
//		System.out.println(S102_LevelOrder.LevelOrder_1(root));
	}
}
