package question121_140;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * http://www.programcreek.com/2013/03/leetcode-palindrome-partitioning-java/
 * @author chengcheng
 * @time 2016年9月21日 下午9:41:47
 *
 */
public class S131_PalindromePartitioning {
	 public List<List<String>> partition(String s) {
		 List<List<String>> result = new ArrayList<List<String>>();
		 
		 if(s == null || s.length() == 0)
			 return result;
		 
		 ArrayList<String> partition = new ArrayList<String>();
		 addPalindrome(s, 0, partition, result);
		 
		 return result;
 	 }
	 
	 private void addPalindrome(String s, int start, List<String> partition, 
			 List<List<String>> result){
		 
		 if(start == s.length()){  //判断到字符串的最后
			 List<String> temp = new ArrayList<String>(partition); //在内存上重新开辟一块空间，放partition，把这个对象放到result中。
			 System.out.println("temp is "+temp);
			 result.add(temp);
			 return ;
		 }
		 //用递归循环找子问题的方法，把母串按所有组合可能性拆分，
		 //看递归程序，不能盯着一处看，陷入细节局部不容易理解，从整体逻辑来思考
		 
		 /**
		  * 以下代码的思路是：
		  * 	1.从s中取字符串，取出来判断这个字符串是不是回文？
		  * 	2.如果是回文，就把这个字符串加入到partition中，然后通过递归调用，在此基础上继续判断字符串剩下的部分。直到剩余部分全部判断完成，并且都是回文，就把当前
		  * 	  partition添加到result中。在退出递归的时候，把之前添加的元素依次删除掉。
		  * 	3.如果不是回文，则让i++，重现选择新的字符串进行判断。
		  */
		 for(int i = start +1; i<= s.length(); i++){
			 String str = s.substring(start,i);
			 System.out.println("str is : "+str);
			 if(isPalindrome(str)){
				 partition.add(str);
				 addPalindrome(s, i, partition, result);
				 partition.remove(partition.size()-1);
			 }
		 }
		 
	 }
	 
	 private boolean isPalindrome(String str){
		 int left = 0;
		 int right = str.length()-1;
		 
		 while(left < right){
			 if(str.charAt(left) != str.charAt(right)){
				 return false;
			 }
			 left++;
			 right--;
		 }
		 return true;
	 }
	 @Test
	 public void test(){
		 String s = "abba";
		 System.out.println(partition(s));
	 }
}
