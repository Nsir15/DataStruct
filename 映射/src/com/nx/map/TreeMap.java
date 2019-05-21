package com.nx.map;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;


public class TreeMap<K, V> implements Map<K, V> {

	private final static boolean RED = false;
	private final static boolean BLACK = true;
	private Node<K, V> rootNode;
	private int size;
	private Comparator<K> comparator;

	private static class Node<K, V> {
		K key;
		V value;
		boolean color = RED;
		Node<K, V> leftNode;
		Node<K, V> rightNode;
		Node<K, V> parentNode;

		public Node(K key, V value, Node<K, V> parentNode) {
			// TODO Auto-generated constructor stub
			this.key = key;
			this.value = value;
			this.parentNode = parentNode;
		}

		public boolean isLeaf() {
			return leftNode == null && rightNode == null;
		}

		public boolean hasTwoChildren() {
			return leftNode != null && rightNode != null;
		}

		public boolean isLeftChild() {
			return parentNode != null && this == parentNode.leftNode;
		}

		public boolean isRightChild() {
			return parentNode != null && this == parentNode.rightNode;
		}

		public Node<K, V> sibling() {
			if (isLeftChild()) {
				return parentNode.rightNode;
			}

			if (isRightChild()) {
				return parentNode.leftNode;
			}

			return null;
		}
	}

	public TreeMap(){
		this(null);
	}
	
	public TreeMap(Comparator<K> comparator) {
		this.comparator = comparator;
	}
	
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size == 0;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		size = 0;
		rootNode = null;
	}

	@Override
	/**
	 * 返回之前的值
	 */
	public V put(K key, V value) {
		// TODO Auto-generated method stub
		checkKeyNotNull(key);
		if (rootNode == null) { //根节点空说明是空的
			rootNode = new Node<>(key,value, null);
			afterPut(rootNode);
			size ++;
		}else {
			
			Node<K, V> node = rootNode;
			Node<K, V> parent = rootNode;
			int compare = 0;
			while (node != null) {
				compare = comparator(key, node.key);
				parent = node;
				if (compare > 0) {
					node = node.rightNode;
				}else if (compare < 0) {
					node = node.leftNode;
				}else {
					node.key = key;
					V oldValue = value;
					node.value = value;
					return oldValue;
				}
			}
			
			Node<K, V> newNode = new Node<>(key,value, parent);
			if (compare > 0) {
				parent.rightNode = newNode;
			}else {
				parent.leftNode = newNode;
			}
			afterPut(newNode);
			size ++;
		}
		return null;
	}

	@Override
	public V get(K key) {
		// TODO Auto-generated method stub
		return node(key).value;
	}

	@Override
	public V remove(K key) {
		// TODO Auto-generated method stub
		return remove(node(key));
	}

	@Override
	public boolean containersKey(K key) {
		// TODO Auto-generated method stub
		return node(key) != null;
	}

	@Override
	public boolean containersValue(V value) {
		Queue<Node<K, V>> queue = new LinkedList<>();
		queue.offer(rootNode);
		while (!queue.isEmpty()) {
			Node<K, V> node = queue.poll();
			if (value == null ? node.value == null : value.equals(node.value)) {
				return true;
			}
			if (node.leftNode != null) {
				queue.offer(node.leftNode);
			}
			
			if (node.rightNode != null) {
				queue.offer(node.rightNode);
			}
		}
		return false;
	}

	@Override
	public void traversal(Visitor<K, V> visitor) {
		// TODO Auto-generated method stub
		traversal(rootNode, visitor);	
	}
	
	
	/*========================== private  method ===========================*/
	
	
	private void checkKeyNotNull(K key) {
		
		if (key == null) {
			throw new IllegalArgumentException("key must not be null");
		}
	}

	private Node<K, V> node(K key){
		checkKeyNotNull(key);
		Node<K, V> node = rootNode;
		while (node != null) {
			int cmp = comparator(key, node.key);
			if (cmp == 0) {
				return node;
			}
			if (cmp > 0) {
				node = node.rightNode;
			}else {
				node = node.leftNode;
			}
		}
		return null;
	}
	
	/**
	 * 获取前继结点 (中序遍历中的前继)
	 * @param node
	 * @return
	 */
	private Node<K, V> predecessor(Node<K, V> node){
		if (node == null) {
			return null;
		}
		
		//node 有左子树，在左子树中找
		Node<K, V> pNode = node.leftNode;
		if (pNode != null) {
			while (pNode.rightNode != null) {
				pNode = pNode.rightNode;
			}
			return pNode;
		}
		
		//没有左子树，在父节点，祖父结点中寻找
		while (node.parentNode != null && node == node.parentNode.leftNode) {
			node = node.parentNode;
		}
		return node.parentNode;
	}
	
	/**
	 * 返回后继结点
	 * @param node
	 * @return
	 */
	private Node<K, V> successor(Node<K, V> node){
		if (node == null) {
			return null;
		}
		
		Node<K, V> pNode = node.rightNode;
		if (pNode != null) {
			while (pNode.leftNode != null) {
				pNode = pNode.leftNode;
			}
			return pNode;
		}
		
		while (node.parentNode != null && node == node.parentNode.rightNode) {
			node = node.parentNode;
		}
		return node.parentNode;
	}
	
	private V remove(Node<K, V> node) {
		if (node == null) {
			return null;
		}
		
		V oldV = node.value;

		size --;
		//度为2的结点，这里先把处理度为2 的结点，使用前驱或者后继覆盖要删除结点的值，最后在删除叶子结点的时候，会把之前的那个后继或者前驱删除掉
		if (node.hasTwoChildren()) {
			Node<K, V> predecessorNode = predecessor(node);
			node.key = predecessorNode.key;
			node.value = predecessorNode.value;
			node = predecessorNode; // 这里重新赋值之后，在删除叶子结点的时候会把这个删除掉。
		}
		
		Node<K, V> childrentNode = node.leftNode != null ? node.leftNode : node.rightNode;

		//叶子节点
		if (node.isLeaf() && node != rootNode) {
			if (node == node.parentNode.leftNode) {
				node.parentNode.leftNode = null;
			}else {
				node.parentNode.rightNode = null;
			}
			
			afterRemove(node);

		}else if (node.isLeaf() && node == rootNode) {
			rootNode = null;
			afterRemove(node);

		}else if (childrentNode != null) {//((node.leftNode == null && node.rightNode != null) || (node.leftNode != null && node.rightNode == null)) {
			//度为1的结点
			childrentNode.parentNode = node.parentNode;
			if (node.parentNode == null) { //度为1并且是根节点
				rootNode = childrentNode;
			}else if (node == node.parentNode.leftNode) {
				node.parentNode.leftNode = childrentNode;
			}else {
				node.parentNode.rightNode = childrentNode;
			}
			afterRemove(childrentNode);
		}
		return oldV;
	}
	
	private int comparator(K k1,K k2) {
		if (comparator != null) {
			return comparator.compare(k1, k2);
		}
		return ((Comparable<K>)k1).compareTo(k2);
	}
	
	private void traversal(Node<K, V> node , Visitor<K, V> visitor) {
		if (node == null || visitor.stop) {
			return;
		}
		
		traversal(node.leftNode, visitor);
		if (visitor.stop) {
			return;
		}
		visitor.visitor(node.key, node.value);
		traversal(node.rightNode, visitor);
	}
	
	private void afterPut(Node<K, V> node) {
		Node<K, V> parentNode = node.parentNode;
		if (parentNode == null) { //添加的是根节点
			setBlack(node);
			return;
		}
		//父节点是黑色的，不需要处理
		if (isBlack(parentNode)) return;
		
		//能来到这里说明父节点是红色的
		
		Node<K, V> grandNode = parentNode.parentNode;
		Node<K, V> uncleNode = parentNode.sibling();
		
		//uncle节点是红色的
		if (isRed(uncleNode)) {
			setBlack(parentNode);
			setBlack(uncleNode);
			setRed(grandNode);
			afterPut(grandNode);
		}else {
			if (parentNode.isLeftChild()) {//L
				setRed(grandNode);
				if (node.isLeftChild()) { //LL
					setBlack(parentNode);
					
				}else { //LR
					setBlack(node);
					rotateLeft(parentNode);
				}
				rotateRight(grandNode);
			}else { //R
				setRed(grandNode);
				if (node.isLeftChild()) {//RL
					setBlack(node);
					rotateRight(parentNode);
				}else {//RR
					setBlack(parentNode);
				}
				rotateLeft(grandNode);
			}
		}
	}
	
	private void afterRemove(Node<K, V> node) {
		Node<K, V> parentNode = node.parentNode;
		//删除的是根节点
		if (parentNode == null) {
			return;
		}
		
		//删除的是黑色节点
		if (isRed(node)) { //删除的是红色节点 或者 删除拥有一个RED子节点的黑色节点
			setBlack(node); //将替代的节点染成黑色即可
		}else {//删除的是黑色叶子节点
//			Node<K, V> siblingNode = node.siblingNode(); //这种方式有问题，因为node 已经被删除了，所以得到的一直是 NULL.
			Boolean isLeft = parentNode.leftNode == null || node.isLeftChild(); //判断被删除节点是左还是右.
			Node<K, V> siblingNode = isLeft ? parentNode.rightNode : parentNode.leftNode;
			if (isLeft) { //被删除的叶子节点是左节点
				
				//和右的情况完全对称
				
				if (isRed(siblingNode)) { //兄弟节点是红色  需要将侄子变为自己的兄弟。
					setBlack(siblingNode);
					setRed(parentNode);
					rotateLeft(parentNode);
					siblingNode = parentNode.rightNode;
				}
				
				//来到这里兄弟节点是黑色的
				if (isBlack(siblingNode.leftNode) && isBlack(siblingNode.rightNode)) { //没有一个RED子节点
					//兄弟节点没法借，需要父节点下来合并
					setBlack(parentNode);
					setRed(siblingNode);
					if (isBlack(parentNode)) {
						afterRemove(parentNode);
					}
				}else { //至少有一个RED子节点
					//如果是两个RED子节点的话，就用左边，这里就是RR情况，只需要进行一次旋转就可以了
					if (isBlack(siblingNode.rightNode)) { //RED子节点在左侧，先对parent进行右旋
						rotateRight(siblingNode);
						siblingNode = parentNode.rightNode;
					}
					
					setColor(siblingNode, colorOf(parentNode));
					setBlack(siblingNode.rightNode);
					setBlack(parentNode);
					rotateLeft(parentNode);
				}
				
			}else {
				if (isRed(siblingNode)) { //兄弟节点是红色  需要将侄子变为自己的兄弟。
					setBlack(siblingNode);
					setRed(parentNode);
					rotateRight(parentNode);
					siblingNode = parentNode.leftNode;
				}
				
				//来到这里兄弟节点是黑色的
				if (isBlack(siblingNode.leftNode) && isBlack(siblingNode.rightNode)) { //没有一个RED子节点
					//兄弟节点没法借，需要父节点下来合并
					setBlack(parentNode);
					setRed(siblingNode);
					if (isBlack(parentNode)) {
						afterRemove(parentNode);
					}
				}else { //至少有一个RED子节点
					//如果是两个RED子节点的话，就用左边，这里就是LL情况，只需要进行一次旋转就可以了
					if (isBlack(siblingNode.leftNode)) { //RED子节点在右侧，先对parent进行左旋
						rotateLeft(siblingNode);
						siblingNode = parentNode.leftNode;
					}
					
					setColor(siblingNode, colorOf(parentNode));
					setBlack(siblingNode.leftNode);
					setBlack(parentNode);
					rotateRight(parentNode);
				}
			}
		}
	}
	
	private void setBlack(Node<K, V> node) {
		setColor(node, BLACK);
	}
	
	private void setRed(Node<K, V> node) {
		setColor(node, RED);
	}
	
	private void setColor(Node<K, V> node ,boolean color) {
		node.color = color;
	}
	
	private boolean colorOf(Node<K, V> node) {
		return node == null ? BLACK : node.color;
	}
	
	private boolean isBlack(Node<K, V> node) {
		return colorOf(node) == BLACK;
	}
	
	private boolean isRed(Node<K, V> node) {
		return colorOf(node) == RED;
	}
	
	private void rotateRight(Node<K, V> grandNode) {
		Node<K, V> parentNode = grandNode.leftNode;
		Node<K, V> childrenNode = parentNode.rightNode;
		grandNode.leftNode = childrenNode;
		parentNode.rightNode = grandNode;
		afterBalance(grandNode, parentNode, childrenNode);
	}
	
	private void rotateLeft(Node<K, V> grandNode) {
		Node<K, V> parentNode = grandNode.rightNode;
		Node<K, V> childrenNode = parentNode.leftNode;
		grandNode.rightNode = childrenNode;
		parentNode.leftNode = grandNode;
		afterBalance(grandNode, parentNode, childrenNode);
	}
	
	private void afterBalance(Node<K, V> grandNode,Node<K, V> parentNode,Node<K, V> childrenNode) {
		parentNode.parentNode = grandNode.parentNode;
		if (grandNode.isLeftChild()) {
			grandNode.parentNode.leftNode = parentNode;
		}else if (grandNode.isRightChild()) {
			grandNode.parentNode.rightNode = parentNode;
		}else {
			rootNode = parentNode;
		}
		
		if (childrenNode != null) {
			childrenNode.parentNode = grandNode;
		}
		grandNode.parentNode = parentNode;
	}
}
