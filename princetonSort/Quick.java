package princetonSort;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 
 * @author chengcheng
 * @time 2016年10月2日 上午11:47:03
 *
 */
public class Quick {
	
	public static void main(String[] args){
		Integer[] arr = {1,6,2,4,5,9,3,7,8};
		sort(arr);
		for(int i:arr)
			System.out.print(i+" ");
	}
	public static void sort(Comparable[] a){
		
		sort(a, 0, a.length);
		
	}
	
	private static void sort(Comparable[] a, int lo, int hi){
		if(hi <= lo) return;
		int j = partition(a, lo, hi);
		sort(a, lo, j-1);
		sort(a, j+1, hi);
	}
	
	private static int partition(Comparable[] a, int lo, int hi){
		//Partition into a[lo..i-1], a[i], a[i+1,..hi].
		int i = lo, j = hi+1;
		Comparable v = a[lo];
		while(true){
			//Scan right, scan left, check for scan complete, and exchange.
			while(less(a[i++], v)) 
				if(i == hi)
					break;
			
			while(less(v, a[j--]))
				if(j == lo)
					break;
			if(i >= j)
				break;
			exch(a, i, j);
		}
		exch(a, lo, j);
		return j;
	}
	
	private static boolean less(Comparable v, Comparable w){
		return v.compareTo(w) < 0;
	}
	
	private static void exch(Comparable[] a, int i, int j){
		Comparable t = a[i];
		a[i] = a[j];
		a[j] = t;
	}
	
	
	public void test(){
		Object[] a = new Object[5];
		Arrays.sort(a);
	}
	
}
