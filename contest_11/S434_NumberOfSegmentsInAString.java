package contest_11;

import org.junit.Test;

public class S434_NumberOfSegmentsInAString {
	public int countSegments(String s) {
        if(s == null || s.length() == 0)
        	return 0;
        String[] strs = s.split(" ");
        return strs.length;
    }
	@Test
	public void test(){
		String s = "Hello, my name is John";
		System.out.println(countSegments(s));
	}
}
