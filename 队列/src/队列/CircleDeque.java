package 队列;

/**
 * 循环双端队列
 * @author nancy
 *
 */
@SuppressWarnings("unchecked")
public class CircleDeque<E> {
	private int size;
	private E[] elements;
	private int frontIndex;
	private final static int DEFAULT_CAPACITY = 10;
	
	
	public CircleDeque() {
		elements = (E[]) new Object[DEFAULT_CAPACITY];
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * 队尾入队
	 */
	public void enQueueRear(E element) {
		ensureCapacity(size + 1);
		int index = (frontIndex + size) % elements.length;
		elements[index] = element;
		size ++;
	}
	
	/**
	 * 队头入队
	 * @param element
	 */
	public void enQueueFront(E element) {
		ensureCapacity(size + 1);
		frontIndex  = frontIndex != 0 ? frontIndex - 1 : elements.length - 1;
		elements[frontIndex] = element;
		size ++;
	}
	
	/**
	 * 队尾出队
	 * @return
	 */
	public E deQueueRear() {
		if (isEmpty()) {
			return null;
		}
		int rearIndex = (frontIndex + (size - 1)) % elements.length;
		E deletE = elements[rearIndex];
		elements[rearIndex] = null;
		size --;
		return deletE;
	}
	
	/**
	 * 队头出队
	 * @return
	 */
	public E deQueueFront() {
		if (isEmpty()) {
			return null;
		}
		E deleteE = elements[frontIndex];
		elements[frontIndex] = null;
		frontIndex = (frontIndex + 1) % elements.length;
		size --;
		return deleteE;
	}
	
	/**
	 * 返回队尾
	 * @return
	 */
	public E rear() {
		return elements[(frontIndex + (size - 1)) % elements.length];
	}
	
	/**
	 * 返回队头
	 * @return
	 */
	public E front() {
		return elements[(frontIndex + size ) % elements.length];
	}
	
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("{ size" + size + ", front:" + frontIndex + " , [");
		for (int i = 0; i < elements.length; i++) {
			if (i!= 0) {
				stringBuilder.append(",");
			}
			stringBuilder.append(elements[i]);
		}
		stringBuilder.append("]}");
		return stringBuilder.toString();
	}
	
	private void ensureCapacity(int capacity) {
		if (elements.length >= capacity) {
			return;
		}
		
		int newCapacity = elements.length + (elements.length >> 1);
		E[] newElements = (E[]) new Object[newCapacity];
		for (int i = 0; i < size; i++) {
			newElements[i] = elements[(frontIndex + i) % elements.length];
		}
		elements = newElements;
		frontIndex = 0;
	}
}
