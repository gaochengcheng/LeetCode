package contest19;

import java.util.ArrayList;
import java.util.LinkedList;

import org.junit.Test;


/**
 * 
 * @author chengcheng
 * @time 2017年2月12日 上午10:52:11
 *
 */
public class S515_FindLargestElementinEachRow {
	public int[] findValueMostElement(TreeNode root) {
		if(root == null)
			return new int[0];
		
		ArrayList<Integer> res = new ArrayList<Integer>();
		LinkedList<TreeNode> cur = new LinkedList<TreeNode>();
		LinkedList<TreeNode> next = new LinkedList<TreeNode>();
		
		TreeNode start = root;
		cur.add(start);
		int max = Integer.MIN_VALUE;
		
		while(cur.size() != 0 || next.size() != 0){
			
			while(cur.size() != 0){
				TreeNode node = cur.getFirst();
				int val = node.val;
				if(val > max)
					max = val;
				if(node.left != null)
					next.addLast(node.left);
				if(node.right != null)
					next.addLast(node.right);
				cur.remove();  //移除第一个元素
			}
			res.add(max);
			max = Integer.MIN_VALUE;
			cur = next;
			next = new LinkedList<TreeNode>();
		}
		
		int[] array = res.stream().mapToInt(i->i).toArray();
		return array;
    }
	@Test
	public void test(){
		TreeNode node1 = new TreeNode(1);
		node1.left = new TreeNode(3);
		node1.right = new TreeNode(2);
		node1.left.left = new TreeNode(5);
		node1.left.right = new TreeNode(3);
		node1.right.right = new TreeNode(9);
		int[] arr = findValueMostElement(node1);
		for(int val : arr){
			System.out.println(val);
		}
	}
}
