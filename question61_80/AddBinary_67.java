package question61_80;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/**
 * For example, a = "11" b = "1" Return "100".
 * 
 * @author chengcheng
 *
 */
public class AddBinary_67 {
	public static void main(String[] args) {
		String a = "0";
		String b = "1";
//		ArrayList<String> list = new ArrayList<String>();
//		list.add("a");
//		list.add("a");
//		list.add("a");
//		list.add("a");
//		Iterator<String> it = list.iterator();
//		while(it.hasNext()){
//			a += ""+it.next();
//		}
		String c = addBinary(a, b);
		System.out.println(c);
		
	}

	public static String addBinary(String a, String b) {
		
		ArrayList<String> result = new ArrayList<String>();
		int i,j;
		result.add("0");
		for(i = a.length()-1,j = b.length()-1; i >= 0 || j>= 0; i--,j--){
			int length = result.size();
			int temp ;
			temp = Integer.parseInt(result.get(length-1));
			if(i >=0 && j>=0 ){
				if(temp + Integer.parseInt(a.substring(i,i+1)) + Integer.parseInt(b.substring(j,j+1)) == 2){
					result.remove(length-1);
					result.add("0");
					result.add("1");
				}
				else if(temp + Integer.parseInt(a.substring(i,i+1)) + Integer.parseInt(b.substring(j,j+1)) >= 2){
					result.remove(length-1);
					result.add("1");
					result.add("1");
				}
				else if(temp + Integer.parseInt(a.substring(i,i+1)) + Integer.parseInt(b.substring(j,j+1)) == 1){
					result.remove(length-1);
					result.add("1");
					if(i !=0 || j!= 0)
						result.add("0");
				}
				else{
					result.remove(length-1);
					result.add("0");
					if(i != 0 || j != 0){
					result.add("0");
					}
					
				}
			}
			else if(i >=0 ){
				if(temp + Integer.parseInt(a.substring(i,i+1)) == 2){
					result.remove(length-1);
					result.add("0");
					result.add("1");
				}
				else if(temp + Integer.parseInt(a.substring(i,i+1)) == 1){
					result.remove(length-1);
					result.add("1");
					if(i != 0){
						result.add("0");
					}
					
				}
				else{
					result.remove(length-1);
					result.add("0");
					if(i != 0){
						result.add("0");
					}
				}
			}
			else if( j>=0 ){
				if(temp + Integer.parseInt(b.substring(j,j+1)) == 2){
					result.remove(length-1);
					result.add("0");
					result.add("1");
				}
				else if(temp + Integer.parseInt(b.substring(j,j+1)) == 1){
					result.remove(length-1);
					result.add("1");
					if(j != 0){
						result.add("0");
					}
				}
				else{
					result.remove(length-1);
					result.add("0");
					if(j != 0){
						result.add("0");
					}
				}
			}
			
		}
		String lastresult = "";
		Collections.reverse(result);
		Iterator<String> it = result.iterator();
		while(it.hasNext()){
			lastresult += ""+it.next();
		}
		return lastresult;
	}
}
