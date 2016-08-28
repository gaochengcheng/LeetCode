package question101_120;
/**
 * 
 * @author chengcheng
 * @time 2016年8月28日 下午9:44:47
 *
 */
public class S112_PathSum {
	public boolean hasPathSum(TreeNode root, int sum) {
		if(root == null)
			return false;
		
		sum = sum-root.val;
		if(root.left == null && root.right == null)
			//当root.left==null,root.right==null的时候，说明这个节点是根节点。
			//并且这个时候如果sum的和也是0，那就说明找到一条路径，他的和是0.
			return sum == 0;   
		return hasPathSum(root.left,sum) || hasPathSum(root.right,sum);
		
	}
	
	
	
	
}
