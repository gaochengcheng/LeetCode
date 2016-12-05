package contest_11;

import org.junit.Test;

public class S434_NumberOfSegmentsInAString {
	public int countSegments(String s) {
        if(s == null || s.length() == 0)
        	return 0;
        String split = "";
        String[] strs = s.split(" ");//按照空格分割，空格和空格之间其实是什么都没有的
        int cnt = 0;
        for(int i = 0; i<strs.length; i++){
        	System.out.println(strs[i]);
        	System.out.println(split);
        	if(!strs[i].equals(split))
        		cnt++;
        }
        return cnt;
    }
	@Test
	public void test(){
		String s = ", , , ,        a, eaefa";
		System.out.println(countSegments(s));
	}
}
