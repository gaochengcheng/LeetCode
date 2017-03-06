package contest20;

import org.junit.Test;

/**
 * 又是一道基于数组的题目。
 * solution:
 *     
 * 
 * @author chengcheng
 * @time 2017年2月19日 下午12:03:12
 *
 */
public class S517_SuperWashingMachines {
	public int findMinMoves(int[] machines) {
		int[] count = new int[machines.length];  //这个数组记录每个位置需要移动的次数
        int sum = 0;
        for (int machine : machines) {
            sum += machine;
        }
        if (sum % machines.length != 0) {
            return -1;
        }
        int target = sum / machines.length;
        
        for (int i = 0; i < machines.length - 1; i++) {
            if (machines[i] > target) {
            	//当前数字>target，由于每次只能+-1，所以当前值要想成为target，需要移动的次数是(machines[i]-target)+count[i]
                count[i] += machines[i] - target;  //记录需要移动的次数
                machines[i + 1] += machines[i] - target;
                machines[i] = target;
            } else {
                count[i + 1] += target - machines[i];
                machines[i + 1] -= target - machines[i];
                machines[i] = target;
            }
            for(int j : machines)
            	System.out.print(j+ "  ");
            System.out.println();
        }
        int answer = 0;
        for (int i : count) {
        	System.out.println(i);
            answer = Math.max(answer, i);
        }
        return answer;
    }
	@Test
	public void test(){
		int[] a = {1,0,5};
		System.out.println(findMinMoves(a));
	}
}
