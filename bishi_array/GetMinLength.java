package bishi_array;

import org.junit.Test;

public class GetMinLength {
	public int getMinLength(int[] arr){
		if(arr == null || arr.length < 2)
			return 0;
		
		int Lindex = -1;
		int Rindex = -1;
		for(int i=arr.length-1; i>0; i--){
			if(arr[i]>arr[i-1])
				Rindex=i-1;
			else
				break;
		}
		
		for(int i=0; i<arr.length-1; i++){
			if(arr[i]<arr[i+1])
				Lindex=i;
			else
				break;
		}
		
		return Rindex-Lindex;
	}
	@Test
	public void test(){
		int[] arr = {1,5,3,4,2,6,7};
		System.out.println(getMinLength(arr));
	}
}
