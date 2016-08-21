# Leetcode刷题笔记（树部分）	

[TOC]

# 二叉树的遍历

## S.144_Binary Tree Preorder Traversal

原题地址；https://leetcode.com/problems/binary-tree-preorder-traversal/

思路：

​	这是一道很典型的题目，对一颗二叉树进行先序遍历。有三种方法，第一种是使用递归的方式，第二种是人为地设计一个栈，手动执行入栈和出栈操作，第三种是对这棵树进行线索化，然后遍历这棵树。

​	###**方法一（重点掌握）**：递归，时间复杂度O（n），空间复杂度O（n）。

```java
public static void PreOrder_2(TreeNode root){
		TreeNode p = root;
		if(p != null ){
			System.out.println(p.val);
			if(p.left != null){
				PreOrder_2(p.left);
			}
			if(p.right != null){
				PreOrder_2(p.right);
			}
			
		}
	}
```

​	**方法二（重点掌握）**：手动使用栈，时间复杂度O（n），空间复杂度O（n）。

1. 访问根节点，根节点入栈。

2. 当栈非空的时候，从栈中弹出一个元素，访问该元素。

3. 把该元素的右孩子入栈，把该节点的左孩子入栈。重复第2步。

   ​


```java
public static ArrayList<TreeNode> PreOrder_1(TreeNode root){
		TreeNode p = root;
		ArrayList<TreeNode> result = new ArrayList<TreeNode>(); 
		Stack<TreeNode> stack = new Stack<TreeNode>();
		stack.push(p);                    //put the root into the stack
		
		while(!stack.isEmpty()){
			TreeNode node = stack.pop();
			int value = node.val;
			System.out.println(value);
			result.add(node);
			
			if(node.right != null)             //if right tree exist, then put the right into the stack
				stack.push(node.right);
			if(node.left != null)              //if left tree exist, then put the left into the stack.
				stack.push(node.left);
		}
		return result;
	}
```

​	**方法三**：对树进行先序线索化。时间复杂度O（n），空间复杂度O（1）,因为只用了两个辅助指针。

1. 如果当前节点的左孩子为空，则输出当前节点并将其右孩子作为当前节点。

2. 如果当前节点的左孩子不为空，在当前节点的左子树中找到当前节点在中序遍历下的前驱节点。

      a) 如果前驱节点的右孩子为空，将它的右孩子设置为当前节点。**输出当前节点（在这里输出，这是与中序遍历唯一一点不同）。**当前节点更新为当前节点的左孩子。

      b) 如果前驱节点的右孩子为当前节点，将它的右孩子重新设为空。当前节点更新为当前节点的右孩子。

3. 重复以上1、2直到当前节点为空。

```java
public static void PreOrder_3(TreeNode root){

		TreeNode cur = root,prev = null;
		while(cur != null){
			if(cur.left == null){
				System.out.println("1:  "+cur.val);
				cur = cur.right;
			}
			else{
				prev = cur.left;
				while(prev.right != null && prev.right != cur)
					prev = prev.right;      //new previous node
				if(prev.right == null){ //2.a
					System.out.println(cur.val);      //访问当前结点
					prev.right = cur;   //做线索
					cur = cur.left;     
				}
				else{ // 2.b
					prev.right = null;   //线索已经发挥作用，取消线索，恢复树。
					//System.out.println("2 :      "+cur.val);      //已经访问过了
					cur = cur.right;
				}
			}
		}
	
	}
```




## S.94_Binary Tree Inorder Traversal

原题地址；https://leetcode.com/problems/binary-tree-inorder-traversal/

参考链接：http://www.cnblogs.com/AnnieKim/archive/2013/06/15/morristraversal.html

思路和代码：

​	二叉树的中序遍历，同样是三种方式，前两种方式分别是使用递归遍历、使用一个手动设置的stack。第三种是对二叉树进行中序线索化。

​	**方法一**：使用递归的方式遍历。时间复杂度O（n），空间复杂度O（n）。

```java
public static void InOrder_2(TreeNode root){
		TreeNode p = root;
		if(p != null){
			if(p.left != null)
				InOrder_2(p.left);
			System.out.println(p.val);
			if(p.right != null)
				InOrder_2(p.right);
			
		}
	}
```

​	**方法二**：手动设计一个栈，人为控制元素的入栈和出栈。时间复杂度O（n），空间复杂度O（n）。

```java
public static void InOrder_1(TreeNode root){
		TreeNode p = root;
		Stack<TreeNode> stack = new Stack<TreeNode>();
  		//p不空，或者栈不空，循环都可以执行
		while(!stack.isEmpty() || p != null){
          //p不空的时候压栈，p为空的时候出栈
			if(p != null){
				stack.push(p);
				p = p.left;
			}
			else{
				TreeNode node = stack.pop();
				System.out.println(node.val);
				p = node.right;
			}
		}
	}
```

​	**方法三**：对这课二叉树进行中序线索化，把树状结构转化为线性结构。时间复杂度O（n），空间复杂度O（1）。

> 直觉上，认为它的复杂度是O(nlgn)，因为找单个节点的前驱节点与树的高度有关。但事实上，寻找所有节点的前驱节点只需要O(n)时间。n个节点的二叉树中一共有n-1条边，整个过程中每条边最多只走2次，一次是为了定位到某个节点，另一次是为了寻找上面某个节点的前驱节点，如下图所示，其中红色是为了定位到某个节点，黑色线是为了找到前驱节点。所以复杂度为O(n)。
>
>  ![复杂度分析](pics\复杂度分析.PNG)

​	步骤如下：

```
1. if   (当前节点的左孩子为空)
		则输出当前节点并将其右孩子作为当前节点。
2. else (当前节点的左孩子不为空)
		在当前节点的左子树中找到当前节点在中序遍历下的前驱节点。
		找到前驱后：分a、b两种情况。
		a) 如果前驱节点的右孩子为空，将前驱节点的右孩子设置为当前节点。当前节点更新为当前节点的左孩子。
		b) 如果前驱节点的右孩子不为null，而是当前节点，将它的右孩子重新设为空（恢复树的形状）。输出当前节点。当前节点更新为当前节点的右孩子。
3. 重复以上1、2直到当前节点为空。
```

代码：

```java
public static void InOrder_3(TreeNode root){
		TreeNode cur = root,prev = null;
		while(cur != null){
			if(cur.left == null){
				System.out.println("1:  "+cur.val);
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
				else{ // 2.b pre.right != null,因为之前做过线索，有指向
					prev.right = null;   //线索已经发挥作用，取消线索，恢复树。
					System.out.println("2 :      "+cur.val);
					cur = cur.right;
				}
			}
		}
	}
```

## S.145_Binary Tree Postorder Traversal

原题地址：https://leetcode.com/problems/binary-tree-postorder-traversal/

思路；

​	1.使用递归的方法。

​	2.使用栈。

​	3.使用线索二叉树。

​	方法一：使用递归，时间复杂度O（n），空间复杂度O（n）。

```java
public static void PostOrder_2(TreeNode root){
		TreeNode p = root;
		if(p != null){
			if(p.left != null)
				PostOrder_2(p.left);
			if(p.right != null)
				PostOrder_2(p.right);
			System.out.println(p.val);
		}
	}
```

​	方法二：手动创建一个stack，时间复杂度O（n），空间复杂度O（n）。

1.   从根节点出发，每次判断当前节点的做孩子是否存在，存在则把他们全部压栈。

2.   判断stack是否为空，若不为空，则从中取出一个元素。

     a)如果该元素的右子树为空，或者右子树已经被访问过，那个刚问这个节点。

     b)如果该元素的右子树不为空，则该节点第二次入栈，当前节点更新为该节点的右孩子。

3.   ​

```java
public List<Integer> postorderTraversal(TreeNode root) {
		List<Integer> result = new ArrayList<Integer>();
		TreeNode p = root,q;
  
		Stack<TreeNode> stack = new Stack<TreeNode>();
		do{
			while(p != null){
				stack.push(p);
				p = p.left;  //一口气走到最左边
			}
			q = null;
			while(!stack.isEmpty()){
				p = stack.peek();
				stack.pop();
				/*右孩子不存在或已被访问，访问之*/
				if(p.right == q){
					result.add(p.val);
					System.out.println("--------"+p.val);
					q = p; /* 保存刚访问过的结点 */
				}
				else{
					/* 当前结点不能访问，需第二次进栈 */
					stack.push(p);
					/* 先处理右子树 */
					p = p.right;
					break;
				}
			}
		}
		while(!stack.isEmpty());
		
		return result;
    }
```





​	