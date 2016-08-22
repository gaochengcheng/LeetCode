package question101_120;

import java.util.LinkedList;

import org.junit.Test;

public class S117_PopulatingNextRightPointersinEachNodeII {
	public void connect(TreeLinkNode root) {
		if(root == null){
			return;
		}
		
		LinkedList<TreeLinkNode> current = new LinkedList<TreeLinkNode>();
		LinkedList<TreeLinkNode> next = new LinkedList<TreeLinkNode>();
		
		current.addLast(root);
		while(!current.isEmpty()){
			while(!current.isEmpty()){
				TreeLinkNode node = current.getFirst();   //只拿到值，并不删除元素，还需要调用poll，才会删除
				
				current.poll();
				if(!current.isEmpty()){
					node.next = current.getFirst();
				}else{
					node.next = null;
				}
				
				if(node.left != null)
					next.addLast(node.left);
				if(node.right != null)
					next.addLast(node.right);
				
			}
			
			current = next;
			next = new LinkedList<TreeLinkNode>();
		}
		
	}

	@Test
	public void test() {

	}
}
