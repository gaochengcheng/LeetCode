package bishi_array;
/**
 * 
 * @author chengcheng
 * @time 2017年3月10日 下午2:16:49
 *
 */
public class S11_ContainerWithMostWater {
	public int maxArea(int[] height) {
        if(height == null || height.length == 0)
        	return 0;
        
        int left = 0;
        int right = height.length-1;
        int max = 0;
        while(left < right){
        	int area = (right-left) * (Math.min(height[left], height[right]));
        	max = Math.max(max, area);
        	if(height[left]<height[right])
        		left++;
        	else
        		right--;
        }
        
        return max;
    }
}
