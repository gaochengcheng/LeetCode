package bishi_stringQuestions;

import org.junit.Test;

/**
 * 问题：判断两个字符串是否互为变形词
 * 
 * @author chengcheng
 * @time 2016年11月13日 下午9:53:31
 *
 */
public class IsDeformation {
	public static void main(){
		
	}
	
	public boolean solution(String str1, String str2){
		if(str1 == null || str2 == null || str1.length() != str2.length())
			return false;
		char[] c1 = str1.toCharArray();
		char[] c2 = str2.toCharArray();
		int[] map = new int[256];
		
		for(int i = 0; i<c1.length; i++){
			map[c1[i]]++;
		}
		for(int i = 0; i<c2.length; i++){
			System.out.println(map[c2[i]]);
			if(map[c2[i]]-- == 0)
				return false;
		}
		return true;
	}

	@Test
	public void test(){
		System.out.println(solution("abcc","abcc"));
	}
	
}
