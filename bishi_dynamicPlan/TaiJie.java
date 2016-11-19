package bishi_dynamicPlan;
/**
 * 问题：
 * 		给定整数N，代表台阶数，一次可以跨2个或者1个台阶，返回有多少种走法？	
 * 
 * 
 * @author chengcheng
 * @time 2016年11月15日 下午9:26:04
 *
 */
public class TaiJie {
	//分析：	如果台阶数为1，只有一种走法。
	//		如果台阶数为2，有两种走法，每次走一个台阶，走两步;或者每次走两个台阶，走1步。
	//		如果台阶数为n，最后一步要么从n-1的地方走1步跨过去的，或者从n-2的地方走两步跨过去的。
	public int s1(int n){
		if(n < 1)
			return 0;
		if(n == 1 || n ==2)
			return n;
		return s1(n-1)+s1(n-2);
	}
	
}
