package question101_120;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

/**
 * 
 * @author chengcheng
 * @time 2016年8月29日 上午11:28:46
 *
 */
public class S113_PathSumII {
	public List<List<Integer>> pathSum(TreeNode root, int sum) {
		
		List<List<Integer>> pathlist=new ArrayList<List<Integer>>();
		List<Integer> sumlist = new ArrayList<Integer>();
		pathSumHelper(root,sum,sumlist,pathlist);
		
		return pathlist;
	}
	
	public void pathSumHelper_2(TreeNode root, int sum, List <Integer> sumlist, List<List<Integer>> pathlist){
        if(root==null) 
            return;
        
        sumlist.add(root.val);
        sum = sum-root.val;
        if(root.left==null && root.right==null){
            if(sum==0){
                pathlist.add(new ArrayList<Integer>(sumlist));//这里必须重新定一个ArrayList,否则会把原先的ArrayList的值修改了，因为是同一块地址。
            }
        }else{
            if(root.left != null)
                pathSumHelper(root.left,sum,sumlist,pathlist);
            if(root.right != null)
                pathSumHelper(root.right,sum,sumlist,pathlist);
        }
        sumlist.remove(sumlist.size()-1);   //这里是回溯时候执行的的
    }

	@Test
	public void test() {
		TreeNode node1 = new TreeNode(5);
		TreeNode node2 = node1.left = new TreeNode(4);
		TreeNode node3 = node1.right = new TreeNode(8);
		TreeNode node4 = node2.left = new TreeNode(11);
		node4.left = new TreeNode(7);
		node4.right = new TreeNode(2);
		node3.left = new TreeNode(13);
		TreeNode node5 = node3.right = new TreeNode(4);
		node5.left = new TreeNode(5);
		node5.right = new TreeNode(1);
		
		List<List<Integer>> list = pathSum(node1,22);
		System.out.println(list);
		
	}
	
	//因为是数组传递，空间是共享的。返回值是void，但是主调函数中的pathlist已经被修改了
	public void pathSumHelper(TreeNode root, int sum, List <Integer> sumlist, List<List<Integer>> pathlist){
		if(root == null)
			return ;
		
		sumlist.add(root.val);
		sum = sum-root.val;
		if(root.left == null && root.right == null){
			if(sum == 0){
				pathlist.add(new ArrayList<Integer>(sumlist));
			}
		}else{
			if(root.left != null)
				pathSumHelper(root.left,sum,sumlist,pathlist);
			if(root.right != null)
				pathSumHelper(root.right,sum,sumlist,pathlist);
		}
		sumlist.remove(sumlist.size()-1);
		
	}

}
