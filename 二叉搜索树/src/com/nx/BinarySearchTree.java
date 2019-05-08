package com.nx;

import java.util.Comparator;

public class BinarySearchTree <E> extends BinaryTree<E>{
	
	private Comparator<E> comparator;

	public BinarySearchTree(Comparator<E> comparator) {
		this.comparator = comparator;
	}
	
	public BinarySearchTree(){
		this(null);
	}
	
	
	public void add(E element) {
		checkElementNotNull(element);
		
		if (rootNode == null) { //根节点空说明是空的
			rootNode = new Node<>(element, null);
			size ++;
		}else {
			
			Node<E> node = rootNode;
			Node<E> parent = rootNode;
			int compare = 0;
			while (node != null) {
				compare = comparetor(element, node.element);
				parent = node;
				if (compare > 0) {
					node = node.rightNode;
				}else if (compare < 0) {
					node = node.leftNode;
				}else {
					return;
				}
			}
			
			Node<E> newNode = new Node<>(element, parent);
			if (compare > 0) {
				parent.rightNode = newNode;
			}else {
				parent.leftNode = newNode;
			}
			size ++;
		}
	}
	
	public void remove(E element) {
		
	}
	
	public boolean containers(E element) {
		
		return node(element) != null;
	}
	
	@SuppressWarnings("unchecked")
	public int comparetor(E elementa,E elementb) {
		if (comparator != null) {
			return comparator.compare(elementa, elementb);
		}
		return ((Comparable<E>)elementa).compareTo(elementb);
	}

	
	private Node<E> node(E element){
		checkElementNotNull(element);
		
		Node<E> node = rootNode;
		while (node != null) {
			int comparetor = comparetor(element, node.element);
			if (comparetor == 0) {
				return node;
			}else if (comparetor > 0) {
				node = node.rightNode;
			}else {
				node = node.leftNode;
			}
		}
		return node;
	}
	
	private void checkElementNotNull(E element) {
		if (element == null) {
			throw new IllegalArgumentException("argument must not be null");
		}
	}
	
	
}
