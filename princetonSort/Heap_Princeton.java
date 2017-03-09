package princetonSort;

public class Heap_Princeton {
	public static void sort(Comparable[] a){
		int N = a.length-1;
		for(int k = N/2; k >=0; k--){
			sink(a, k, N);
		}
		while(N > 0 ){
			System.out.println("..."+a[0]);   //a[0]处代表的元素是最大元素
			exch(a, 0, N--);
			sink(a, 0,N);
		}
	}
	private static boolean less(Comparable[] a, int i, int j){
		return a[i].compareTo(a[j]) < 0;
	}
	private static void exch(Comparable[] a, int i, int j){
//		System.out.println("j is "+j);
		Comparable temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	
	//a[0]...a[a.length-1]
	private static void sink(Comparable[] a, int k, int N){
		while(2*k <= N){
			int j = 2*k;
			if(j < N && less(a, j, j+1)){
				j++;
//				System.out.println(j);
			}
			if(!less(a, k, j))
				break;
			exch(a, k, j);
			k = j;
		}
	}
	
	public static void main(String[] args) {
		Comparable[] a = {1,5,3,2,7,6,8};
		sort(a);
		for(Comparable ele : a)
			System.out.print(ele + " ");
	}
}
