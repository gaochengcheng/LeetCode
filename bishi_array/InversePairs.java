package bishi_array;

import org.junit.Test;

public class InversePairs {

	public int inversePairs(int[] data){
		if(data == null || data.length == 0)
			return 0;
		
		int[] copy = new int[data.length];
		for(int i = 0; i<data.length; i++)
			copy[i] = data[i];
		
		int count = inversePairsCore(data,copy,0,data.length-1);
		return count;
		
	}
	
	public int inversePairsCore(int[] data, int[] copy, int start, int end){
		if(start == end){
			copy[start] = data[end];
			return 0;   //有0个逆序对
		}
		
		int length = (end-start)/2;
		int left = inversePairsCore(data,copy,start,start+length);
		int right = inversePairsCore(data,copy,start+length+1,end);
		
		//i初始化为前半段数组最后一个数字的下标
		int i = start+length;
		//j初始化为后半段数组最后一个数字的下标
		int j = end;
		int count = 0;
		//copy数组中下标,初始化为最大值，并逐渐减小
		int indexCopy = end;
		
		while(i>=start && j >=start+length+1){
			if(data[i]>data[j]){
				copy[indexCopy--] = data[i--];
				count = count+(j-(start+length+1)+1);
			}
			else{
				copy[indexCopy--] = data[j--];
			}
		}
		
		while(i>=start){
			copy[indexCopy--] = data[i--];
		}
		
		while(j>=start+length+1){
			copy[indexCopy--] = data[j--];
		}
		
		return left+right+count;
				
	}
	
	@Test
	public void test(){
		int[] data =  {1, 2, 1, 2, 1};
		//expected 3
		System.out.println(inversePairs(data));
	}
}
