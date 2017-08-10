package jianzhiOffer;
/**
 * 在两个二叉树中，tree1是否包含tree2.
 * @author chengcheng
 * @time 2017年8月10日 下午3:04:27
 *
 */
public class Tree1HaveTree2 {

	public boolean hasSubTree(TreeNode root1, TreeNode root2){
		//先找到root1和root2
		if(root1 == null || root2 == null)
			return false;
		boolean result = false;
		
		if(root1 != null && root2 != null){
			if(root1.val == root2.val)
				result = DoesTree1HaveTree2(root1, root2);
			if(!result)
				result = hasSubTree(root1.left,root2);
			if(!result)
				result = hasSubTree(root1.right, root2);
		}
		return result;
	}
	
	public boolean DoesTree1HaveTree2(TreeNode root1, TreeNode root2){
		
		//如果root2 == null 返回true
		if(root2 == null)
			return true;
		if(root1 == null)
			return false;
		
		if(root1.val != root2.val)
			return false;
		
		return DoesTree1HaveTree2(root1.left,root2.left) && DoesTree1HaveTree2(root2.right, root2.right);
	}
}
