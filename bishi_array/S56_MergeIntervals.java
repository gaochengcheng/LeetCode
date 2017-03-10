package bishi_array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 
 * @author chengcheng
 * @time 2017年3月10日 下午3:17:29
 *
 */
 class Interval {
	      int start;
	      int end;
	      Interval() { start = 0; end = 0; }
	      Interval(int s, int e) { start = s; end = e; }
	 }



public class S56_MergeIntervals {
	public List<Interval> merge(List<Interval> intervals) {
        if (intervals == null || intervals.size() <= 1)
            return intervals;
 
        // sort intervals by using self-defined Comparator
        Collections.sort(intervals, new IntervalComparator());   //见到新的用法
 
        ArrayList<Interval> result = new ArrayList<Interval>();
 
        Interval prev = intervals.get(0);
        for (int i = 1; i < intervals.size(); i++) {
            Interval curr = intervals.get(i);
 
            if (prev.end >= curr.start) {
                // merged case
                Interval merged = new Interval(prev.start, Math.max(prev.end, curr.end));
                prev = merged;
            } else {
                result.add(prev);
                prev = curr;
            }
        }
 
        result.add(prev);
 
        return result;
        }
    }
 	//实现Comparator接口之后，重写其中的compare方法，
    class IntervalComparator implements Comparator<Interval> {
        public int compare(Interval i1, Interval i2) {
            return i1.start - i2.start;
        }
}
