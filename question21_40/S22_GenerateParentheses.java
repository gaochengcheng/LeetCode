package question21_40;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.junit.Test;

/**
 * 
 * @author chengcheng
 * @time 2016年9月26日 上午8:43:20
 *
 */
public class S22_GenerateParentheses {
	public List<String> generateParenthesis(int n) {
		List<String> res = new ArrayList<String>();
		if(n == 0)
			return res;
		String[] str = {"(",")"};
		dfs(n*2, str, "", res);
		return res;
		
    }
	
	public void dfs(int count, String[] str, String item, List<String> res){
		if(count == 0){
			if(isValid(item)){
				System.out.println(item);
				res.add(item);
			}
			return ;
		}
		if(count < 0)
			return ;
		if(count > 0){
			for(int i = 0; i < str.length; i++){
				int newCount = count - 1;
				item = item+str[i];
				dfs(newCount, str, item, res);
				item = item.substring(0, item.length()-1);
			}
		}
	}
	
	public boolean isValid(String str){
		Stack<Character> stack = new Stack<Character>();
		if(str == null)
			return true;

		for(int i = 0; i < str.length(); i++){
			Character c = str.charAt(i);
			if(c.equals('(')){
				stack.push(c);
				
			}
			else{
				if(!stack.isEmpty()){
					Character temp = stack.peek();
					if(temp.equals('('))
						stack.pop();
				}
				else
					stack.push(c);
			}
		}
		if(stack.isEmpty())
			return true;
		else
			return false;
	}
	
	@Test
	public void test(){
		String str = "(())))";
		//  generateParenthesis
		System.out.println(generateParenthesis(3));
	}
}
