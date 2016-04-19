package question101_120;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class LevelOrder_107 {
	public static void main(String[] args) {
		TreeNode root = new TreeNode(3);
		root.left = new TreeNode(9);
		root.right = new TreeNode(20);
		root.right.left = new TreeNode(15);
		root.right.right = new TreeNode(7);
		List<List<Integer>> list = levelOrderBottom(root);
		System.out.println(list);
	}
	public static List<List<Integer>> levelOrderBottom(TreeNode root){
		ArrayList<List<Integer>> result = new ArrayList<List<Integer>>();
		LinkedList<TreeNode> current = new LinkedList<TreeNode>();
		LinkedList<TreeNode> next = new LinkedList<TreeNode>();
		ArrayList<Integer> level = new ArrayList<Integer>();
		
		if(root == null)
			return result;
		current.addLast(root);
		while(!current.isEmpty()){
			while(!current.isEmpty()){
				TreeNode node = current.getFirst();
				current.pop();         //移除第一个元素
				level.add(node.val);
				
				if(node.left != null)
					next.addLast(node.left);
				if(node.right != null)
					next.addLast(node.right);
			}
			result.add(level);
			level = new ArrayList<Integer>();
			LinkedList<TreeNode> temp = null;
			temp = current;
			current = next;
			next = temp;
		}
		Collections.reverse(result);
		return result;
	}
}
