package 链表;

public class LinkedList<E> extends AbstractList<E>{
	private class Node<E>{
		E element;
		Node<E> nextNode;
		public Node(E elementE , Node<E> nextNode) {
			this.element = elementE;
			this.nextNode = nextNode;
		}
	}
	
	private	 Node<E> firstNode;
	
	
	@Override
	public void clear() {
		size = 0;
		firstNode = null;
	}
	
	
	@Override
	public E remove(int index) {
		rangeCheck(index);
		Node<E> node = firstNode;
		if (index == 0) {
			firstNode = firstNode.nextNode;
		}else {
			Node<E> previousNode= node(index -1);
			previousNode.nextNode = previousNode.nextNode.nextNode; 
		}
		return node.element;
	}
	
	@Override
	public E update(int index, E element) {
		Node<E> node = node(index);
		E oldElement = node.element;
		node.element = element;
		return oldElement;
	}
	
	@Override
	public void insert(int index, E element) {
		if (index == 0) {
			firstNode = new Node<>(element, firstNode);
		}else {
			Node<E> previousNode = node(index -1);
			previousNode.nextNode = new Node<>(element, previousNode.nextNode);
//			Node<E> node = new Node<E>(element, previousNode.nextNode);
//			previousNode.nextNode = node;
		}
		size ++;
	}
	
	@Override
	public E get(int index) {
		return node(index).element;
	}
	
	@Override
	public int indexOf(E element) {
		Node<E> node = firstNode;
		if (element == null) {
			for (int i = 0; i < size; i++) {
				if (null == node.element) {
					return i;
				}
				node = node.nextNode;
			}
		}else {
			for (int i = 0; i < size; i++) {
				if (element.equals(node.element)) {
					return i;
				}
				node = node.nextNode;
			}
		}
		return ELEMENT_NOT_FOUND;
	}
	
	@Override
	public String toString() {
		StringBuilder stringBulider = new StringBuilder();
		stringBulider.append("LinkedList { size :" + size + ", [");
		Node<E> node = firstNode;
		while (node != null) {
			if (node != firstNode) {
				stringBulider.append(",");
			}
			stringBulider.append(node.element);
			node = node.nextNode;
		}
		stringBulider.append("]}");
		return stringBulider.toString();
	}
	
	
	/**
	 * 这个是为了测试LeetCode的结果写的一个函数
	 * 删除重复的结点 (这个方法的前提得是给的数据都是排好序的，不然会失效 比如：【1，1，2，3，3】可以，但是【1，2，1，3，2】就不能得到预期效果)
	 * @param head
	 * @return
	 */
	public void deleteDuplicates0(){
		Node<E> head = firstNode;
		if (head == null || head.nextNode == null) {
			return ;
		}
		
		Node<E> currentLinkedList = head;
		while (currentLinkedList != null && currentLinkedList.nextNode != null) {
			
			if (currentLinkedList.element == currentLinkedList.nextNode.element) {
				currentLinkedList.nextNode = currentLinkedList.nextNode.nextNode;
				size --;
			}else {
				currentLinkedList = currentLinkedList.nextNode;
			}
		}
	}
	
	
	/**
	 * 删除重复的结点  不需要事先排好序
	 */
	public void deleteDuplicates() {
		Node<E> currentNode = firstNode;
		int currentNodeIndex = 0;
		while (currentNode != null && currentNode.nextNode != null) {
			Node<E> nextNode = currentNode.nextNode;
			int nextNodeIndex = currentNodeIndex + 1;
			while (nextNode != null) {			
				if (nextNode.element == currentNode.element) {
//					currentNode.nextNode = nextNode.nextNode;
					remove(nextNodeIndex);
					size --;
				}else {
					nextNodeIndex ++;
				}
				nextNode = nextNode.nextNode;
			}
			currentNode = currentNode.nextNode;
			currentNodeIndex ++;
		}
	}
	
	/*========== private  method ===========*/
	
	private Node<E> node(int index){
		rangeCheck(index);
		Node<E> node = firstNode;
		for (int i = 0; i < index; i++) {
			node = node.nextNode;
		}
		return node;
	}
	
	
}
