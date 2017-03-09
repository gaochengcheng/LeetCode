# Leetcode刷题笔记（数组部分）



[TOC]

## S.33_Search in Rotated Sorted Array

## S.1_Two Sum

原题地址：

https://leetcode.com/problems/two-sum/

**思路1**：

​	使用hash表，假设a+b=target。

1. 遍历元素，假设每次遍历到的元素是a，如果map中不包含这个target-a，则把target-a作为key进行存储，当前下标作为value进行存储。
2. 由于存储的是差值，当下次再遍历到该这个key的时候，就证明我们已经找到了这两个元素。
3. 存储两次的下标就行。

**代码**：时间复杂度O(n)

```java
public static int[] test(int[] nums, int target){
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		int[] result = new int[2];
	 
		for(int i = 0; i < nums.length; i++){
			if(!map.containsKey(nums[i]))
				map.put(target-nums[i], i);   //把差值作为key存储
			else{
				int index = map.get(nums[i]);  //找到这个key时，就说明找到了两个数
				result[0] = index;
				result[1] = i;
				break;
			}
		}
	 
		return result;
	}

	
```

**思路2**：

​	

## S.15_3Sum

原题地址：

[http://www.lintcode.com/problem/3sum/](http://www.lintcode.com/problem/3sum/)

思路：

1. 把数组排序。
2. 最外层使用一个循环，遍历数组元素，范围（0~num.length-1）。
3. 内层使用一个循环和两个指针，分别从头部和尾部开始移动，在外层循环固定到一个数的情况下，头部向后移动，尾部向前移动，直至满足a+b+c=0。

代码：

```java
public static List<List<Integer>> threeSum(int[] nums) {
		List<List<Integer>> result = new ArrayList<>();
		Arrays.sort(nums);
		
		for(int i = 0; i < nums.length-1; i++){
			//[-4,-1,-1,0,1,2]，当nums[i]=第一个-1时，有一个答案[-1,0,1],当nums[i]=第二个-1时，还有一个
			//答案是[-1,0,-1]，所以就需要排除这个情况。
			if(i > 0 && nums[i] == nums[i-1]) continue;
			int begin = i+1;
			int end = nums.length-1;
			while(begin < end){
				if(nums[i] + nums[begin] + nums[end] == 0){ 
					List<Integer> list = new ArrayList<Integer>();
					list.add(nums[i]);
					list.add(nums[begin]);
					list.add(nums[end]);
					result.add(list);
					begin++;
					end--;
					while(begin < end && nums[begin] == nums[begin-1])//begin 要和之前的begin 比较
						begin++;
					while(begin < end && nums[end] == nums[end+1])//end要和之后的end比较
						end--;
					
				}else if(nums[i] + nums[begin] + nums[end] > 0){
					end--;
				}else{
					begin++;
				}
			}
			
		}
		return result;
	}	
```



## S.16_3Sum Closest

原题地址：

https://leetcode.com/problems/3sum-closest/

思路：

	1.	先排序。
	2.	外层使用for循环，遍历每一个元素。内层左右夹逼，每当发现a+b+c更接近target的时候，就更改result的值。
	3.	如果sum > target，如果sum < target ，begin++。

代码：

```java
public static int threeSumClosest(int[] nums, int target) {
		Arrays.sort(nums);   //先排序
		int sum = 0;
		int result = 0;
		int diff = 0;
		int min_diff = 10000;
		for(int i = 0; i < nums.length-1; i++){  //外层循环
			int begin = i+1;
			int end = nums.length-1;
			while(begin < end){
				sum = nums[i] + nums[begin] + nums[end];
				diff = Math.abs(sum - target);
				if(diff < min_diff){
					min_diff = diff;
					result = sum;
				}
				
				if(sum < target)   //之前排序的原因在这里，sum<target，begin 就往后移动
					begin++;
				else				//否则 如果 sum > target，end 就往前移动
					end--;
			}
		}
			
		return result;
    }
```

##S.18_4Sum

原题地址：

https://leetcode.com/problems/4sum/

思路：

1. 对数组排序。
2. 外面使用两层循环，枚举数组中的a 和b。内层使用being和end两个指针代表c和d，分别从两端往中间遍历。
3. 当a+b+c+d=target时，保存一次答案，如果a+b+c+d>target，end--；如果a+b+c+d<target，begin++。

代码：

```java
public static List<List<Integer>> fourSum(int[] nums, int target) {
		List<List<Integer>> result = new ArrayList<>();
		Arrays.sort(nums);
		
		for(int i = 0; i<nums.length-2; i++){
//			if( i > 0 && nums[i] == nums[i-1]) continue;   //去重
			for(int j = i+1; j<nums.length-1; j++){
//				if(j > 1 && nums[j] == nums[j-1] ) continue;  //两个去重条件，写不完整，干脆将去重的工作放到最后。
				int begin = j+1;
				int end = nums.length-1;
				while(begin < end){
					if(nums[i] + nums[j] + nums[begin] + nums[end] == target){
						List<Integer> list = new ArrayList<>();
						list.add(nums[i]);
						list.add(nums[j]);
						list.add(nums[begin]);
						list.add(nums[end]);
						result.add(list);
						begin++;
						end--;
						while(begin < end && nums[begin]==nums[begin-1]) begin++;
						while(begin < end && nums[end]==nums[end+1]) end--;
					}else if(nums[i] + nums[j] + nums[begin] + nums[end] > target){
						end--;
					}
					else{
						begin++;
					}
				}
			}
		}
		HashSet<List<Integer>> set = new HashSet<>(result);  //去重的工作在这里做
		List<List<Integer>> res = new ArrayList<>(set);
		return res;
	}
```

技巧：通过下面的两步，让ArrayList中的元素去重复。

​	   先把ArrayList中的元素放到Hashset中，然后在使用HastSet构造一个ArrayList。

```java
	HashSet<List<Integer>> set = new HashSet<>(result);
	List<List<Integer>> res = new ArrayList<>(set);
```

## S.17_Remove Element

原题地址：

https://leetcode.com/problems/remove-element/

思路：

​	找出数组中不等于val的值有多少个，该值作为数组的新length，并且保证前length个元素不包含val。

​	i 遍历所有元素，index记录val的位置，并且把和val值不同的元素覆盖到val的位置。

代码：

```java
public int removeElement(int[] nums, int val) {
        
		int index = 0;
		for(int i= 0; i<nums.length; i++){
			if(nums[i] != val)
				nums[index++] = nums[i];
		}
        return index;
    
    }
```

## S.31_Next Permutation

原题地址：https://leetcode.com/problems/next-permutation/

**补充知识**：

​	参考：http://www.cnblogs.com/houkai/p/3675270.html

​	http://www.cnblogs.com/houkai/p/3675270.html

​	什么是产生下一个排列数？

​	全排序：

​	从n个不同元素中任取m（m≤n）个元素，按照一定的顺序排列起来，叫做从n个不同元素中取出m个元素的一个排列。当m=n时所有的排列情况叫全排列。例如n=3，全排序为：123、132、213、231、312、321共6种。

​	字典序法：

​	对给定的字符集中的字符规定了一个先后关系，在此基础上规定两个全排列的先后是：从左到右逐个比较对应的字符大小。字符集{1,2,3}，较小的数字较先，这样按字典序生成的全排列即：123、132、213、231、312、321。

​	所谓的产生下一个排列数是，输入一个全排列中的一串数字，按照字典序法给出排在该串数字之后的下一串数字，就是产生下一个排列数。输入：123，输出132。输入12435，输出12453。

思路：

​	1.从数列的右边向左寻找连续递增序列, 例如对于：1,3,**5,4,2**，其中5-4-2即为递增序列。找到5的下标，标记为index。

​	2.从上述序列中找一个比它前面的数（3）大的最小数（4），把（4）的下标标记为exchangeIndex，并将且交换这两个数。于是1,3,5,4,2->1,4,**5,3,2**，此时交换后的依然是递增序列。

​	3.新的递增序列逆序，即：1,4,5,3,2 => 1,4,2,3,5。把5，3，2改为升序2，3，5。



代码：

```java
public static void nextPermutation(int[] nums) {
		int index = nums.length-1;
		
		//find index's value. index present the maximum subscript in ascending order from right to left.
		while(index > 0){
			if(nums[index-1] < nums[index]){
				break;
			}	
			index--;
		}
		//如果index = 0,说明当前排序从左往右是降序，全部改为升序即可。
		if(index == 0){
			Arrays.sort(nums);
			return ;
		}
		
		int exchangeIndex = -1;
		for(int i = nums.length-1; i>=index; i--){
			if(nums[i] > nums[index-1]){
				exchangeIndex = i;
				break;
			}
		}
		
		int temp = nums[index-1];
		nums[index-1] = nums[exchangeIndex];
		nums[exchangeIndex] = temp;
		//对nums数组中元素排序。index要排序的第一个元素的索引（包括），
		//toIndex - 要排序的最后一个元素的索引（不包括） 
		Arrays.sort(nums,index,nums.length);   

		for(int i : nums)
			System.out.println(i+ " ");
		
		
	}
```

## S.36_Valid Sudoku

原题地址：https://leetcode.com/problems/valid-sudoku/

思路：

​	在已经给出的数独中，判断每一行、每一列、每一个小9宫格不存在重复元素。

代码：

```java
private  Set<Character> set = new HashSet<Character>();
public boolean check(char c){
		if(c == '.'){
			return true;
		}else if(c >= '1' && c <= '9'){
			if(set.contains(c)){
				return false;
			}else{
				set.add(c);
				return true;
			}
		}else{
			return false;
		}
	}
	public boolean isValidSudoku(char[][] board) {
		
		//比较每一个行
		for(int i =0; i<9; i++){
			for(int j=0; j<9; j++){
				if(!check(board[i][j])) 
					return false;
			}
			set.clear();     //这一步很关键，每次比较新的一个行时，都要清空set.
		}
		
		//比较每一个列
		for(int i=0; i<9; i++){
			for(int j=0; j<9; j++){
				if(!check(board[j][i]))
					return false;
			}
			set.clear();
		}
		
		//比较每一9宫格内部，元素是 不重复的
		for(int i=0; i<9; i++){
			for(int j=0; j<9; j++){
				if(!check(board[i/3*3+j/3][i%3*3+j%3]))   //难点
					return false;
			}
			set.clear();
		}
		
		return true;
	}
```

难点1：

​	数独中一共有9个3*3的格子，判断这个9个格子元素是否重复的时候，条件是这样的：

```java
for(int i=0; i<9; i++){
			for(int j=0; j<9; j++){
				if(!check(board[i/3*3+j/3][i%3*3+j%3]))   //难点
					return false;
			}
}
```

难点2：

​	如何判断一行中有没有重复元素，使用HashSet。每次遍历的元素先判断HashSet中没有，如果有就是包含重复元素。如果没有，则表示没有，并把该元素放到HashSet中。

## S.42_Trapping Rain Water（我都没有看懂题目意思啊）

原题地址：https://leetcode.com/problems/trapping-rain-water/

参考：http://blog.csdn.net/kenden23/article/details/17115097

## S.48_Rotate Image

原题地址：https://leetcode.com/problems/rotate-image/

思路：

​	纯粹考察如何旋转的技巧。

​	1.先沿着副对角线交换元素。

​	2.再沿着水平线以镜像方式交换所有元素，就是顺时针旋转90度的结果。

代码：

```java
public void rotate(int[][] matrix) {
        int n = matrix[0].length;
        int sum = n-1;  //sum是长度-1.  发现i和j的和总是sum.
        //沿着副对角线调整
        for(int i=0; i<n-1; i++){
        	for(int j=0; j<n-1-i; j++){
        		int temp = matrix[sum-j][sum-i];
        		matrix[sum-j][sum-i] = matrix[i][j];
        		matrix[i][j] = temp;
        	}
        }
        //沿着中心轴线做镜面对称的元素交换
        int mid = n/2 ;
        for(int i=0; i<mid; i++){
        	for(int j=0; j<n; j++){
        		int temp = matrix[i][j];
        		matrix[i][j] = matrix[sum-i][j];
        		matrix[sum-i][j] = temp;
        	}
        }
        for(int i=0; i<n; i++){
        	for(int j=0; j<n; j++){
        		System.out.println(matrix[i][j]);
        	}
        } 
    }
```

## S.66_Plus One

原题地址：https://leetcode.com/problems/plus-one/

思路：

​	这套题目是一个实现题。大数题目。

​	1.先对原数组进行处理。从数组最后一位开始往前检查，如果当前数字是<9的，说明你加1无需进位，从循环跳出即可，如果当前数字等于9，说明加1涉及进位，且加1后当前数字应记为0，继续循环处理。

​	2.当对原数组处理完后，还需要判断当前第0位是不是已经变为0了，如果已经变为0了说明是类似99+1这种，需要进位。其他则不需要。

代码：

```java
public int[] plusOne(int[] digits) {
        
		int length = digits.length;
		for(int i = length-1; i >= 0; i--){ 
			if(digits[i]<9){
				digits[i]++;
				break;
			}else{
				digits[i]=0;
			}
		}
		int[] result;
		if(digits[0]==0){
			result = new int[length+1];
			result[0] = 1;
			for(int i = 1; i < length+1; i++){
				result[i] = digits[i-1];
			}
		}else{
			result = new int[length];
			for(int i = 0; i<length; i++){
				result[i] = digits[i];
			}
		}
		return result;
    }
```

## S.77_Climbing Stairs

原题地址：https://leetcode.com/problems/climbing-stairs/

思路：

​	查资料，说这是一道典型的动态规划的题目。主要思想就是找出递推式，然后利用子问题的解来求最后的最优解。

​	一共有n层，要想爬到第n层，有两种方法。第一种，从第n-1层爬1步上来；第二种，从第n-2层爬2步上来。所以，假如从0层到n-1层有dp[n-1]种方法，从0层到n-2层有dp[n-2]中方法，那么到第n层的方法数，就是dp[n-1]+dp[n-2]种方法。因为从他们都可以到达第n层。

​	综上，推出的递推式是：dp[n] = dp[n-1] + dp[n-2]。如果梯子有1层或者2层，dp[1] = 1, dp[2] = 2，如果梯子有0层，自然dp[0] = 0 。

代码：

```java
public int climbStairs(int n) {
        
		if(n == 0 || n == 1 || n == 2)
			return n;
		int[] dp = new int[n+1];
		dp[0] = 0;
		dp[1] = 1;
		dp[2] = 2;
		for(int i = 3; i <= n; i++)
			dp[i] = dp[i-1] + dp[i-2];
		return dp[n];
    }
```

## S.73_Set Matrix Zeroes

原题地址：https://leetcode.com/problems/set-matrix-zeroes/

思路：

​	方法1：空间复杂度O(m+n).

​	建立一个长度为m的数组，记录m行中哪一行有0。建立一个长度为n的数组，记录n列中哪一列存在0.然后访问这两个数组，并且对原来的matrix做更新。

代码：

​	空间复杂度：O(m+n). 时间复杂度O(m*n).

```java
public void setZeroes(int[][] matrix) {
       
		int m = matrix.length; //返回二维数组行数
		int n = matrix[0].length; //返回二维数组列数
		int[] rows = new int[m];
		int[] columns = new int[n];
		
		for(int i = 0; i < m; i++){
			for(int j = 0; j < n; j++){
				if(matrix[i][j] == 0){
					rows[i] = -1;  //记录哪一行有0.并用-1进行标记。
					columns[j] = -1;  //记录哪一列有0.并用-1进行标记。
				}
			}
		}
		
		for(int i = 0; i < m; i++){
			if(rows[i] == -1){
				for(int j = 0; j < n; j++){
					matrix[i][j] = 0;   //把对应行的元素全部置为0.
				}
			}
		}
		
		for(int i = 0; i < n; i++){
			if(columns[i] == -1){
				for(int j = 0; j < m; j++){
					matrix[j][i] = 0;  //把对应列的元素全部置为0.
				}
			}
		}
    }
```

## S.134_Gas Station

原题地址：https://leetcode.com/problems/gas-station/

思路1：

​	gas和cost是两个数组，并且gas中不同位置代表了不同的station所拥有的不同油量。理解上，gas要作为一个循环数组，从任何一个位置出发都可以再回到这个位置。

​	初始状态：gas[i] - cost[i] > 0.就可以从i位置达到i+1的位置。

​	随后状态：上次剩余油量  + gas[i+1]  - cost[i+1] > 0就可以到达i+2的位置。

代码：时间复杂度O(n^2).

```java
public int canCompleteCircuit(int[] gas, int[] cost) {
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
```

思路2：

​	本题如果有解，那么题目中gas[0]+...+gas[n]的和一定大于cost[0]+...+cost[n]的和。并且起始位置是第一次出现gas[0]+...+gas[i]>cost[0]+...+cost[i]的位置。前i-1个位置，gas的和是小于cost的和，知道出现第i个位置，gas的和大于cost的和。

​	本题如果无解，那么题目中gas[0]+...+gas[n]的和一定小于cost[0]+...+cost[n]的和。

​	于是：

​	如果total<0则一定没有解，因为不管从哪个位置开始，一定无法通过最后一个加油站。

​	如果total>=0一定有解，而且这个解一定是最后一个sum<0的下一个位置（j=i+1）。

代码：时间复杂度O(n)

```java
public int canCompleteCircuit(int[] gas, int[] cost) {
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
```

## S.135_Candy

原题地址：https://leetcode.com/problems/candy/

思路：

​	这道题和Trapping water那个是一样的想法，因为无论是水坑还是得到糖的小朋友，影响因素都不只一边，都是左右两边的最小值/最大值来决定的。做法是分别从左右两边遍历数组。

​	1.leftResult数组存从左边遍历，当前小朋友对比其左边小朋友，他能拿到糖的数量；

​	2.rightResult数组存从右边遍历，当前小朋友对比其右边小朋友，他能拿到的糖的数量。

​	3.最后针对这两个数组，每个小朋友能拿到的糖的数量就是这两个数最大的那个数，求总加和就好了。

代码：

```java
public int candy(int[] ratings){
		
		int length = ratings.length;
		int[] leftResult = new int[length];
		int[] rightResult = new int[length];
		//每个元素只和左边元素相比较
		leftResult[0] = 1;
		for(int i = 1; i < length; i++){
			if(ratings[i] > ratings[i-1])
				leftResult[i] = leftResult[i-1]+1;
			else
				leftResult[i] = 1;
		}
		//每个元素只和右边元素比较
		rightResult[length-1] = 1;
		for(int i = length-2; i >= 0; i--){
			if(ratings[i] > ratings[i+1])
				rightResult[i] = rightResult[i+1]+1;
			else
				rightResult[i] = 1;
		}
		int sum = 0;
		for(int i = 0; i<length; i++){
			if(leftResult[i] > rightResult[i])
				sum += leftResult[i];
			else
				sum += rightResult[i];
		}
		return sum;
	}
```

## S.136_Single Number

原题地址：https://leetcode.com/problems/single-number/

思路：

​	这道题目要求使用线性的时间复杂度，和很少的附加空间。运用亦或^，相同为0，不同为1的性质。0^x = x.设定一个初始值num=0，然后和数组中所有元素亦或，最后得到的那个值就是出现次数为奇数次的值。 因此出现次数为1次的数字就产生了。

代码：

```java
public int singleNumber(int[] nums) {
		int num = 0;
		int length = nums.length;
		for(int i = 0; i < length; i++){
			num = num^nums[i];
		}
		return num;
	}
```

##S.137_ Single Number II
原题地址：https://leetcode.com/problems/single-number-ii/
思路1：

​	扫描数组，把数据读入hashMap中，然后遍历hashMap，找到按个次数不是3的元素。空间上增加了hashMap的空间，时间上多遍历一次hashMap。

代码：

```java
public int singleNumber(int[] nums) {
		int length = nums.length;
		HashMap<Integer,Integer> map = new HashMap<>();
		for(int i = 0; i < length; i++){
			if(map.containsKey(nums[i])){
				map.put(nums[i], map.get(nums[i])+1);
			}
			else
				map.put(nums[i], 1);
		}
		int result = 0;
		Iterator<java.util.Map.Entry<Integer,Integer>> it = map.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry<Integer, Integer> entry = it.next();
			Integer key = entry.getKey();
			Integer value = entry.getValue();
			if(value != 3){
				result = key;
				break;
			}
		}
		return result;
	}
```

## S. 子数组最大累加和

问题：

- 令arr = [1,-2,3,5,-2,6,-1]，求子数组最大累加和是[3,5,-2,6]，返回12.
- 思路：cur记录当前累加和。当cur\<0时，说明累加到当前数出现了和\<0的情况，那么累加的这一部分一定不能作为产生最大累加和的子数组的左边部分，此时令cur=0表示重新从下一个数累加。当cur\>0时，每一次累加都可能是最大的累加和，所以，用另外一个变量max全程跟踪记录cur出现的最大值。

```java
public int solution(int[] arr){
		if(arr==null || arr.length == 0)
			return 0;
		
		int cur = 0;
		int max = Integer.MIN_VALUE;
		for(int i=0; i<arr.length; i++){
			cur += arr[i];    //记录当前累加和
			max = Math.max(cur, max);
			cur = cur < 0? 0 : cur;  
		}
		
		return max;
	}
```









​	







