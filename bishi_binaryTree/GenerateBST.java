package bishi_binaryTree;
/**
 * 通过一个递增序列，生成一颗二叉排序树
 * 
 * 1.使用递归写法，取出序列的中间值，作为这棵树的根节点。
 * 2.递归的构建根节点的左子树和右子树，直到这颗树完全生成好。
 * @author chengcheng
 * @time 2016年10月17日 下午5:07:19
 *
 */
public class GenerateBST {
	public Node generateBST(int[] sortedArr){
		if(sortedArr == null){
			return null;
		}
		return generate(sortedArr, 0, sortedArr.length-1);
	}
	
	
	public Node generate(int[] sortedArr, int start, int end){
		if(start > end)
			return null;
		
		int mid = (start + end) / 2;
		Node root = new Node(sortedArr[mid]);
		root.left = generate(sortedArr, 0, mid-1);
		root.right = generate(sortedArr, mid+1, end);
		return root;
	}
	
	
	
}
