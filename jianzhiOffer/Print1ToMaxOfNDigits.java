package jianzhiOffer;

import org.junit.Test;

public class Print1ToMaxOfNDigits {
	
	public void print1ToMaxOfDigits(int n){
		
		if(n<0)
			return ;
		
		int[] number = new int[n+1];
		number[n] = '\0';
		for(int i=0; i< 10; i++){
			number[0]= (i+'\0');
			print1ToMaxOfDigitsRecursively(number,n,0);
		}
	}
	
	public void print1ToMaxOfDigitsRecursively(int[] number, int length, int index){
		//递归停止的条件
		if(index == length-1){
			PrintNumber(number);
			return ;
		}
		for(int i = 0; i < 10; i++){
			number[index+1]=(i+'\0');
			print1ToMaxOfDigitsRecursively(number, length,index+1);
		}
	}
	
	public void PrintNumber(int[] number){
		boolean isBeginning0 = true;
		int nLength = number.length;
		
		for(int i = 0; i < nLength; i++){
			if(isBeginning0 && number[i] != '\0')
				isBeginning0 = false;
			if(!isBeginning0)
				System.out.print(number[i]);
		}
	}
	@Test
	public void test(){
		print1ToMaxOfDigits(8);
	}
}
