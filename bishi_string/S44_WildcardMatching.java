package bishi_string;

import org.junit.Test;

/**
 * 
 * @author chengcheng
 * @time 2016年9月12日 上午10:16:05
 *
 */
public class S44_WildcardMatching {
	public boolean isMatch(String s, String p) {
		int indexS = 0;
		int indexP = 0;
		int preP = -1;
		int preS = -1;
		while (indexS < s.length()) {
			if (indexP < p.length() && (p.charAt(indexP) == '?' || p.charAt(indexP) == s.charAt(indexS))) {
				indexP++;
				indexS++;
			} else if (indexP < p.length() && p.charAt(indexP) == '*') {
				preP = indexP; // 记录 * 出现的位置
				preS = indexS; //
				indexP++;
			} else if (preP != -1) {
				// indexP = preP +1;
				// indexS = preS++;
				indexP = preP;
				indexP++;

				preS++;
				indexS = preS;

			} else {
				return false;
			}

		}
		// 看不懂的写法
		// while(indexP < p.length() && p.charAt(indexP) == '*'){
		// indexP++;
		// }
		// return indexP == p.length();
		while (indexP < p.length()) {
			if (p.charAt(indexP) != '*') {
				return false;
			}
			indexP++;
		}
		return true;
	}

	@Test
	public void test() {
		String s = "dbcefgsdfhj";
		String p = "db*hj";
		System.out.println(isMatch(s, p));
	}
}
