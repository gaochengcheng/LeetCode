package question101_120;

import org.junit.Test;

public class S105_ConstructBinaryTreefromPreorderandInorderTraversal {
	public TreeNode buildTree(int[] preorder, int[] inorder) {
		int preLength = preorder.length;
		int inLength = inorder.length;
		return buildTree(preorder, 0, preLength - 1, inorder, 0, inLength - 1);
	}
	
	public TreeNode buildTree(int[] pre, int preStart, int preEnd, int[] in, int inStart, int inEnd) {
		if (inStart > inEnd) {
			return null;
		}
		
		//rootVal是根节点
		int rootVal = pre[preStart];
		int rootIndex = 0;
		//找到根节点下标,rootIndex
		for (int i = inStart; i <= inEnd; i++) {
			if (in[i] == rootVal || preStart > preEnd) {
				rootIndex = i;
				break;
			}
		}
		
		int len = rootIndex - inStart;
		TreeNode root = new TreeNode(rootVal);
		
		//详细解读这里的参数
		//构建左子树的时候，通过左子树的先序和左子树的中序来构建。所以需要传递整个先序序列，以及控制左子树范围的首尾。
		//传递整个中序序列，和确保是左子树范围的首尾下标
		root.left = buildTree(pre, preStart + 1, preStart + len, in, inStart, rootIndex - 1);
		root.right = buildTree(pre, preStart + len + 1, preEnd, in, rootIndex + 1, inEnd);
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
