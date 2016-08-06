package question21_40;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class S36_ValidSudoku {
	private  Set<Character> set = new HashSet<Character>();
	public static void main(String[] args) {

	}
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
				if(!check(board[i/3*3+j/3][i%3*3+j%3]))
					return false;
			}
			set.clear();
		}
		
		return true;
	}
	
	@Test
	public void test(){
		
	}
}
