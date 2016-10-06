package princetonFind;
/**
 * 问题：在一个类上也可以添加泛型信息？
 * @author chengcheng
 * @time 2016年10月6日 下午3:16:45
 *
 * @param <Key>
 * @param <Value>
 */
public class BST<Key extends Comparable<Key>, Value> {
	private Node root;
	
	private class Node{
		private Key key;
		private Value val;
		private Node left, right;
		private int N;
		
		public Node(Key key, Value val, int N){
			this.key = key;
			this.val = val;
			this.N = N;
		}
		
		
	}
	public int size(){  
		return size(root);
	}
	
	private int size(Node x){
		if(x == null)
			return 0;
		else
			return x.N;
	}
	
	//在二叉查找树上使用递归的方式进行查找
	public Value get(Key key){
		return get(root, key);
	}
	
	private Value get(Node x, Key key){
		if(x == null)
			return null;
		int cmp = key.compareTo(x.key);
		
		if(cmp < 0) return get(x.left, key);
		else if (cmp > 0) return get(x.right, key);
		else return x.val;
	}
	
	
	public void put(Key key, Value val){
		//查找key，找到则更新它的值，否则为它创建一个新的结点
		root = put(root, key, val);
	}
	
	private Node put(Node x, Key key, Value val){
		//1.如果key 存在于以x为根节点的子树中，则更新它的值
		//2.否则将以key和val为键值对的新节点插入到该子树中
		
		//2.
		if(x == null ) return new Node(key, val, 1);
		
		//1.
		int cmp = key.compareTo(x.key);
		if(cmp < 0) x.left = put(x.left, key, val);
		else if(cmp > 0) x.right = put(x.right, key, val);
		else x.val = val;
		x.N = size(x.left) + size(x.right)+1;
		return x;
	}

	

	
}
