package bishi_stringQuestions;

import org.junit.Test;

/**
 * question:在str中，连续删除k个0，返回删除后的字符串。
 * 
 * @author chengcheng
 * @time 2017年2月9日 下午3:18:49
 *
 */
public class removeKZeroes {
	public String removeKZeroes_solution(String str, int k){
		if(str == null || k<1)
			return str;
		char[] charArr = str.toCharArray();
		int start = -1;
		int count = 0;
		for(int i=0; i<str.length(); i++){
			//1.  从头到尾遍历str，对访问到的每一个字符进行判断。
			//2.  如果是'0'则进行计数，记录起始位置。
			//3.  如果不是则尝试删除，不管是否删除成功，都恢复count和start的值，这样避免了在2中嵌套循环
			if(charArr[i] == '0'){
				count++;
				start = start == -1 ?i:start;
				
			}
			else{
				if(count == k){
					//删除k个0
					while(count-- != 0)
						charArr[start++] = 0;   //0对应的ASCII码字符是空字符
				}
				count = 0;
				start = -1;
			}
			
			
		}
		if(count == k){
			//删除k个0
			while(count-- != 0)
				charArr[start++] = 0;
		}
		return String.valueOf(charArr);
	}
	@Test
	public void test(){
		System.out.println(removeKZeroes_solution("A00B",2));
	}
}
