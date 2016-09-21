package bishi;

import java.util.Scanner;

import org.junit.Test;

/**
 * 
 * @author chengcheng
 * @time 2016年9月21日 下午3:53:16
 *
 */
public class Baoshi {
	
	public int calculate(String s){
		if(s == null || s.length() == 0)
			return 0;
		
		
		
	}
	public void solution(){
		Scanner sc = new Scanner(System.in);
		
		while(sc.hasNext()){
			String s = sc.nextLine();
			System.out.println(calculate(s));
		}
		
		
		
	}
	@Test
	public void test(){
		
	}
}
