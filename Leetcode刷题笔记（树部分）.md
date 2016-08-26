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

​	根节点入栈，

​	while( ! stack.isEmpty()){
​		1.取出节点值，并让节点出栈

​		2.节点的右孩子入栈

​		3.节点的做孩子入栈

​		4.修改节点的做孩子为null

​	·	5.如果栈不空，修改节点右孩子为栈顶元素的值。（这一步很难想到的）

}

代码：

```java
public void flatten(TreeNode root) {
		 if( root == null){
			 return;
		 }
		 Stack<TreeNode> stack = new Stack<TreeNode>();
		 stack.push(root);
		 
		 while(!stack.isEmpty()){
			 TreeNode node = stack.peek();
			 stack.pop();
			 
			 if(node.right != null)
				 stack.push(node.right);
			 if(node.left != null)
				 stack.push(node.left);
			 
			 node.left = null;
			 
			 if(!stack.isEmpty()){
				 node.right = stack.peek();
			 }
		 }
		 
	 }
```

## S.117_Populating Next Right Pointers in Each Node II

原题地址：https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/

思路：

​	层次遍历这棵树，然后给每一层的元素添加next指针的指向。

代码：

```java
public void connect(TreeLinkNode root) {
        
		if(root == null){
			return;
		}
		
		LinkedList<TreeLinkNode> current = new LinkedList<TreeLinkNode>();
		LinkedList<TreeLinkNode> next = new LinkedList<TreeLinkNode>();
		
		current.addLast(root);
		while(!current.isEmpty()){
			while(!current.isEmpty()){
				TreeLinkNode node = current.getFirst();   //只拿到值，并不删除元素，还需要调用poll，才会删除
				
				current.poll();
				if(!current.isEmpty()){
					node.next = current.getFirst();
				}else{
					node.next = null;
				}
				
				if(node.left != null)
					next.addLast(node.left);
				if(node.right != null)
					next.addLast(node.right);
				
			}
			current = next;
			next = new LinkedList<TreeLinkNode>();
		}
		
	
    }
```

## S.105_Construct Binary Tree from Preorder and Inorder Traversal

原题地址：https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/

思路：

1. 先序数组中最左边的值就是树的头结点值，记为head。在中序数组中找到head，下标记为rootIndex。那么在中序数组中rootIndex左边的数组就是头结点左子树的中序数组，长度记为len，则左子树的先序数组就是先序数组中head往后长为len的数组。

   同理于右子树。

2. 用左子树的先序和中序数组，递归整个过程建立左子树，返回的头节点记为root.left.

3. 用右子树的先序和中序数组，递归整个过程建立右子树，返回的头节点记为root.right.

4. 等head的left和right构建完成，返回root。

代码：

```java
public TreeNode buildTree(int[] preorder, int[] inorder) {
        
		int preLength = preorder.length;
		int inLength = inorder.length;
		return buildTree(preorder, 0, preLength - 1, inorder, 0, inLength - 1);
	
    }
    public TreeNode buildTree(int[] pre, int preStart, int preEnd, int[] in, int inStart, int inEnd) {
		if (inStart > inEnd || preStart > preEnd) {
			return null;
		}
		
		//rootVal是根节点
		int rootVal = pre[preStart];
		int rootIndex = 0;
		//找到根节点下标,rootIndex
		for (int i = inStart; i <= inEnd; i++) {
			if (in[i] == rootVal) {
				rootIndex = i;
				break;
			}
		}
		
		int len = rootIndex - inStart;
		TreeNode root = new TreeNode(rootVal);
		
		//详细解读这里的参数
		//构建左子树的时候，通过左子树的先序和左子树的中序来构建。所以需要传递整个先序序列，以及控制左子树范围的首尾下标。
		//传递整个中序序列，和确保是左子树范围的首尾下标
		root.left = buildTree(pre, preStart + 1, preStart + len, in, inStart, rootIndex - 1);
		root.right = buildTree(pre, preStart + len + 1, preEnd, in, rootIndex + 1, inEnd);
		return root;
	}
```

## S.108_Construct Binary Tree from Inorder and Postorder Traversal

原题地址：https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/

思路：

​	使用递归的方法构建这颗二叉树。对比于上一道题目（通过先序和中序构建二叉树），这道题目是通过中序和后序遍历构建二叉树。

1. 后序遍历中最后一个节点是整棵树的头节点，记为root，然后在中序数组中找到这个root，并且标记为rootIndex。那么在中序数组中rootIndex左边的数组就是头结点左子树的中序数组，长度记为len，左子树的后序数组就是后序数组中从0开始到len-1的部分。

   同理于头节点的右子树。

2. 用head节点左子树的中序数组和后序数组，递归构建head节点的左子树部分，返回结果记为root.left.

3. 用head节点右子树的中序数组和后序数组，递归构建head节点的右子树部分，返回结果记为root.right.

4. 当root的left和right全部构建完成，返回root。

代码：

```java
public TreeNode buildTree(int[] inorder, int[] postorder) {
        
		int inLength = inorder.length;
		int postLength = postorder.length;
		
		return buildTree(inorder, 0, inLength-1, postorder, 0, postLength-1);
    
    }
    public TreeNode buildTree(int[] inorder, int inStart, int inEnd,int[] postorder, int postStart, int postEnd){
		
		if(inStart > inEnd || postStart > postEnd){
			return null;
		}
		
		int rootVal = postorder[postEnd];
		int rootIndex = 0;
		for(int i = inStart; i <= inEnd; i++){
			if(inorder[i] == rootVal){
				rootIndex = i;
				break;
			}
		}
		int len = rootIndex - inStart;
		TreeNode root = new TreeNode(rootVal);
		root.left = buildTree(inorder,inStart,rootIndex-1,postorder,postStart,postStart+len-1);
		root.right = buildTree(inorder,rootIndex+1,inEnd,postorder,postStart+len,postEnd-1);
		
		return root;
	}
```

## S.96_Unique Binary Search Trees

原题地址：https://leetcode.com/problems/unique-binary-search-trees/

思路：

​	**师兄过来就说这是一道动态规划的问题。怎么看出来的，先打一个大大的问号？**

​	如果把题目的顺序改变一下，就可以看出规律了；

```java
1      1           2        3      3
 \      \        /  \       /     /
  3      2      1    3     2     1
  /       \               /       \
 2         3             1         2
```

> 比如，以1为根的树的个数，等于左子树的个数乘以右子树的个数，左子树是0个元素的树，右子树是2个元素的树。以2为根的树的个数，等于左子树的个数*右子树的个数，左子树是1个元素的树，右子树也是1个元素的树。以此类推。
>
> 当数组为1，2，3，4……，n时，基于以下原则构建的BST树具有唯一性：以i为根节点的树，其左子树由【1，i-1】构成，其右子树由【i+1,n】构成。
>
> 定义f(i)为以【1,i】能产生的Unique Binary Search Tree的数目，则
>
> 如果数组为空，毫无疑问只有一种BST，即空树，f(0)=1.
>
> 如果数组仅有一个元素1，只有一种BST，单个节点，f(1)=1.
>
> 如果数组有两个元素1，2，那么有如下两种可能：
>
> 1              2
>
>  \           / 
>
>    2     1	

```
f(2) = f(0)*f(1),   1 为根的情况
    +  f(1)*f(0),   2 为根的情况
```

再看3个元素的数组，可以发现BST的取值方式如下：

```
f(3) = f(0)*f(2),   1为根的情况
     + f(1)*f(1),   2为根的情况
     + f(2)+f(0),   3为根的情况
```

所以，由此观察，可以得出f的递推公式为

​					$f(i)=\Sigma_{k-1}^{i} f(k-1)*f(i-k)$

至此，问题划归为一维动态规划。

代码：

```java
 public int numTrees(int n) {
        
		//创建一个有n+1个元素的数组，f[0]的含义是：当有0个元素时，树的形态有f[0]种。
		//f[n]的含义是：当有n个元素时，树的形态有f[n]中。
		int[] f = new int[n+1];
		f[0] = 1;
		f[1] = 1;
		//i表示树中包含的元素个数
		for(int i = 2; i <=n; i++){
			//当k为根时，f[k-1]和f[i-k]分别代表左子树有几种情况和右子树的有几种情况。
			//f[k-1]和f[i-k]相乘，就是f[k]为根时，所拥有的不同形态的数量。
			for(int k = 1; k <= i; k++){
				f[i] += f[k-1] * f[i-k];
			}
		}
		return f[n];
	
    } 
```

## S.95_Unique Binary Search Trees II（难度很大）

原题地址：https://leetcode.com/problems/unique-binary-search-trees-ii/

思路：





## S.98_Validate Binary Search Tree

原题地址：https://leetcode.com/problems/validate-binary-search-tree/

思路：

​	最最直接的思路，因为一颗二叉查找树的中序遍历结果是一个递增序列。所以：

1. 对这个树进行中序遍历。
2. 判断这个序列是不是递增的。如果是递增，返回true，如果不是，返回false。

代码：

```java
public boolean isValidBST(TreeNode root) {
		if(root == null)
			return true;
		
		Stack<TreeNode> stack = new Stack<TreeNode>();
		TreeNode p = root;
		ArrayList<Integer> list = new ArrayList<Integer>();
		while(!stack.isEmpty() || p != null){
			if(p != null){
				stack.push(p);
				p = p.left;
			}
			else{
				TreeNode node = stack.pop();
				list.add(node.val);
				p = node.right;
			}
			
		}
		int length = list.size();
		for(int i = 0; i < length-1; i++){
			if(list.get(i) >= list.get(i+1))
				return false;
		}
		return true;
	}
```

解法二：

>“根据题目中的定义来实现，其实就是对于每个结点保存左右界，也就是保证结点满足它的左子树的每个结点比当前结点值小，右子树的每个结点比当前结 点值大。对于根节点不用定位界，所以是无穷小到无穷大，接下来当我们往左边走时，上界就变成当前结点的值，下界不变，而往右边走时，下界则变成当前结点 值，上界不变。如果在递归中遇到结点值超越了自己的上下界，则返回false，否则返回左右子树的结果。” 
>

代码：

​	备注：这个代码测试没有通过

```java
public boolean isValidBST(TreeNode root){
		
		return isBST(root,Integer.MIN_VALUE,Integer.MAX_VALUE);
	}
	public boolean isBST(TreeNode node, int low, int high){
		if(node == null)
			return true;
		if(node.val > low && node.val < high)
			//往左边递归的时候，所有节点要小于root.val，往右边递归的时候，所有节点要大于root.val
			return isBST(node.left, low, node.val) && isBST(node.right, node.val, high);
		else
			return false;
	}
```





## S.108_Convert Sorted Array to Binary Search Tree

原题地址：https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/

思路：

​	树这块，很多问题都使用到递归的思想。因为树的很多定义本身就是使用递归方式来定义的，所以要构建符合一些定义的树的时候，使用**递归**是一种很好的方式。

​	解决方法：选中点构造根节点，然后递归的构造左子树和右子树。

​	比如：对于1，2，3，4，5，6，7，这样的一个序列。中间节点是4，4就是整个树的根节点。1，2，3节点是根节点的左边的数组。5，6，7值根节点的右边的数组。其中，1，2，3的中间节点是2，而这个2就是4的左孩子。5，6，7的中间节点是6，6这个节点就是4的右孩子。

​	按照这样的顺序，递归构建这颗树。

> 什么是二叉查找树？
>
> **二叉查找树**（*Binary Search Tree*），也称有序二叉树（ordered binary tree）,排序二叉树（sorted binary tree），是指一棵空树或者具有下列性质的[二叉树](http://zh.wikipedia.org/wiki/%E4%BA%8C%E5%8F%89%E6%A0%91)：
>
> 1. 若任意节点的左子树不空，则左子树上所有结点的值均小于它的根结点的值；
> 2. 任意节点的右子树不空，则右子树上所有结点的值均大于它的根结点的值；
> 3. 任意节点的左、右子树也分别为二叉查找树。
>
> 什么是平衡二叉树？
>
> An empty tree is height-balanced. A non-empty binary tree T is balanced if:   
>
> 1) Left subtree of T is balanced   
>
> 2) Right subtree of T is balanced   
>
> 3) The difference between heights of left subtree and right subtree is not more than 1.  

代码：

```java
public TreeNode sortedArrayToBST(int[] nums) {
        return createTree(nums, 0, nums.length-1);
    }

    public TreeNode createTree(int[] nums, int low, int high){
		
		//递归基是什么？什么时候停止呢？
		if(low > high)
			return null;
		
		int mid = (low+high)/2;
		TreeNode root = new TreeNode(nums[mid]);
		root.left = createTree(nums, low, mid-1);
		root.right = createTree(nums, mid+1, high);
		
		return root;
	}
```






