package question61_80;

import org.junit.Test;

/**
 * 这是一道开平方根的题目,运用二分查找的思想去定位到一个更小的数a，然后通过判断a*a和x的大小关系，进而确定sqrt(x)是多少。
 * 其实这道题目的解法，你同样可以通过逐一减小x的值得到a，然后再通过a*a和x的大小关系，确定x的开方根究竟是多少。
 * 只不过，逐一减小相比于折半而言，太慢了。
 * @author chengcheng
 * @time 2016年9月28日 下午2:04:55
 *
 */
public class S69_Sqrt_x {
	public int mySqrt(int x) {
        int low = 0;
        int high = x;
        while(low <= high){
        	long mid = (long)(low + high) / 2;
        	System.out.println(mid);
        	if(mid * mid < x)
        		low = (int)mid+1;
        	else if(mid * mid > x)
        		high = (int)mid - 1;
        	else
        		return (int)mid;
        }
		
		return high;
    }
	
	@Test
	public void test(){
		System.out.println(mySqrt(17));
	}
}
