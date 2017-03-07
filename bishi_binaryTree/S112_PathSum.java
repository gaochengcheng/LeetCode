package bishi_binaryTree;

import java.util.LinkedList;

import org.junit.Test;


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
	
	public boolean hasPathSum_2(TreeNode root, int sum){
		if(root == null)
			return false;
		
		
		LinkedList<TreeNode> nodes = new LinkedList<TreeNode>();
        LinkedList<Integer> values = new LinkedList<Integer>();
 
        nodes.add(root);
        values.add(root.val);
 
        while(!nodes.isEmpty()){
            TreeNode curr = nodes.poll();
            int sumValue = values.poll();
 
            if(curr.left == null && curr.right == null && sumValue==sum){
                return true;
            }
 
            if(curr.left != null){
                nodes.add(curr.left);
                values.add(sumValue+curr.left.val);
            }
 
            if(curr.right != null){
                nodes.add(curr.right);
                values.add(sumValue+curr.right.val);
            }
            
        }
 
        return false;
		
	}
	@Test
	public void test(){
		TreeNode root = new TreeNode(3);
		root.left = new TreeNode(9);
		root.right = new TreeNode(20);
		root.right.left = new TreeNode(15);
		root.right.right = new TreeNode(7);
		System.out.println(hasPathSum_2(root,38));
	}
	
}
