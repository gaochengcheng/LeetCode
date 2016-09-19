package question1_20;

import java.util.Stack;

import org.junit.Test;

/**
 * 
 * @author chengcheng
 * @time 2016年9月19日 上午9:39:15
 *
 */
public class S20_ValidParentheses {
	public boolean isValid(String s) {
        if(s == null || s.length() == 0)
        	return false;
        String[] strs = s.split("");
        int length = strs.length;
        if(length %2 != 0)
        	return false;
        Stack<String> stack = new Stack<String>();
        int i = 0;
        while(i < length){
        	String str = strs[i];
        	if(str.equals("(") || str.equals("[") || str.equals("{")){
        		stack.push(str);
        		i++;
        	}
        	
        	else if(str.equals(")") ){
        		if(!stack.isEmpty()){
        			String temp = stack.peek();
        			if(temp.equals("(")){
            			i++;
            			stack.pop();
            		}
        			else
        				return false;
        		}
        		else{
        			stack.push(str);
        			i++;
        		}
        	}
        	else if(str.equals("]") ){

        		if(!stack.isEmpty()){
        			String temp = stack.peek();
        			if(temp.equals("[")){
            			i++;
            			stack.pop();
            		}
        			else
        				return false;
        		}
        		else{
        			stack.push(str);
        			i++;
        		}
        	
        		
        	}
        	else if(str.equals("}") ){

        		if(!stack.isEmpty()){
        			String temp = stack.peek();
        			if(temp.equals("{")){
            			i++;
            			stack.pop();
            		}
        			else
        				return false;
        		}
        		else{
        			stack.push(str);
        			i++;
        		}
        	}
        		
        }
        if(stack.isEmpty())
        	return true;
        else
        	return false;
    }
	@Test
	public void test(){
		String s = "()[]{}";
		System.out.println(isValid(s));
	}
}
