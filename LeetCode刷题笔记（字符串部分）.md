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

​	我们的做法是，设立两个指针，分别指向这个字符串的首位两端。

