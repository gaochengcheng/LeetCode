package question141_160;

import java.util.HashMap;

/**
 * 
 * @author chengcheng
 * @time 2016年8月20日 上午9:35:28
 *
 */
public class S146_LRUCache {
	private HashMap<Integer,DoubleLinkedListNode> map = 
			new HashMap<Integer,DoubleLinkedListNode>();
	private DoubleLinkedListNode head;
	private DoubleLinkedListNode end;
	private int capacity;
	private int len;
	
	public S146_LRUCache(int capacity) {
		this.capacity = capacity;
	}
	//获得一个元素，如果该元素存在就把这个元素从当前位置移除并且放到双端队列的头部
	//如果不存在，就返回-1
	public int get(int key) {
		if(map.containsKey(key)){
			DoubleLinkedListNode latest = map.get(key);
			removeNode(latest);
			setHead(latest);
			return latest.val;
		}else{
			return -1;
		}
	}
	
	public void setHead(DoubleLinkedListNode node){
		node.next = head;
		node.pre = null;   //缺少这一行，答案不对
		if(head != null){
			head.pre = node;
		}
		head = node;  //head指向node
		
		if(end == null){
			end = node;
		}
	}
	
	public void removeNode(DoubleLinkedListNode node){
		DoubleLinkedListNode cur = node;
		DoubleLinkedListNode pre = cur.pre;
		DoubleLinkedListNode post = cur.next;
		
		if(pre != null){
			pre.next = post;
		}else{
			head = post;
		}
		
		if(post != null){
			post.pre = pre;
		}else{
			end = pre;
		}
		
	}
	
	public void set(int key, int value) {
		if(map.containsKey(key)){//key值存在，只需要更新
			DoubleLinkedListNode oldNode = map.get(key);
			oldNode.val = value;
			removeNode(oldNode);
			setHead(oldNode);
		}else{
			//key不存在，分两种情况，一种是length == capacity，一种是length < capacity
			DoubleLinkedListNode newNode = new DoubleLinkedListNode(key,value);
			if(len < capacity){
				setHead(newNode);
				map.put(key, newNode);
				len++;
			}else{
				//map中删除最后的元素，双端队列中也删除
				//新放入的node放到head,同时放入map中
				map.remove(end.key);
				end = end.pre;
				if(end != null){
					end.next = null;
				}
				
				setHead(newNode);
				map.put(key, newNode);
			}
		}
	}
	
}


//双端队列数据结构
class DoubleLinkedListNode{
	public int val;
	public int key;
	public DoubleLinkedListNode pre;
	public DoubleLinkedListNode next;
	
	public DoubleLinkedListNode(int key,int value){
		this.key = key;
		val = value;
	}
}