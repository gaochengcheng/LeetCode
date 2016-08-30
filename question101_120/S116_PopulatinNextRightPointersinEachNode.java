package question101_120;

import java.util.LinkedList;
/**
 * 
 * @author chengcheng
 * @time 2016年8月30日 下午1:35:38
 *
 */
public class S116_PopulatinNextRightPointersinEachNode {
	public void connect(TreeLinkNode root) {
        if(root == null)
            return ;
        
        LinkedList<TreeLinkNode> cur = new LinkedList<TreeLinkNode>();
        LinkedList<TreeLinkNode> next = new LinkedList<TreeLinkNode>();
        
        cur.addLast(root);
        while(!cur.isEmpty()){
            while(!cur.isEmpty()){
                TreeLinkNode node = cur.getFirst();
                cur.poll();
                
                if(!cur.isEmpty()){
                    node.next = cur.getFirst();
                }else{
                    node.next = null;
                }
                
                if(node.left != null)
                    next.addLast(node.left);
                if(node.right != null)
                    next.addLast(node.right);
                    
                
            }
            
            cur = next;
            next = new LinkedList<TreeLinkNode>();
            
        }
    }
}
