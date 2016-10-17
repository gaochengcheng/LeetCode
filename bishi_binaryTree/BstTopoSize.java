package bishi_binaryTree;

import org.junit.Test;

/**
 * 返回一颗二叉树的最大拓扑结构
 * @author chengcheng
 * @time 2016年10月13日 下午2:49:11
 *
 */
public class BstTopoSize {
	
	public int bstTopoSize1(Node head){
		if(head == null)
			return 0;
		//采用递归的方式，针对每一个结点，在条件1的情况下，找到每个结点的最大拓扑结构然后返回
		int max = maxTopo(head, head);
		System.out.println(max);
		
		max = Math.max(bstTopoSize1(head.left), max);
		max = Math.max(bstTopoSize1(head.right), max);
		return max;
	}
	
	//条件1：以h为头结点，并且在拓扑结构中也必须以h为头结点。
	//在这样的条件下，找到最大的拓扑结构数。
	public int maxTopo(Node h, Node n){
		if(h != null && n != null && isBSTNode(h, n, n.value)){
			return maxTopo(h, n.left) + maxTopo(h, n.right) + 1;
		}
		return 0;
	}
	
	
	//按照在二叉搜索树中使用递归的方法，查找结点n.
	//如果找到 return true,如果没有找到 return false;
	public boolean isBSTNode(Node h, Node n, int value){
		if(h == null)
			return false;
		if(h == n)  //n是待找的结点，h是查找过程中不断变化的结点，只有最后h==n,才表明是找到了
			return true;
		
		return isBSTNode(h.value > value ? h.left : h.right, n, value);
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
		
		
		System.out.println(bstTopoSize1(node6));
		
		
	}
}

