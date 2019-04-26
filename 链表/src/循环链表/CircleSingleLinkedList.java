package 循环链表;

import 链表.AbstractList;

public class CircleSingleLinkedList<E> extends AbstractList<E> {

	private static class Node<E>{
		E element;
		Node<E> nextNode;
		public Node(E element,Node<E> nextNode) {
			this.element = element;
			this.nextNode = nextNode;
		}
		
		@Override
		public String toString() {
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(element+"_"+nextNode.element);
			return stringBuilder.toString();
		}
	}
	
	private Node<E> first;
	
	
	@Override
	public void clear() {
		size = 0;
		first = null;		
	}

	@Override
	public E remove(int index) {
		rangeCheck(index);
		if (size == 1) {
			E deleteElement = first.element;
			first = null;
			size --;
			return deleteElement;
		}
		Node<E> currentNode = node(index);
		E deleteElement = currentNode.element;
		if (index == 0) {
			Node<E> lastNode = node(size -1);
			first = first.nextNode;
			lastNode.nextNode = first;
		}else {

			Node<E> prevNode = node(index - 1);
			Node<E> nextNode = currentNode.nextNode;
			prevNode.nextNode = nextNode;
		}
		
		size --;
		return deleteElement;
	}

	@Override
	public E update(int index, E element) {
		rangeCheck(index);
		Node<E> currentNode = node(index);
		E oldElement = currentNode.element;
		currentNode.element = element;
		return oldElement;
	}

	@Override
	public void insert(int index, E element) {
		rangeCheckForAdd(index);
		
		if (index == 0) {
			Node<E> newNode = new Node<>(element, first);
			Node<E> lastNode = size == 0 ? newNode : node(size -1);
			first = newNode;
			lastNode.nextNode = first;
			
		}else {
			Node<E> prevNode = node(index -1);
			prevNode.nextNode = new Node<>(element, prevNode.nextNode);
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
				E elementE = node(i).element;
				if (elementE == null) {
					return i;
				}
			}
		}else {
			for (int i = 0; i < size; i++) {
				E elementE = node(i).element;
				if (element.equals(elementE)) {
					return i;
				}
			}
		}
		return ELEMENT_NOT_FOUND;
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("{ size:" + size + ", elements:[");
		for (int i = 0; i < size; i++) {
			if (i != 0) {
				stringBuilder.append(" ,");
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
	
	
	
	/*=========================== private  method ================================*/
	
	private Node<E> node(int index){
		rangeCheck(index);
		Node<E> node = first;
		for (int i = 0; i < index; i++) {
			node = node.nextNode;
		}
		return node;
	} 

}
