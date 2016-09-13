# LeetCode刷题笔记（动态规划	）

[TOC]

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

