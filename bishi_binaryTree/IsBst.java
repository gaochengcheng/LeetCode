package bishi_binaryTree;

import java.util.Stack;

import org.junit.Test;

/**
 * 判断一颗二叉树是否是二叉搜索树，改写非递归中序遍历，
 * 判断其中是否有降序的序列，如果有则不是二叉搜索树。
 * @author chengcheng
 * @time 2016年10月17日 上午9:28:06
 *
 */
public class IsBst {
	public boolean isBst(Node root){
		if(root == null)
			return true;
		
		Stack<Node> stack = new Stack<Node>();
		
		Node cur = root;
		Node pre = null;
		while(!stack.isEmpty() || cur != null){
			if(cur != null){
				stack.push(cur);
				cur = cur.left;
			}
			else{
				cur = stack.pop();
				if(pre != null && pre.value > cur.value)
					return false;
				System.out.println(cur.value);
				pre = cur;
				cur = cur.right;
			}
			
		}
		return true;
	}
	 
	@Test
	public void test(){
		Node node12 = new Node(12);
		
		Node node10 = node12.left = new Node(10);
		Node node16 = node12.right = new Node(16);
		
		Node node4 = node10.left = new Node(4);
//		
		
		Node node13 = node16.left = new Node(13);
		Node node20 = node16.right = new Node(20);
		
		node4.left = new Node(2);
		node4.right = new Node(5);
		

		
		System.out.println(isBst(node12));
	}
}
