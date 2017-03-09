package bishi_array;
/**
 * 字数组最大累加和
 * 
 * 
 * @author chengcheng
 * @time 2017年3月9日 下午9:11:04
 *
 */
public class MaxSum {
	public int solution(int[] arr){
		if(arr==null || arr.length == 0)
			return 0;
		
		int cur = 0;
		int max = Integer.MIN_VALUE;
		for(int i=0; i<arr.length; i++){
			cur += arr[i];    //记录当前累加和
			max = Math.max(cur, max);
			cur = cur < 0? 0 : cur;
		}
		
		return max;
	}
}
