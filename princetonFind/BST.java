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
		if( x == null ) return new Node(key, val, 1);
		
		//1.
		int cmp = key.compareTo(x.key);
		if(cmp < 0) x.left = put(x.left, key, val);
		else if(cmp > 0) x.right = put(x.right, key, val);
		else x.val = val;
		x.N = size(x.left) + size(x.right)+1;
		return x;
	}

	public Key min(){
		return min(root).key;
	}
	private Node min(Node x){
		if(x.left == null)
			return x;
		return min(x.left);
	}
	
	public Key floor(Key key){
		Node x = floor(root, key);
		if(x == null) 
			return null;
		return x.key;
	}
	//找到小于或者等于key的最大值
	private Node floor(Node x, Key key){
		if(x == null) 
			return null;
		int cmp = key.compareTo(x.key);
		if(cmp == 0) return x;
		if(cmp < 0) return floor(x.left, key);
		Node t = floor(x.right, key);
		if(t != null)
			return t;
		else 
			return x;
	}
	
	//找到排序为k的结点，排序从0开始
	public Key select(int k){
		return select(root, k).key;
	}
	
	private Node select(Node x, int k){
		//返回排名为k的的结点
		if(x == null) return null;
		int t = size(x.left);  //先拿到x的排名，然后用x和k做比较
		if(t > k) 
			return select(x.left, k);
		else if(t < k)
			return select(x.right, k-t-1);
		else
			return x;
		
	}
	
	//rank 返回指定键的排名，排名从0开始
	public int rank(Key key){
		return rank(key, root);
	}
	
	private int rank(Key key, Node x){
		//返回以x为根结点的子树中小于x.key的键的数量
		if(x == null) return 0;
		int cmp = key.compareTo(x.key);
		if(cmp < 0)
			return rank(key, x.left);
		else if(cmp > 0)
			return 1 + size(x.left) + size(x.right);
		else
			return size(x.left);//如果key和x.key相等，那么x的左子树的结点的数量，就是小于x.key的键的数量
	}
	
	//二叉查找树中删除最小结点
	public void deleteMin(){
		root = deleteMin(root);
	}
	private Node deleteMin(Node x){
		if(x.left == null )
			return x.right;   //相当于删除了x结点（即root结点），x.right是新的结点。
		x.left = deleteMin(x.left);
		x.N = size(x.left) + size(x.right) + 1;
		return x;
	}
	
}
