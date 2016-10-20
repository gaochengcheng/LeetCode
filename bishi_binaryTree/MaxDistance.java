package bishi_binaryTree;
/**
 * 
 * @author chengcheng
 * @time 2016年10月20日 下午1:57:45
 *
 */
public class MaxDistance {
	public int maxDistance(Node head){
		int[] record = new int[1];
		return posOrder(head, record);
	}
	
	public int posOrder(Node head, int[] record){
		if(head == null){
			record[0] = 0;
			return 0;
		}
		
		int lMax = posOrder(head.left, record);    // lMax记录左子树上(左孩子上)最大距离值
		int maxFromLeft = record[0];        // 左子树上，距离head做孩子最远距离值
		
		int rMax = posOrder(head.right, record);   //  rMax记录右子树上(右孩子上)最大距离值
		int maxFromRight = record[0];
		int curNodeMax = maxFromLeft + 1 + maxFromRight;   //head结点(当前结点)上的最大距离值
		record[0] = Math.max(maxFromLeft, maxFromRight) + 1;
		return Math.max(Math.max(lMax, rMax),curNodeMax);
	}
}
