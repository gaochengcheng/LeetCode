package bishi_array;

import org.junit.Test;

/**
 * 
 * @author chengcheng
 * @time 2016年9月26日 上午10:53:02
 *
 */
public class S79_WordSearch {
	 public boolean exist_2(char[][] board, String word) {
	      if(board == null || board.length == 0)
	    	  return false;
	      //这个地方要写成双重循环，因为可能从这个二维数组的任何一个地方开始。所以，每个位置都要遍历到
	      
	      return true;
	 }
	 
	 public boolean dfs_2(char[][] board, int x, int y,  String item, String word){
		 if(item.length() == word.length()){
			 if(item.equals(word))
				 return true;
			 return false;
		 }
		 int depth = board.length;
		 int width = board[0].length;
		 if(item.length() < word.length()){
			 if(x>=1)
				 dfs_2(board, x-1, y, item+board[x][y], word);
			 if(x<=width-2)
				 dfs_2(board, x+1, y, item+board[x][y], word);
			 if(y>=1)
				 dfs_2(board, x, y-1, item+board[x][y], word);
			 if(y<=depth-2)
				 dfs_2(board, x, y+1, item+board[x][y], word);
				 
		 }
		 return true;
		 
	 }
	 @Test
	 public void test(){
		 char[][] board = {{'A','B','C','E'},{'S','F','S','S'},{'A','D','E','E'}};
		 String word = "SEE";
		 System.out.println(exist(board,word));
	 }
	 
	 /**
	  * reference
	  */
	 public boolean exist(char[][] board, String word) {
	        int m = board.length;  
	        int n = board[0].length;  
	      //每当选定一个字母之后，用过的位置就不能再用了，所以需要设立一个数组来标记这个位置是否访问过了
	        boolean[][] visited = new boolean[m][n];  
	      //这个地方要写成双重循环，因为可能从这个二维数组的任何一个地方开始。所以，每个位置都要遍历到
	        for (int i = 0; i < m; i++) {  
	            for (int j = 0; j < n; j++) {  
	            	System.out.println(i + " " + j);
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
	 
}
