package bishi_binaryTree;
/**
 * 统计一颗完全二叉树中的结点个数
 * 时间复杂度O(h^2)  h是树的高度
 * @author chengcheng
 * @time 2016年10月21日 下午7:10:36
 *
 */
public class NodeNum {
	public int nodeNum(Node head){
		if(head == null)   //1.如果head == null，说明是空树，直接返回0.
			return 0;

		return bs(head, 1, mostLeftLevel(head,1));
	}
	//2.如果不是空树，就求出树的高度，求法是找到树的最左结点看能到达哪一层。
	public int mostLeftLevel(Node node, int level){
		while(node != null){
			node = node.left;
			level += 1;
		}
		return level - 1;
	}
	//3.通过一个递归的过程。
	//接下来的这个程序是递归形式，所以首先要写上递归停止的条件
	public int bs(Node node, int l, int h){ 
		if(l == h)
			return l;
		
		if(mostLeftLevel(node.left, l+1) == h){
			return (1 << (h - 1) + bs(node.right, l + 1, h));  //左子树是满的，
		}else{
			return (1 << (h - l - 1) + bs(node.left, l + 1, h)); //右子树是满的。
		}
			
	}
}
