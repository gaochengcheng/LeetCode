package bishi_binaryTree;

import org.junit.Test;

/**
 * 
 * @author chengcheng
 * @time 2016年8月24日 下午9:18:09
 *
 */
public class S96_UniqueBinarySearchTrees {
	public int numTrees(int n) {
		
		//创建一个有n+1个元素的数组，f[0]的含义是：当有0个元素时，树的形态有f[0]种。
		//f[n]的含义是：当有n个元素时，树的形态有f[n]中。
		int[] f = new int[n+1];
		for(int temp:f)
			System.out.println(temp);
		
		
		f[0] = 1;
		f[1] = 1;
		//i表示树中包含的元素个数
		for(int i = 2; i <= n; i++){
			//当k为根时，f[k-1]和f[i-k]分别代表左子树有几种情况和右子树的有几种情况。
			//f[k-1]和f[i-k]相乘，就是f[k]为根时，所拥有的不同形态的数量。
			for(int k = 1; k <= i; k++){
				f[i] += f[k-1] * f[i-k];
			}
		}
		return f[n];
	}

	@Test
	public void test() {
		System.out.println(numTrees(1));
	}
}
