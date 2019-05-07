package 队列;

import java.util.LinkedList;
import java.util.Queue;

public class _225_用队列实现栈 {
	
	private Queue<Integer> queue  ;
	
    public _225_用队列实现栈() {
    	queue = new LinkedList<Integer>();
    }
    
	/** Push element x onto stack. */
    public void push(int x) {
        int size = queue.size();
        queue.offer(x);
    	for (int i = 0; i < size; i++) {
			queue.offer(queue.poll());
		}
    }
    
    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        if (queue.isEmpty()) {
			return 0;
		}
        return queue.poll();
    }
    
    /** Get the top element. */
    public int top() {
    	if (queue.isEmpty()) {
			return 0;
		}
        return queue.peek();
    }
    
    /** Returns whether the stack is empty. */
    public boolean empty() {
        return queue.isEmpty();
    }
    
    
    public static void main(String[] args) {
    	_225_用队列实现栈 stack = new _225_用队列实现栈();
//    	stack.push(1);
//    	stack.push(2);
    	int[] arr = {2,5,1,3,4};
    	for (int i : arr) {
			stack.push(i);
		}
    	System.out.println(stack.top());
    	System.out.println(stack.pop());
    	System.out.println(stack.empty());
	}
}
