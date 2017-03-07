package bishi_string;

import org.junit.Test;

/**
 * 
 * @author chengcheng
 * @time 2016年9月17日 下午8:20:51
 *
 */
public class S38_CountandSay {
	public String countAndSay(int n) {
	    if(n == 0)
	    	return "";
	    
	    int start = 1;
	    String res = "1";
	    int count = 1;
	    while(start < n){
	    	StringBuffer curRes = new StringBuffer();
	    	count = 1;
	    	for(int j = 1; j<res.length(); j++){
	    		if(res.charAt(j) == res.charAt(j-1))
	    			count++;
	    		else{
	    			curRes.append(count);
	    			curRes.append(res.charAt(j-1));
	    			count = 1;
	    		}
	    			
	    	}
	    	
	    	curRes.append(count);
	    	curRes.append(res.charAt(res.length()-1));
	    	res = curRes.toString();
	    	start++;
	    }
	    
	    return res;
	}
	@Test
	public void test(){
//		for(int i = 1; i <=7; i++){
			
			System.out.println(countAndSay(5));
//		}
	}
}
