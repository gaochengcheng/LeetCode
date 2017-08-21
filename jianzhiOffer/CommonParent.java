package jianzhiOffer;

import java.util.ArrayList;

import org.junit.Test;



public class CommonParent {
	//一种写法
	public boolean getPath(TreeNode root, TreeNode node, ArrayList<TreeNode> list){
		if(root == null) return false;
		list.add(root);
		if(root == node) return true;
		if(getPath(root.left, node, list)) return true;
		if(getPath(root.right, node, list))return true;
		list.remove(list.size()-1);
		return false;
	}
	
	public int getLastCommonNode(ArrayList<TreeNode> list1, ArrayList<TreeNode> list2){
		if(list1 == null || list2 == null || list1.size() == 0 || list2.size() == 0)
			return -1;
		
		int comValue = -1;
		int index = 0;
		while(index < list1.size() && index < list2.size()){
			if(list1.get(index) == list2.get(index)){
				
				comValue = list1.get(index).val;
				index++;
					
			}
			else
				break;
		}
		return comValue;
	}
	
	public int commonParentsNode(TreeNode root, TreeNode p1, TreeNode p2){
		if(root == null || p1 == null || p2 == null)
			return -1;
		
		ArrayList<TreeNode> list1 = new ArrayList<TreeNode>();
		ArrayList<TreeNode> list2 = new ArrayList<TreeNode>();
		
		getPath(root, p1, list1);
		getPath(root, p2, list2);

		
		int val = getLastCommonNode(list1, list2);
		return val;
	}
	// 测试最近公共父节点
	@Test  
	public void test2(){
		TreeNode root = new TreeNode(3);
		TreeNode node9 = root.left = new TreeNode(9);
		root.right = new TreeNode(20);
		TreeNode node15 = root.right.left = new TreeNode(15);
		TreeNode node7 = root.right.right = new TreeNode(7);
		System.out.println(commonParentsNode(root, node9, node7));
	}
	
	
	
	
	// 测试getPath写法的正确性
	
	public void test(){
		TreeNode root = new TreeNode(3);
		root.left = new TreeNode(9);
		root.right = new TreeNode(20);
		TreeNode node = root.right.left = new TreeNode(15);
		root.right.right = new TreeNode(7);
		ArrayList<TreeNode> list = new ArrayList<>();
		getPath(root, node, list);
		for(TreeNode ele: list)
			System.out.println(ele.val);
	}
	//第二种写法
	public boolean getPath2(TreeNode root, TreeNode node, ArrayList<TreeNode> list){
		if(root == node) {
			return true;
		}
		if(root.left != null) {
			list.add(root.left);
			if(getPath2(root.left, node, list))return true;
			list.remove(list.size()-1);
		}
		if(root.right != null){
			list.add(root.right);
			if(getPath2(root.right, node, list)) return true;
			list.remove(list.size()-1);
		}
		
		return false;
	}
	//  多叉树 ,一棵树的孩子节点存放在List结构中
	public boolean getPath3(TreeNode root, TreeNode node, ArrayList<TreeNode> list){
		list.add(root);
		if(root == node) return true;
		for(TreeNode son=root.first; son != null; son = root.next) {
			if(getPath(son, node, list)) return true;
		}
		list.remove(list.size()-1);
		return false;
	}
}
