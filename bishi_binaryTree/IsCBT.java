package bishi_binaryTree;

import java.util.LinkedList;

/**
 * 判断一颗二叉树是否是完全二叉树
 * 1.使用层次遍历
 * 2.如果当前节点有右孩子，但是没有左孩子。说明这棵二叉树不是完全二叉树，直接返回false。
 * 3.如果当前不是左右孩子都有，那么当前结点之后的所有结点必须是孩子结点，否则返回false。
 * 4.遍历过程中如果不返回false，遍历结束后返回true。
 * 
 * @author chengcheng
 * @time 2016年10月17日 上午11:39:33
 *
 */
public class IsCBT {
	public boolean isCBT(Node head){
		if(head == null){
			return true;
		}
		
		LinkedList<Node>	queue = new LinkedList<Node>();
		boolean leaf = false;
		Node l = null;
		Node r = null;
		queue.offer(head);
		
		while(!queue.isEmpty()){
			Node cur = queue.poll();
			l = cur.left;
			r = cur.right;
			if(leaf && (l != null || r != null) || (l == null && r != null)){
				return false;
			}
			if(l != null){
				queue.offer(l);
			}
			if(r != null){
				queue.offer(r);
			}
			else{
				leaf = true;
			}
			
		}
		
		return true;
	}
}
