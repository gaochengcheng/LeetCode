package question121_140;

import org.junit.Test;

/**
 * 
 * @author chengcheng
 * @time 2016年8月10日 上午8:30:55
 *
 */
public class S134_GasStation {
	
	public int canCompleteCircuit(int[] gas, int[] cost){
		int length = gas.length;
		int total = 0;
		int sum = 0;
		int j = 0;
		for(int i = 0; i < length; i++){
			sum += (gas[i] - cost[i]);
			total += (gas[i] - cost[i]);
			//只要第一次出现gas[i]-cost[i]>0，那么i就可以作为start。
			//并且此后一直用sum做判断，是否一直满足sum>0。一旦出现一次sum<0,重新修改start的值。
			if(sum < 0){  
				j = i + 1;
				sum = 0;
			}
		}
		if(total >= 0)
			return j;
		else
			return -1;
	}
	
	//这个方法很容易想到，但是复杂度是O(n^2)。超时！
	public int canCompleteCircuit_2(int[] gas, int[] cost) {
		int length = gas.length;
		int remain = 0;
		for(int startIndex = 0; startIndex < length; startIndex++){   //把每个点作为开始点进行判断
			int positions = 1;
			int index = startIndex;
			while(remain+gas[index]-cost[index] >= 0){   //
				if(positions == length)
					return startIndex;
				positions++;
				remain = remain + gas[index] - cost[index];
				index = (index+1) % length;
			}
		}
		return -1;
	}
	
	
	@Test
	public void test() {
		int[] gas = {5};
		int[] cost = {4};
		System.out.println(canCompleteCircuit(gas,cost));
	}
}
