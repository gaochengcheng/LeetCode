package contest19;


/**
 * 考察“递归方式先序遍历”的掌握
 * 巧用全局变量
 * 
 * @author chengcheng
 * @time 2017年2月12日 上午10:47:19
 *
 */
public class S513_FindLeftMostElement {
	int mxd,val;
	public int findLeftMostNode(TreeNode root) {
		
		mxd = -1;
		dfs(root,0);
		
        return val;
    }
	
	private void dfs(TreeNode root, int depth){
		if(root == null)
			return ;
		//先序遍历
		if(mxd < depth){
			mxd = depth;
			//val的值满足两个条件：1.该node是最深深度的。2.最深深度中最左边的那个（通过先序遍历保证了是最左边的那个）
			val = root.val;
		}

		dfs(root.left,depth+1);
		dfs(root.right,depth+1);
	}
}
