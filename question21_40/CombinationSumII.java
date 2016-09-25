package question21_40;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/**
 * 
 * @author chengcheng
 * @time 2016年9月25日 下午7:50:01
 *
 */
public class CombinationSumII {
	public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(candidates == null || candidates.length == 0)
        	return res;
        
        Arrays.sort(candidates);
        dfs(0, target, new ArrayList<Integer>(), candidates, res);
        
        return res;
    }
	
	public void dfs(int start, int target, ArrayList<Integer> item, int[] candidates, List<List<Integer>> res){
		if(target == 0){
			if(!res.contains(item))
				res.add(new ArrayList<Integer>(item));
			return;
		}
		if(target < 0){
			return ;
		}
		if(target > 0){
			for(int i = start; i < candidates.length; i++){
				int newtarget = target - candidates[i];
				item.add(candidates[i]);
				dfs(i+1, newtarget, item, candidates, res);
				item.remove(item.size()-1);
			}
		}
	}
	
	@Test
	public void test(){
		int[] candidates = {10, 1, 2, 7, 6, 1, 5};
		System.out.println(combinationSum2(candidates,8));
	}
}
