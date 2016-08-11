package question121_140;

import org.junit.Test;

/**
 * 
 * @author chengcheng
 * @time 2016年8月11日 上午8:22:33
 *
 */
public class S135_Candy {

	public int candy(int[] ratings){
		
		int length = ratings.length;
		int[] leftResult = new int[length];
		int[] rightResult = new int[length];
		//每个元素只和左边元素相比较
		leftResult[0] = 1;
		for(int i = 1; i < length; i++){
			if(ratings[i] > ratings[i-1])
				leftResult[i] = leftResult[i-1]+1;
			else
				leftResult[i] = 1;
		}
		//每个元素只和右边元素比较
		rightResult[length-1] = 1;
		for(int i = length-2; i >= 0; i--){
			if(ratings[i] > ratings[i+1])
				rightResult[i] = rightResult[i+1]+1;
			else
				rightResult[i] = 1;
		}
		int sum = 0;
		for(int i = 0; i<length; i++){
			if(leftResult[i] > rightResult[i])
				sum += leftResult[i];
			else
				sum += rightResult[i];
		}
		return sum;
	}
	/**
	 * 写法应该正确，但是运行超时
	 * @param ratings
	 * @return
	 */
	public int candy_2(int[] ratings) {
		int length = ratings.length;
		int[] result = new int[length];
		for(int i = 0; i < length; i++){
			result[i] = 1;
		}
		for(int i = 0; i < length; i++){
			if(length > 1){
				if(i == 0 ){
					while(ratings[i] > ratings[i+1] && result[i] <= result[i+1]){
						result[i]++;
					}
					
				}
				else if(i == length-1){
					while(ratings[i-1] < ratings[i] && result[i-1] >= result[i]){
						result[i]++;
					}
				}
				else{
					while(ratings[i] > ratings[i-1] && result[i] <= result[i-1]){
						result[i]++;
					}
					while(ratings[i] > ratings[i+1] && result[i] <= result[i+1]){ 
						result[i]++;
						//i位置值的改变，很可能会导致前边的值也需要做出相应的改变
						//重复计算的地方在于：前面计算好的值，由于后面值+1，也需要相应做出调整。而且经常需要检查前面的值是否需要+1.
						while(ratings[i] < ratings[i-1] && result[i] >= result[i-1]){
							result[i-1]++;
							if(i>1)
								i--;
						}
					}
				}
				
			}
			else{
				result[i] = 1;
			}
			
		}
		int sum = 0;
		for(int element : result){
			sum += element;
		}
		return sum;
	}

	@Test
	public void test() {
		int[] arr = {1,3,4,3,2,1};
		System.out.println(candy(arr));
	}
}
