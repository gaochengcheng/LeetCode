package bishi;

import org.junit.Test;

public class KthMax {

	public void KthMaxNum(int[] list, int k){
		if(list == null || k < 0 || k > list.length-1)
			return;
		int start = 0;
		int end = list.length-1;
		int index = partition(list, start, end);
		while(index != k-1){
			if(index > k-1){
				end = index-1;
				index = partition(list, start, end);
			}
			else{
				start = index + 1;
				index = partition(list, start, end);
			}
			
		}
		System.out.println(list[index]);
	}




	public int partition(int[] list, int start, int end){
		int pivot = list[start];
		while(start < end){
			while(start < end && list[end] > pivot)
				end--;
			list[start] = list[end];
			while(start < end && list[start] <= pivot)
				start++;
			list[end] = list[start];
		}
		list[start] = pivot;
		return start;
	}
	@Test
	public void test(){
		int[] list = { 2, 3, 2, 5, 6, 1, -2, 3, 14, 12 };
		
		KthMaxNum(list,8);
		
	}
}
