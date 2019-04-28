package com.nx.stack;

import com.nx.list.ArrayList;
import com.nx.list.List;

public class Stack<E> {
	
	private List<E> list =  new ArrayList<>();
	
	final static int DEFAULTCAPACITY = 10;
	
	public int size() {
		return list.size();
	}
	
	public boolean isEmpty() {
		return list.size() == 0;
	}
	
	public void push(E element) {
		list.add(element);
	}
	
	public E pop() {
		
		return list.remove(list.size() - 1);
	}
	
	public E top() {
		return list.get(list.size() - 1);
	}
	

}
