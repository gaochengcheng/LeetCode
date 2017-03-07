package bishi_array;

/**
 * Question: Median of Two Sorted Arrays
 * http://www.acmerblog.com/leetcode-median-of-two-sorted-arrays-5330.html
 * 
 * @author chengcheng
 * @time 2016年6月24日 下午8:04:45
 *
 */
public class S4_MedianofTwoSortedArrays {
	public static void main(String[] args) {
		int[] num1 = new int[]{1,3,5};
		int[] num2 = new int[]{2,4,6};
		double median = findMedianSortedArrays(num1,num2);
		System.out.println(median);
	}
	/**
	 * 使用了归并的思想，在两个数组中找较小的那个，并且总计数+1.
	 * @param nums1
	 * @param nums2
	 * @return
	 */
	public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
		int m = nums1.length, n = nums2.length;
		int i = 0, j = 0, total = nums1.length + nums2.length;
		double prev = 0, last = 0;
		if(total < 2)//两个数组中有0个，或者有1个值。
		{
			if(m == 0 && n == 0) return 0;
			//有一个值时，返回这个值
			if(m == 1) return nums1[0];
			else return nums2[0];
		}
		//当数组中有2个或者2个以上的时候
		while( (i+j) <= (total/2))
		{
			prev = last;
			if(i >= m){//如果nums1中的元素已经用完，直接取nums2数组
				last = nums2[j];
				j++;
			}
			else if(j >= n){//如果nums2中的元素已经用完，直接取nums1数组
				last = nums1[i];
				i++;
			}
			else if(nums1[i] < nums2[j]){//取nums1[i] 和 nums2[j] 中较小的
				last = nums1[i];
				i++;
			}
			else{
				last = nums2[j];
				j++;
			}
		}
		if ((total % 2 == 0))
			return (prev + last) / 2.0;
		else
			return last;
	}
}
