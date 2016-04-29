package question101_120;

import java.util.Stack;

/**
      * 1
	   / \
	  2   2
	 / \ / \
	3  4 4  3
		
		1
	   / \
	  2   2
	   \   \
	   3    3
 * @author chengcheng
 *
 */
public class S101_SymmetricTree {
	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(2);
		root.left.left = new TreeNode(3);
		root.left.right = new TreeNode(4);
//		root.right.left = new TreeNode(null);
		root.right.right = new TreeNode(3);
		
		boolean flat = isSymmetric(root);
		System.out.println(flat);
	}
	
	public static boolean isSymmetric(TreeNode root) {
		Stack<TreeNode> stack = new Stack<TreeNode>();
		if(root == null)
			return true;
		
		stack.push(root.left);
		stack.push(root.right);
		while(!stack.isEmpty()){
			TreeNode p = stack.pop();
			TreeNode q = stack.pop();
			//写条件的时候，要保证所有的能遍历到不对称的部分。
			//每个叶子节点的左右孩子都是null，如果仅仅遍历到这样的null就返回true，就会造成错误。
			//所以，要使用continue
			if(p != null & q!= null){ 
				if(p.val != q.val)
					return false;
					stack.push(p.left);
					stack.push(q.right);

					stack.push(p.right);
					stack.push(q.left);
				
			}else if(p == null && q == null){
				continue;       //妥善利用好continue
			}else{
				return false;
			}
			
			
			
		}
		
		return true;
	}
}
