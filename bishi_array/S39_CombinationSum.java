package bishi_array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/**
 * 
 * @author chengcheng
 * @time 2016年9月25日 下午4:08:13
 *
 */
public class S39_CombinationSum {
	public List<List<Integer>> combinationSum(int[] candidates, int target) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		if(candidates == null || candidates.length == 0)
			return res;
		Arrays.sort(candidates);
		dfs(0, target, new ArrayList<Integer>(), candidates, res);
		return res;
		
    }
	
	public void dfs(int start, int target, List<Integer> contains, int[] candidates, List<List<Integer>> res){
		
		if(target == 0){
			System.out.println(contains);
			if(!res.contains(contains))
				res.add(new ArrayList<Integer>(contains));
			return ;
		}
		if( target < 0){
			return ;
		}
		if (target > 0) {
			for (int i = start; i < candidates.length; i++) {
				if(i > 0 && candidates[i] == candidates[i-1])
					continue;   //排除重复元素
				int newtarget = target - candidates[i];
				contains.add(candidates[i]);
				dfs(i, newtarget, contains, candidates, res);
				contains.remove(contains.size()-1);
			
			}
		}
			
	}
	
	
	@Test
	public void test(){
		int[] candidates = {2,3,6,7};
		System.out.println(combinationSum(candidates,7));
	}
}
