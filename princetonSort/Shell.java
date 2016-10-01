package princetonSort;

import edu.princeton.cs.algs4.StdOut;

/**
 * 
 * @author chengcheng
 * @time 2016年9月30日 下午10:10:14
 *
 */
public class Shell {
	public static void sort(Comparable[] a){
		//Sort a[] into increasing order.
		int N = a.length;
		int h = 1;
		while(h < N/3) 
			h = 3*h+1;  //首先找到间隔是多大
		System.out.println("h is : "+h);
		while(h >=1){
			// h-sort the array.
			for(int i = h; i < N; i++){
				//Insert a[i] among a[i-h], a[i-2h], a[i-3h]...
				for(int j = i; j >= h && less(a[j],a[j-h]); j= j-h)
					exch(a, j, j-h);
			}
			h = h / 3;
		}
	}
	
	public static void main(String[] args) {
//		String[] a = In.readStrings();
		
//		String[] a = {"A","C","E","B","D"};
//		Integer[] a = {9,8,7,6,5,4,3,2,1};
		Integer[] a = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27};
//		String[] a = {"A","B"};
		sort(a);
		assert isSorted(a);
		show(a);
	}
	private static void show(Comparable[] a){
		for(int i = 0; i < a.length; i++)
			StdOut.print(a[i] + " ");
		StdOut.println();
	}
	
	private static boolean less(Comparable v, Comparable w){
		return v.compareTo(w) < 0;
	}
	
	private static void exch(Comparable[] a, int i, int j){
		Comparable t = a[i];
		a[i] = a[j];
		a[j] = t;
	}
	public static boolean isSorted(Comparable[] a){
		for(int i = 1; i < a.length; i++){
			if(less(a[i],a[i-1]))
				return false;
		}
		return true;
	}
}
