package question461_480;

import org.junit.Test;

/**
 * 给定一个字符串，用“-”进行分割。
 *  输入k值，表示分割后的每部分长度为k。（第一部分可以除外，但是第一部分至少有一个字符或者数字）
 * 
 * @author chengcheng
 * @time 2017年1月8日 上午11:16:24
 *
 */
public class S482_LicenseKeyFormatting {
	// k == 3
	//input:2-4A0r7-4k
	//output:24-A0R-74K
	public String licenseKeyFormatting(String S, int K) {
		S = S.toUpperCase();
		String[] strs =S.split("-");
		String res = "";
		for(String s : strs)
			res+=s;
		int length = res.length()-1;
		int startLen = length % K;
		StringBuffer result = new StringBuffer(res);
		for(int i = length; i>=0;){
			i = i-K;
			if(i>=0){
				result.insert(i+1, "-");
			}
		}
		
		return result+"";
	}
	@Test
	public void test(){
		String s = "24-A0r-74k";
		System.out.println(licenseKeyFormatting(s,3));
	}
}
