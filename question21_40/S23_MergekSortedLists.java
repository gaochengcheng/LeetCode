package question21_40;

import org.junit.Test;

/**
 * 
 * @author chengcheng
 * @time 2016年9月1日 下午3:23:48
 *
 */
public class S23_MergekSortedLists {
	public ListNode mergeKLists(ListNode[] lists) {

		int length = lists.length;
		if (length == 0)
			return null;

		ListNode head = lists[0];
		for (int i = 1; i < length; i++) {
			head = mergeList(head, lists[i]);
		}

		return head;
	}

	public ListNode mergeList(ListNode l1, ListNode l2) {
		if (l1 == null)

			return l2;
		if (l2 == null)
			return l1;

		ListNode newhead = new ListNode(-1);
		ListNode head = newhead;

		while (l1 != null && l2 != null) {
			if (l1.val < l2.val) {
				newhead.next = l1;
				l1 = l1.next;
				newhead = newhead.next;
			} else {
				newhead.next = l2;
				l2 = l2.next;
				newhead = newhead.next;
			}
		}
		if (l1 != null)
			newhead.next = l1;
		if (l2 != null)
			newhead.next = l2;

		return head.next;
	}

	@Test
	public void test() {

	}

	/**
	 * 对一个数组进行归并排序
	 * 
	 * @param arr
	 * @return
	 */
	public int[] mergeSort(int[] arr) {
		if (arr.length < 2 || arr == null)
			return arr;

		return MSort(arr, 0, arr.length - 1);
	}

	public int[] MSort(int[] arr, int low, int high) {
		if (low < high) {
			int mid = (low + high) / 2;
			int[] left = MSort(arr, low, mid);
			int[] right = MSort(arr, mid + 1, high);
			return mergeTwoList(left, right);
		}
		return mergeTwoList(arr,arr);
	}

	public int[] mergeTwoList(int[] a, int[] b) {
		int[] c = new int[a.length + b.length];
		int i = 0; // visit a
		int j = 0; // visit b
		int k = 0; // visit c
		while (i < a.length && j < b.length) {
			if (a[i] < b[j])
				c[k++] = a[i++];
			else
				c[k++] = b[j++];
		}
		while (i < a.length)
			c[k++] = a[i++];
		while (j < b.length)
			c[k++] = b[j++];
		return c;
	}
	@Test
	public void testmergeSort(){
		int[] a = {9,8,7,6,5,4,3,2,1};
		int[] b = mergeSort(a);
		for(int ele:b)
			System.out.println(ele);
		
	}
}
