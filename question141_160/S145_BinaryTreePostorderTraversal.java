package question141_160;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 
 * @author chengcheng
 * @time 2016年8月21日 上午11:25:11
 *
 */
public class S145_BinaryTreePostorderTraversal {
	public List<Integer> postorderTraversal(TreeNode root) {
		List<Integer> result = new ArrayList<Integer>();
		TreeNode p = root,q = null;
		Stack<TreeNode> stack = new Stack<TreeNode>();
		
		do{
			while(p != null){
				stack.push(p);
				p = p.left;
			}
			q = null;   //q 初始化为null
			
			while(!stack.isEmpty()){
				p = stack.peek();
				stack.pop();
				
				if(p.right == q){
					result.add(p.val);
					q = p;   //保存刚刚访问过的节点
				}else{
					//再次入栈
					stack.push(p);
					p = p.right;
					break;
				}
			}
			
		}
		while(!stack.isEmpty());
		
		return result;
	}
}
