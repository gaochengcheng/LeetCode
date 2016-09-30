package princetonSort;
/**
 * 
 * @author chengcheng
 * @time 2016年9月1日 下午5:04:58
 *
 */
public class Merge {
	private static int[] aux;
	
	public static void sort(int[] a){
		aux = new int[a.length];
		sort(a, 0, a.length-1);
	}
	private static void sort(int[] a, int lo, int hi){
		if(hi <= lo)
			return ;
		int mid = (hi+lo)/2;
		sort(a, lo, mid);
		sort(a, mid+1,hi);
		merge(a, lo, mid, hi);
	}
	
	public static void merge(int[] a, int lo, int mid, int hi){
		int i = lo, j = mid+1;
		for(int k = lo; k <= hi; k++)
			aux[k] = a[k];
		
		for(int k = lo; k <= hi; k++)
			if(i > mid)  //mid左边的全部排序结束，右边的接着放就行
				a[k] = aux[j++];
			else if(j > hi)   //mid右边的全部排序结束，左边的直接放即可
				a[k] = aux[i++];
			else if(aux[j] < aux[i])  //mid两边逐个比较，
				a[k] = aux[j++];
			else 
				a[k] = aux[i++];
		
	}
	
	public static void main(String[] args) {
//		int[] a = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16};
		int[] a = {16,15,14,13,12,11,10,9,8,7,6,5,4,3,2,1};
		for(int ele : a)
			System.out.print(ele+" ");
		sort(a);
		System.out.println();
		for(int ele : a)
			System.out.print(ele+" ");
		
	}
	
}
