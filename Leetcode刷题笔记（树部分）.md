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

3. 把该元素的右孩子入栈，把该节点的左孩子入栈。重复第2步。​



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

​	这个题目同样是层次遍历的变形题目，相邻两层之间的访问次序相反。同样使用Collections.reverse()函数。在所有偶数层的遍历结果上面使用这个函数。

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

## S.109_Convert Sorted List to Binary Search Tree

原题地址：https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/

我写了题目，但是结果不通过。C++版本可以通过。

思路：

​	自底向上构建这棵树。

代码：

```C++
class Solution {
	public:
	TreeNode *sortedListToBST(ListNode *head) {
	int len = 0;
	ListNode *p = head;
	while (p) {
		len++;
		p = p->next;
	}
	return sortedListToBST(head, 0, len - 1);
}
private:
	TreeNode* sortedListToBST(ListNode*& list, int start, int end) {
	if (start > end) return nullptr;
	int mid = start + (end - start) / 2;
	TreeNode *leftChild = sortedListToBST(list, start, mid - 1);
	TreeNode *parent = new TreeNode(list->val);
	parent->left = leftChild;
	list = list->next;
	parent->right = sortedListToBST(list, mid + 1, end);
	return parent;
	}
};
```

### java版本的错误代码：

```java
public TreeNode sortedListToBST(ListNode head) {
		
		if(head == null)
			return null;
		int length = 0;
		ListNode cur = head;
		while(cur != null){
			length++;
			cur = cur.next;
		}
		System.out.println(length);
		return sortedListToBST(head, 0, length-1);
    }
	
	public TreeNode sortedListToBST(ListNode list, int start, int end){
		if(start > end)
			return null;
		
		int mid = (start+end)/2;
		TreeNode leftChild = sortedListToBST(list, start, mid-1);
		TreeNode parent = new TreeNode(list.val);
		parent.left = leftChild;
		list = list.next;   //错就错在这里是值传递不是C++中引用传递，在第n层改变list后，不能作用在n-1这层。
		parent.right = sortedListToBST(list, mid+1, end);
		
		return parent;
	}
```

### 错误版本的运行过程图：

   ![二叉树递归回溯](pics\二叉树递归回溯.jpg)

解决方法：

​	拿上面的例子来做说明，就是第3层调用中，修改的list能够影响到第2层的list。否则list在第2层的值还是3.就会出现3的parent还是3，而不是3的parent是5.设置为全局变量可以解决这个问题。

### 正确代码：

```java
static ListNode h;  //为了解决java中没有引用传递，只有值传递的情况，我们在这里搞了一个全局变量。
	public TreeNode sortedListToBST(ListNode head) {
		
		if(head == null)
			return null;
		int length = 0;
		h = head;
		ListNode cur = head;
		while(cur != null){
			length++;
			cur = cur.next;
		}
		System.out.println(length);
		return sortedListToBST(0, length-1);
    }
	
	public TreeNode sortedListToBST(int start, int end){
		if(start > end)
			return null;
		
		int mid = (start+end)/2;
		TreeNode leftChild = sortedListToBST(start, mid-1);
		TreeNode parent = new TreeNode(h.val);
		parent.left = leftChild;
		h = h.next;
		parent.right = sortedListToBST(mid+1, end);
		
		return parent;
	}
```

## S.111_Minimum Depth of Binary Tree

原题地址：https://leetcode.com/problems/minimum-depth-of-binary-tree/

思路：

​	树是一种递归的数据结构，所以在求最小树高的时候，使用递归的思想进行求解。

​	递归程序结构如下：

1. 如果root == null，返回0.

2. 否则比较当前节点root的左子树和右子树，返回min（root.left+1,root.right+1）。+1加的是本层所占的高度。

   但是这个时候出现一个问题，就是当只有两个节点的时候，理应返回2.我们的程序却返回1.所以加了一些判断处理。当前节点左孩子为空时，返回右子树高度+1，当前节点右孩子为空时，返回左子树高度+1.只有左右子树都不空时，我们返回较小值+1.

代码：

```java
public int minDepth(TreeNode root) {
        return Depth(root);
    }
    public int Depth(TreeNode root){
		
		if(root == null)
			return 0;
		if(root.left != null && root.right != null)
			return Depth(root.left) > Depth(root.right) ? Depth(root.right)+1 : Depth(root.left)+1;
		else if(root.left != null)
			return Depth(root.left) +1;
		else
			return Depth(root.right) +1;
			
	
	}
```

## S.104_Maximum Depth of Binary Tree

原题地址：https://leetcode.com/problems/maximum-depth-of-binary-tree/

思路：

​	树作为一个递归定义的数据结构，依然使用递归思想解决。

​	递归求解树的高度，返回当前节点左右子树中较大值+1。

代码：

```java
public int maxDepth(TreeNode root) {
		return Depth(root);
	}

	public int Depth(TreeNode root) {
		if(root == null)
			return 0;
		return Integer.max(Depth(root.left)+1, Depth(root.right)+1);
	}
```

## S.112_Path Sum(重点看)

原题地址：https://leetcode.com/problems/path-sum/

思路1：

​	一句老话写在前面，树是一种使用递归方式定义的数据结构。所以，在解决的时候仍然使用递归的思路。

​	题目的意思是，在一颗二叉树中，是否存在一条从根节点到叶子节点的路径，让这条路径上的和等于sum。

1. 如果root==null，直接返回false。
2. 否则，每次访问到一个节点，sum就减去这个节点的值。然后递归该节点的左子树和右子树。
3. 直到，访问到根节点（root.left==null && root.right == null）,判断sum是不是减到0.如果减到0，那么说明这样的一条路径知道了。

代码1：

```java
public boolean hasPathSum(TreeNode root, int sum) {
		if(root == null)
			return false;
		
		sum = sum-root.val;
		if(root.left == null && root.right == null)
			//当root.left==null,root.right==null的时候，说明这个节点是根节点。
			//并且这个时候如果sum的和也是0，那就说明找到一条路径，他的和是0.
			return sum == 0;   
  
		return hasPathSum(root.left,sum) || hasPathSum(root.right,sum);
		
	}
```

思路2：

1. 使用两个LinkedList，其中一个（nodes）存放入队列的节点，另外一个（values）存放入到当前节点为止的和。
2. 当nodes队列不空时，取出nodes中的节点和values中的值，然后根据该节点，向左左右孩子扩展，让左右孩子入连同从根到左右孩子节点的求和值分别入队列。
3. 如果左右孩子为空，则说明访问到叶子节点，判断当前的和与给定值是否相等。
4. 如果左右孩子不为空，重复第2步操作。

代码2：

```java
public boolean hasPathSum_2(TreeNode root, int sum){
		if(root == null)
			return false;
		
		
		LinkedList<TreeNode> nodes = new LinkedList<TreeNode>();
        LinkedList<Integer> values = new LinkedList<Integer>();
 
        nodes.add(root);
        values.add(root.val);
 
        while(!nodes.isEmpty()){
            TreeNode curr = nodes.poll();
            int sumValue = values.poll();
 
            if(curr.left == null && curr.right == null && sumValue==sum){
                return true;
            }
 
            if(curr.left != null){
                nodes.add(curr.left);
                values.add(sumValue+curr.left.val);
            }
 
            if(curr.right != null){
                nodes.add(curr.right);
                values.add(sumValue+curr.right.val);
            }
        }
 
        return false;
		
	}
```

## S.113_Path Sum II（重点看）

原题地址：https://leetcode.com/problems/path-sum-ii/

思路：

​	这个题目想不出来。参考别人的结题报告：还是使用递归方法：

> 这道题除了要判断是否有这样的一个path sum，还需要把所有的都可能性结果都返回，所以就用传统的DFS递归解决子问题。代码如下：

代码：

```java
public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> pathlist = new ArrayList();
        List<Integer> sumlist = new ArrayList();
        pathSumHelper(root,sum,sumlist,pathlist);
        return pathlist;
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
```

## S.116_Populating Next Right Pointers in Each Node

原题地址：https://leetcode.com/problems/populating-next-right-pointers-in-each-node/

思路：

​	没有使用递归，使用了层次遍历，cur保存当前层所有节点。next保存下一层所有节点。每当获取一层的节点之后，就顺次把每个节点的next指赋值。

代码：

```java
public void connect(TreeLinkNode root) {
        
        if(root == null)
            return ;
        
        LinkedList<TreeLinkNode> cur = new LinkedList<TreeLinkNode>();
        LinkedList<TreeLinkNode> next = new LinkedList<TreeLinkNode>();
        
        cur.addLast(root);
        while(!cur.isEmpty()){
            while(!cur.isEmpty()){
                TreeLinkNode node = cur.getFirst();
                cur.poll();
                
                if(!cur.isEmpty()){
                    node.next = cur.getFirst();
                }else{
                    node.next = null;
                }
                
                if(node.left != null)
                    next.addLast(node.left);
                if(node.right != null)
                    next.addLast(node.right);      
                
            } 
            cur = next;
            next = new LinkedList<TreeLinkNode>();
        }            
    }
```

## S.129_Sum Root to Leaf Numbers

原题地址：https://leetcode.com/problems/sum-root-to-leaf-numbers/

思路：

​	题目的意思是从根节点到叶子节点会构成一个数，构成的方式是父节点的值*10+子节点的值。然后把所有构成相加求和。

​	使用递归思想，求左子树的值+右子树的值。并且每次都是父节点*10+当前节点值。

代码：

```java
public int sumNumbers(TreeNode root) {
        return dfs(root,0);
    }
    
    public int dfs(TreeNode root, int sum){
		if(root == null)
			return 0;
		
		
		if(root.left == null && root.right == null)
			return sum = sum*10 + root.val;
		
		return dfs(root.left,sum*10+root.val)+dfs(root.right,sum*10+root.val);
	}
```

## S.124_Binary Tree Maximum Path Sum

原题地址：https://leetcode.com/problems/binary-tree-maximum-path-sum/

思路：

​	题目的含义是从树中的任意一个节点出发，到达另外一个点，从而使这个路径上所有节点的和最大。

​	维护的max使用的是数组。为什么使用数组呢？因为数组是引用类型，在递归的过程中可以保存结果。而如果不实用引用类型，在递归层层递进过程中值就无法保存。

​	除了使用数组之外，可以设置一个全局变量。

代码：

```java
int maxValue;
    public int maxPathSum(TreeNode root) {
        maxValue = Integer.MIN_VALUE;
//        System.out.println(maxPathDown(root));
		maxPathDown(root);
        return maxValue;
    }
    
    public int maxPathDown(TreeNode root){
        if(root == null)
            return 0;
            
        int left = Math.max(0,maxPathDown(root.left));
        int right = Math.max(0,maxPathDown(root.right));
        //下面都是回溯过程中要执行的
        maxValue = Math.max(maxValue, left + right + node.val);//我们认为最大值从root+root.left+root.right中产生。
        return Math.max(left, right) + node.val;//每次返回的时候，必须选择较大的那个值，然后加上当前节点的值。
    }
```

## S.book_Max Search Binary Tree

题目：

​	返回一颗二叉树中最大子二叉搜索树的根节点。

代码：

```java
public Node biggestSubBST(Node head){
		int[] record = new int[3];
		return posOrder(head, record);
	}
	
	//后续遍历的方式
	public Node posOrder(Node head, int[] record){
		if(head == null){
			record[0] = 0;
			record[1] = Integer.MAX_VALUE;
			record[2] = Integer.MIN_VALUE;
			return null;
		}
		
		int value = head.value;
		Node left = head.left;
		Node right = head.right;
		
		Node lBST = posOrder(left, record); //左子树头结点
		int lSize = record[0];  //左子树的节点数
		int lMin = record[1];   //左子树最小值
		int lMax = record[2];   //左子树最大值
		
		Node rBST = posOrder(right, record);
		int rSize = record[0];
		int rMin = record[1];
		int rMax = record[2];
		
		record[1] = Math.min(lMin, value);
		record[2] = Math.max(rMax, value);
		
		if(left == lBST && right == rBST && lMax < value && value < rMin){
			record[0] = lSize + rSize +1;
			return head;
		}
		
		record[0] = Math.max(lSize, rSize);
		return lSize > rSize ? lBST : rBST;
		
	}
```

## S.book_BST Topo Size

题目：

​	在一个二叉树中，返回符合搜索二叉树条件的最大拓扑结构。

代码：

```java
public int bstTopoSize1(Node head){
		if(head == null)
			return 0;
		//采用递归的方式，针对每一个结点，在条件1的情况下，找到每个结点的最大拓扑结构然后返回
		int max = maxTopo(head, head);
		System.out.println(max);
		
		max = Math.max(bstTopoSize1(head.left), max);
		max = Math.max(bstTopoSize1(head.right), max);
		return max;
	}
	
	//条件1：以h为头结点，并且在拓扑结构中也必须以h为头结点。
	//在这样的条件下，找到最大的拓扑结构数。
	public int maxTopo(Node h, Node n){
		if(h != null && n != null && isBSTNode(h, n, n.value)){
			return maxTopo(h, n.left) + maxTopo(h, n.right) + 1;
		}
		return 0;
	}
	
	
	//按照在二叉搜索树中使用递归的方法，查找结点n.
	//如果找到 return true,如果没有找到 return false;
	public boolean isBSTNode(Node h, Node n, int value){
		if(h == null)
			return false;
		if(h == n)  //n是待找的结点，h是查找过程中不断变化的结点，只有最后h==n,才表明是找到了
			return true;
		
		return isBSTNode(h.value > value ? h.left : h.right, n, value);
	}
```

## S.book_is BST

题目描述：

​	给定一个结点，判断一棵树是否是二叉搜索树。

代码：

```java
public boolean isBst(Node root){
		if(root == null)
			return true;
		
		Stack<Node> stack = new Stack<Node>();
		
		Node cur = root;
		Node pre = null;
		while(!stack.isEmpty() || cur != null){
			if(cur != null){
				stack.push(cur);
				cur = cur.left;
			}
			else{
				cur = stack.pop();
				if(pre != null && pre.value > cur.value)
					return false;
				System.out.println(cur.value);
				pre = cur;
				cur = cur.right;
			}
			
		}
		return true;
	}
	 
	@Test
	public void test(){
		Node node12 = new Node(12);
		
		Node node10 = node12.left = new Node(10);
		Node node16 = node12.right = new Node(16);
		
		Node node4 = node10.left = new Node(4);
	
		Node node13 = node16.left = new Node(13);
		Node node20 = node16.right = new Node(20);
		
		node4.left = new Node(2);
		node4.right = new Node(5);
		
		System.out.println(isBst(node12));
	}
```

## S.book_is CBT

题目：

​	给一颗二叉树，判断这棵二叉树是不是完全二叉树。

思路：

- 1.使用层次遍历
- 2.如果当前节点有右孩子，但是没有左孩子。说明这棵二叉树不是完全二叉树，直接返回false。
- 3.如果当前不是左右孩子都有，那么当前结点之后的所有结点必须是孩子结点，否则返回false。
- 4.遍历过程中如果不返回false，遍历结束后返回true。

代码：

```java
public boolean isCBT(Node head){
		if(head == null){
			return true;
		}
		
		LinkedList<Node>	queue = new LinkedList<Node>();
		boolean leaf = false;
		Node l = null;
		Node r = null;
		queue.offer(head);
		
		while(!queue.isEmpty()){
			Node cur = queue.poll();
			l = cur.left;
			r = cur.right;
			if(leaf && (l != null || r != null) || (l == null && r != null)){
				return false;
			}
			if(l != null){
				queue.offer(l);
			}
			if(r != null){
				queue.offer(r);
			}
			else{
				leaf = true;
			}
			
		}
		
		return true;
	}
```

## S.book_Generate BST From Sorted Array

题目：

​	给定一个已经排好序的数组，通过这个数组构建一颗二叉查找树。

思路：	

- 1.使用递归写法，取出序列的中间值，作为这棵树的根节点。
- 2.递归的构建根节点的左子树和右子树，直到这颗树完全生成好。

代码：

```java
public Node generateBST(int[] sortedArr){
		if(sortedArr == null){
			return null;
		}
		return generate(sortedArr, 0, sortedArr.length-1);
	}
	
	
	public Node generate(int[] sortedArr, int start, int end){
		if(start > end)
			return null;
		
		int mid = (start + end) / 2;
		Node root = new Node(sortedArr[mid]);
		root.left = generate(sortedArr, 0, mid-1);
		root.right = generate(sortedArr, mid+1, end);
		return root;
	}
```

## S.book_Max Distance

题目：

​	给定一个二叉树，找到二叉树中最大距离。

思路：

​	一个以h为头的树上，最大距离只可能来自以下三种情况：

​	1.h的左子树上的最大距离。

​	2.h的右子树上的最大距离。

​	3.h左子树上距离h.left最远的距离+1+h右子树上距离h.right最远的距离。

​	三个值中最大的那个就是整颗h树中最远的距离。

代码：

```java
public int maxDistance(Node head){
		int[] record = new int[1];
		return posOrder(head, record);
	}
	
	public int posOrder(Node head, int[] record){
		if(head == null){
			record[0] = 0;
			return 0;
		}
		
		int lMax = posOrder(head.left, record);    // lMax记录左子树上(左孩子上)最大距离值
		int maxFromLeft = record[0];        // 左子树上，距离head做孩子最远距离值
		
		int rMax = posOrder(head.right, record);   //  rMax记录右子树上(右孩子上)最大距离值
		int maxFromRight = record[0];
		int curNodeMax = maxFromLeft + 1 + maxFromRight;   //head结点(当前结点)上的最大距离值
		record[0] = Math.max(maxFromLeft, maxFromRight) + 1;
		return Math.max(Math.max(lMax, rMax),curNodeMax);
	}
```

## S.book_Node Num

题目：

​	给定一颗完全二叉树，统计这颗完全二叉树中一共有多少个结点。

思路：

​	如果采用逐一遍历的方式，很容易求出结点个数。但此时时间复杂度是O(n).在下面的代码中，时间复杂度是O(h^2).其中h代表这颗树的高度。

代码：

```java
public int nodeNum(Node head){
		if(head == null)   //1.如果head == null，说明是空树，直接返回0.
			return 0;

		return bs(head, 1, mostLeftLevel(head,1));
	}
	//2.如果不是空树，就求出树的高度，求法是找到树的最左结点看能到达哪一层。
	public int mostLeftLevel(Node node, int level){
		while(node != null){
			node = node.left;
			level += 1;
		}
		return level - 1;
	}
	//3.通过一个递归的过程。
	//接下来的这个程序是递归形式，所以首先要写上递归停止的条件
	public int bs(Node node, int l, int h){ 
		if(l == h)
			return l;
		
		if(mostLeftLevel(node.left, l+1) == h){
			return (1 << (h - 1) + bs(node.right, l + 1, h));  //左子树是满的，
		}else{
			return (1 << (h - l - 1) + bs(node.left, l + 1, h)); //右子树是满的。
		}
			
	}
```








