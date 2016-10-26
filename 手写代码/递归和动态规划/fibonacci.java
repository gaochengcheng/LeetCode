// 给定数字N，求fibonacci序列的第N项是多少。



class Test{

	public static void main(String[] args){
		int n = 6;
		System.out.println(f2(n));
	}
	public int f1(int n){
		if(n < 1)
			return 0;
		if(n == 1 || n == 2)
			return 1;

		return f1(n-1) + f1(n-2);
	}
//方法一：递归，时间复杂度O(2^N)
//方法二：使用递推的方法，时间复杂度O(N)
// 1     1     2     3      5
// pre  res
//      pre   res
//            pre   res
	public static int f2(int n){
		if(n < 1)
			return 0;
		if(n == 1 || n == 2)
			return 1;
		int pre = 1;
		int res = 1;
		int tmp = 0;
		for(int i = 3; i <= n; i++){
			tmp = res;
			res = pre + res;
			pre = tmp;
		}
		return res;
	}


}