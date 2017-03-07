package bishi_binaryTree;


/**
 * 
 * @author chengcheng
 * @time 2016年8月26日 上午11:44:39
 *
 */
public class S108_ConvertSortedArraytoBinarySearchTree {
	public TreeNode sortedArrayToBST(int[] nums) {
		return createTree(nums, 0, nums.length-1);
	}
	
	//使用递归的方式构建这个平衡二叉查找树,每次让中点作为根节点
	public TreeNode createTree(int[] nums, int low, int high){
		
		//递归基是什么？什么时候停止呢？
		if(low < high)
			return null;
		
		
		int mid = (low+high)/2;
		TreeNode root = new TreeNode(nums[mid]);
		root.left = createTree(nums, low, mid-1);
		root.right = createTree(nums, mid+1, high);
		
		return root;
	}
}
