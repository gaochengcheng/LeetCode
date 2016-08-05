# Leetcode刷题笔记（数组部分）



[TOC]

## S.33_Search in Rotated Sorted Array

## S.1_Two Sum

原题地址：

https://leetcode.com/problems/two-sum/

**思路1**：

​	使用hash表，假设a+b=target。

1. 遍历元素，假设每次遍历到的元素是a，如果map中不包含这个target-a，则把target-a作为key进行存储，当前下标作为value进行存储。
2. 由于存储的是差值，当下次再遍历到该这个key的时候，就证明我们已经找到了这两个元素。
3. 存储两次的下标就行。

**代码**：时间复杂度O(n)

```java
public static int[] test(int[] nums, int target){
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		int[] result = new int[2];
	 
		for(int i = 0; i < nums.length; i++){
			if(!map.containsKey(nums[i]))
				map.put(target-nums[i], i);   //把差值作为key存储
			else{
				int index = map.get(nums[i]);  //找到这个key时，就说明找到了两个数
				result[0] = index;
				result[1] = i;
				break;
			}
		}
	 
		return result;
	}

	
```

**思路2**：

​	

## S.15_3Sum

原题地址：

[http://www.lintcode.com/problem/3sum/](http://www.lintcode.com/problem/3sum/)

思路：

1. 把数组排序。
2. 最外层使用一个循环，遍历数组元素，范围（0~num.length-1）。
3. 内层使用一个循环和两个指针，分别从头部和尾部开始移动，在外层循环固定到一个数的情况下，头部向后移动，尾部向前移动，直至满足a+b+c=0。

代码：

```java
public static List<List<Integer>> threeSum(int[] nums) {
		List<List<Integer>> result = new ArrayList<>();
		Arrays.sort(nums);
		
		for(int i = 0; i < nums.length-1; i++){
			//[-4,-1,-1,0,1,2]，当nums[i]=第一个-1时，有一个答案[-1,0,1],当nums[i]=第二个-1时，还有一个
			//答案是[-1,0,-1]，所以就需要排除这个情况。
			if(i > 0 && nums[i] == nums[i-1]) continue;
			int begin = i+1;
			int end = nums.length-1;
			while(begin < end){
				if(nums[i] + nums[begin] + nums[end] == 0){ 
					List<Integer> list = new ArrayList<Integer>();
					list.add(nums[i]);
					list.add(nums[begin]);
					list.add(nums[end]);
					result.add(list);
					begin++;
					end--;
					while(begin < end && nums[begin] == nums[begin-1])//begin 要和之前的begin 比较
						begin++;
					while(begin < end && nums[end] == nums[end+1])//end要和之后的end比较
						end--;
					
				}else if(nums[i] + nums[begin] + nums[end] > 0){
					end--;
				}else{
					begin++;
				}
			}
			
		}
		return result;
	}	
```



## S.16_3Sum Closest

原题地址：

https://leetcode.com/problems/3sum-closest/

思路：

	1.	先排序。
	2.	外层使用for循环，遍历每一个元素。内层左右夹逼，每当发现a+b+c更接近target的时候，就更改result的值。
	3.	如果sum > target，如果sum < target ，begin++。

代码：

```java
public static int threeSumClosest(int[] nums, int target) {
		Arrays.sort(nums);   //先排序
		int sum = 0;
		int result = 0;
		int diff = 0;
		int min_diff = 10000;
		for(int i = 0; i < nums.length-1; i++){  //外层循环
			int begin = i+1;
			int end = nums.length-1;
			while(begin < end){
				sum = nums[i] + nums[begin] + nums[end];
				diff = Math.abs(sum - target);
				if(diff < min_diff){
					min_diff = diff;
					result = sum;
				}
				
				if(sum < target)   //之前排序的原因在这里，sum<target，begin 就往后移动
					begin++;
				else				//否则 如果 sum > target，end 就往前移动
					end--;
			}
		}
			
		return result;
    }
```

##S.18_4Sum

原题地址：

https://leetcode.com/problems/4sum/

思路：

1. 对数组排序。
2. 外面使用两层循环，枚举数组中的a 和b。内层使用being和end两个指针代表c和d，分别从两端往中间遍历。
3. 当a+b+c+d=target时，保存一次答案，如果a+b+c+d>target，end--；如果a+b+c+d<target，begin++。

代码：

```java
public static List<List<Integer>> fourSum(int[] nums, int target) {
		List<List<Integer>> result = new ArrayList<>();
		Arrays.sort(nums);
		
		for(int i = 0; i<nums.length-2; i++){
//			if( i > 0 && nums[i] == nums[i-1]) continue;   //去重
			for(int j = i+1; j<nums.length-1; j++){
//				if(j > 1 && nums[j] == nums[j-1] ) continue;  //两个去重条件，写不完整，干脆将去重的工作放到最后。
				int begin = j+1;
				int end = nums.length-1;
				while(begin < end){
					if(nums[i] + nums[j] + nums[begin] + nums[end] == target){
						List<Integer> list = new ArrayList<>();
						list.add(nums[i]);
						list.add(nums[j]);
						list.add(nums[begin]);
						list.add(nums[end]);
						result.add(list);
						begin++;
						end--;
						while(begin < end && nums[begin]==nums[begin-1]) begin++;
						while(begin < end && nums[end]==nums[end+1]) end--;
					}else if(nums[i] + nums[j] + nums[begin] + nums[end] > target){
						end--;
					}
					else{
						begin++;
					}
				}
			}
		}
		HashSet<List<Integer>> set = new HashSet<>(result);  //去重的工作在这里做
		List<List<Integer>> res = new ArrayList<>(set);
		return res;
	}
```

技巧：通过下面的两步，让ArrayList中的元素去重复。

​	   先把ArrayList中的元素放到Hashset中，然后在使用HastSet构造一个ArrayList。

```java
	HashSet<List<Integer>> set = new HashSet<>(result);
	List<List<Integer>> res = new ArrayList<>(set);
```

