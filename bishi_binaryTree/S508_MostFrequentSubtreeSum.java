package bishi_binaryTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 这道题目的关键在于，使用后续遍历的方式，对每个结点求和。left+right+current.val,然后把求得的值以及该
 * 值出现的次数放到map中。并且更新全局变量max。
 * 
 * 最后对map做遍历，只要map中某个key出现的次数达到max，就把该key放入最后的数组中。
 * 
 * @author chengcheng
 * @time 2017年3月6日 下午8:55:19
 *
 */
public class S508_MostFrequentSubtreeSum {
	Map<Integer, Integer> map = new HashMap<>();
	int max;
	
	public int[] findFrequentTreeSum(TreeNode root) {
		max = 0;
		postOrder(root);
		List<Integer> res = new ArrayList<>();
		for(int key:map.keySet()){
			if(map.get(key) == max)
				res.add(key);
		}
		int[] result = new int[res.size()]; 
		for(int i=0; i<res.size(); i++){
			result[i] = res.get(i);
		}
		return result;
    }
	
	public int postOrder(TreeNode root){
		if(root == null)
			return 0;
		
		int left = postOrder(root.left);
		int right = postOrder(root.right);
		int sum = left+right+root.val;
		int count = map.getOrDefault(sum, 0)+1;
		map.put(sum, count);
		
		max = Math.max(max, count);
		
		return sum;
	}
}
