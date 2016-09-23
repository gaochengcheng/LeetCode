package question81_100;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * 
 * @author chengcheng
 * @time 2016年9月23日 下午7:02:25
 *
 */
public class S93_RestoreIPAddresses {
	public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<String>();
        if(s.length() < 4 || s.length() > 12)
        	return res;
        dfs(s,"",res,0);  
        return res;
        
    }
	
	public void dfs(String s, String tmp, List<String> res, int count){
		if(count == 3 && isValid(s)){
			res.add(tmp + s);
			return ;
		}
		/**
		 * 每个Part 可能由一个字符组成，二个字符组成，或者是三个字符组成。那这又成为组合问题了，dfs便呼之欲出
		 * 所以我们写一个For循环每一层从1个字符开始取一直到3个字符，再加一个isValid的函数来验证取的字符是否是合法数字，
		 * 如果是合法的数字，我们再进行下一层递归，否则跳过。
		 */
		for(int i = 1; i < 4 && i<s.length(); i++){
			String substr = s.substring(0,i);
			System.out.println(substr);
			if(isValid(substr)){
				
				dfs(s.substring(i), tmp + substr + '.', res, count+1);
			}
		}
			
	}
	
	public boolean isValid(String s){
		if(s.charAt(0) == '0')
			return s.equals("0");
		int num = Integer.parseInt(s);
		return num <= 255 && num > 0;
	}
	@Test
	public void test(){
		System.out.println(restoreIpAddresses_2("0000"));
	}
	
	public List<String> restoreIpAddresses_2(String s) {
        List<String> res = new ArrayList<String>();
        if(s.length() < 4 || s.length() > 12)
            return res;
        dfs_2(s, "", res, 0);
        return res;
    }
    public void dfs_2(String s, String tmp, List<String> res, int count){
        if(count == 3){
            if(isValid_2(s)){
                tmp += s;
                res.add(tmp);
            }
            return ;
        }
        
        for(int i = 1; i<4; i++){
            String subStr = s.substring(0,i);
            if(isValid_2(subStr)){
                dfs(s.substring(i), tmp + subStr+'.', res, count+1);
            }
        }
    }
    
    public boolean isValid_2(String s){
        if(s.charAt(0) == '0')
            return s.equals("0");
        int num = Integer.parseInt(s);
        return num <= 255 && num >0;
    }
}
