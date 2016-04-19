package question81_100;
/**
 * Recover Binary Search Tree
 * Two elements of a binary search tree (BST) are swapped by mistake.
   Recover the tree without changing its structure.
	
 * @author chengcheng
 *
 */
public class BinarySearchTree_99 {
	TreeNode pre;
	TreeNode first;
	TreeNode second;
	public static void main(String[] args) {
		
	}
	public void inorder(TreeNode root){
		if(root == null)
			return ;
		inorder(root.left);
		if(pre == null){
			pre = root; //pre initial pointer
		}else{
			if(pre.val > root.val){
				if(first == null){
					first = pre;
				}
			}
		}
	}
	public static void recoverTree(TreeNode root){
		
	}
}
