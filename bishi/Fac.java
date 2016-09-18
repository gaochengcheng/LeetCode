package bishi;

import java.util.Scanner;

public class Fac {  
      public Fac() {  
    }  
      
    public static void Calc(int n)  
    {  
        int RAD=10000;  
        int buffSize=(int)(n * Math.log10((n+1)/2) / Math.log10(RAD)+1);  
        short[] buff = new short[buffSize];  
        int len=1;  
        buff[0]=1;  
        for (int i=1;i<=n;i++)  
        {  
            int c=0;  
            for (int j=0;j<len;j++)  
            {  
                int prod=(buff[j]*i+c);  
                buff[j]=(short)(prod % RAD);  
                c=prod / RAD;  
            }  
            while (c>0)  
            {  
                buff[len++]= (short)(c % RAD);  
                c=c/RAD;  
            }  
        }  
          
        System.out.printf("%d!=%d", n, buff[len-1]);
        for (int i=len-2;i>=0;i--)  
            System.out.printf("%04d",buff[i]);  
        
        
        int count = 0;
        for (int i=len-1;i>=0;i--) {
        	System.out.println(buff[i]);
        	if(buff[i] == 0){
        		count++;
        	}
        	else
        		break;
        }
        System.out.println(count);
     }  
  
    public static void main(String[] args) {  
       System.out.println("Please input a integer");  
       Scanner in=new Scanner(System.in);  
       int n=in.nextInt();  
       Calc(n);  
    }  
}  