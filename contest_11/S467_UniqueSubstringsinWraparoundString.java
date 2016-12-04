package contest_11;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * 生成一个字符串的所有组合排列
 * http://www.programgo.com/article/14642995934/;jsessionid=CAB625C2BE1E7951447E4C2CAF8130E9
 * @author chengcheng
 * @time 2016年12月4日 上午11:05:08
 *
 */
public class S467_UniqueSubstringsinWraparoundString {
	
	public  void combiantion(char chs[]){  
        if(chs == null || chs.length == 0){  
            return;  
        }  
  
        List<Character> list = new ArrayList<Character>();  
        for(int i = 1; i <= chs.length; i++){  
            combine(chs, 0, i, list);  
        }  
    }  
  
    //从字符数组中第begin个字符开始挑选number个字符加入list中  
	String s = "zabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcd";
	List<String> subString = new ArrayList<String>();
	int nums = 0;
    public  void combine(char[] cs, int begin, int number, List<Character> list){  
        if(number == 0){  
            String str = ListToString(list);
            
            if(!subString.contains(str) && s.contains(str)){
            	nums++;
            	subString.add(str);
            }
            return;  
        }  
  
        if(begin == cs.length){  
            return;  
        }  
  
        list.add(cs[begin]);  
        combine(cs, begin + 1, number - 1, list);  
        list.remove((Character)cs[begin]);  
        combine(cs, begin + 1, number, list);  
    }  
  
//    public static void main(String args[]){  
//        char chs[] = {'a','b','c'};  
//        combiantion(chs);  
//    }  
    
    public int findSubstringInWraproundString(String p) {
        char[] chs = p.toCharArray();
        
        combiantion(chs);
        return nums;
    }
    
    public String ListToString(List list){
    	String str = "";
    	int length = list.size();
    	for(int i = 0; i<length; i++){
    		str += list.get(i);
    	}
    	return str;
    }
    
    
    @Test
    public void test(){
    	int result = findSubstringInWraproundString("cac");
    	System.out.println(result);
    }
}
