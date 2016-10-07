package princetonFind;

import princetonFind.BST.Node;

/**
 * P281课本
 * @author chengcheng
 * @time 2016年10月7日 下午9:30:34
 *
 */
public class RedBlackBST<Key extends Comparable<Key>,Value> {
	private Node root;
	private static final boolean RED = true;
	private static final boolean BLACK = false;
	private class Node{
		Key key;
		Value val;
		Node left, right;
		int N;
		boolean color;
		
		Node(Key key, Value val, int N, boolean color){
			this.key = key;
			this.val = val;
			this.N = N;
			this.color = color;
		}
	}
	
	private boolean isRed(Node x){
		if(x == null)
			return false;
		return x.color = RED;
	}
	
	private int size(Node x){
		if(x == null)
			return 0;
		else
			return x.N;
	}
	
	Node rotateLeft(Node h){
		Node x = h.right;
		h.right = x.left;
		x.left = h;
		x.color = h.color;
		h.color = RED;
		x.N = h.N;
		h.N = 1 + size(h.left) + size(h.right);
	}
	
	Node rotateRight(Node h){
		Node x = h.left;
		h.left = x.right;
		x.right = h;
		x.color = h.color;
		h.color = RED;
		x.N = h.N;
		h.N = 1 + size(h.left) + size(h.right);
		return x;
	}
	
	void flipColors(Node h){
		h.color = RED;
		h.left.color = BLACK;
		h.right.color = BLACK;
	}
	
	private void put(Key key, Value val){
		//查找key，找到则更新其值，否则为它新建一个结点
		root = put(root, key, val);
		root.color = BLACK;
	}
	
	private Node put(Node h, Key key, Value val){
		if(h == null)
			//标准的插入操作，和父结点用红链接相连
			return new Node(key, val, 1, RED);
		
		int cmp = key.compareTo(h.key);
		if(cmp < 0)
			h.left = put(h.left, key, val);
		else if(cmp > 0)
			h.right = put(h.right, key, val);
		else
			h.val = val;  //找到了，则跟新这个键的值
		
		if(isRed(h.right) && !isRed(h.left))
			h = rotateLeft(h);
		if(isRed(h.left) && isRed(h.left.left))
			h = rotateRight(h);
		if(isRed(h.left) && isRed(h.right))
			flipColors(h);
		
		h.N = size(h.left) + size(h.right) + 1;
		return h;
		
	}
	
}
