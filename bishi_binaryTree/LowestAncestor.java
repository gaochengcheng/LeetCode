package bishi_binaryTree;
/**
 * 寻找o1,o2结点的最近公共祖先结点。
 * 1.
 * 2.
 * 3.
 * 4.
 * @author chengcheng
 * @time 2016年10月17日 下午7:27:16
 *
 */
public class LowestAncestor {
	
	public Node lowestAncestor(Node head, Node o1, Node o2){
		if(head == null || head == o1 || head == o2){
			return head;
		}
		Node left = lowestAncestor(head.left, o1, o2);
		Node right = lowestAncestor(head.right, o1, o2);
		if(left != null && right != null)
			return head;
		return left != null ? left : right;
	}
}
