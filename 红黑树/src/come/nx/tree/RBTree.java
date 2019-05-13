/**
 * 红黑树
 */

package come.nx.tree;

import java.util.Comparator;

public class RBTree<E> extends BalanceBinarySearchTree<E> {
	
	private final static boolean RED = false;
	private final static boolean BLACK = true;
	
	public RBTree() {
		this(null);
	}

	public RBTree(Comparator<E> comoparator){
		super(comoparator);
	}
	
	@Override
	protected void dealBalance(Node<E> node) {
		
		Node<E> parentNode = node.parentNode;
		if (parentNode == null) { //添加的是根节点
			black(node);
			return;
		}
		//父节点是黑色的，不需要处理
		if (isBlack(parentNode)) return;
		
		//父节点是红色的
		Node<E> grandNode = parentNode.parentNode;

		//uncle节点是红色的
		Node<E> uncleNode = parentNode.siblingNode();
		if (isRed(uncleNode)) {
			black(parentNode);
			black(uncleNode);
			dealBalance(grandNode);
		}else {
			if (parentNode.isLeftChildren()) {//L
				red(grandNode);
				if (node.isLeftChildren()) { //LL
					black(parentNode);
					
				}else { //LR
					black(node);
					rotateLeft(parentNode);
				}
				rotateRight(grandNode);
			}else { //R
				red(grandNode);
				if (node.isLeftChildren()) {//RL
					black(node);
					rotateRight(parentNode);
				}else {//RR
					black(parentNode);
				}
				rotateLeft(grandNode);
			}
		}
	}
	
	@Override
	protected void dealBalanceWithRemove(Node<E> node) {
		
		super.dealBalanceWithRemove(node);
	}
	
	@Override
	protected Node<E> createNode(E element, Node<E> parentNode) {
		// TODO Auto-generated method stub
		return new RBNode<>(element, parentNode);
	}
	
	private static class RBNode<E> extends Node<E>{
		boolean color = RED;
		public RBNode(E element, Node<E> parentNode) {
			super(element, parentNode);
		}
		
		@Override
		public String toString() {
			String string = "";
			if (color == RED) {
				string = "R-";
			}
			return string + element.toString();
		}
	}
	
	private boolean isBlack(Node<E> node) {
		return colorOf((RBNode<E>)node) == BLACK;
	}
	
	private boolean isRed(Node<E> node) {
		return colorOf((RBNode<E>)node) == RED;
	}
	
	private boolean colorOf(RBNode<E> node) {
		return node == null ? BLACK :node.color;
	}
	
	private Node<E> color(Node<E> node,boolean color){
		if (node == null) {
			return node;
		}
		((RBNode<E>)node).color = color;
		return node;
	}
	
	private Node<E> red(Node<E> node){
		return color(node, RED);
	}
	
	private Node<E> black(Node<E> node){
		return color(node, BLACK);
	}
}
