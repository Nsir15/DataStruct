package com.nx.set;

import 链表.DoubleLinkeList;
import 链表.List;

public class ListSet<E> implements Set<E> {

	private DoubleLinkeList<E> list = new DoubleLinkeList<>();
	
	@Override
	public int size() {
		
		return list.size();
	}

	@Override
	public boolean isEmpty() {
		
		return list.isEmpty();
	}

	@Override
	public void clear() {
		list.clear();
	}

	@Override
	public boolean containers(E element) {
		return list.containers(element);
	}

	@Override
	public void add(E element) {
		int index = list.indexOf(element);
		if (index != List.ELEMENT_NOT_FOUND) { //直接覆盖
			list.update(index, element);
		}else {
			list.add(element);
		}
	}

	@Override
	public void remove(E element) {
		int index = list.indexOf(element);
		if (index != List.ELEMENT_NOT_FOUND) {
			list.remove(list.indexOf(element));
		}
	}

	@Override
	public void traversal(Visitor<E> visitor) {
		if (visitor == null) {
			return;
		}
		
		for (int i = 0; i < size(); i++) {
			if (visitor.visitor(list.get(i))) return;
		}
	}

}
