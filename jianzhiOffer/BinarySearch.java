package jianzhiOffer;
/**
 * 
 * @author chengcheng
 * @time 2017年8月11日 上午9:14:49
 *
 */
public class BinarySearch {
	//在数组a中找元素a，如果能找到返回a的下标，如果没有找到，返回-1.
	public int solution(int[] list, int a){
		int low = 0;
		int high = list.length-1;
		int mid;
		while(low <= high){
			mid = (low+high)/2;
			if(list[mid] == a)
				return mid;
			else if(list[mid] > a){
				high = mid -1;
			}else{
				low = mid +1;
			}
		}
		//查找失败，返回-1.
		return -1;
		
	}
}
