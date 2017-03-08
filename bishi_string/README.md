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

​	我们的做法是，设立两个指针，分别指向这个字符串的首位两端。当这两个指针都指向字母或者数字的时候，判断他们是不是相等，如果相等，两个指针就继续向中间移动。如果不相等，就返回false。

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

## S.10_Regular Expression Matching(很难)

原题地址：https://leetcode.com/problems/regular-expression-matching/

思路：

​	`String.subString(2)`的含义是：从一个字符串的下标是2的地方开始，返回这个字符串的子串。

​	使用**递归**的思想，这是我完完全全没有想到的。

>- 首先要理解题意:
>  - "a"对应"a", 这种匹配不解释了
>  - 任意字母对应".", 这也是正则常见
>  - 0到多个相同字符x,对应"x*", 比起普通正则,这个地方多出来一个前缀x. x代表的是 相同的字符中取一个,比如"aaaab"对应是"a*b"
>  - "*"还有一个易于疏忽的地方就是它的"贪婪性"要有一个限度.比如"aaa"对应"a*a", 代码逻辑不能一路贪婪到底
>- 正则表达式如果期望着一个字符一个字符的匹配,是非常不现实的.而"匹配"这个问题,非 常容易转换成"匹配了一部分",整个匹配不匹配,要看"剩下的匹配"情况.这就很好的把 一个大的问题转换成了规模较小的问题:递归
>- 确定了递归以后,使用java来实现这个问题,会遇到很多和c不一样的地方,因为java对字符 的控制不像c语言指针那么灵活charAt一定要确定某个位置存在才可以使用.
>- 如果pattern是"x*"类型的话,那么pattern每次要两个两个的减少.否则,就是一个一个 的减少. 无论怎样减少,都要保证pattern有那么多个.比如s.substring(n), 其中n 最大也就是s.length()



代码：

```java
public boolean isMatch(String s, String p) {
        
		if(p.length() == 0)
			return s.length() == 0;
		
		// length == 1 is the case that is easy to forget.
		// as p is subtracted 2 each time, so if original
		// p is odd, then finally it will face the length 1
		if(p.length() == 1)
			return (s.length() == 1) && (p.charAt(0) == s.charAt(0) || p.charAt(0) == '.');
		
		//if next char is not '*': must match current character
		if(p.charAt(1) != '*'){
			if(s.length() == 0)
				return false;
			else 
				return (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.')
						&& isMatch(s.substring(1),p.substring(1));
		}else{
			//if next char is *
			while(s.length() > 0 && (p.charAt(0) == s.charAt(0) || p.charAt(0) == '.')){
				//*的前面有一个前缀，前缀可以是   '.' 或者是一个实际的字符，所以一次性去掉前缀和*,一共两个字符。
				if(isMatch(s,p.substring(2)))
					return true;
				s = s.substring(1);//从左往右，逐一去掉s的元素。
			}
			return isMatch(s,p.substring(2));
		}
	}
```

## S.44_Wildcard Matching（很难）

原题地址：https://leetcode.com/problems/wildcard-matching/

思路：

>```
>'?' Matches any single character.
>'*' Matches any sequence of characters (including the empty sequence).
>```

1. 二个指针i, j分别指向字符串、匹配公式。
2. 如果匹配，直接2个指针一起前进。
3. 如果匹配公式是*，在字符串中依次匹配即可。

 下面是迭代程序，要熟悉这个思维：记录上一次开始比较的位置，如图：

 ![字符串比较](pics\/字符串比较.PNG)

代码：

```java
public boolean isMatch(String s, String p) {
        

		int indexS = 0;
		int indexP = 0;
		int preP = -1;
		int preS = -1;
		while(indexS < s.length()){
			if(indexP < p.length()
					&& (p.charAt(indexP) == '?' || p.charAt(indexP) == s.charAt(indexS))){
				indexP++;
				indexS++;
			}else if(indexP < p.length() && p.charAt(indexP) == '*'){
				preP = indexP;  //记录 * 出现的位置
				preS = indexS;  //
				indexP++;
			}else if(preP != -1){
//				indexP = preP +1;
//				indexS = preS++;
				indexP = preP;
				indexP++;
				
				preS++;
				indexS = preS;
				
				
			}else{
				return false;
			}
			
		}
		//看不懂的写法
//		while(indexP < p.length() && p.charAt(indexP) == '*'){
//			indexP++;
//		}
//		return indexP == p.length();
		while(indexP < p.length()){
			if(p.charAt(indexP) != '*'){
				return false;
			}
			indexP++;
		}
		return true;
	
    }
```

## S.14_Longest Common Prefix

原题地址：https://leetcode.com/problems/longest-common-prefix/

思路：

> Write a function to find the longest common prefix string amongst an array of strings.

- 从第一个字符串的第一个位置开始扫描，同时依次分别扫描其他字符串。如果所有字符串都包含该字符，那么就在commonprefix中添加该字符，否则不添加，并且终止程序。

代码：

```java
public String longestCommonPrefix(String[] strs) {
		if(strs == null || strs.length == 0)
			return "";
		
		String prefix = "";
		int length = strs.length;
		int index = 0;
		int len = strs[0].length();
		while(index < len){
			char temp = strs[0].charAt(index);
			boolean flag = true;
			for(int i = 1; i < length; i++){
				if(strs[i].length() > index && strs[i].charAt(index) != temp){
					flag = false;
					break;
				}
				if(strs[i].length() <= index){
					flag = false;
				}
			}
			if(flag == true)
				prefix = prefix+temp;
			else{
				break;
			}	
			index++;
		}
		
		return prefix;
	}
```

## S.65_Valid Number（未完成）

原题地址：https://leetcode.com/problems/valid-number/

思路：

>Validate if a given string is numeric.
>
>Some examples:
>`"0"` => `true`
>`" 0.1 "` => `true`
>`"abc"` => `false`
>`"1 a"` => `false`
>`"2e10"` => `true`
>



## S.12_Integer to Roman

原题地址：https://leetcode.com/problems/integer-to-roman/

思路：

>Given an integer, convert it to a roman numeral.
>
>Input is guaranteed to be within the range from 1 to 3999.

​	以下代码太精巧了。

​	这道题目的要求就是把一个数字转换成古罗马记号的数字。那具体来说应该怎么做呢，其实就和进制转换很像。

- 用radix中的数字，从大到小尝试着去除num。
  - 如果结果>0（其实如果结果>0，结果只可能是1，因为是从大到小试着除的，在此之前已经尝试过大的）,说明num中包含这个数字。当包含这个数字的时候，需要在下一次做运算之前，减去这个数字。用mod运算，正好可以达到这个目的。
  - 如果结果==0.说明不包含这个数字，换下一个较小的数字，接着做运算。



代码：

```java
public String intToRoman(int num) {
        
		int radix[] = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9,5,4,1};
		String symbol[] = {"M", "CM", "D", "CD", "C", "XC",
				"L", "XL", "X", "IX", "V", "IV", "I"};
		
		String roman = "";
		for(int i = 0; num > 0; i++){
			int count = num / radix[i];
			num = num % radix[i];
			for(; count > 0; count--)
				roman += symbol[i];
		}
		
		return roman;
    
    }
```

## S.13_Roman to Integer

原题地址：https://leetcode.com/problems/roman-to-integer/

思路：

>Given a roman numeral, convert it to an integer.
>
>Input is guaranteed to be within the range from 1 to 3999.

​	起初，我的做法是从左往后，依次遍历。在遍历的过程中同时判断相邻的两个字符是否满足罗马数字中用两个字符表示一个数字的情况。这个时候，发现有一个测试用例不能通过。`MLXXIV`转化为数字之后应该是`1074`的。其中`ML`是分别表示`M`和`L`，但是使用之前的算法会把`ML`解析为一个数字。

​	所以，转化思路，我们从右往左开始判断。并且这次做判断的时候，只判断每个位置的单个字符，不对连续两个字符的情况做处理。对于某些字符，根据当前的和来判断究竟是加这个字符对应的数字，还是减去一个值。

代码：

```java
public static int romanToInt(String s) {
        
		if(s == null || s.length() == 0)
			return 0;
		int sum = 0;
		int length = s.length()-1;
		for(int i = length; i >= 0; i--){
			char c = s.charAt(i);
			
			if(c == 'I'){   
				if(sum >= 5)      //通过当前和的大小判断究竟是加操作还是减操作
					sum = sum - 1;
				else
					sum = sum + 1;
			}
			else if(c == 'X'){
				if(sum >= 50)
					sum = sum - 10;
				else
					sum = sum + 10;
			}
			else if(c == 'C'){
				if(sum >= 500)
					sum = sum - 100;
				else
					sum = sum + 100;
			}
			else if(c == 'V')
				sum = sum + 5;
			else if(c == 'L')
				sum = sum + 50;
			else if(c == 'D')
				sum = sum + 500;
			else
				sum = sum + 1000;
				
		}
		return sum;
    
    }
```

## S.38_Count and Say

原题地址：https://leetcode.com/problems/count-and-say/

思路：

>The count-and-say sequence is the sequence of integers beginning as follows:
>`1, 11, 21, 1211, 111221, ...`
>
>`1` is read off as `"one 1"` or `11`.
>`11` is read off as `"two 1s"` or `21`.
>`21` is read off as `"one 2`, then `one 1"` or `1211`.
>
>Given an integer *n*, generate the *n*th sequence.

​	这道题目的意思起初根本没有看懂，参考了别人的解答之后才明白题目是什么意思。

​	第一行是`1`.

​	第二行是描述第一行的：一个一，所以第二行是`11`.

​	第三行是描述第二行的：两个一，所以第三行是`21`.

​	第四行是描述第3行的：一个二，一个一，所以是`1211`.

​	依次类推，所以这个题目本质上就是一道细节实现题，去实现这样一种规律。

​	需要注意的一点是，跑完for循环之后记得把最后一个字符也加上，因为之前只是计数而已。

代码：

```java
public String countAndSay(int n) {
	    if(n<=0)
	        return "";
	    String curRes = "1";
	    int start = 1;//从1开始算
	    while(start < n){
	        StringBuilder res = new StringBuilder();
	        int count = 1;
	        for(int j=1;j<curRes.length();j++){
	            if(curRes.charAt(j)==curRes.charAt(j-1))
	                count++;
	            else{
	                res.append(count);
	                res.append(curRes.charAt(j-1));
	                count = 1;
	            }
	        }
	        res.append(count);
	        res.append(curRes.charAt(curRes.length()-1));       	        
	        curRes = res.toString();
	        start++;
	    }
	    return curRes;
	}
```

## S.49_Group Anagrams(巧用hashmap)

原题地址：https://leetcode.com/problems/anagrams/

思路：

>Given an array of strings, group anagrams together.
>
>For example, given: `["eat", "tea", "tan", "ate", "nat", "bat"]`, 
>
>Return:
>
>```
>[
>  ["ate", "eat","tea"],
>  ["nat","tan"],
>  ["bat"]
>]
>```

​	使用到了HashMap这种数据结构。不太清楚这种数据结构在这样的一道题目中是如何发挥作用的。

- 用一个字符数组来表示一个特定的字符串，即便这些字符串中字母顺序不同，但是他们最终表示成字符数组是相同的。
- 把这个字符数组转化为String类型，然后用作HashMap中的key值。接下来的一切就很容易了。因为只要字符串拥有相同的字符，即便字符顺序不同，他们构成的字符数组和之后转化为String值都是相同的。
- key是String类型，value是ArrayList类型，当一个字符串的key已经在map中存在时，只需要在value中进行add操作。当一个字符串的key不存在的时候，先添加key，然后再添加对应的value。
- 当上一步操作完成时，所有字符串分别对应不同的key且全部存在values中，这个时候只需要把values添加到result中即可。

代码：

```java
public List<List<String>> groupAnagrams(String[] strs) {
		List<List<String>> result = new ArrayList<List<String>>();
		
		HashMap<String, ArrayList<String>> map = new HashMap<String,ArrayList<String>>();
		for(String str : strs){ 
			char[] arr = new char[26];
			for(int i = 0; i < str.length(); i++){
				arr[str.charAt(i)-'a']++;  //不同的str，对应的结果不同，str中字符顺序不影响结果
			}
			//下面这两句的功能应该是一样的，都是返回字符数组的字符串表达形式。
//			String ns = new String(arr);
			String ns = String.valueOf(arr);
			
			if(map.containsKey(ns)){   //这样的写法头一次见到唉。把一个字符数组转变成string类型，然后让这个String类型去做HashMap的key。
				map.get(ns).add(str);
			}else{
				ArrayList<String> al = new ArrayList<String>();
				al.add(str);
				map.put(ns, al);
			}
		}
		
		result.addAll(map.values());
		return result;
    }
```

## S.71_Simplify Path(看不懂这个题目)

原题地址：https://leetcode.com/problems/simplify-path/

思路：

>Given an absolute path for a file (Unix-style), simplify it.
>
>For example,
>**path** = `"/home/"`, => `"/home"`
>**path** = `"/a/./b/../../c/"`, => `"/c"`





## S.59_Length of Last Word

原题地址：https://leetcode.com/problems/length-of-last-word/

思路：

>Given a string *s* consists of upper/lower-case alphabets and empty space characters `' '`, return the length of last word in the string.
>
>If the last word does not exist, return 0.
>
>**Note:** A word is defined as a character sequence consists of non-space characters only.
>
>For example, 
>Given *s* = `"Hello World"`,
>return `5`.

​	思路很简单了，就是用“  ” 对字符串进行分割，分割后的结果是一个String类型的数组，计算这个数组最后一个元素的长度就可以了。

代码：

```java
public int lengthOfLastWord(String s) {
		if(s == null || s.length() == 0 || s == " ")
			return 0;
		String[] strs = s.split(" ");
		if(strs.length >= 1)
			return strs[strs.length-1].length();
		else return 0;
    }
```

## S.delete digits

题目：

​	输入一个数字num ，然后给定一个K，在num中删除k位数字，剩下的数字按照原先的相对顺序不变，使得该数字最大。

输入样例：

​	54921

​	2

返回：921

思路：

>- 确保一个原则：在删除数字的过程中，尽可能让高位的数字最大。为此，我们需要找到最大数字的下标，记作index。需要移除的位数，我们记作remove。
>- 如果index<=remove，这种情况下，index左边的数字需要全部移除。
>  - 当index是最后一位（同时，remove的值和index左边数字的位数相同），只需要返回index本身，因为删除的位数够了，同时index右边也没有数字了。
>  - 否则，删除index左边所有的数，保留index本身的情况下，需要在index右边删除remove-index位数字。在删除的过程中，也是将最大值放到最高位。**此处也是一个递归的过程。**
>- 如果index>remove，这种情况下，index以及index右边所有的元素需要保留。同时在index的左边的字串中找到最大值，然后将其放到最高位。**至此，可以发现是一个递归的过程，只需要调用自身即可。**

代码：

```java
public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String origNum = scanner.nextLine();
        String remove = scanner.nextLine();
        System.out.println(remainNum(origNum,Integer.parseInt(remove)));
    }
    public static String remainNum(String origNum,int remove) {
        if(origNum == null || origNum.length() <= 0 || remove >= origNum.length()) {
            return "";
        }
        char[] numbers = origNum.toCharArray();
        Arrays.sort(numbers);
        int index = origNum.indexOf(numbers[numbers.length-1]);
        if(remove <=0 ) {
            return origNum;
        }
        if(origNum.length() == 1 && remove == 1) {
            return "";
        }
        //当需要删除的位数大于等于最大数的下标时，此时index左边的数需要全部删除
        if(index <= remove) {
            if(index == origNum.length()-1){   //remove == origNum.length()-1也是可以的
            	//这种情况下，最大数的左边正好有remove位，此时需要把这些位全部移出，只保留最后一位
            	return String.valueOf(numbers[numbers.length-1]);
            	
            }
            else {
            	//最大数左边的位数小于remove的值，index左边的全部删掉还不够，还需要在index的右边继续删除remove-index位数字
                return String.valueOf(numbers[numbers.length-1])+remainNum(origNum.substring(index+1),remove -index);
            }
        } else {
        	//index左边的数需要有选择的删除，此时index右边的数全部保留
        	return remainNum(origNum.substring(0,index),remove)+ origNum.substring(index);
        }
    }
```

## S.520_Detect Capital

题目：

>Given a word, you need to judge whether the usage of capitals in it is right or not.
>
>We define the usage of capitals in a word to be right when one of the following cases holds:
>
>1. All letters in this word are capitals, like "USA".
>2. All letters in this word are not capitals, like "leetcode".
>3. Only the first letter in this word is capital if it has more than one letter, like "Google".
>
>Otherwise, we define that this word doesn't use capitals in a right way.

代码：

```java
public boolean detectCapitalUse(String word) {
  
		int capitalCount = 0;
		for(char c : word.toCharArray()){
			if(Character.isUpperCase(c)){
				capitalCount++;
			}
		}
		
		if(capitalCount == word.length())
			return true;
		if(capitalCount == 0)
			return true;
		if(capitalCount == 1 && Character.isUpperCase(word.charAt(0)) && word.length() >1 )
			return true;
		
		return  false;
	}
```









