package bishi_string;

import org.junit.Test;
/**
 * 判断str2是否是str1的反转词
 * 方法：
 *     构建一个新的字符串str3，str3是两个str2拼接起来。如果str1包含于str3中，说明str2是str1的反转词。
 *     其中，判断str3是否包含str1时，使用到KMP算法进行判断。getIndexOf()函数是KMP算法的实现。
 * @author chengcheng
 * @time 2017年2月10日 下午2:33:55
 *
 */
public class IsRotation {
	public boolean isRotation(String str1, String str2){
		if(str1 == null || str2 == null || str1.length() != str2.length())
			return false;
		
		String str3 = str2 + str2;
		return getIndexOf(str3 ,str1)!=-1;
		
	}
	
	public int getIndexOf(String a, String b){
		return -1;
	}
	
	@Test
	public void test(){
		
	}
}
