# Leetcode刷题笔记（树部分）	

[TOC]

# 二叉树的遍历

## S.144_Binary Tree Preorder Traversal

原题地址；https://leetcode.com/problems/binary-tree-preorder-traversal/

思路：

​	这是一道很典型的题目，对一颗二叉树进行先序遍历。有三种方法，第一种是使用递归的方式，第二种是人为地设计一个栈，手动执行入栈和出栈操作，第三种是对这棵树进行线索化，然后遍历这棵树。

### **方法一（重点掌握）**：递归，时间复杂度O（n），空间复杂度O（n）。

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

### **方法二（重点掌握）**：手动使用栈，时间复杂度O（n），空间复杂度O（n）。

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

### **方法一(重点掌握)**：使用递归的方式遍历。时间复杂度O（n），空间复杂度O（n）。

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

### **方法二（重点掌握）**：手动设计一个栈，人为控制元素的入栈和出栈。时间复杂度O（n），空间复杂度O（n）。

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

### **方法一（重点掌握）**：使用递归，时间复杂度O（n），空间复杂度O（n）。

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

### **方法二（重点掌握）**：手动创建一个stack，时间复杂度O（n），空间复杂度O（n）。

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

## S.102_Binary Tree Level Order Traversal

原题地址：https://leetcode.com/problems/binary-tree-level-order-traversal/

思路：

​	对于一颗形如：

```java
	3
   /  \
  9   20
      / \
     15  7
```

二叉树而言，输出格式为：[ [3], [9, 20], [15, 7] ]。属于list当中套用list的情况。

```java

   向LinkedList类型的current中添加root节点
   while(!current.isEmpty()){    
     while(!current.isEmpty()){        
       从current中取出头部元素node，访问之，并将元素添加到level中        
         if(node.left != null)            
           next.addLast(node.left)        
         if(node.right != null)            
           next.addLast(node.right)	
        }
   			result.add(level);
     		将next的值赋给current，每次current都保存着一行节点
   
   
   }
```

代码：

```java
public static List<List<Integer>> LevelOrder_1(TreeNode root){
		ArrayList<List<Integer>> result = new ArrayList<List<Integer>>();
		if(root == null)
			return result;
		//为什么使用LinkedList，因为它有在末尾添加元素，从头部取出元素的函数。
		LinkedList<TreeNode> current = new LinkedList<TreeNode>(),next = new LinkedList<TreeNode>();
		ArrayList<Integer> level = new ArrayList<Integer>();
		
		
		
		current.push(root);
		while(!current.isEmpty()){
			while(!current.isEmpty()){
				TreeNode node = current.getFirst();
				current.pop();
				level.add(node.val);
				if(node.left != null){
					next.addLast(node.left);
				}
					
				if(node.right != null){
					next.addLast(node.right);
					
				}
			}
			result.add(level);
//			level.clear();           //  这种写法在这里不对，因为在同一块内存区域上，把之前的值抹去后，用后面的值填写了之前的位置
			level = new ArrayList<Integer>();     //不能使用level.clear()的解决方法是从新new出空间来
			LinkedList<TreeNode> temp = null;
			temp = current;
			current = next;
			next = temp;
			
		}
	
		return result;
	}
```

## S.107_Binary Tree Level Order Traversal II

原题地址：https://leetcode.com/problems/binary-tree-level-order-traversal-ii/

思路：

​	这道题目和上面一题都是同一类型的题目，对一颗二叉树进行层次遍历么。不过这个题目不是从第一层开始遍历，而是从最后一层开始遍历。没有什么特别的技术含量，就是应用了java的一个集合类Collections的一个函数，叫做reverse()函数。

代码：

```java
public List<List<Integer>> levelOrderBottom(TreeNode root) {
        
		ArrayList<List<Integer>> result = new ArrayList<List<Integer>>();
		LinkedList<TreeNode> current = new LinkedList<TreeNode>();
		LinkedList<TreeNode> next = new LinkedList<TreeNode>();
		ArrayList<Integer> level = new ArrayList<Integer>();
		
		if(root == null)
			return result;
		current.addLast(root);
		while(!current.isEmpty()){
			while(!current.isEmpty()){
				TreeNode node = current.getFirst();
				current.pop();         //移除第一个元素
				level.add(node.val);
				
				if(node.left != null)
					next.addLast(node.left);
				if(node.right != null)
					next.addLast(node.right);
			}
			result.add(level);
			level = new ArrayList<Integer>();
			LinkedList<TreeNode> temp = null;
			temp = current;
			current = next;
			next = temp;
		}
		Collections.reverse(result);
		return result;
	
    }
```

## S.103_Binary Tree Zigzag Level Order Traversal

原题地址：https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/

思路：

​	这个题目同样是层次遍历的变形题目，相邻两层之间的访问次序相反。同样适用Collections.reverse()函数。在所有偶数层的遍历结果上面使用这个函数。

代码：

```java
public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
		ArrayList<List<Integer>> result = new ArrayList<List<Integer>>();
		LinkedList<TreeNode> current = new LinkedList<TreeNode>();
		LinkedList<TreeNode> next = new LinkedList<TreeNode>();
		ArrayList<Integer> level = new ArrayList<Integer>();
		if(root == null)
			return result;
		current.addLast(root);
		int flag = 1;
		while (!current.isEmpty()) {
			{
				while (!current.isEmpty()) {
					TreeNode node = current.getFirst();
					current.poll();
					level.add(node.val);
					if (node.left != null)
						next.addLast(node.left);
					if (node.right != null)
						next.addLast(node.right);
					}
				if (1 == flag) {
					flag = -flag;
				} else {
					flag = -flag;
					Collections.reverse(level);
				}
				result.add(level);
				level = new ArrayList<Integer>();
				LinkedList<TreeNode> temp = current;
				current = next;
				next = temp;
			}

		}
		return result;
	}
```

## S.99_Recover Binary Search Tree

原题地址：https://leetcode.com/problems/recover-binary-search-tree/

思路：

​	这道题目的意思是恢复一颗二叉搜索树。所谓的二叉搜索树是这个样子的，如果对这棵树进行中序遍历，那么遍历的结果是一个从小到大的递增序列。

​	所以解决这道题目的关键在于找到哪两个节点的位置是不符合中序遍历的结果的，找到这两个节点的位置。交换他们的值。

代码：

```java
public class Solution {
  
    TreeNode pre;       // 指向当前遍历元素的前一个
    TreeNode first;     // 第一个乱序的元素
    TreeNode second;    // 第二个乱序的元素
  
	//找乱序元素的时候，就是当前元素root和前一个元素pre做比较。  
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
            pre = root;                  // 继续搜索
        }
        inorder(root.right);
    }
  
	//两步走，第一步找到乱序的元素，第二步交换乱序元素的值。  
    public void recoverTree(TreeNode root) {
        pre = null;                             // 必须在这里初始化一遍，否则OJ会报错
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
```

## S.100_Same Tree

原题地址：https://leetcode.com/problems/same-tree/

思路：

​	按照相同的顺序，同时遍历两棵树，比较每一个位置的结构和值是否是相等的。比如按照先序的方式，同时遍历两棵树。

​	当获得一个节点node之后，依次做一下判断：

​	1.是否是同时为null

​		a)如果同时为空，继续做判断。

​		b)如果不同时为空，其中有一个不为null，那么return false。

​		c)如果都不是null，那么判断他们的值，是否相等，相等继续做判断，不想等则return  false。

​	2.同时访问node的left。

​	3.同时访问node的right。

代码：

```java
public boolean isSameTree(TreeNode p, TreeNode q) {
		Stack<TreeNode> stack = new Stack<TreeNode>();
		stack.push(p);
		stack.push(q);
		while(!stack.isEmpty()){
			TreeNode node1 = stack.pop();
			TreeNode node2 = stack.pop();
          	//三个 if 正好把所有情况考虑进来
			if(node1 == null && node2 == null)
				continue;
			if(node1 == null || node2 == null)
				return false;
			if(node1.val != node2.val)
				return false;
			stack.push(node1.left);
			stack.push(node2.left);
			
			stack.push(node1.right);
			stack.push(node2.right);
		}
		return true;
	}
```

## S.101_Symmetric Tree

原题地址：https://leetcode.com/problems/symmetric-tree/

思路：

​	这道题目的意思是判断一棵树是否是对称的。个人认为应该和判断两棵树是否相同有很相似的地方。从根节点开始，视左右子树为两棵树，然后然后按照对称的方式遍历两个子树，并且比较他们的值。在遍历的过程中出现一次不相等，则return false,如果遍历结束全部相等，return true.

代码：

```java
public static boolean isSymmetric(TreeNode root) {
		Stack<TreeNode> stack = new Stack<TreeNode>();
		if(root == null)
			return true;
		
		stack.push(root.left);
		stack.push(root.right);
		while(!stack.isEmpty()){
			TreeNode p = stack.pop();
			TreeNode q = stack.pop();
			//写条件的时候，要保证所有的能遍历到不对称的部分。
			//每个叶子节点的左右孩子都是null，如果仅仅遍历到这样的null就返回true，就会造成错误。
			//所以，要使用continue
			if(p != null && q!= null){   
				if(p.val != q.val)
					return false;
				stack.push(p.left);
				stack.push(q.right);

				stack.push(p.right);
				stack.push(q.left);
				
			}else if(p == null && q == null){  //p == null,q == null
				continue;       //妥善利用好continue
			}else{   			//p、q 之间有一个是null，这种情况肯定不对称
				return false;
			}
			
		}
		
		return true;
	}
```

## S.110_Balanced Binary Tree

原题地址：https://leetcode.com/problems/balanced-binary-tree/

思路：

​	这道题目的意思是判断一棵树是否是平衡二叉树。那什么是平衡二叉树呢？对每个节点而言，它左右子树的高度不超过1.

​	求一颗树的高度的时候，使用递归程序：

```java
 public int getHeight(TreeNode root){
		if(root == null)
			return 0;
		
		int left = getHeight(root.left);
		int right = getHeight(root.right);
		
		return Math.max(left, right)+1;
	}
```

​	在求树高的基础上，加入一些处理，即一旦某个节点的左右子树的树高差超过1，则return -1.

代码：

```java
public boolean isBalanced(TreeNode root) {
        if(root == null)
			return true;
		if(getHeight(root) == -1)
			return false;
		
		return true;
    }
    public int getHeight(TreeNode root){
		if(root == null)
			return 0;
		
		int left = getHeight(root.left);
		int right = getHeight(root.right);
		if(left == -1 || right == -1)
			return -1;
		if(Math.abs(left - right) > 1)
			return -1;
		
		return Math.max(left, right)+1;
	}
```

## S.114_Flatten Binary Tree to Linked List

原题地址：https://leetcode.com/problems/flatten-binary-tree-to-linked-list/

思路：

​	

​	





​	