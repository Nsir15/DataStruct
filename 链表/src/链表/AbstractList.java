package 链表;

/**
 * 抽象类，不对外公开，只抽取一些公共方法。
 * 抽象类可以只实现接口的部分方法。
 * 抽象类不可以创建对象
 * @author nancy
 *
 * @param <E>
 */


public abstract class AbstractList<E> implements List<E>{
	protected int size;
	
	
	@Override
	public int size() {
		return size;
	}
	
	@Override
	public boolean isEmpty() {
		return size == 0;
	}
	
//	是否包含某个元素
	@Override
	public boolean containers(E element) {
		return indexOf(element) != ELEMENT_NOT_FOUND;
	}
	
	@Override
	public void add(E element) {
		insert(size, element);
	}
	
	@Override
	public void rangeCheck(int index) {
		if (index < 0 || index >= size) {
			outOfBounds(index);
		}
	}
	
	@Override
	public void 	rangeCheckForAdd(int index){
		if (index < 0 || index > size) {
			outOfBounds(index);
		}
	}
	
	@Override
	public void outOfBounds(int index) {
		throw new IndexOutOfBoundsException("index : " + index + ", bounds : [ 0 ~ " + size + " ]");
	}

}
