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
		remove(node(element));
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
	
	private void remove(Node<E> node) {
		if (node == null) {
			return;
		}
		//度为2的结点，这里先把处理度为2 的结点，使用前驱或者后继覆盖要删除结点的值，最后在删除叶子结点的时候，会把之前的那个后继或者前驱删除掉
		if (node.hasChildren()) {
			Node<E> predecessorNode = predecessor(node);
			node.element = predecessorNode.element;
			node = predecessorNode; // 这里重新赋值之后，在删除叶子结点的时候会把这个删除掉。
		}
		
		Node<E> childrentNode = node.leftNode != null ? node.leftNode : node.rightNode;

		//叶子节点
		if (node.isLeaf() && node != rootNode) {
			if (node == node.parentNode.leftNode) {
				node.parentNode.leftNode = null;
			}else {
				node.parentNode.rightNode = null;
			}
		}else if (node.isLeaf() && node == rootNode) {
			rootNode = null;
		}else if (childrentNode != null) {//((node.leftNode == null && node.rightNode != null) || (node.leftNode != null && node.rightNode == null)) {
			//度为1的结点
			childrentNode.parentNode = node.parentNode;
			if (node.parentNode == null) { //度为1并且是根节点
				rootNode = childrentNode;
			}else if (node == node.parentNode.leftNode) {
				node.parentNode.leftNode = childrentNode;
			}else {
				node.parentNode.rightNode = childrentNode;
			}
		}
		
		size --;
		
	}
	
}
