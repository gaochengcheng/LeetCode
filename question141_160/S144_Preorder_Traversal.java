package question141_160;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


public class S144_Preorder_Traversal {
	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.left.right = new TreeNode(4);
		List<Integer> list = PreOrder_2(root);
		System.out.println(list.get(0)+"  "+list.get(1)+"  "+list.get(2)+"  "+list.get(3));
	}
	public static List<Integer> PreOrder(TreeNode root){
		ArrayList<Integer> list = new ArrayList<Integer>();
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode p = root;
		if(p != null)
			stack.push(p);
		
		while(stack.size() != 0){
			TreeNode node = stack.pop();
			list.add(node.val);
			if(node.right != null)
				stack.push(node.right);
			if(node.left != null)
				stack.push(node.left);
		}
		return list;
	}
	
	public static List<Integer> PreOrder_2(TreeNode root){
		ArrayList<Integer> list = new ArrayList<Integer>();
		TreeNode cur = root,pre;
		while(cur != null){
			if(cur.left == null){
				list.add(cur.val);
				cur = cur.right;
			}
			else{
				//find the cur's previous node
				pre = cur.left;
				//refresh  the cur's previous node
				while(pre.right != null && pre.right != cur)
					pre = pre.right;
				if(pre.right == null){
					list.add(cur.val); //找到前驱，先把当前结点访问，再做线索，再访问当前node的left node 
					pre.right = cur;   // 做线索
					cur = cur.left;
				}
				else{
					pre.right = null;
					cur = cur.right;
				}
			}
				
		}
		
		return list;
	}
}
 