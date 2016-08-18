package question121_140;

import java.util.HashMap;

public class S138_CopyListwithRandomPointer {
	//使用hashmap，key存储原始链接的RandomListNode，value存储新链接的RandomListNode。
	public RandomListNode copyRandomList(RandomListNode head) {
        if(head == null)
        	return null;
        //map中key和value的节点值是相同的，不过是不同的对象。
        HashMap<RandomListNode,RandomListNode> map = new HashMap<RandomListNode,RandomListNode>();
        RandomListNode newhead = new RandomListNode(head.label);
        map.put(head, newhead);
        
        RandomListNode oldp = head.next;
        RandomListNode newp = newhead;
        
        while(oldp != null){
        	RandomListNode newnode = new RandomListNode(oldp.label);
        	map.put(oldp, newnode);
        	newp.next = newnode;
        	
        	oldp = oldp.next;
        	newp = newp.next;
        	
        }
        oldp = head;
        newp = newhead;
        while(oldp != null){
        	// 等号右边是一个新的节点,因为oldp的值和newp的值相同，现在取出oldp.random对应的value给了newp.random。
        	newp.random = map.get(oldp.random); 
        	oldp = oldp.next;
        	newp = newp.next;
        	
        }
        
        return newhead;
    }
}
