package princetonSort;

import java.util.concurrent.SynchronousQueue;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.introcs.In;

/**
 * 
 * @author chengcheng
 * @time 2016年9月30日 上午10:28:42
 *
 */
public class Selection {
	public static void main(String[] args) {
//		String[] a = In.readStrings();
		
		String[] a = {"A","C","E","B","D"};
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
	
	public static void sort(Comparable[] a){
		int N = a.length;
		for(int i = 0; i < N; i++){
			int min = i;
			for(int j = i + 1; j < N; j++)
				if(less(a[j],a[min]))
					min = j;
			exch(a, i, min);
		}
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
