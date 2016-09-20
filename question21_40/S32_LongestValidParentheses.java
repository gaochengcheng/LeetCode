package question21_40;

import java.util.Stack;

import org.junit.Test;

/**
 * 
 * @author chengcheng
 * @time 2016年9月20日 上午8:58:45
 *
 */
public class S32_LongestValidParentheses {
	
	public static int longestValidParentheses(String s) {
		
		if (s == null || s.length() == 0) {
			return 0;
		}

		int start = 0;
		int maxLen = 0;
		Stack<Integer> stack = new Stack<Integer>();

		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(') {
				stack.push(i);
			} else {
				if (stack.isEmpty()) {
					// record the position before first left parenthesis
					//记录第一个左括号的位置
					start = i + 1;
				} else {
					
					//碰到一个右括号，就弹出一个栈中的左括号
					stack.pop();  
					// if stack is empty mean the positon before the valid left
					// parenthesis is "last"
					if (stack.isEmpty()) {
						maxLen = Math.max(i - start + 1, maxLen);
					} else {
						// if stack is not empty, then for current i the longest
						// valid parenthesis length is
						// i-stack.peek()
						maxLen = Math.max(i - stack.peek(), maxLen);
					}
				}
			}
		}
		return maxLen;
	}
	
	
	public int longestValidParentheses_2(String s) {
        if(s == null || s.length() == 0)
        	return 0;
        
        String[] strs = s.split("");
        int length = strs.length;
        int count = 0;
        Stack<String> stack = new Stack<String>();
        int i = 0;
        while(i < length){
        	String str = strs[i];
        	if(str.equals("(")){//如果是左括号，那么一律入栈。
        		stack.push(str);
        		
        	}
        	else{//这块关于右括号的判断不够。右括号的情况比较多。
        		if(!stack.isEmpty()){
        			String temp = stack.peek();
        			if(temp.equals("(")){
        				count = count + 2;
        				stack.pop();
        			}
        			else{
        				stack.push(str);
        			}
        				
        		}
        	}
        	
        	i++;
        }
        return count;
    }
	
	
	@Test
	public void test(){
//		String s = "()(((()()()";
		String s = "))()()()";
		System.out.println(longestValidParentheses(s));
	}
}	
