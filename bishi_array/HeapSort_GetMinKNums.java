package bishi_array;

import org.junit.Test;

public class HeapSort_GetMinKNums {
	public int[] getMinKNumbsByHeap(int[] arr, int k){
		if(k<1 || k>arr.length){
			return arr;
		}
		int[] kHeap = new int[k]; 
		for(int i=0; i!=k; i++){
			heapInsert(kHeap, arr[i], i);  //往堆中插入k个数
		}
		for(int i=k; i!=arr.length; i++){
			if(arr[i] < kHeap[0]){   
				kHeap[0] = arr[i];  //堆顶的元素就被更新了
				heapify(kHeap, 0, k);
			}
		}
		return kHeap;
	}
	
	//建立堆
	public void heapInsert(int[] arr, int value, int index){
		arr[index] = value;
		while(index != 0){
			int parent = (index-1)/2;    //取到该元素的parent.
			if(arr[parent] <arr[index]){  //每插入一个数，都要动态调整这个堆 ，调整的过程为从当前插入的位置往上走
				swap(arr, parent, index);
				index = parent;
			}else{
				break;
			}
		}
	}
	
	//调整堆，从上往下
	public void heapify(int[] arr, int index, int heapSize){
		
		int left = index*2+1;
		int right= index*2+2;
		
		int largest = index;
		
		while(left < heapSize){
			if(arr[left] > arr[index]){
				largest = left;
			}
			if(right < heapSize && arr[right] > arr[largest]){
				largest = right;
			}
			if(largest != index){
				swap(arr, largest, index);
			}else{
				break;
			}
			
			index = largest;
			left = index*2+1;
			right = index*2+2;
		}
		
	}
	public void swap(int[] arr, int parent, int index){
		int temp = parent;
		arr[parent] = arr[index];
		arr[index] = temp;
		
	}
	
	@Test
	public void test(){
		int[] arr = {5,4,3,2,1,10,9,8,7,6};
		int[] res = getMinKNumbsByHeap(arr,3);
		for(int i: res)
			System.out.print(i+ "  ");
	}
}
