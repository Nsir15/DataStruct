package 队列;

import list.LinkedList;
import list.List;

/**
 * 双端队列
 * @author nancy
 *
 */
public class Deque<E> {
	
	private List<E> list = new LinkedList<>();
	
	/**
	 * 返回队列的大小
	 * @return
	 */
	public int size() {
		return list.size();
	}
	
	/**
	 * 是否为空
	 * @return
	 */
	public boolean isEmpty() {
		return list.size() == 0;
	}
	
	/**
	 * 从队尾入队
	 * @param element
	 */
	public void enQueueRear(E element) {
		list.add(element);
	}
	
	/**
	 * 从对头入队
	 * @param element
	 */
	public void enQueueFront(E element) {
		list.insert(size() - 1, element);
	}
	
	/**
	 * 从队尾出队
	 * @return
	 */
	public E deQueueRear() {
		return list.remove(size() - 1);
	}
	
	/**
	 * 从队头出队
	 * @return
	 */
	public E deQueueFront() {
		return list.remove(0);
	}
	
	/**
	 * 返回队尾
	 * @return
	 */
	public E rear() {
		return list.get(size() - 1);
	}
	
	/**
	 * 返回队头
	 * @return
	 */
	public E front() {
		return list.get(0);
	}
}
