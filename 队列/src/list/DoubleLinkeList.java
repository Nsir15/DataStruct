package list;

/**
 * 双向链表
 * @author nancy
 *
 */
public class DoubleLinkeList<E> extends AbstractList<E> {

	private static class Node<E>{
		E element;
		Node<E> prevNode;
		Node<E> nextNode;
		public Node(Node<E> prevNode ,E element ,Node<E> nextNode) {
			this.prevNode = prevNode;
			this.element = element;
			this.nextNode = nextNode;
		}
	}
	private Node<E> firstNode;
	private Node<E> lastNode;
	
	
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		//只要不是被GC ROOT 对象 引用就会被自动销毁
		size = 0;
		firstNode = null;
		lastNode = null;
	}

	@Override
	public E remove(int index) { 
		rangeCheck(index);
		// 先考虑通用情况，再考虑头部和尾部的情况
		
		Node<E> node = node(index);
		Node<E> prevNode = node.prevNode;
		Node<E> nextNode = node.nextNode;
		
		if (prevNode == null) {
			firstNode = nextNode;
		}else {
			prevNode.nextNode = nextNode;
		}
		
		if (nextNode == null) {
			lastNode = prevNode;
		}else {
			nextNode.prevNode = prevNode;
		}
		size --;
		return node.element;
	}

	@Override
	public E update(int index, E element) {
		Node<E> node = node(index);
		E oldElementE = node.element;
		node.element = element;
		return oldElementE;
	}

	@Override
	public void insert(int index, E element) {
		rangeCheckForAdd(index);
		if (index == size) { //这里可能存在两种情况，空的列表的时候和有数据的时候
			Node<E> tempLastNode = lastNode;
			lastNode = new Node<>(tempLastNode, element, null);
			if (tempLastNode == null) { // 说明是空的
				firstNode = lastNode;
			}else {
				tempLastNode.nextNode = lastNode;
			}
		}else {
			Node<E> currentNode  = node(index); 
			Node<E> prevNode = currentNode.prevNode;
			Node<E> node = new Node<E>(currentNode.prevNode, element, currentNode);
			currentNode.prevNode = node;
			if (prevNode != null) {
				prevNode.nextNode = node;
			}else {
				firstNode = node;
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
		Node<E> node = firstNode;
		if (element == null) {
			for (int i = 0; i < size; i++) {
				if (node.element  == null) return i;
				node = node.nextNode;
			}
		}else {
			for (int i = 0; i < size; i++) {
				if (element.equals(node.element)) return i;
				node = node.nextNode;
			}
		}
		return ELEMENT_NOT_FOUND;
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("{ size:" + size + ", elements: [");
		Node<E> node = firstNode;
//		while (node != null	) {
//			
//			
//		}
		for (int i = 0; i < size; i++) {
			if (node != firstNode) {
				stringBuilder.append(",");
			}
			stringBuilder.append(node.element);
			node = node.nextNode;
		}
		stringBuilder.append("]}");
		return stringBuilder.toString();
	}
	
	@Override
	public void deleteDuplicates() {
		// TODO Auto-generated method stub
		
	}
	
	
	/**  ===================================  private method  ============================================  **/
	
	/**
	 * 根据下标返回结点
	 * @param index
	 * @return
	 */
	private Node<E> node(int index){
		rangeCheck(index);
//		System.out.println("size >> 1 = " + (size >> 1));
		if (index < (size >> 1)) {
			Node<E> node = firstNode;
			for (int i = 0; i < index; i++) {
				node = node.nextNode;
			}
			return node;
		}else {
			Node<E> node = lastNode;
			for (int i = size -1; i > index; i--) {
				node = node.prevNode;
			}
			return node;
		}
	}
	
}
