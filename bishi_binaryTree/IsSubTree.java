package bishi_binaryTree;

public class IsSubTree {
	
	public boolean isSubTree(TreeNode root1, TreeNode root2){
		
		boolean res = false;
		
		while(root1 != null	&& root2 != null){
			if(root1.val == root2.val)
				res = DoesTree1HaveTree2(root1, root2);
			//！res 作为判断条件使用得很巧妙
			if(!res)
				isSubTree(root1.left, root2);
			if(!res)
				isSubTree(root1.right, root2);
			
		}
		return res;
	}
	
	public boolean DoesTree1HaveTree2(TreeNode root1, TreeNode root2){
		//当遍历完root2所有结点后，root2为null，此时返回true
		if(root2 == null)
			return true;
		
		if(root1 == null)
			return false;
		
		if(root1.val != root2.val)
			return false;
		
		return DoesTree1HaveTree2(root1.left, root2.left) &&
				DoesTree1HaveTree2(root1.right, root2.right);
	}
	
}
