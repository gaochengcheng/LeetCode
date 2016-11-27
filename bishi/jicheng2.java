package bishi;
/**
 * 
 * @author chengcheng
 * @time 2016年11月26日 下午12:37:09
 *
 */
public class jicheng2 {
	public static void main(String[] args) {
		System.out.println(jiecheng(5));
	}
	
	public static int jiecheng(int n){
		if(n == 1 )
			return 1;
		
		return n*jiecheng(n-1);
	}
}
