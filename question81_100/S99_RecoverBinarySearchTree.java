package question81_100;

import java.util.ArrayList;

import org.junit.Test;

/**
 * Recover Binary Search Tree
 * 
 *  Two elements of a binary search tree (BST) are swapped by mistake.
 
Recover the tree without changing its structure.
 
Note:
A solution using O(n) space is pretty straight forward. Could you devise a constant space solution?
confused what "{1,#,2,3}" means? > read more on how binary tree is serialized on OJ.
 
 
OJ's Binary Tree Serialization:
The serialization of a binary tree follows a level order traversal, where '#' signifies a path terminator where no node exists below.
 
Here's an example:
   1
  / \
 2   3
    /
   4
    \
     5
The above binary tree is serialized as "{1,2,3,#,#,4,#,#,5}".
 *
 */
public class S99_RecoverBinarySearchTree {
 
    public static void main(String[] args) {
		
    }
    /*
     *         3
     *        / \  
     *       1   5
     *        \
     *         2
     *  The main point is to find out what are the mistake values, and record their value, finally change their values.
     */
    @Test
    public void test1(){ 
    	TreeNode root = new TreeNode(3);
    	root.left = new TreeNode(2);
    	root.right = new TreeNode(5);
    	root.left.right = new TreeNode(1);
    	ArrayList<Integer> result1 = (ArrayList<Integer>) S94_InorderTraversal.InOrder(root);
		System.out.println(result1);
		recoverTree(root);
		ArrayList<Integer> result2 = (ArrayList<Integer>) S94_InorderTraversal.InOrder(root);
		System.out.println(result2);
    }
    
    TreeNode pre;       // 指向当前遍历元素的前一个
    TreeNode first; // 第一个乱序的元素
    TreeNode second;// 第二个乱序的元素
     
    public void inorder(TreeNode root){
        if(root == null){
            return;
        }
        inorder(root.left);
        if(pre == null){
            pre = root;
        }else{
            if(pre.val > root.val){
                if(first == null){
                    first = pre;        // 找到第一个乱序的元素
                }
                second = root;      
            }
            pre = root;             // 继续搜索
        }
        inorder(root.right);
    }
    
    public void recoverTree(TreeNode root) {
        pre = null;         // 必须在这里初始化一遍，否则OJ会报错
        first = null;
        second = null;
        inorder(root);
        if(first!=null && second!=null){        // 只需要交换元素值，而没必要进行指针操作！
            int tmp = first.val;
            first.val = second.val;
            second.val = tmp;
        }
        
    }
 
}