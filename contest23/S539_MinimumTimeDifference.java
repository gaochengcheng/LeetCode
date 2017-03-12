package contest23;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/**
 * 这道题目很简单呀，最后要求返回时间是分钟，所以只需要把时间全部换成分钟，用分钟求差就行了。
 * @author chengcheng
 * @time 2017年3月12日 上午11:22:21
 *
 */
public class S539_MinimumTimeDifference {
	private int getMinutes(String time) {
        String[] t = time.split(":");
        return Integer.valueOf(t[0]) * 60 + Integer.valueOf(t[1]);
    }

	public int findMinDifference(List<String> timePoints) {
        int n = timePoints.size();
        int[] times = new int[n];
        for (int i = 0; i < n; i++) {
            times[i] = getMinutes(timePoints.get(i));  //把时间换算成分钟
        }
        Arrays.sort(times);
        int min = Integer.MAX_VALUE;
        //下面这个for循环写的非常好
        //依次比较了数组中相邻两个元素之间的值，包括第一个元素和最后一个元素之间的值.
        //并且在用第一元素减去最后一个元素的时候需要加上一个24*60的值。
        for (int i = 0; i < n; i++) {
            int diff = 0;
            if (i == 0) {
                diff = times[i] + 24 * 60 - times[n-1];
              
            } else {
                diff = times[i] - times[i-1];
            }
            min = Math.min(diff, min);
        }
        return min;
    }

	@Test
	public void test() {
		ArrayList list = new ArrayList();
		list.add("23:59");
		list.add("23:01");
		System.out.println(findMinDifference(list));

	}
}
