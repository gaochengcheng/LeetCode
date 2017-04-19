package bishi_array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/**
 * 
 * @author chengcheng
 * @time 2017年3月10日 下午3:57:23
 *
 */
public class S90_SubsetsII {
	public List<List<Integer>> subsetsWithDup(int[] nums) {
		
		List<List<Integer>> res = new ArrayList<List<Integer>> ();
		ArrayList<Integer> item = new ArrayList<>();
		if(nums == null || nums.length<=0)
			return res;
		
		Arrays.sort(nums);
		for(int i=0; i<=nums.length; i++){
			dfs(nums, 0, 2, item, res);
		}
		return res;
		
    }
	/**
	 * 对于像深度优先这样的题目，做到以下几点：
	 * 1.递归结束的条件。
	 * 2.控制好起始下标
	 * 3.回溯的时候移除元素
	 * @param nums
	 * @param start
	 * @param length
	 * @param item
	 * @param res
	 */
	public void dfs(int[] nums, int start, int length, List<Integer> item, List<List<Integer>> res){
		System.out.println(item.toString());
		if(item.size() == length){   //递归结束条件
			if(!res.contains(item)){
				res.add(new ArrayList<Integer>(item));
				return ;
			}
		}
		
		for(int i=start; i<nums.length; i++){
			item.add(nums[i]);
			dfs(nums, i+1, length, item, res);
			item.remove(item.size()-1);     //回溯时候移除元素
		}
	}
	
	@Test
	public void test(){
		int[] arr = {1,2,3};
		System.out.println(subsetsWithDup(arr));
	}
}
