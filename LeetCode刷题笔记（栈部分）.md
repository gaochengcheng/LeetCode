# LeetCode刷题笔记（栈部分）

[TOC]

## S.20_Valid Parentheses

原题地址：[https://leetcode.com/problems/valid-parentheses/](https://leetcode.com/problems/valid-parentheses/)

思路：

> Given a string containing just the characters `'('`, `')'`, `'{'`, `'}'`, `'['` and `']'`, determine if the input string is valid.
>
> The brackets must close in the correct order, `"()"` and `"()[]{}"` are all valid but `"(]"` and `"([)]"` are not.

​	当遇到例如`([{`时，一律压栈，当遇到例如`)]}`时，检查栈是否是空，如果栈不空，就弹出一个元素进行匹配。如果栈为空，也压栈。当所有元素都检查过一遍之后，如果此时栈为空，说明由于括号是匹配的，都弹出栈了；如果栈不空，就说明括号是不匹配的。

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

