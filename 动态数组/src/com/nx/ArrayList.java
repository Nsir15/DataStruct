package com.nx;

public class ArrayList<E> {

	private int size;
	private E[] elements;
	private final static int DEFAULT_CAPACITY = 10;
	private final static int ELEMENT_NOT_FOUND = -1;
	
	/**
	 * 构造函数
	 */
	public ArrayList(int capacity) {
		elements = (E[]) new Object[capacity < 0 ? DEFAULT_CAPACITY : capacity];
	}
	
	public ArrayList() {
		this(DEFAULT_CAPACITY);
	}
	
	public void add(E element) {
		ensureCapacity(size + 1);
		elements[size] = element;
		size ++;
	}
	
	public E remove(int index) {
		rangeCheck(index);
		E delete = elements[index];
		for (int i = index; i < size; i++) {
			elements[i] = elements[i + 1];
		}
//		size --;
//		elements[size] = null;
		elements[--size] = null;
		return delete;
	}
	
	public E update(int index , E element) {
		rangeCheck(index);
		E oldElementE = elements[index];
		elements[index] = element;
		return oldElementE;
	}
	
	public void insert(int index ,E element) {
		rangeCheckForAdd(index); 
		ensureCapacity(size + 1);
		for (int i = size ; i > index; i--) {
			elements[i] = elements[i - 1]; 
		}
		elements[index] = element;
		size ++;
	}
	
	public E get(int index) {
		rangeCheck(index);
		return elements[index];
	}
	
//	是否包含某个元素
	public boolean container(E element) {
		return indexOf(element) != ELEMENT_NOT_FOUND;
	}
	
//	查找元素索引
	public int indexOf(E element) {
		if (element == null) {
			for (int i = 0; i < size; i++) {
				if (elements[i] == null) return 1;
			}
		}else {
			for (int i = 0; i < size; i++) {
				if (elements[i].equals(element)) return 1;
			}
		}

		return ELEMENT_NOT_FOUND;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public void clear() {
		for (int i = 0; i < size; i++) {
			elements[i] = null;
		}
		size = 0;
	}
	
	@Override
	public String toString() {
		StringBuilder stringBulider = new StringBuilder();
		stringBulider.append("ArrayList : count : " + size + ", [ ");
		for (int i = 0; i < size; i++) {
			if (i != 0) {
				stringBulider.append(",");
			}
			stringBulider.append(elements[i]);
		}
		stringBulider.append(" ]");
		return stringBulider.toString();
	}
	
	
//	私有方法
	
	/**
	 * 边界检查
	 */
	private void rangeCheck(int index) {
		if (index < 0 || index >= size) {
			outOfBounds(index);
		}
	}
	
	private void rangeCheckForAdd(int index) {
		if (index < 0 || index > size) {
			outOfBounds(index);
		}
	}
	
	private void outOfBounds(int index) {
		throw new IndexOutOfBoundsException("index : " + index + ", bounds : [ 0 ~ " + size + " ]");
	}
	
	/**
	 * 确保容量有capacity 主要是扩容
	 * @param capacity
	 */
	private void ensureCapacity(int capacity) {
		int oldCapacity = elements.length;
		if (oldCapacity > capacity) return;
		
		int newCapacity = elements.length + (elements.length >> 1);
		E[] newElements = (E[]) new Object[newCapacity];
		for (int i = 0; i < size; i++) {
			newElements[i] = elements[i];
		}
		elements = newElements;
	}
}
