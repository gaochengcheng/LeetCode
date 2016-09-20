# LeetCode刷题笔记（栈部分）

[TOC]

## S.20_Valid Parentheses

原题地址：[https://leetcode.com/problems/valid-parentheses/](https://leetcode.com/problems/valid-parentheses/)

思路：

> Given a string containing just the characters `'('`, `')'`, `'{'`, `'}'`, `'['` and `']'`, determine if the input string is valid.
>
> The brackets must close in the correct order, `"()"` and `"()[]{}"` are all valid but `"(]"` and `"([)]"` are not.

- 当遇到例如`([{`时，一律压栈，当遇到例如`)]}`时，检查栈是否是空，
  - 如果此时栈不空，就弹出一个元素进行匹配，
    - 如果匹配成功则继续执行程序，
    - 如果匹配失败，直接在此处返回false。
  - 如果此时栈为空，也执行压栈。
- 当所有元素都检查过一遍之后，
  - 如果此时栈为空，说明由于括号是匹配的，都弹出栈了；
  - 如果栈不空，就说明括号是不匹配的。

代码：

```java
public boolean isValid(String s) {
        if(s == null || s.length() == 0)
        	return false;
        String[] strs = s.split("");
        int length = strs.length;
        if(length %2 != 0)
        	return false;
        Stack<String> stack = new Stack<String>();
        int i = 0;
        while(i < length){
        	String str = strs[i];
        	if(str.equals("(") || str.equals("[") || str.equals("{")){
        		stack.push(str);
        		i++;
        	}
        	
        	else if(str.equals(")") ){
        		if(!stack.isEmpty()){
        			String temp = stack.peek();
        			if(temp.equals("(")){
            			i++;
            			stack.pop();
            		}
        			else
        				return false;
        		}
        		else{
        			stack.push(str);
        			i++;
        		}
        	}
        	else if(str.equals("]") ){

        		if(!stack.isEmpty()){
        			String temp = stack.peek();
        			if(temp.equals("[")){
            			i++;
            			stack.pop();
            		}
        			else
        				return false;
        		}
        		else{
        			stack.push(str);
        			i++;
        		}
        	
        		
        	}
        	else if(str.equals("}") ){

        		if(!stack.isEmpty()){
        			String temp = stack.peek();
        			if(temp.equals("{")){
            			i++;
            			stack.pop();
            		}
        			else
        				return false;
        		}
        		else{
        			stack.push(str);
        			i++;
        		}
        	}
        		
        }
        if(stack.isEmpty())
        	return true;
        else
        	return false;
    }
```

## S.32_Longest Valid Parentheses（hard，有参考）

原题地址：https://leetcode.com/problems/longest-valid-parentheses/

思路：

>Given a string containing just the characters `'('` and `')'`, find the length of the longest valid (well-formed) parentheses substring.
>
>For `"(()"`, the longest valid parentheses substring is `"()"`, which has length = 2.
>
>Another example is `")()())"`, where the longest valid parentheses substring is `"()()"`, which has length = 4.

这道题目还是在考察栈的使用。当遇到左括号的时候就入栈，当遇到右括号的时候，就出栈，并且判断当前合法序列是否为最长序列。

具体来说，主要问题就是遇到右括号时如何判断当前的合法序列的长度。比较健壮的方式如下：

当遇到右括号的时候：作如下判断
(1) 如果当前栈为空，则说明加上当前右括号没有合法序列（有也是之前判断过的），我们执行`start = i + 1 `，表示`i`处的值不是左括号，是左括号的位置可能是 `i`处的下一个位置；
(2) 如果栈不空，弹出栈顶元素，如果弹出后栈为空，则说明当前括号匹配，我们会维护一个合法开始的起点start，合法序列的长度即为当前元素的位置 -start+1；否则如果栈内仍有元素，则当前合法序列的长度为当前栈顶元素位置的下一位到当前元素的距离（即`i - stack.peek()`），因为栈顶元素后面的括号对肯定是合法的，而 且左括号出过栈了。

(3)维护一个最长的序列值就可以了。
因为只需要一遍扫描，算法的时间复杂度是O(n)，空间复杂度是栈的空间，最坏情况是都是左括号，所以是O(n)。

代码：

```java
public int longestValidParentheses(String s) {
        
		if (s == null || s.length() == 0) {
			return 0;
		}

		int start = 0;
		int maxLen = 0;
		Stack<Integer> stack = new Stack<Integer>();

		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(') {
				stack.push(i);
			} else {
				if (stack.isEmpty()) {
					// record the position before first left parenthesis
					//记录第一个左括号的位置
					start = i + 1;
				} else {
					
					//碰到一个右括号，就弹出一个栈中的左括号
					stack.pop();  
					// if stack is empty mean the positon before the valid left
					// parenthesis is "last"
					if (stack.isEmpty()) {
						maxLen = Math.max(i - start + 1, maxLen);
					} else {
						// if stack is not empty, then for current i the longest
						// valid parenthesis length is
						// i-stack.peek()
						maxLen = Math.max(i - stack.peek(), maxLen);
					}
				}
			}
		}
		return maxLen;
    }
```

## S.84_Largest Rectangle in Histogram

原题地址：https://leetcode.com/problems/largest-rectangle-in-histogram/

思路：

Given *n* non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.

![img](http://www.leetcode.com/wp-content/uploads/2012/04/histogram.png)

Above is a histogram where width of each bar is 1, given height = `[2,1,5,6,2,3]`.

![img](http://www.leetcode.com/wp-content/uploads/2012/04/histogram_area.png)

The largest rectangle is shown in the shaded area, which has area = `10` unit.

