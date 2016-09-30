# LeetCode刷题笔记（动态规划	）

[TOC]

# 二分法

## S.50_Pow(x, n)

原题地址：https://leetcode.com/problems/powx-n/

题目：

>Implement pow(*x*, *n*).

思路：

​	pow(x,n)就是求x的n次方。x的N次方可以看做：x^n = x^(n/2)*x^(n/2)*x^(n%2)。所以利用递归求解，当n==1的时候，x^n=x。

​	当然n是可以小于0的，2^(-3) = 1/(2^3)。按照上面那个规律就可以解决了。

​	**重点：**写递归程序，就不能死盯着一步，要看到整体。看到问题是如何被分解的。

​	如果n是偶数，那么`x^n = x^(n/2)*x^(n/2)*`即可。

​	如果n是奇数，那么`x^n = x^(n/2)*x^(n/2)*x^(n%2)`即可。

​	所以在写递归的时候，找到收敛的条件，就是`n==0`，然后就是不断处理指数部分即可。

代码：

```java
public double myPow(double x, int n) {
		if(n < 0) 
			return 1.0 / power(x, -n);
		else
			return power(x, n);
    }
	
	public double power(double x, int n){
		if(n == 0)
			return 1;
		double v = power(x, n/2);
		if( n % 2 == 0)
			return v * v;
		else 
			return v * v * x;
	}
```

## S.69_Sqrt(x)

原题地址：https://leetcode.com/problems/sqrtx/

题目：

>Implement `int sqrt(int x)`.
>
>Compute and return the square root of *x*.

思路：

- 这是一道开平方根的题目,运用二分查找的思想去定位到一个更小的数a，然后通过判断a*a和x的大小关系，进而确定sqrt(x)是多少。

* 其实这道题目的解法，你同样可以通过逐一减小x的值得到a，然后再通过a*a和x的大小关系，确定x的开方根究竟是多少。
* 只不过，逐一减小相比于折半而言，太慢了。

 代码：

```java
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
```

# 动态规划

## S.198_House Robber

原题地址：https://leetcode.com/problems/house-robber/

思路：

> 有一排房子，窃贼要进去偷东西，要求是不能同时偷相邻两间房子，问在这样的方式下，窃贼最后偷到的最大值是多少。

​	每个阶段只有一个状态，使用递推。

​	题目要求相邻的两个不能同时取，同时要求和最大，那采用的方式隔一个取一个了。

​	`dp[i] `表示在第i的位置，取得的和是多少。而`dp[i] = max(dp[i-2]+nums[i],dp[i-1])`，所以只要对初始状态进行初始化，然后迭代所有的nums[i],最后就可以使和最大。

代码：空间复杂度	`O(n)`.

```java
public int rob(int[] nums) {
		if(nums == null || nums.length == 0)
			return 0;
		if(nums.length == 1)
			return nums[0];
		
		int[] dp = new int[nums.length];
		dp[0] = nums[0];
		dp[1] = nums[1];
		dp[1] = Math.max(dp[0], dp[1]);  //其实取的方式有两种，要么从0位置开始，要么从1位置开始，我们选其中较大的那个么。
		for(int i = 2; i < nums.length; i++)
			dp[i] = Math.max(dp[i-2]+nums[i],dp[i-1]);
		
		return dp[nums.length-1];
        
    }
```

空间复杂度：O(1).不用开一个和输入数据一样大小的数组。

```java
// O(1)空间的写法
    public int rob(int[] num) {
        if (num.length == 0) return 0;
        int prepre = 0;
        int pre = num[0];
        for (int i = 1; i < num.length; i++) {
            int cur = Math.max(prepre + num[i], pre);
            prepre = pre;
            pre = cur;
        }
        return pre;
    }
```



## S.213_House Robber II

原题地址：https://leetcode.com/problems/house-robber-ii/

思路：

>与第一题的区别在于，这次房子围城了一个圈。第一间房子是最后一间房子的邻居。问这次，窃贼最后盗窃到的最大和是多少？

- 解决这个问题的关键：

  最优解就变成 第1座房子到第n-1座房子能抢的最多的钱 或者 第2座房子到第n座房子能抢的钱了。

代码：

``` java
public int rob(int[] nums) {
		
		if(nums == null || nums.length == 0)
			return 0;
		
		if(nums.length == 1)
			return nums[0];
		
		int length = nums.length;
		//最优解就变成 第1座房子到第n-1座房子能抢的最多的钱 或者 第2座房子到第n座房子能抢的钱了
		return Math.max(robBetween(nums, 0, length-2),robBetween(nums, 1, length-1));
	}
	
	public int robBetween(int[] nums, int start, int end){
		int prepre = 0;
		int pre = nums[start];
		
		for(int i = start+1; i <= end; i++){
			int cur = Math.max(prepre+nums[i],pre);
			prepre = pre;
			pre = cur;
		}
		
		return pre;
		
	}
```

## S.337_House Robber III

原题地址：https://leetcode.com/problems/house-robber-iii/

思路：

