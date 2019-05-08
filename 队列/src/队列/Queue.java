package 队列;

import list.DoubleLinkeList;
import list.List;

public class Queue<E> {
	
	private List<E> list = new DoubleLinkeList<>();
	
	/**
	 * 队列的大小
	 * @return
	 */
	public int size() {
		return list.size();
	}
	
	/**
	 * 是否是空
	 * @return
	 */
	public boolean isEmpty() {
		return list.size() == 0;
	}
	
	/**
	 * 入队
	 * @param element
	 */
	public void enQueue(E element) {
		list.add(element);
	}
	
	/**
	 * 出队
	 * @return
	 */
	public E deQueue() {
		return list.remove(0);
	}
	
	/**
	 * 获取队列的头元素
	 * @return
	 */
	public E front() {
		return list.get(0);
	}
}
