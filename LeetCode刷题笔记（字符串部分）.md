# LeetCode刷题笔记（字符串部分）

[TOC]

## S.125_Valid Palindrome

原题地址：https://leetcode.com/problems/valid-palindrome/

思路：

>Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
>
>For example,
>`"A man, a plan, a canal: Panama"` is a palindrome.
>`"race a car"` is *not* a palindrome.

​	这道题目是判断一个字符串是否是回文。在判断是否是回文的过程中，只对字母和数字进行判断。空格和其他标点符号不纳入考虑范围。

​	我们的做法是，设立两个指针，分别指向这个字符串的首位两端。当这两个指针都指向字幕或者数字的时候，判断他们是不是相等，如果相等，两个指针就继续向中间移动。如果不相等，就返回false。

代码：

```java
public boolean isPalindrome(String s) {
        
		if(s.length() == 0)
			return true;
  
		s = s.toUpperCase();
		int low1 = 'A',high1 = 'Z';
		int low2 = '0',high2 = '9';
		int low = 0, high = s.length()-1;
		
		while(low < high){
			//判断low所指向的字符既不是大写的A到Z，也不是数字0到9.
			if((s.charAt(low)<low1 || s.charAt(low)>high1)
					&& (s.charAt(low)<low2 || s.charAt(low)>high2)){
				low++;
				continue;
			}
			//判断high所指向的字符既不是大写的A到Z，也不是数字0到9.
			if((s.charAt(high)<low1 || s.charAt(high)>high1)
					&& (s.charAt(high)<low2 || s.charAt(high)>high2)){
				high--;
				continue;
			}
			
			if(s.charAt(low) == s.charAt(high)){
				low++;
				high--;
			}else{
				return false;
			}		
		}	
		return true;
    }
```

## S.28_Implement strStr()

原题地址：https://leetcode.com/problems/implement-strstr/

思路：

>Implement strStr().
>
>Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.

​	最直接的方法就是用needle中的每个元素和haystack中的每个元素进行比较。这个方法的时间复杂度是O（m*n）。m和n分别是haystack和needle的长度。

​	用一个for循环来控制haystack中的每个元素。一旦haystack.length()  < needle.length()，则不用再继续查找。用一个while循环来比较haystack和needle中的每一个元素。

代码：

```java
public int strStr(String haystack, String needle) {
		
		if(needle.length() == 0)
			return 0;

		for(int i = 0; i<haystack.length(); i++){
			
			//如果haystack串剩下的长度短于needle的长度，则直接返回-1，表示查找失败
			if(haystack.length()-i+1 < needle.length())
				return -1;
			
			int k = i;
			int j = 0;
			
			while(j < needle.length() && k < haystack.length()
					&& needle.charAt(j) == haystack.charAt(k)){
				j++;
				k++;
				if(j == needle.length())
					return i;
			}
		}
		return -1;
	}
```

## S.5_Longest Palindromic Substring

原题地址：https://leetcode.com/problems/longest-palindromic-substring/

思路：

>Given a string *S*, find the longest palindromic substring in *S*. You may assume that the maximum length of *S* is 1000, and there exists one unique longest palindromic substring.

在一个字符串中，找到一个最长的回文子串，并且返回这个回文子串。

一开始我是没有思路的，一点思路都没有，无从下手的感觉。后来想到以每个字符为中心，从左右两边扩展，每次只要新的回文串的长度更长，便进行更新，就可以得到最长的回文串。



是对于每个子串的中心（可以是一个字符，或者是两个字符的间隙，比如串abc,中心可以是a,b,c,或者是ab的间隙，bc的间隙，例如aba是回文，abba也是回文，这两种情况要分情况考虑）往两边同时进 行扫描，直到不是回文串为止。假设字符串的长度为n,那么中心的个数为2*n-1(字符作为中心有n个，间隙有n-1个）。对于每个中心往两边扫描的复杂 度为O(n),所以时间复杂度为O((2*n-1)*n)=O(n^2),空间复杂度为O(1)

代码：

```java
public String longestPalindrome(String s) {  
        
		if(s.isEmpty() || s == null || s.length() ==1)
			return s;
		
		String longest = s.substring(0,1);
		for(int i = 0; i < s.length(); i++){
			//get longest palindrome with center of i
			String tmp = helper(s, i, i);
			if(tmp.length() > longest.length())
				longest = tmp;
			
			//get longest palindrome with center of i, i+1
			tmp = helper(s, i, i+1);
			if(tmp.length() > longest.length())
				longest = tmp;
		}
		return longest;
    }
    
    public String helper(String s, int begin, int end){
		while(begin >= 0 && end <= s.length()-1 && s.charAt(begin) == s.charAt(end)){
			begin--;
			end++;
		}
		//因为在begin 这个环节不相等了，所以从begin+1这个位置起，存在一个回文
		return s.substring(begin+1,end);
	}
```

## S.10_Regular Expression Matching

原题地址：https://leetcode.com/problems/regular-expression-matching/

思路：

​	



