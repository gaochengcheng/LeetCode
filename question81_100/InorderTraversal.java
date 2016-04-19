package question81_100;

import java.util.ArrayList;
import java.util.List;



/**
 * {1,#,2,3},
 * 1
    \
     2
    /
   3
   output:[1,3,2]
 * @author chengcheng
 *
 */
public class InorderTraversal {
	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = null;
		List<Integer> list = InOrder(root);
		System.out.println(list.get(0)+"  "+list.get(1));
	}
	public static List<Integer> InOrder(TreeNode root){
		ArrayList<Integer> list = new ArrayList<Integer>();
		TreeNode cur = root,prev = null;
		while(cur != null){
			if(cur.left == null){
				list.add(cur.val);
				cur = cur.right;
			}
			else{
				prev = cur.left;
				while(prev.right != null && prev.right != cur)
					prev = prev.right;      //new previous node
				if(prev.right == null){ //2.a
					prev.right = cur;   //做线索
					cur = cur.left;
				}
				else{ // 2.b
					prev.right = null;   //线索已经发挥作用，取消线索，恢复树。
					list.add(cur.val);
					cur = cur.right;	
				}
			}
		}
		return list;
	}
}
