package com.nx.hashMap;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

import com.mj.printer.BinaryTreeInfo;
import com.mj.printer.BinaryTrees;
import com.nx.Map;


public class HashMap<K,V> implements Map<K, V> {

	private final static boolean RED = false;
	private final static boolean BLACK = true;
	private int size;
	private Node<K, V>[] table;
	private final static int DEFATULT_CAPACITY = 1<<4;
	private final static float DEFAULT_LOAD_FACTOR = 0.75f;
	private static class Node<K,V>{
		boolean color = RED;
		int hashCode;
		K key;
		V value;
		Node<K, V> parentNode;
		Node<K, V> leftNode;
		Node<K, V> rightNode;
		public Node(K key,V value,Node<K, V> parentNode) {
			this.key = key;
			this.value = value;
			int hash = key == null ? 0 : key.hashCode();
			this.hashCode = hash ^(hash >>> 16);
			this.parentNode = parentNode;
		}
		
		public boolean hasTwoChild() {
			return leftNode != null && rightNode != null;
		}
		
		public boolean isLeftChild() {
			return parentNode != null && this == parentNode.leftNode;
		}
		
		public boolean isRightChild() {
			return parentNode != null && this == parentNode.rightNode;
		}
		
		
//		public Node<K, V> siblingNode(){
//			if (isLeftChild()) {
//				return rightNode; //这个是错误的写法，导致后面bug
//			}
//			
//			if (isRightChild()) {
//				return leftNode;
//			}
//			return null;
//		}
		
		public Node<K, V> siblingNode(){
			if (isLeftChild()) {
				return parentNode.rightNode;
			}
			if (isRightChild()) {
				return parentNode.leftNode;
			}
			return null;
		}
		
		@Override
		public String toString() {
			
		  return "Node_" + key + "_" + value;
		}
	}
	
	@SuppressWarnings("unchecked")
	public HashMap(){
		table = new Node[DEFATULT_CAPACITY];
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
		if (size == 0) {
			return;
		}
		
		for (int i = 0; i < table.length; i++) {
			Node<K, V> rootNode = table[i];
			rootNode = null;
		}
		size = 0;
	}

	@Override
	public V put(K key1, V value) {
		// TODO Auto-generated method stub
		
		resize();
		
		int index = index(key1);
		Node<K, V> rootNode = table[index];
		if (rootNode == null) {
			rootNode = new Node<>(key1, value, null);
			table[index] = rootNode;
			size ++;
			afterPut(rootNode);
			return null;
		}
		
		Node<K, V> parentNode = rootNode;
		Node<K, V> node = rootNode;
		int hashCode1 = hashCode(key1);
		int cmp = 0;
		boolean searched = false;
		Node<K, V> resultNode = null;
		while (node != null) {
			parentNode = node;
			K key2 = node.key;
			int hashCode2 = node.hashCode;
			if (hashCode1 > hashCode2) {
				cmp = 1;
			}else if (hashCode1 < hashCode2) {
				cmp = -1;
			}else if (Objects.equals(key1, key2)) {
				cmp = 0;
			}else if (key1 != null && key2 != null && key1.getClass() == key2.getClass() && key1 instanceof Comparable && (cmp = ((Comparable)key1).compareTo(key2)) != 0) {
				
			}else if (searched) { //已经遍历过了
				cmp = System.identityHashCode(key1) - System.identityHashCode(key2);
			}else {
				if (node.leftNode != null && (resultNode = node(node.leftNode, key1)) != null || node.rightNode != null && (resultNode = node(node.rightNode, key1))!= null) {
					node = resultNode;
					cmp = 0;
				}else {
					searched = true;
					cmp = System.identityHashCode(key1) - System.identityHashCode(key2);
					}
			}
			
			
			if (cmp > 0) {
				node = node.rightNode;
			}else if (cmp < 0) {
				node = node.leftNode;
			}else {
				node.key = key1;
				V oldV = node.value;
				node.value = value;
				//一开始忘了覆盖hashCode的操作
				node.hashCode = hashCode1;
				return oldV;
			}
		}
		
		//插入新节点
		Node<K, V> newNode = new Node<>(key1, value, parentNode);
		if (cmp > 0) {
			parentNode.rightNode = newNode; 
		}else {
			parentNode.leftNode = newNode;
		}
		size ++;
		afterPut(newNode);
		return null;
	}

	@Override
	public V get(K key) {
		Node<K, V> node = node(key);
		return node != null ? node.value : null;
	}

	@Override
	public V remove(K key) {
		
		return remove(node(key));
	}

	@Override
	public boolean containersKey(K key) {
		// TODO Auto-generated method stub
		return node(key) != null;
	}

	@Override
	public boolean containersValue(V value) {
		if(size == 0) return false;
		Queue<Node<K, V>> queue = new LinkedList<>();
		for (int i = 0; i < table.length; i++) {
			Node<K, V> rootNode = table[i];
			if(rootNode == null) continue;
			queue.offer(rootNode);
			while (!queue.isEmpty()) {
				Node<K, V> node = queue.poll();
				if (Objects.equals(node.value, value)) {
					return true;
				}
				
				if (node.leftNode != null) {
					queue.offer(node.leftNode);
				}
				
				if (node.rightNode != null) {
					queue.offer(node.rightNode);
				}
			}
		}
		return false;
	}

	@Override
	public void traversal(Visitor<K, V> visitor) {
		// TODO Auto-generated method stub
		if (size == 0 || visitor == null) {
			return;
		}
		
		Queue<Node<K, V>> queue = new LinkedList<>();
		for (int i = 0; i < table.length; i++) {
			Node<K, V> node = table[i];
			if (node == null) {
				continue;
			}
			queue.offer(node);
			while (!queue.isEmpty()) {
				Node<K, V> node2 = queue.poll();
				if (visitor.visitor(node2.key, node2.value)) {
					return;
				}
				
				if (node.leftNode != null) {
					queue.offer(node.leftNode);
				}
				
				if (node.rightNode != null) {
					queue.offer(node.rightNode);
				}
			}
				
		}
		
	}

	
	public void print() {
		if (size == 0) return;
		for (int i = 0; i < table.length; i++) {
			final Node<K, V> root = table[i];
			System.out.println("【index = " + i + "】");
			BinaryTrees.println(new BinaryTreeInfo() {
				@Override
				public Object string(Object node) {
					return node;
				}
				
				@Override
				public Object root() {
					return root;
				}
				
				@Override
				public Object right(Object node) {
					return ((Node<K, V>)node).rightNode;
				}
				
				@Override
				public Object left(Object node) {
					return ((Node<K, V>)node).leftNode;
				}
			});
			System.out.println("---------------------------------------------------");
		}
	}
	
	
	private Node<K, V> node(K key1){
		int index = index(key1);
		Node<K, V> rootNode = table[index];
		if (rootNode == null) {
			return null;
		}
		return node(rootNode, key1);
	}
	
	private Node<K, V> node(Node<K, V> node,K key1){
		int index = index(key1);
		Node<K, V> rootNode = table[index];
		if (rootNode == null) {
			return null;
		}
		int hashCode1 = hashCode(key1);
		Node<K, V> resutlNode = null;
		int cmp = 0;
		while (node != null) {
			int hashCode2 = node.hashCode;
			K key2 = node.key;
			if (hashCode2 > hashCode1) {
				node = node.leftNode;
			}else if (hashCode2 < hashCode1) {
				node = node.rightNode;
			}else if (Objects.equals(key1, key2)) {
				return node;
			}else if (key1 != null && key2 != null && key1.getClass() == key2.getClass() && key1 instanceof Comparable && (cmp = ((Comparable)key1).compareTo(key2)) != 0 ) {
				node = cmp > 0 ? node.rightNode : node.leftNode;
			}else if (node.rightNode != null && (resutlNode = node(node.rightNode, key1)) != null) {
				return resutlNode;
			}else {
				node = node.leftNode;
			}
		}
		return null;
	}
	
	private int index(K key) {
		return hashCode(key) & (table.length - 1);
	}
	
	private int index(Node<K, V> node) {
		return node.hashCode & (table.length - 1);
	}
	
	private int hashCode(K key) {
		if (key == null) {
			return 0;
		}
		int hashCode = key.hashCode();
		return hashCode ^ (hashCode >>>16);
	}
	
	private V remove(Node<K, V> node) {
		if (node == null) {
			return null;
		}
		
		V oldV  = node.value;
		if (node.hasTwoChild()) {//度为2的节点
			Node<K, V> successorNode = successor(node);
			node.key = successorNode.key;
			node.value = successorNode.value;
			node = successorNode;
		}
		
		size --;
		//删除度为0或者1的节点
		Node<K, V> replacementNode = node.leftNode != null ? node.leftNode : node.rightNode;
		int index = index(node);
		if (replacementNode != null) { //度为1的节点
			replacementNode.parentNode = node.parentNode;
			if (node.parentNode == null) { //根节点
				table[index] = replacementNode;
			}else if (node == node.parentNode.leftNode) {
				node.parentNode.leftNode = replacementNode;
			}else {
				node.parentNode.rightNode = replacementNode;
			}
			afterRemove(replacementNode);
		}else if (node.parentNode == null) { //叶子节点并且是根节点
			table[index] = null;
		}else {//叶子节点
			if (node == node.parentNode.leftNode) {
				node.parentNode.leftNode = null;
			}else{
				node.parentNode.rightNode = null;
			}
			afterRemove(node);
		}
		
		return oldV;
	}
	

	
	private void afterPut(Node<K, V> node) {
		Node<K, V> parentNode = node.parentNode;
		if (parentNode == null) {
			black(node);
			return;
		}
		
		if (isBlack(parentNode)) {
			return;
		}
		
		Node<K, V> uncleNode = parentNode.siblingNode();
		Node<K, V> grandNode = red(parentNode.parentNode);
		if (isRed(uncleNode)) { //uncle节点是红色的，添加会导致上溢
			black(uncleNode);
			black(parentNode);
			afterPut(grandNode);
			return;
		}
		
		if (parentNode.isLeftChild()) {
			if (node.isLeftChild()) {//LL
				black(parentNode);
			}else {//LR
				black(node);
				rotateLeft(parentNode);
			}
			rotateRight(grandNode);
		}else {
			if (node.isLeftChild()) {//RL
				black(node);
				rotateRight(parentNode);
			}else {//RR
				black(parentNode);
			}
			rotateLeft(grandNode);
		}
	}
	
	
	private void afterRemove(Node<K, V> node) {
		Node<K, V> parentNode = node.parentNode;
		if (parentNode == null) {
			return;
		}
		
		if (isRed(node)) {//删除的是红色结点或者用以替换被删除节点的是红色结点
			black(node);
			return;
		}
		
		//来到这里删除的是黑色叶子节点
		boolean left = parentNode.leftNode  == null || node.isLeftChild();
		Node<K, V> siblingNode = node.isLeftChild() ? parentNode.rightNode : parentNode.leftNode;
		if (left) {
			
			if (isRed(siblingNode)) {
				red(parentNode);
				black(siblingNode);
				rotateLeft(parentNode);
				siblingNode = parentNode.rightNode;
			}
			
			if (isBlack(siblingNode.rightNode) && isBlack(siblingNode.leftNode)) { //sibling没有红色子节点
				boolean parentIsBlack = isBlack(parentNode);
				red(siblingNode);
				black(parentNode);
				if (parentIsBlack) {
					afterRemove(parentNode);
				}
			}else {//至少有一个红色子节点
				if (isBlack(siblingNode.rightNode)) {
					rotateRight(siblingNode);
					siblingNode = parentNode.rightNode;
				}
				
				setColor(siblingNode, colorOf(parentNode));
				black(siblingNode.rightNode);
				black(parentNode);
				rotateLeft(parentNode);
			}
			
		}else {
			if (isRed(siblingNode)) {
				red(parentNode);
				black(siblingNode);
				rotateRight(parentNode);
				siblingNode = parentNode.leftNode;
			}
			
			if (isBlack(siblingNode.leftNode) && isBlack(siblingNode.rightNode)) { //sibling没有红色子节点
				boolean parentIsBlack = isBlack(parentNode);
				red(siblingNode);
				black(parentNode);
				if (parentIsBlack) {
					afterRemove(parentNode);
				}
			}else {//至少有一个红色子节点
				if (isBlack(siblingNode.leftNode)) {
					rotateLeft(siblingNode);
					siblingNode = parentNode.leftNode;
				}
				
				setColor(siblingNode, colorOf(parentNode));
				black(siblingNode.leftNode);
				black(parentNode);
				rotateRight(parentNode);
			}
		}
	}
	
	private void rotateLeft(Node<K, V> grandNode) {
		Node<K, V> parentNode = grandNode.rightNode;
		Node<K, V> childrenNode = parentNode.leftNode;
		grandNode.rightNode = childrenNode;
		parentNode.leftNode = grandNode;
		afterRotate(grandNode, parentNode, childrenNode);
	}
	
	private void rotateRight(Node<K, V> grandNode) {
		Node<K, V> parentNode = grandNode.leftNode;
		Node<K, V> childrenNode = parentNode.rightNode;
		grandNode.leftNode = childrenNode;
		parentNode.rightNode = grandNode;
		afterRotate(grandNode, parentNode, childrenNode);
	}
	
	
	
	private void afterRotate(Node<K, V> grandNode,Node<K, V> parentNode,Node<K, V> childrenNode) {
		
		if (grandNode.isLeftChild()) {
			grandNode.parentNode.leftNode = parentNode;
		}else if(grandNode.isRightChild()) {
			grandNode.parentNode.rightNode = parentNode;
		}else {
			table[index(grandNode)] = parentNode;
		}
		
		if (childrenNode != null) {
			childrenNode.parentNode = grandNode;
		}
		parentNode.parentNode = grandNode.parentNode;
		grandNode.parentNode = parentNode;
	}
	
	/**
	 * 扩容
	 */
	private void resize() {
		//装填因子<= 0.75
		if(size / table.length <= DEFAULT_LOAD_FACTOR ) return;
		
		Node<K, V>[] oldTable = table;
		table = new Node[oldTable.length << 1];
		
		Queue<Node<K, V>> queue = new LinkedList<>();
		for (int i = 0; i < oldTable.length; i++) {
			Node<K, V> root = oldTable[i];
			if(root == null) continue;
			queue.offer(root);
			while (!queue.isEmpty()) {
				Node<K, V> node = queue.poll();
				if (node.leftNode != null) {
					queue.offer(node.leftNode);
				}
				
				if (node.rightNode != null) {
					queue.offer(node.rightNode);
				}
				
				moveNode(node);
			}
		}
	}
	
	private void moveNode(Node<K, V> node) {
		//先清空
		node.parentNode = null;
		node.leftNode = null;
		node.rightNode = null;
		node.color = RED;
		
		int index = index(node);
		Node<K, V> rootNode = table[index];
		if (rootNode == null) {
			rootNode = node;
			table[index] = rootNode;
			afterPut(rootNode);
			return;
		}
		
		Node<K, V> parentNode = rootNode;
		Node<K, V> node2 = rootNode;
		K K1 = node.key;
		int hash1 = node.hashCode;
		int cmp = 0;
		do {
			parentNode = node2;
			K K2 = node2.key;
			int hash2 = node2.hashCode;
			if (hash1 > hash2) {
				cmp = 1;
			}else if (hash1 < hash2) {
				cmp = -1;
				
			}else if (K1 != null && K2 != null && K1 instanceof Comparable && K1.getClass() == K2.getClass() && (cmp=((Comparable)K1).compareTo(K2)) != 0) {
				
			}else {
				cmp = System.identityHashCode(K1) - System.identityHashCode(K2);
			}
			
			if (cmp > 0) {
				node2 = node2.rightNode;
			}else if (cmp < 0) {
				node2 = node2.leftNode;
			}
			
		} while (node2 != null);
		
		
		node.parentNode = parentNode;
		if (cmp > 0) {
			parentNode.rightNode = node;
		}else if (cmp < 0) {
			parentNode.leftNode = node;
		}
		afterPut(node);
	}
	
	/**
	 * 返回后继结点
	 * @param node
	 * @return
	 */
	private  Node<K, V> successor(Node<K, V> node){
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
	
	
	private boolean colorOf(Node<K, V> node) {
		return node == null ? BLACK : node.color;
	}
	
	private Node<K, V> setColor(Node<K, V> node ,boolean color) {
		if (node == null) {
			//直接返回node
			return node;
		}
		node.color = color;
		return node;
	}
	
	private Node<K, V> red(Node<K, V> node){
		return setColor(node, RED);
	}
	
	private Node<K, V> black(Node<K, V> node){
		return setColor(node, BLACK);
	}
	
	private boolean isBlack(Node<K, V> node) {
		return colorOf(node) == BLACK;
	}
	
	private boolean isRed(Node<K, V> node) {
		return colorOf(node) == RED;
 	}
	
}
