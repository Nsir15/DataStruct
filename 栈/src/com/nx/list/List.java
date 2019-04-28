package com.nx.list;

public interface List<E>{
	
	final static int ELEMENT_NOT_FOUND = -1;
	
	/**
	 * 清除所有元素
	 */
	void clear();
	
	/**
	 * 元素的个数
	 * @return
	 */
	int size();
	
	/**
	 * 是否空
	 * @return
	 */
	boolean isEmpty();
	
	/**
	 * 是否包含某个元素
	 * @param element
	 * @return
	 */
	boolean containers(E element);
	
	/**
	 * 追加元素到尾部
	 * @param element
	 */
	void add(E element);
	
	/**
	 * 删除指定下标的元素
	 * @param index
	 * @return
	 */
	E remove(int index);
	
	/**
	 * 修改指定下标的元素
	 * @param index
	 * @param element
	 */
	E update(int index,E element);
	
	/**
	 * 指定位置插入元素
	 * @param index
	 * @param element
	 */
	void insert(int index ,E element);
	
	/**
	 * 获取指定位置的元素
	 * @param index
	 * @return
	 */
	E get(int index);
	
	/**
	 * 查看元素索引 返回的是值为element的第一个元素
	 * @param element
	 * @return
	 */
	int indexOf(E element);

	/**
	 * 检查边界问题
	 * @param index
	 */
	void rangeCheck(int index);
	
	/**
	 * 检查边界问题 -- 主要是针对添加元素
	 * @param index
	 */
	void rangeCheckForAdd(int index);
	
	/**
	 * 抛出越界的异常
	 * @param index
	 */
	void outOfBounds(int index);
	
	
	/**      这里的不属于该有的接口，只是为了测试LeetCode的一些问题而写的       **/
	
	/**
	 * 这个是为了测试LeetCode的结果写的一个函数(83.删除重复元素)
	 */
	void deleteDuplicates();
	
}
