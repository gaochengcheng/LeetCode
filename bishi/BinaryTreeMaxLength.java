package bishi;

import java.util.HashMap;

import org.junit.Test;

/**
 * 
 * @author chengcheng
 * @time 2016年10月12日 上午8:49:16
 *
 */
public class BinaryTreeMaxLength {
	public int getMaxLength(Node head, int sum){
		HashMap<Integer,Integer> sumMap = new HashMap<Integer,Integer>();
		sumMap.put(0, 0);
		return preOrder(head, sum, 0, 1, 0, sumMap);
	}
	
	public int preOrder(Node head, int sum, int preSum, int level,
			int maxLen, HashMap<Integer,Integer> sumMap){
		if(head == null)
			return maxLen;
		
		int curSum = preSum + head.val;
		if(!sumMap.containsKey(curSum))
			sumMap.put(curSum, level);
		
		if(sumMap.containsKey(curSum-sum))
			maxLen = Math.max(level - sumMap.get(curSum - sum), maxLen);
		
		maxLen = preOrder(head.left, sum, curSum, level+1, maxLen, sumMap);
		maxLen = preOrder(head.right, sum, curSum,level+1, maxLen, sumMap);
		System.out.print(sumMap+ "    ");
		if(level == sumMap.get(curSum)){ 
			sumMap.remove(curSum);
			System.out.print(sumMap);
		}
		System.out.println();
		
		return maxLen;
	}

	
	@Test
	public void test(){
		Node node1 = new Node(-3);
		
		Node node2 = node1.left = new Node(3);
		Node node3 = node1.right = new Node(-9);
		
		node2.left = new Node(1);
		Node node4 = node2.right = new Node(0);
		
		node4.left = new Node(1);
		node4.right = new Node(6);
		
		node3.left = new Node(2);
		node3.right = new Node(1);
		
		System.out.println(getMaxLength(node1,6));
	}
}

class Node{
	public int val;
	public Node left;
	public Node right;
	public Node(int data){
		this.val = data;
	}
}

