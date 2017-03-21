package bishi;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Ali_tree {
	public static void main(String[] args) {
		
	}
	//[113, 215, 221]
//	    3
//	   /  \
//	  5     1
	public int solution(int[] arr){
		
		//通过数组构造这棵树
		Arrays.sort(arr);
		int[] tree = new int[arr.length+1];
		Arrays.fill(tree, -1);
		for(int i=1; i<arr.length; i++){
			
			int a = arr[i]/100;
			int b = arr[i]/100%10;
			int c = arr[i]%10;
			
		}
		
		//遍历所有从根到叶子结点的路径和
		
		return 0;
	}
	
	public static int calPathSum(int[] nums) {
        if (nums.length == 1) return nums[0]%10;
        Map<Integer, Integer> res = new HashMap<Integer, Integer>();
        int depth = 0;
        int count = 0;
        for (int i : nums) {
            depth = (int) (Math.pow(2, i/100 - 1) + (i%100)/10) - 1;
            res.put((int) (Math.pow(2, i/100 - 1) + (i%100)/10) - 1, i%10);
        }
        for (int i = depth; i > 1; i--) {
            if (!res.containsKey(i*2)) {
                count = calPath(res, i, count);
            }
        }
        return count;
    }
    public static int calPath(Map<Integer, Integer> hashMap, int index, int count) {
        count+=hashMap.get(index);
 
        if (hashMap.containsKey(index/2)) {
            count = calPath(hashMap, index/2, count);
        }
        return count;
    }
}
