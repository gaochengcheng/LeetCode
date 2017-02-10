package bishi_stringQuestions;

import org.junit.Test;

/**
 * 时间复杂度O(n),空间复杂度O(1).
 * 做好每个字符的判断工作，当完成一次遍历的时候，生成累加和。
 * @author chengcheng
 * @time 2017年2月9日 下午2:49:37
 *
 */
public class numSum {
	public static void main(String[] args) {
		
	}
	
	public int numSum_solution(String str){
		if(str == null)
			return 0;
		char[] charArr = str.toCharArray();
		int res = 0;
		int num = 0;
		boolean posi = true;
		
		int cur = 0;
		for(int i = 0; i<charArr.length; i++){
			cur = charArr[i]-'0';
			if(cur <0 || cur > 9){
				res += num;
				num = 0;
				if(charArr[i] =='-'){//当前字符是 '-'
					if(i-1>-1 && charArr[i-1] == '-'){//当前字符的前一个是'-'
						posi = !posi;
					}
					else{//当前字符的前一个是字母
						posi = false;
					}
				}else{//当前字符是字母
					posi = true;
				}
			}
			else{ //当前字符是数字
				num = num * 10 + (posi?cur:-cur);
			}
		}
		res += num;
		return res;
	}
	@Test
	public void test(){
		System.out.println(numSum_solution("A-1B--2C--D6E"));
	}
}
