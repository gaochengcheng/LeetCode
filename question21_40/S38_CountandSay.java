package question21_40;

import org.junit.Test;

/**
 * 
 * @author chengcheng
 * @time 2016年9月17日 下午8:20:51
 *
 */
public class S38_CountandSay {
	public String countAndSay(int n) {
	    if(n<=0)
	        return "";
	    String curRes = "1";
	    int start = 1;//从1开始算
	    while(start < n){
	        StringBuilder res = new StringBuilder();
	        int count = 1;
	        for(int j=1;j<curRes.length();j++){
	            if(curRes.charAt(j)==curRes.charAt(j-1))
	                count++;
	            else{
	                res.append(count);
	                res.append(curRes.charAt(j-1));
	                count = 1;
	            }
	        }
	        res.append(count);
	        res.append(curRes.charAt(curRes.length()-1));
	       
	        System.out.println("res is : "+res);
	        
	        curRes = res.toString();
	        start++;
	    }
	    return curRes;
	}
	@Test
	public void test(){
//		for(int i = 1; i <=7; i++){
			
			System.out.println(countAndSay(5));
//		}
	}
}
