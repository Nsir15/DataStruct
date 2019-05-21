package com.nx.set;

public interface Set<E> {
	int size();
	boolean isEmpty();
	void clear();
	boolean containers(E element);
	void add(E element);
	void remove(E element);
	void traversal(Visitor<E> visitor);
	
	public static abstract class Visitor<E>{
		boolean stop;
		public abstract boolean visitor(E element);
	}
}
