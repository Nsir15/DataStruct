package 队列;

/**
 * 循环队列
 * @author nancy
 *
 */
@SuppressWarnings("unchecked")

public class CircleQueue<E> {
	private E[] elements;
	private int size;
	private int frontIndex; //队头此时的下标位置
	private final static int DEFATULT_CAPACITY = 10;
	
	public CircleQueue(){
		elements = (E[]) new Object[DEFATULT_CAPACITY];
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public void enQueue(E element) {
		ensureCapacity(size + 1);
		int index = (frontIndex + size ) % elements.length;
		elements[index] = element;
		size ++;
	}
	
	public E deQueue() {
		if (isEmpty()) {
			return null;
		}
		E deleteE = elements[frontIndex];
		elements[frontIndex] = null;
		frontIndex  = (frontIndex + 1) % elements.length;
		size --;
		return deleteE;
	}
	
	public E front() {
		return elements[frontIndex];
	}
	
	
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("{ size:" + size + ", front:" + frontIndex + ", [");
		for (int i = 0; i < elements.length; i++) {
			if (i!=0) {
				stringBuilder.append(",");
			}
			stringBuilder.append(elements[i]);
		}
		stringBuilder.append("]}");
		return stringBuilder.toString();
	}
	
	private void ensureCapacity(int capacity) {
		if (capacity <= elements.length) {
			return;
		}
		int newCapacity = elements.length + (elements.length >>1);
		E[] newElements = (E[]) new Object[newCapacity];
		for (int i = 0; i < size; i++) {
			newElements[i] = elements[(frontIndex + i) % elements.length];
		}
		elements = newElements;
		frontIndex = 0;
	}
}
