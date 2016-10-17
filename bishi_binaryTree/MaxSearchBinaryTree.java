package bishi_binaryTree;

import org.junit.Test;

public class MaxSearchBinaryTree {
	public Node biggestSubBST(Node head){
		int[] record = new int[3];
		return posOrder(head, record);
	}
	
	//后续遍历的方式
	public Node posOrder(Node head, int[] record){
		if(head == null){
			record[0] = 0;
			record[1] = Integer.MAX_VALUE;
			record[2] = Integer.MIN_VALUE;
			return null;
		}
		
		int value = head.value;
		Node left = head.left;
		Node right = head.right;
		
		Node lBST = posOrder(left, record); //左子树头结点
		int lSize = record[0];  //左子树的节点数
		int lMin = record[1];   //左子树最小值
		int lMax = record[2];   //左子树最大值
		
		Node rBST = posOrder(right, record);
		int rSize = record[0];
		int rMin = record[1];
		int rMax = record[2];
		
		record[1] = Math.min(lMin, value);
		record[2] = Math.max(rMax, value);
		
		
		
		if(left == lBST && right == rBST && lMax < value && value < rMin){
			record[0] = lSize + rSize +1;
			for(int ele: record){
				System.err.print(ele + "  ");
			}
			System.out.print( lMax + " "+ rMin);
			System.out.println();
			return head;
		}
		
		record[0] = Math.max(lSize, rSize);
		for(int ele: record){
			System.err.print(ele + "  ");
		}
		System.out.print( lMax + " "+ rMin);
		System.out.println();
		return lSize > rSize ? lBST : rBST;
		
	}
	@Test
	public void test(){
		Node node6 = new Node(6);
		Node node1 = node6.left = new Node(1); 
		Node node12 = node6.right = new Node(12);
		
		Node node0 = node1.left = new Node(0);
		Node node3 = node1.right = new Node(3);
		
		Node node10 = node12.left = new Node(10);
		Node node13 = node12.right = new Node(13);
		
		Node node4 = node10.left = new Node(4);
		Node node14 = node10.right = new Node(14);
		
		Node node20 = node13.left = new Node(20);
		Node node16 = node13.right = new Node(16);
		
		node4.left = new Node(2);
		node4.right = new Node(5);
		
		node14.left = new Node(11);
		node14.right = new Node(15);
		
		
		System.out.println(biggestSubBST(node12).value);
		
		
	}
}

class Node{
	public int value;
	public Node left;
	public Node right;
	 
	public Node(int data){
		this.value = data;
	}
}
