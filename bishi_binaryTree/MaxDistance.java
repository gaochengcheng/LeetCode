package bishi_binaryTree;

import org.junit.Test;



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
	
	public int maxPathSum(Node head){
		int[] record = new int[1];
		return posOrder_2(head, record);
	}
	
	public int posOrder_2(Node head, int[] record){
		if(head == null){
			record[0] = 0;
			return 0;
		}
		int lMax = posOrder_2(head.left, record);
		int maxFromLeft = record[0];
		int rMax = posOrder_2(head.right, record);
		int maxFromRight = record[0];
		int curNodeMax = maxFromLeft + maxFromRight + head.value;
		record[0] = Math.max(maxFromLeft, maxFromRight) + head.value;
		return Math.max(Math.max(lMax, rMax), curNodeMax);
		
	}
	
	
	@Test
	public void test(){
		Node node1 = new Node(5);
		Node node2 = node1.left = new Node(4);
		Node node3 = node1.right = new Node(8);
		Node node4 = node2.left = new Node(11);
		node4.left = new Node(7);
		node4.right = new Node(2);
		node3.left = new Node(13);
		Node node5 = node3.right = new Node(4);
		node5.left = new Node(5);
		node5.right = new Node(1);
		
		System.out.println(maxPathSum(node1));
	}
}
