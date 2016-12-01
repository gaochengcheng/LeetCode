package question41_60;

import org.junit.Test;

/**
 * 
 * @author chengcheng
 * @time 2016年12月1日 下午7:12:22
 *
 */
public class S55_JumpGame {
	//从每个位置往后跳，判断最远能跳到的位置和长度之间的大小关系。
	//如果最远到达的地方>=数组长度 ,说明可以到达。
	//如果最远到达的地方<数组长度,说明不能到达。
	public boolean canJump(int[] nums) {
        int reach = 1;	//最右能跳到哪里
        for(int i = 0; i<reach && reach<nums.length; i++){
        	reach = Math.max(reach, i+1+nums[i]);  //i+nums[i]就表示从当前位置跳nums[i]个能到达的地方。+1是因为下标和长度之间差1.
        }
        return reach >= nums.length;
        
    }
	//从后往前检查,判断能不能到达下标是0的位置。
	public boolean canJump_2(int[] nums){
		int reach = nums.length-1; //最后能跳到哪里
		for(int i = nums.length-2; i >=0; i-- ){
			if(i + nums[i] > reach)
				reach = i;
		}
		return reach == 0;
	}
	
	@Test
	public void tset(){
		
	}
}
