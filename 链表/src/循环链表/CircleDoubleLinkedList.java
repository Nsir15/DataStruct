package 循环链表;

import 链表.AbstractList;

public class CircleDoubleLinkedList<E> extends AbstractList<E> {

	private static class Node<E>{
		E element;
		Node<E> prevNode;
		Node<E> nextNode;
		
		public Node(Node<E> prevNode,E element,Node<E> nexNode) {
			this.prevNode = prevNode;
			this.element = element;
			this.nextNode = nexNode;
		}
		
		@Override
		public String toString() {
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append((prevNode != null ? prevNode.element : "null") +"_"+element+"_"+ (nextNode != null? nextNode.element : "null"));
			return stringBuilder.toString();
		}
	}
	
	private Node<E> firstNode;
	private Node<E> lastNode;
	
	
	@Override
	public void clear() {
		size = 0;
		firstNode = null;
		lastNode = null;
	}

	@Override
	public E remove(int index) {
		rangeCheck(index);
	
		Node<E> currentNode = node(index);
		currentNode.prevNode.nextNode = currentNode.nextNode;
		currentNode.nextNode.prevNode = currentNode.prevNode;
		if (index == 0) {
			firstNode = currentNode.nextNode;
		}
		if (index == size - 1) {
			lastNode = currentNode.prevNode;
		}
		size --;
		return currentNode.element;
	}

	@Override
	public E update(int index, E element) {
		Node<E> currentNode = node(index);
		E deletedElement = currentNode.element;
		currentNode.element = element;
		return deletedElement;
	}

	@Override
	public void insert(int index, E element) {
		rangeCheckForAdd(index);
		
		if (index == 0) {
			if (size == 0) {
				firstNode = lastNode = new Node<>(firstNode, element, lastNode);
				firstNode.nextNode = firstNode;
				firstNode.prevNode = firstNode;
			}else {
				Node<E> newHeadNode = new Node<>(firstNode.prevNode, element, firstNode);
				firstNode.prevNode = newHeadNode;
				firstNode = newHeadNode;
				lastNode.nextNode = firstNode;
			}
		}else {
			Node<E> prevNode = node(index - 1);
			Node<E> newNode = new Node<>(prevNode,element,prevNode.nextNode);
			prevNode.nextNode = newNode;
			if (index == size) {
				lastNode = newNode;
				firstNode.prevNode = lastNode;
			}else {
				newNode.nextNode.prevNode = newNode;
			}
		}
		size ++;
	}

	@Override
	public E get(int index) {
		
		return node(index).element;
	}

	@Override
	public int indexOf(E element) {
		if (element == null) {
			for (int i = 0; i < size; i++) {
				if (node(i).element == null) {
					return i;
				}
			}
		}else {
			for (int i = 0; i < size; i++) {
				if (element.equals(node(i).element)) {
					return i;
				}
			}
		}
		return ELEMENT_NOT_FOUND;
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("{size:"+size+", elements:[");
		for (int i = 0; i < size; i++) {
			if (i!= 0) {
				stringBuilder.append(",");
			}
			stringBuilder.append(node(i));
		}
		stringBuilder.append("]}");
		return stringBuilder.toString();
	}
	
	
	@Override
	public void deleteDuplicates() {
		// TODO Auto-generated method stub
		
	}

	
	/*================================ private  method ==================================*/
	
	private Node<E> node(int index){
		rangeCheck(index);
		Node<E> node;
		if (index < (size >> 1)) {
			node = firstNode;
			for (int i = 0; i < index; i++) {
				node = node.nextNode;
			}
		}else {
			node = lastNode;
			for (int i = size - 1; i > index; i--) {
				node = node.prevNode;
			}
		}
		return node;
	}
}
