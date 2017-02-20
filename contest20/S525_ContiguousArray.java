package contest20;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

/**
 * solution:
 *   (1)暴力算法，O(n^2)，从数组0下标开始到数组末尾统计最长长度，再从数组1下标开始到数组末尾统计最长长度。
 *      最后得到一个最长子串的长度，代表该字串中0和1的数目相同，并且子串长最长。
 *   (2)使用前缀和数组，把0的位置换成-1，然后求前缀和数组。
 *   	
 * @author chengcheng
 * @time 2017年2月19日 上午11:23:22
 *
 */
public class S525_ContiguousArray {
	public int findMaxLength(int[] nums) {
		
		//生成前缀和，sum[i]保存的是nums[0...i]的和。
		int[] sum = new int[nums.length+1];
		for(int i=0; i<nums.length; i++){
			if(nums[i] == 0){
				sum[i+1] = sum[i]-1;
			} else {
				sum[i+1] = sum[i]+1;
			}
		}
		
		
		Map<Integer,List<Integer>> map = new HashMap<Integer,List<Integer>>();
		for(int i=0; i<sum.length; i++){
			if(!map.containsKey(sum[i])){
				map.put(sum[i], new ArrayList<>());
			}
			map.get(sum[i]).add(i);
		}
		int answer = 0;
		for (List<Integer> list : map.values()) {
			
			//list.get(list.size()-1)  list.size()-1是最后一个元素的下标
            answer = Math.max(answer, list.get(list.size() - 1) - list.get(0));
        }
        return answer;
		
		
    }
	
	@Test
	public void test(){
		int[] a = {1,0,1,0,1,1,1,1,0,0,0,0,1};
		System.out.println(findMaxLength(a));
	}
}
