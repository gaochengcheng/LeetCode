package princetonSort;

public class QuickSort {
	public static void quickSort(int[] list) {
		quickSort(list, 0, list.length - 1);
	}

	private static void quickSort(int[] list, int first, int last) {
		if (last > first) {
			int pivotIndex = partition(list, first, last);
			quickSort(list, first, pivotIndex - 1);
			quickSort(list, pivotIndex + 1, last);
		}
	}

	/** Partition the array list[first..last] */
  private static int partition(int[] list, int first, int last) {
    int pivot = list[first]; // Choose the first element as the pivot
    int low = first; // Index for forward search
    int high = last; // Index for backward search

    while (high > low) {
    	// Search backward from right
    	while(high > low && list[high] >= pivot)
    		--high;
    	list[low] = list[high];  //此时list[high]是小于pivot,所以到左边
    	
    	//Search forward from left
    	while(high > low && list[low] <= pivot)
    		++low;
    	list[high] = list[low];
    }  
    list[low] = pivot;
    return low;
  }

	/** A test method */
	public static void main(String[] args) {
		int[] list = { 2, 3, 2, 5, 6, 1, -2, 3, 14, 12 };
		quickSort(list);
		for (int i = 0; i < list.length; i++)
			System.out.print(list[i] + " ");
	}
}
