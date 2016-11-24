# LeetCode刷题笔记（深度优先和广度优先部分）

[TOC]

>当题目看不出任何规律，既不能用分治，贪心，也不能用动态规划时，这时候万能方法----搜索，就派上用场了。搜索分为广搜和深搜，广搜里面又有普通广搜，双向广搜，A*搜索等。深搜里面又有普通深搜，回溯法等。
>
>广搜和深搜非常类似（除了在扩展节点这部分不一样），二者有相同的框架，如何表示状态？如何扩展状态？如何判重？尤其是判重，解决了这个问题，基本上整个问题就解决了。
>
>**涉及到递归函数如何思考？**
>
> 用递归循环找子问题的方法，把母串按所有组合可能性拆分，
>
>看递归程序，不能盯着一处看，陷入细节局部不容易理解，从整体逻辑来思考。

# 深度优先部分

## S.131_Palindrome Partitioning

原题地址：https://leetcode.com/problems/palindrome-partitioning/

题目：

>Given a string *s*, partition *s* such that every substring of the partition is a palindrome.
>
>Return all possible palindrome partitioning of *s*.
>
>For example, given *s* = `"aab"`,
>Return
>
>```
>[
>  ["aa","b"],
>  ["a","a","b"]
>]
>```

思路：

​	生成这个字符串所有可能的子串，并且判断这些子串是否是回文，如果是回文，就把他们添加到结果中。

​	具体执行步骤是这样的：

- 1.从s中取字符串，取出来判断这个字符串是不是回文？

- 2.如果是回文，就把这个字符串加入到partition中，然后通过递归调用，在此基础上继续判断字符串剩下的部分。直到剩余部分全部判断完成，并且都是回文，就把当前partition添加到result中。在退出递归的时候，把之前添加的元素依次删除掉。**只要涉及递归，这一步操作往往存在，这块就属于回溯过程中做的事情。**
- 3.如果不是回文，则让i++，重现选择新的字符串进行判断。

```java
public List<List<String>> partition(String s) {
		 List<List<String>> result = new ArrayList<List<String>>();
		 
		 if(s == null || s.length() == 0)
			 return result;
		 
		 ArrayList<String> partition = new ArrayList<String>();
		 addPalindrome(s, 0, partition, result);
		 
		 return result;
 	 }
	 
	 private void addPalindrome(String s, int start, List<String> partition, 
			 List<List<String>> result){
		 
		 if(start == s.length()){  //判断到字符串的最后
			 List<String> temp = new ArrayList<String>(partition); //在内存上重新开辟一块空间，放partition，把这个对象放到result中。
			 System.out.println("temp is "+temp);
			 result.add(temp);
			 return ;
		 }
		
		 
		 /**
		  * 以下代码的思路是：
		  * 	1.从s中取字符串，取出来判断这个字符串是不是回文？
		  * 	2.如果是回文，就把这个字符串加入到partition中，然后通过递归调用，在此基础上继续判断字符串剩下的部分。直到剩余部分全部判断完成，并且都是回文，就把当前
		  * 	  partition添加到result中。在退出递归的时候，把之前添加的元素依次删除掉。
		  * 	3.如果不是回文，则让i++，重现选择新的字符串进行判断。
		  */
		 for(int i = start +1; i<= s.length(); i++){
			 String str = s.substring(start,i);
			 System.out.println("str is : "+str);
			 if(isPalindrome(str)){
				 partition.add(str);
				 addPalindrome(s, i, partition, result);
				 partition.remove(partition.size()-1);
			 }
		 }
		 
	 }
	 //判断一个字符串是否是回文
	 private boolean isPalindrome(String str){
		 int left = 0;
		 int right = str.length()-1;
		 
		 while(left < right){
			 if(str.charAt(left) != str.charAt(right)){
				 return false;
			 }
			 left++;
			 right--;
		 }
		 return true;
	 }
```

## S.132_Palindrome Partitioning II（未完成）

原题地址：https://leetcode.com/problems/palindrome-partitioning-ii/

题目：

>Given a string *s*, partition *s* such that every substring of the partition is a palindrome.
>
>Return the minimum cuts needed for a palindrome partitioning of *s*.
>
>For example, given *s* = `"aab"`,
>Return `1` since the palindrome partitioning `["aa","b"]` could be produced using 1 cut.

思路：



## S.62_Unique Paths

原题地址：https://leetcode.com/problems/unique-paths/

题目：

>A robot is located at the top-left corner of a *m* x *n* grid (marked 'Start' in the diagram below).
>
>The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
>
>How many possible unique paths are there?

![img](http://leetcode.com/wp-content/uploads/2014/12/robot_maze.png)

### 思路1：动态规划思想

其实跟爬梯子挺类似的，按个就是只能往上爬，这个就是方向可以换了下。同样想法动态规划。 

分析方法也一样的，想想要到最右下角。到达右下角的方法只有两个，从上面往下，和从右面往左。

 利用到达终点的唯一性，就可以写出递推公式（dp[i][j]表示到坐标（i,j）的走法数量）：

 `dp[i][j] = dp[i-1][j] + dp[i][j-1]`

初始条件的话，当整个格子只有一行，那么到每个格子走法只有1种；只有一列的情况同理。

所以，理解的这些，代码就非常好写了。

通常来讲，我们会初始dp数组为`dp[m+1][n+1]`。但是这里的话，因为dp[i][j]是表示坐标点，所以这里声明`dp[m][n]`更容易理解。

代码：

```java
public int uniquePaths(int m, int n) {
        
		if(m == 0 || n == 0) return 0;
		if(m == 1 || n == 1) return 1;
		
		int[][] dp = new int[m][n];
		
		//initial the first row
		for(int i = 0; i < n; i++)
			dp[0][i] = 1;
		
		//initial the first column
		for(int j = 0; j < m; j++)
			dp[j][0] = 1;
		
		// for each body node, number of path = paths from top + paths from left
		for(int i = 1; i < m; i++)
			for(int j = 1; j < n; j++){
				dp[i][j] = dp[i-1][j] + dp[i][j-1];
			}
		return dp[m-1][n-1];
    }
```

### 思路2：深度优先搜索思想

首先需要明确一个概念，DFS和BFS都是在对树和图进行搜索时候的一种方式。DFS就是先尽可能地往深走，当沿着深走到头的时候，往回返。所以，针对这道题目为什么可以用DFS呢？

举例来说：假如现在有一个2，3的棋盘。

| (2,3) | (2,2) | (2,1) |
| :---: | :---: | :---: |
| (1,3) | (1,2) | (1,1) |

如果现在从2,3的位置出发，有两条路径可以选。分别是向右和向下走。同理`2,2`的位置也是两条路。`2,1`的位置是一条路。`1,3`的位置是一条路。`1,2`的位置是一条路。`1,1`的位置是终点，是收敛的地方。 ![DFS](pics\/DFS.PNG)

`1,1`位置的1表示有一种走法，`1,2`位置的1表示在该位置有一种走法。`2,2`位置的2表示在该位置有两种走法。同理`2,3`处的3就表示有3中走法。

其实这样分析一下题目，就是从下至上返回结果。而动态规划的写法是从上至下的。

代码：代码是简单的，背后的分析过程和逻辑是复杂的严格的。

```java
public int uniquePaths_2(int m, int n){
		
		System.out.println(m + " " + n);
		if(m < 1 || n < 1 ) return 0;
		if(m == 1 && n == 1) return 1;
		
		return uniquePaths_2(m-1, n) + uniquePaths_2(m, n-1);
	}
```

## S.63_Unique Paths II

原题地址：https://leetcode.com/problems/unique-paths-ii/

题目：

>Follow up for "Unique Paths":
>
>Now consider if some obstacles are added to the grids. How many unique paths would there be?
>
>An obstacle and empty space is marked as `1` and `0` respectively in the grid.
>
>For example,
>
>There is one obstacle in the middle of a 3x3 grid as illustrated below.
>
>```
>[
>  [0,0,0],
>  [0,1,0],
>  [0,0,0]
>]
>
>```
>
>The total number of unique paths is `2`.

思路：

​	基于动态规划思想。首先要做初始化。初始化过程完成后，下一个状态都是由之前的状态生成的。不过需要注意的地方是有障碍物，有障碍物的地方用0添加即可。

图解如下：

**右下角位置的值 = 其左边一个位置的值 + 其上边一个位置的值**  ![动态规划图解](pics\/动态规划图解.PNG)

代码：

```java
public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        
        if(obstacleGrid == null)
        	return 0;
        if(obstacleGrid[0][0] == 1)
        	return 0;
        
        int rows = obstacleGrid.length;
        int columns = obstacleGrid[0].length;
        int[][] dp = new int[rows][columns];
        
        
        
        //initial the first row
        int num = 1;
        for(int i = 0; i < columns; i++){
        	if(obstacleGrid[0][i] == 1){
        		dp[0][i] = 0;
        		num = 0;
        	}
        	else
        		dp[0][i] = num;
        }
        //initial the first column
        num = 1;
        for(int i = 0; i < rows; i++){
        	if(obstacleGrid[i][0] == 1){
        		dp[i][0] = 0;
        		num = 0;
        	}
        	else
        		dp[i][0] = num;
        }
        for(int i = 1; i < rows; i++)
        	for(int j = 1; j < columns; j++){
        		if(obstacleGrid[i][j] == 1)
        			dp[i][j] = 0;
        		else
        			dp[i][j] = dp[i-1][j]+dp[i][j-1];

        	}
        return dp[rows-1][columns-1];
    
    }
```

## S.51_N-Queens

原题地址：https://leetcode.com/problems/n-queens/

题目：

>The *n*-queens puzzle is the problem of placing *n* queens on an *n*×*n* chessboard such that no two queens attack each other.
>
>![img](http://www.leetcode.com/wp-content/uploads/2012/03/8-queens.png)
>
>Given an integer *n*, return all distinct solutions to the *n*-queens puzzle.
>
>Each solution contains a distinct board configuration of the *n*-queens' placement, where `'Q'` and `'.'` both indicate a queen and an empty space respectively.
>
>For example,
>There exist two distinct solutions to the 4-queens puzzle:
>
>```java
>[
> [".Q..",  // Solution 1
>  "...Q",
>  "Q...",
>  "..Q."],
>
> ["..Q.",  // Solution 2
>  "Q...",
>  "...Q",
>  ".Q.."]
>]
>```

思路：

​	之前一直没有系统研究过N皇后问题，所以借着这道题目，好好整理下N皇后问题。

​	如果在`(i,j)`位置（第i行，第j列）放置了一个皇后，接下来在哪些位置不能放置皇后呢？

1. 整个第`i`行都不能放置。

2. 整个第`j`列都不能放置。

3. 如果位置`(a,b)`满足`|a-i|==|b-j|`,说明（a，b）与（i，j）处在同一条斜线上，也不能放置。

   把递归过程直接设计成逐行放置皇后的方式，可以避开条件`1`的那些不能放置的位置。接下来用一个数组保存已经放置的皇后位置，假设数组为record，`record[i]`的值表示第`i`行皇后所在的列。在递归计算到第`i`行第`j`列时，查看`record[0...k]（k < i）`的值，看是否有`j`相等的值，若有，则说明`（i,j）`不能放置皇后，再看是否有`|k-i| == |record[k]-j|`，若有，也说明`（i,j）`不能放置皇后。

### 具体代码1：

   输入皇后的个数，返回皇后的摆法数目有多少种？

   如输入8，返回92.

```java
   	//主程序入口
      public int num(int n){
   		if(n < 1){
   			return 0;
   		}
   		
   		int[] record = new int[n];
   		return process(0, record, n);
   	}
   	//这是一个逐行放皇后的过程
   	public int process(int i, int[] record, int n){
   		if(i == n){
   			return 1;    //直到所有的皇后都放好了，返回一个1.代表这是一种放皇后的方法。
   		}
   		int res = 0;
   		for(int j = 0; j < n; j++){
   			if(isValid(record, i, j)){
   				record[i] = j;
   				res += process(i+1, record, n);
   			}
   		}
   		return res;
   	}
   	//用以判断这个位置是否可以放置皇后
   	public boolean isValid(int[] record, int i, int j){
   		for(int k = 0; k < i; k++){
   			if( j == record[k] || Math.abs(record[k]-j) == Math.abs(i-k)){
   				return false;
   			}
   		}
   		return true;
   	}
```

### 具体代码2：

输入皇后的数目，然后返回具体的

```java
public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<List<String>>();
		helper(n, 0, new int[n], res);
		return res;
		
		
    }
	
	private void helper(int n, int row, int[] columnForRow, List<List<String>> res){
		if(row == n){   
			//当前面所有的皇后都放好之后，columnForRow数组已经正确生成，
			//此时只需要根据这个columnForRow数组生成如何放皇后的信息
			ArrayList<String> item = new ArrayList<String>();
			for(int i = 0; i < n; i++){
				StringBuilder strRow = new StringBuilder();
				for(int j = 0; j < n; j++){
					if(columnForRow[i] == j) //i的实际含义代表行。
						strRow.append('Q');
					else
						strRow.append('.');
				}
				System.out.println(strRow.toString());
				item.add(i,strRow.toString());
			}
			res.add(item);
			return ;
		}
		for(int i = 0; i < n; i++){
			columnForRow[row] = i;
			if(check(row, columnForRow)){
				helper(n, row+1, columnForRow, res);
			}
		}
	}
	private boolean check(int row, int[] columnForRow){
		for(int i =0; i < row; i++){
			if(columnForRow[row] == columnForRow[i] || 
					Math.abs(columnForRow[row] - columnForRow[i]) == row-i){
				return false;
			}
		}
		return true;
	}
```

## S.52_N-Queens II

原题地址：https://leetcode.com/problems/n-queens-ii/

题目：

>Follow up for N-Queens problem.
>
>Now, instead outputting board configurations, return the total number of distinct solutions.
>
>![img](http://www.leetcode.com/wp-content/uploads/2012/03/8-queens.png)
>
>这次只需要根据皇后的个数，返回一共有多少种不同的摆放的方法即可。

思路：

​	一个一个位置逐个去尝试，只有在此前所有已经放好的皇后都不冲突的情况下，才考虑放新的皇后。当所有皇后都放完，说明这是一种放置方案。

代码：

```java
public int totalNQueens(int n) {
        if(n < 1)
        	return 0;
        int[] record = new int[n];
        
        return process(0, n,record );
    }
	
	public int process(int i, int n, int[] record){ //i 表示第i行
		
		if(i == n){
			return 1;
		}
		int res = 0;
		for(int j = 0; j < n; j++){ 
			record[i] = j;          //让第i行的皇后放在第j列
			if(isValid(i, j, record)){
				res += process(i+1, n , record);//第i行的皇后放置完成后，放置第i+1行的皇后
			}
		}
		return res;
	}
	
	
	public boolean isValid(int i, int j, int[] record){
		
		for(int k = 0; k < i; k++){
			//判断第i行的皇后，是否可以放在record[i]列
			//要求，第i行之前的所有皇后，不能放在第j列
			//要求，两个皇后之间，行的差值 ！= 列的差值
			if(j == record[k] || Math.abs(i - k) == Math.abs(j - record[k])){
				return false;
			}
		}
		
		return true;
	}
```

## S.93_Restore IP Addresses

原题地址：https://leetcode.com/problems/restore-ip-addresses/

参考链接：http://blog.csdn.net/u011095253/article/details/9158449

题目：

>Given a string containing only digits, restore it by returning all possible valid IP address combinations.
>
>For example:
>Given `"25525511135"`,
>
>return `["255.255.11.135", "255.255.111.35"]`. (Order does not matter)

思路：

​	这道题目需要将字符串且分为4部分，然后对每个部分判断是否合法，如果合法就把这个切分之后的形式放到list中。

1. 首先我们要明确传统IP 地址的规律是分4个Part，每个Part是从0-255的数字.

2. 0-255的数字，转换成字符，即每个Part 可能由一个字符组成，二个字符组成，或者是三个字符组成。那这又成为组合问题了，dfs便呼之欲出。

3. 所以我们写一个For循环每一层从1个字符开始取一直到3个字符，再加一个isValid的函数来验证取的字符是否是合法数字，如果是合法的数字，我们再进行下一层递归，否则跳过。

   题目分析到这里发现可以使用DFS的思路。概括起来，就是取1到3位长度的字符，判断是否是合法的，如果是合法的，暂时保存到中间结果集中，并且继续下一层递归调用。否则跳过。在代码方面，其结构和之前的**N皇后**问题非常相似。

**几个需要注意的点**：

1. 在验证字符串是否是数字的时候，要注意0的情况，001，010，03都是非法的。所以，如果第一位取出来是0，那么我们就判断字符串是否是"0"，不是的情况都是非法的
2. 取字符串的时候，注意位数不够的问题，不仅<4, 而且<s.length()
3. 注意substring的范围
4. 字符串转换成数字 Integer.parseInt(); 
5. 别忘了IP 地址里面的 "."
6. 到第4个Part的时候我们就可以整体验证剩下的所有字符串（因为第4个Part最后一定要取到结尾才是正确的字符串）

代码：

```java
public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<String>();
        if(s.length() < 4 || s.length() > 12)
            return res;
        dfs(s, "", res, 0);
        return res;
    }

    public void dfs(String s, String tmp, List<String> res, int count){
        if(count == 3){
            if(isValid(s)){
                tmp += s;
                res.add(tmp);
            }
            return ;
        }
        for(int i = 1; i<4 && i<s.length(); i++){ //i < s.length()容易忽视，导致越界
            String subStr = s.substring(0,i);
            if(isValid(subStr)){
                dfs(s.substring(i), tmp + subStr+'.', res, count+1);
            }
        }
    }
    
    public boolean isValid(String s){
        if(s.charAt(0) == '0')
            return s.equals("0");
        int num = Integer.parseInt(s);
        return num <= 255 && num >0;
    }
```

## S.39_Combination Sum

原题地址：https://leetcode.com/problems/combination-sum/

题目：

>Given a set of candidate numbers (**C**) and a target number (**T**), find all unique combinations in **C** where the candidate numbers sums to **T**.
>
>The **same** repeated number may be chosen from **C** unlimited number of times.
>
>**Note:**
>
>- All numbers (including target) will be positive integers.
>- The solution set must not contain duplicate combinations.
>
>For example, given candidate set `[2, 3, 6, 7]` and target `7`, 
>A solution set is: 
>
>```java
>[
>  [7],
>  [2, 2, 3]
>]
>```

思路：

​	采用DFS的思路。使用深度优先搜索的方法遍历所有可能的组合，当找到的组合满足条件（他们的和==target）时，就把这样的一组序列添加到最后的结果中。需要注意的地方是，同样的数字可以出现多次，但是结果集中的结果不能重复，比如说，不能出现这样的情况：`[[2, 2, 3], [2, 3, 2], [3, 2, 2], [7]]`.

​	为了解决结果中不包含重复结果，需要原先的数组排序，然后每次跳进递归的时候不需要从`0`位置开始重新找元素。因为如果2，2，3是一个结果集。那么当遍历到3的时候，如果从0下标开始，必然会找到3，2，2的一个序列。所以每次递归不要从0下标开始。

​	也不需要重当前位置+1的地方开始，因为每个元素可以重复使用，所以只需要从当前这个元素的位置开始即可。

### 代码1：这个代码没有排除重复元素：

```java
public List<List<Integer>> combinationSum(int[] candidates, int target) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		if(candidates == null || candidates.length == 0)
			return res;
		
		dfs(target, new ArrayList<Integer>(), candidates, res);
		return res;
		
    }
	
	public void dfs(int target, List<Integer> contains, int[] candidates, List<List<Integer>> res){
		int sum = sum(contains);
		if(sum == target){
			System.out.println("sum is : "+sum);
			System.out.println(contains);
			if(!res.contains(contains))
				res.add(new ArrayList<Integer>(contains));
			return ;
		}
		if( sum > target){
			return ;
		}
		if (sum < target) {
			for (int i = 0; i < candidates.length; i++) {
				System.out.println("...");
				contains.add(candidates[i]);
				System.out.println(sum(contains));
				dfs(target, contains, candidates, res);
				contains.remove(contains.size()-1);    //重点在这里，回溯的时候曾经添加的元素要一个一个再次移除
			}
		}
			
	}
	
	public int sum (List<Integer> contains){
		int sum = 0;
		for(int ele : contains)
			sum += ele;
		return sum;
	}
```

  ### 代码2：排除重复元素

排除res中重复元素主要用到两步：

- 对原先candidates进行排序，然后在进行递归调用的时候，不要从当前位置的下一个位置调用。也不要从0下标位置开始。2，2，3和3，2，2是同一个答案。如果做到对原先candidates排序，并且进行递归调用时从仍然从当前位置开始，自然可以排除3，2，2这个答案。
- 使用`if(i > 0 && candidates[i] == candidates[i-1])`，也可以排除重复元素。如果candidates是`[2,3,3,3,3,6,7]`时，只有生成一个2，3，3答案，后面的2，3，3都会被这条语句过滤掉的。

```java
public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
		if(candidates == null || candidates.length == 0)
			return res;
		Arrays.sort(candidates);
		dfs(0, target, new ArrayList<Integer>(), candidates, res);
		return res;
    }
    
    public void dfs(int start, int target, List<Integer> contains, int[] candidates, List<List<Integer>> res){
		
		if(target == 0){
			System.out.println(contains);
			if(!res.contains(contains))
				res.add(new ArrayList<Integer>(contains));
			return ;
		}
		if( target < 0){
			return ;
		}
		if (target > 0) {
			for (int i = start; i < candidates.length; i++) {
				if(i > 0 && candidates[i] == candidates[i-1])
					continue;   //排除重复元素
				int newtarget = target - candidates[i];
				contains.add(candidates[i]);
				dfs(i, newtarget, contains, candidates, res);
				contains.remove(contains.size()-1);
			
			}
		}	
	}
```

## S.40_Combination Sum II

原题地址：https://leetcode.com/problems/combination-sum-ii/

题目：

>Given a collection of candidate numbers (**C**) and a target number (**T**), find all unique combinations in **C** where the candidate numbers sums to **T**.
>
>Each number in **C** may only be used **once** in the combination.
>
>**Note:**
>
>- All numbers (including target) will be positive integers.
>- The solution set must not contain duplicate combinations.
>
>For example, given candidate set `[10, 1, 2, 7, 6, 1, 5]` and target `8`, 
>A solution set is: 
>
>```
>[
>  [1, 7],
>  [1, 2, 5],
>  [2, 6],
>  [1, 1, 6]
>]
>```

思路：

​	这道题目同样使用DFS方法。相比于上一题不同的地方在于这道题目中，数字不能重复出现。所以使用过的数字就不能再使用了，进行递归调用的时候从当前位置的下一个位置开始。

代码：

```java
public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(candidates == null || candidates.length == 0)
        	return res;
        
        Arrays.sort(candidates);
        dfs(0, target, new ArrayList<Integer>(), candidates, res);
        
        return res;
    }
    public void dfs(int start, int target, ArrayList<Integer> item, int[] candidates, List<List<Integer>> res){
		if(target == 0){
			if(!res.contains(item))
				res.add(new ArrayList<Integer>(item));
			return;
		}
		if(target < 0){
			return ;
		}
		if(target > 0){
			for(int i = start; i < candidates.length; i++){
				int newtarget = target - candidates[i];
				item.add(candidates[i]);
				dfs(i+1, newtarget, item, candidates, res);
				item.remove(item.size()-1);
			}
		}
	}
```

## S.22_Generate Parentheses

原题地址：https://leetcode.com/problems/generate-parentheses/

题目：

>Given *n* pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
>
>For example, given *n* = 3, a solution set is:
>
>```java
>[
>  "((()))",
>  "(()())",
>  "(())()",
>  "()(())",
>  "()()()"
>]
>```

思路：

​	同样使用DFS的思路。

- 首先使用DFS，进行递归调用，生成所有可能的结果。
- 对所有生成的结果做判断，判断是否是一个合法的括号匹配。匹配则加入最后的结果集，不匹配则不加入。

代码：

```java
public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<String>();
		if(n == 0)
			return res;
		String[] str = {"(",")"};
		dfs(n*2, str, "", res);
		return res;
    }
    
    public void dfs(int count, String[] str, String item, List<String> res){
		if(count == 0){
			if(isValid(item)){
				
				res.add(item);
			}
			return ;
		}
		if(count < 0)
			return ;
		if(count > 0){
			for(int i = 0; i < str.length; i++){
				int newCount = count - 1;
				item = item+str[i];
				dfs(newCount, str, item, res);
				item = item.substring(0, item.length()-1); ////回溯的时候移除刚刚添加的字符
			}
		}
	}
	
	public boolean isValid(String str){
		Stack<Character> stack = new Stack<Character>();
		if(str == null)
			return true;

		for(int i = 0; i < str.length(); i++){
			Character c = str.charAt(i);
			if(c.equals('(')){
				stack.push(c);
				
			}
			else{
				if(!stack.isEmpty()){
					Character temp = stack.peek();
					if(temp.equals('('))
						stack.pop();
				}
				else
					stack.push(c);
			}
		}
		if(stack.isEmpty())
			return true;
		else
			return false;
	}
```

## S.37_Sudoku Solver（未完成）

原题地址：https://leetcode.com/problems/sudoku-solver/

题目：



## S.79_Word Search

原题地址：https://leetcode.com/problems/word-search/

题目：

>Given a 2D board and a word, find if the word exists in the grid.
>
>The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.
>
>For example,
>Given **board** =
>
>```
>[
>  ['A','B','C','E'],
>  ['S','F','C','S'],
>  ['A','D','E','E']
>]
>
>```
>
>**word** = `"ABCCED"`, -> returns `true`,
>
>**word** = `"SEE"`, -> returns `true`,
>
>**word** = `"ABCB"`, -> returns `false`.
>

思路：





代码：

```java
public boolean exist(char[][] board, String word) {
	        int m = board.length;  
	        int n = board[0].length;  
	      //每当选定一个字母之后，用过的位置就不能再用了，所以需要设立一个数组来标记这个位置是否访问过了
	        boolean[][] visited = new boolean[m][n];  
	      //这个地方要写成双重循环，因为可能从这个二维数组的任何一个地方开始。所以，每个位置都要遍历到
	        for (int i = 0; i < m; i++) {  
	            for (int j = 0; j < n; j++) {  
	            	
	                if (dfs(board, word, 0, i, j, visited))  
	                    return true;  
	            }  
	        }  
	        return false;  
	    }
	    
	    public boolean dfs(char[][] board, String word, int index, int rowindex, int colindex, boolean[][] visited) {  
	    	
	        if (index == word.length())  
	            return true;  
	        if (rowindex < 0 || colindex < 0 || rowindex >=board.length || colindex >= board[0].length)  
	            return false;  
	        if (visited[rowindex][colindex])  
	            return false;  
	        if (board[rowindex][colindex] != word.charAt(index))  //因为这个地方相等了，所以下面dfs的地方才有index + 1.
	            return false;  
	        visited[rowindex][colindex] = true;  
	        boolean res = dfs(board, word, index + 1, rowindex - 1, colindex,  
	                visited)  
	                || dfs(board, word, index + 1, rowindex + 1, colindex, visited)  
	                || dfs(board, word, index + 1, rowindex, colindex + 1, visited)  
	                || dfs(board, word, index + 1, rowindex, colindex - 1, visited);  
	        visited[rowindex][colindex] = false;  //回溯的过程中重新把该位置置为false，表示可以重新访问。
	        return res;  
	   }
```

# 广度优先部分

## S.127_Word Ladder

原题地址：https://leetcode.com/problems/word-ladder/

题目：

>Given two words (*beginWord* and *endWord*), and a dictionary's word list, find the length of shortest transformation sequence from*beginWord* to *endWord*, such that:
>
>1. Only one letter can be changed at a time
>2. Each intermediate word must exist in the word list
>
>For example,
>
>Given:
>*beginWord* = `"hit"`
>*endWord* = `"cog"`
>*wordList* = `["hot","dot","dog","lot","log"]`
>
>As one shortest transformation is `"hit" -> "hot" -> "dot" -> "dog" -> "cog"`,
>return its length `5`.
>
>**Note:**
>
>- Return 0 if there is no such transformation sequence.
>- All words have the same length.
>- All words contain only lowercase alphabetic characters.

思路：

​	这道题是套用BFS同时也利用BFS能寻找最短路径的特性来解决问题。

​	把每个单词作为一个node进行BFS。当取得当前字符串时，对他的每一位字符进行从a~z的替换，如果替换之后的字符串在字典里面，就入队，并将下层count++(即nextnum++)，并且为了避免环路，需把在字典里检测到的单词从字典里删除。这样对于当前字符串的每一位字符安装a~z替换后，在queue中的单词就作为下一层需要遍历的单词了。

​	正因为BFS能够把一层所有可能性都遍历了，所以就保证了一旦找到一个单词equals（end），那么return的路径肯定是最短的。

​	**理解上的一个难点**：curnum的含义是有多少个候选项。什么是候选项呢，就是从前一个状态往后扩展的时候，一共可以扩展出多少个状态。当curnum = 0 的时候，说明所有的候选项都尝试过找新的状态了，这个时候可以往新的一层走。

代码：

```java
public int ladderLength(String start, String end, Set<String> dict) {
		if(start == null || end == null 
				|| start.length() == 0 || end.length() == 0
				|| start.length()!=end.length())
			return 0;
		
		LinkedList<String> wordQueue = new LinkedList<String>();
		int level = 1;  //the start string already count for 1
		int curnum = 1; // the candidate num on current level
		int nextnum = 0; //counter for next level
		
		wordQueue.add(start);
		while(!wordQueue.isEmpty()){
			String word = wordQueue.poll();
			curnum--;
			
			for(int i = 0; i < word.length(); i++){
				char[] wordunit = word.toCharArray();
				for(char j = 'a'; j <= 'z'; j++){
					wordunit[i] = j;
					String temp = new String(wordunit);
					
					if(temp.equals(end))
						return level+1;   //  if found, return the result. 
					if(dict.contains(temp)){
						
						wordQueue.add(temp);
						nextnum++;
						dict.remove(temp);
					}
					
				}
			}
			if(curnum == 0){
				curnum = nextnum;
				nextnum = 0;
				level++;
			}
		}
		return 0;
    }
```



