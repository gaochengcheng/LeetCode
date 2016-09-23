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

如果现在从2,3的位置出发，有两条路径可以选。分别是向右和向下走。同理`2,2`的位置也是两条路。`2,1`的位置是一条路。`1,3`的位置是一条路。`1,2`的位置是一条路。`1,1`的位置是终点，是收敛的地方。 ![DFS](pics\DFS.PNG)

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

**右下角位置的值 = 其左边一个位置的值 + 其上边一个位置的值**  ![动态规划图解](pics\动态规划图解.PNG)

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



