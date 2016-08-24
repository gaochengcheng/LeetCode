package question101_120;

import org.junit.Test;

public class S105_ConstructBinaryTreefromPreorderandInorderTraversal {
	public TreeNode buildTree(int[] preorder, int[] inorder) {
		int preLength = preorder.length;
		int inLength = inorder.length;
		return buildTree(preorder,0,preLength-1,inorder,0,inLength-1);
	}
	
	public TreeNode buildTree(int[] preorder,int preStart,int preEnd,int[] inorder,int inStart,int inEnd){
		if(preStart > preEnd || inStart > inEnd){
			return null;
		}
		int rootVal = preorder[preStart];
		int rootIndex = 0;
		//在中序数组中找到头节点
		for(int i = inStart; i <= inEnd; i++){
			if(inorder[i] == rootVal){
				rootIndex = i;
				break;
			}
		}
		int len = rootIndex - inStart;
		TreeNode root = new TreeNode(rootVal);
		root.left = buildTree(preorder, preStart+1, preStart+len, inorder, inStart, rootIndex-1);
		root.right = buildTree(preorder, preStart+len+1, preEnd, inorder, rootIndex+1, inEnd);
		return root;
		
		
	}
	

	@Test
	public void test() {
		int[] preOrder = {1,2,4,5,3,6,7};
		int[] inOrder = {4,2,5,1,6,3,7};
		TreeNode root = buildTree(preOrder,inOrder);
		System.out.println(S102_LevelOrder.LevelOrder_1(root));
	}
}
