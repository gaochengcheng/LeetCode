package jianzhiOffer;

import org.junit.Test;

public class quickSort {
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

	public void Sort(int[] list, int start, int end){
		if(start < end){
			int pivotIndex = partition(list, start, end);
			Sort(list, start, pivotIndex-1);
			Sort(list, pivotIndex+1,end);
		}
	}

	public void quickSortMain(int[] list){
		if(list == null)
			return ;
		int start = 0;
		int end = list.length-1;
		Sort(list, start, end);
	}
	
	@Test
	public void test(){
		int[] list = {1,3,2,5,8,4,10,15,12,7};
		for(int ele: list)
			System.out.print(ele+ " ");
		System.out.println();
		quickSortMain(list);
		for(int ele:list)
			System.out.print(ele + " ");
	}
}
