package com.nx.set;

import come.nx.tree.BinaryTree;
import come.nx.tree.RBTree;

public class TreeSet<E> implements Set<E> {

	private RBTree<E> tree  = new RBTree<>();
	
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return tree.size();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return tree.isEmpty();
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		tree.clear();
	}

	@Override
	public boolean containers(E element) {
		// TODO Auto-generated method stub
		return tree.containers(element);
	}

	@Override
	public void add(E element) {
		// TODO Auto-generated method stub
		tree.add(element);
	}

	@Override
	public void remove(E element) {
		// TODO Auto-generated method stub
		tree.remove(element);
	}

	@Override
	public void traversal(Visitor<E> visitor) {
		// TODO Auto-generated method stub
		tree.inorderTraversal(new BinaryTree.Visitor<E>() {

			@Override
			public boolean visitor(E element) {
				// TODO Auto-generated method stub
				return visitor.visitor(element);
			}
			
		});

	}

}
