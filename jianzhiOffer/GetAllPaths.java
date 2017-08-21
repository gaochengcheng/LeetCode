package jianzhiOffer;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class GetAllPaths {
	public List<String> resultList = new ArrayList<String>();

	public List<String> binaryTreePaths(TreeNode root) {
	    if (root == null) {
	        return resultList;
	    }
	    List<String> singleResult = new ArrayList<>();

	    getTreePath(root, singleResult);
	    return resultList;
	}
	
	
	
	private void getTreePath(TreeNode node, List<String> singleResult) {
	    singleResult.add(node.val + "");
	    if (node.left == null && node.right == null) {
	        resultList.add(singleResult.toString());
	    }
	    if (node.left != null) {
	        getTreePath(node.left, new ArrayList<>(singleResult));
	    }
	    if (node.right != null) {
	        getTreePath(node.right, new ArrayList<>(singleResult));
	    }
	}
	@Test
	public void test(){
		TreeNode root = new TreeNode(3);
		root.left = new TreeNode(9);
		root.right = new TreeNode(20);
		TreeNode node = root.right.left = new TreeNode(15);
		root.right.right = new TreeNode(7);
		
		System.out.println(binaryTreePaths(root));
	}
}
