package bishi;

import java.util.Scanner;

public class ones {

	public static void main(String[] args){
		
		Scanner sc = new Scanner(System.in);
		while(sc.hasNext()){
			int n = sc.nextInt();
			numberOf1(n);
			numberOf2(n);
		}
	}
	
	public static void numberOf1(int n){
		int count = 0;
		int flag = 1;
		while(flag>=0){
			
			if((n & flag) >= 1)
				count++;
			flag = flag<<1;
		}
		System.out.println(count);
	}
	
	public static void numberOf2(int n){
		int count = 0;
		while(n>0){
			if((n&1) == 1)
				count++;
			n=n>>1;
		}
		System.out.println(count);
	}
}
