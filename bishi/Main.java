package bishi;
import java.util.Scanner;
import java.math.BigInteger;

/**
 * 大数求阶乘
 * @author chengcheng
 * @time 2016年9月18日 下午4:32:12
 *
 */
public class Main {
	
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
    	Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        BigInteger big = BigInteger.ONE;    
        
        //计算阶乘，虽然和计算普通阶乘的方法一样，但由于使用了BigInteger类型，所以可以计算很大的数。
        for(int i=1;i<=n;i++)
            big=big.multiply(new BigInteger(i+""));
        
        System.out.println(big);
        String num = big+"";
        int count = 0;
		for(int i = num.length()-1; i>=0; i--){
			if(num.charAt(i) == '0'){
				count++;
			}
			else
				break;
		}
		System.out.println(count);
        sc.close();

    }

}