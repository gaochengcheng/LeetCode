package question41_60;

import org.junit.Test;

/**
 * 
 * @author chengcheng
 * @time 2016年12月1日 下午7:12:22
 *
 */
public class S55_JumpGame {
	public boolean canJump(int[] nums) {
        int reach = 1;	//最右能跳到哪里
        for(int i = 0; i<reach && reach<nums.length; i++){
        	reach = Math.max(reach, i+1+nums[i]);  //i+nums[i]就表示从当前位置跳nums[i]个能到达的地方。+1是因为下标和长度之间差1.
        }
        return reach >= nums.length;
    }
	
	@Test
	public void tset(){
		
	}
}
