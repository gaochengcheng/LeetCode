package question101_120;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
/**
 *  3
   / \
  9  20
    /  \
   15   7
   
   output:
   [
  [3],
  [9,20],
  [15,7]
	]
	实现思路：
		使用了两个TreeNode类型的list，相互迭代式地交替每一层结点。
	
 * @author chengcheng
 *
 */


public class S102_LevelOrder {
	
	public static void main(String[] args) {
		TreeNode root = new TreeNode(3);
		root.left = new TreeNode(9);
		root.right = new TreeNode(20);
		root.right.left = new TreeNode(15);
		root.right.right = new TreeNode(7);
		List<List<Integer>> list = LevelOrder_1(root);
		System.out.println(list);
	}
	
	public static List<List<Integer>> LevelOrder_1(TreeNode root){
		ArrayList<List<Integer>> result = new ArrayList<List<Integer>>();
		if(root == null)
			return result;
		//为什么使用LinkedList，因为它有在末尾添加元素，从头部取出元素的函数。
		LinkedList<TreeNode> current = new LinkedList<TreeNode>(),next = new LinkedList<TreeNode>();
		ArrayList<Integer> level = new ArrayList<Integer>();
		
		
		
		current.push(root);
		while(!current.isEmpty()){
			while(!current.isEmpty()){
				TreeNode node = current.getFirst();
				current.pop();
				level.add(node.val);
				if(node.left != null){
					next.addLast(node.left);
				}
					
				if(node.right != null){
					next.addLast(node.right);
					
				}
			}
			result.add(level);
//			level.clear();           //  这种写法在这里不对，因为在同一块内存区域上，把之前的值抹去后，用后面的值填写了之前的位置
			level = new ArrayList<Integer>();     //解决方法是从新new出空间来
			LinkedList<TreeNode> temp = null;
			temp = current;
			current = next;
			next = temp;
			
		}
	
		return result;
	}
}
