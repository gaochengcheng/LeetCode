package contest_11;

import java.util.ArrayList;
import java.util.List;

/**
 * 判断由一系列点构成的图形是不是凸多边形
 * @author chengcheng
 * @time 2016年12月4日 上午10:46:42
 * 参考链接：http://blog.csdn.net/chaiwenjun000/article/details/52168790
 * 百度百科：点积和向量。
 */
public class S469_ConvexPolygon {
	public boolean isConvex(List<List<Integer>> points) {
		
		if (points.size() <= 2) return false;
	    
    	int pre, next;
    	int sign = 0;
    	for (int i = 0; i<points.size(); i++)
    	{
    		if (i == points.size() - 1) //i是最后一个的时候，最后一个下一个就是第0个。
    			next = 0;
    		else 
    			next = i + 1;
    
    		if (i == 0) 
    			pre = points.size() - 1;  //i是第一个时，第一个的前一个就是points中最后一个点，因为构成一个环。
    		else 
    			pre = i - 1;
    		
//    		int t = getDot(points[i][0] - points[pre][0], points[i][1] - points[pre][1], points[next][1] - points[i][1], points[i][0] - points[next][0]);
    		
    		int t = getDot(points.get(i).get(0) - points.get(pre).get(0),
    				points.get(i).get(1) - points.get(pre).get(1),
    				points.get(next).get(1) - points.get(i).get(1),
    				points.get(i).get(0) - points.get(next).get(0));
    		if (t == 0) 
    			continue;
    		else if (sign == 0) 
    			sign = t;
    		else if (sign != t) 
    			return false;
    	}
    	if (sign == 0) 
    		return false;
    	return true;
    }
	
	
	
	
	//两个向量a(x1,y1),b(x2,y2)的点积是x1*x2+y2*y2
	//如果点积>0,说明两个向量形成的角度>90度。
	
	public int getDot(int v1x, int v1y, int v2x, int v2y){  //v1x,v1y是一个向量的坐标表示。  
		int t = v1x*v2x + v1y*v2y;
    	if (t>0) return 1;
    	if (t<0) return -1;
    	return 0;
	}
    
}
