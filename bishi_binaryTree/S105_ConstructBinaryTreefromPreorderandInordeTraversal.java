package bishi_binaryTree;

public class S105_ConstructBinaryTreefromPreorderandInordeTraversal {
	
	public TreeNode buildTree(int[] preorder, int[] inorder) {

		int preLength = preorder.length;
		int inLength = inorder.length;
		return buildTree(preorder, 0, preLength - 1, inorder, 0, inLength - 1);

	}

	public TreeNode buildTree(int[] pre, int preStart, int preEnd, int[] in, int inStart, int inEnd) {
		if (inStart > inEnd || preStart > preEnd) {
			return null;
		}

		// rootVal是根节点
		int rootVal = pre[preStart];
		int rootIndex = 0;
		// 在中序数组中找到根节点下标,rootIndex
		for (int i = inStart; i <= inEnd; i++) {
			if (in[i] == rootVal) {
				rootIndex = i;
				break;
			}
		}

		int len = rootIndex - inStart;
		TreeNode root = new TreeNode(rootVal);

		// 详细解读这里的参数
		// 构建左子树的时候，通过左子树的先序和左子树的中序来构建。所以需要传递整个先序序列，以及控制左子树范围的首尾下标。
		// 传递整个中序序列，和确保是左子树范围的首尾下标
		root.left = buildTree(pre, preStart + 1, preStart + len, in, inStart, rootIndex - 1);
		root.right = buildTree(pre, preStart + len + 1, preEnd, in, rootIndex + 1, inEnd);
		return root;
	}
}
