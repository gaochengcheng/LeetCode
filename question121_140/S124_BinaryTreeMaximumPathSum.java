package question121_140;

import org.junit.Test;


/**
 * 
 * @author chengcheng
 * @time 2016年8月31日 上午8:47:12
 *
 */
public class S124_BinaryTreeMaximumPathSum {
	
	 int maxValue;
	public int maxPathSum(Node root) {
		maxValue = Integer.MIN_VALUE;
//        System.out.println(maxPathDown(root));
		maxPathDown(root);
        return maxValue;
	}

	private int maxPathDown(Node node) {
        if (node == null) return 0;
        int left = Math.max(0, maxPathDown(node.left));
        int right = Math.max(0, maxPathDown(node.right));
        //下面都是回溯过程中要执行的
        maxValue = Math.max(maxValue, left + right + node.val);//我们认为最大值从root+root.left+root.right中产生。
        return Math.max(left, right) + node.val;//每次返回的时候，必须选择较大的那个值，然后加上当前节点的值。
    }
	@Test
	public void test() {
		
		Node node1 = new Node(5);
		Node node2 = node1.left = new Node(4);
		Node node3 = node1.right = new Node(8);
		Node node4 = node2.left = new Node(11);
		node4.left = new Node(7);
		node4.right = new Node(2);
		node3.left = new Node(13);
		Node node5 = node3.right = new Node(4);
		node5.left = new Node(5);
		node5.right = new Node(1);
		
		System.out.println(maxPathSum(node1));
		
	}
}
