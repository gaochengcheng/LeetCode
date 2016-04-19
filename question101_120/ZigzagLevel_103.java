package question101_120;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 *    3
 *   / \ 
 *   9 20 
 *     / \ 
 *     15 7
 * 
 * output: [ [3], [20,9], [15,7]
 * 解题思路：
 * 	  看见了Leetcode给出的参考思路，设置一个标志量。
 * @author chengcheng
 *
 */
public class ZigzagLevel_103 {
	public static void main(String[] args) {
		TreeNode root = new TreeNode(3);
		root.left = new TreeNode(9);
		root.right = new TreeNode(20);
		root.right.left = new TreeNode(15);
		root.right.right = new TreeNode(7);
		List<List<Integer>> list = zigzagLevelOrder(root);
		System.out.println(list);
	}

	public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
		ArrayList<List<Integer>> result = new ArrayList<List<Integer>>();
		LinkedList<TreeNode> current = new LinkedList<TreeNode>();
		LinkedList<TreeNode> next = new LinkedList<TreeNode>();
		ArrayList<Integer> level = new ArrayList<Integer>();
		if(root == null)
			return result;
		current.addLast(root);
		int flag = 1;
		while (!current.isEmpty()) {
			{
				while (!current.isEmpty()) {
					TreeNode node = current.getFirst();
					current.poll();
					level.add(node.val);
					if (node.left != null)
						next.addLast(node.left);
					if (node.right != null)
						next.addLast(node.right);
					}
				if (1 == flag) {
					flag = -flag;
				} else {
					flag = -flag;
					Collections.reverse(level);
				}
				result.add(level);
				level = new ArrayList<Integer>();
				LinkedList<TreeNode> temp = current;
				current = next;
				next = temp;
			}

		}
		return result;
	}
}
