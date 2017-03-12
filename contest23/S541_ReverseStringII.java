package contest23;

import org.junit.Test;

/**
 * 
 * @author chengcheng
 * @time 2017年3月12日 上午10:44:50
 *
 */
public class S541_ReverseStringII {
	public String reverseStr(String s, int k) {
		if(s == null || s.length() == 0)
			return s;
		
		char[] arr = s.toCharArray();
		for(int i=0; i<arr.length; ){
			
			//下面两if语句可以合并成一句
			if(i+2*k < arr.length){
				swap(arr, i, i+k-1);
				i=i+2*k;
			}
			else if(i+k < arr.length){
				swap(arr, i, i+k-1);
				i=i+2*k;
			}
			else{
				swap(arr, i, arr.length-1);
				break;
			}
			
		}
        return new String(arr);
    }
	public void swap(char[] arr, int start, int end){
		while(start < end){
			char temp = arr[start];
			arr[start] = arr[end];
			arr[end] = temp;
			start++;
			end--;
		}
	}
	
	@Test
	public void test(){
//		String s = "abcdefg";
		String s = "abcd";
		System.out.println(reverseStr(s,2));
	}
}
