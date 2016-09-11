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



