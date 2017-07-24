package jianzhiOffer;

import java.util.Stack;

class Stack2Queue{
	Stack<Integer> stack1;
	Stack<Integer> stack2;
}

public class stack2queue {
	
	public void AppendTail(Stack2Queue stack2queue, int element){
		stack2queue.stack1.push(element);
	}
	
	public void DeleteHead(Stack2Queue stack2queue){
		if(stack2queue.stack2.size() <= 0){
			while(stack2queue.stack1.size()>0){
				Integer val = stack2queue.stack1.peek();
				stack2queue.stack1.pop();
				stack2queue.stack2.push(val);
			}
		}
		if(stack2queue.stack2.size() == 0)
			System.out.println("queue is empty");
		
		Integer val = stack2queue.stack2.peek();
		stack2queue.stack2.pop();
		
		System.out.println(val);
	}
}
