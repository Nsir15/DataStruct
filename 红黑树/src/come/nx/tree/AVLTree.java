package come.nx.tree;

import java.util.Comparator;

public class AVLTree<E> extends BalanceBinarySearchTree<E>{

	public AVLTree(){
		this(null);
	}
	
	public AVLTree(Comparator<E> comoparator){
		super(comoparator);
	}
	
	@Override
	protected void dealBalance(Node<E> node) {
		
		while ((node = node.parentNode) != null) {//一般添加是会导致父结点失衡
			if (isBalance(node)) {
				//更新高度
				updateHeight(node);
			}else {
				//调整平衡
				reBalance(node);
				break;
			}
		}
	}
	
	@Override
	protected void dealBalanceWithRemove(Node<E> node) {
		while ((node = node.parentNode) != null) {
			if (isBalance(node)) {
				//更新高度
				updateHeight(node);
			}else {
				//调整平衡
				reBalance(node);
			}
		}
	}
	
	@Override
	protected Node<E> createNode(E element, Node<E> parentNode) {
		// TODO Auto-generated method stub
		return new AVLNode<>(element, parentNode);
	}
	
	private static class AVLNode<E> extends Node<E>{

		int height = 1;
		
		public AVLNode(E element, Node<E> parentNode) {
			super(element, parentNode);
			
		}
		
		//平衡因子
		public int balanceFactor() {
			int leftHeight = leftNode == null ? 0 : ((AVLNode<E>)leftNode).height;
			int rightHeight = rightNode == null ? 0 : ((AVLNode<E>)rightNode).height;
			return Math.abs(leftHeight - rightHeight);
		}
		
		//返回最高的那个子树，也就是parent
		public Node<E> tallerChildren(){
			int leftHeight = leftNode == null ? 0 : ((AVLNode<E>)leftNode).height;
			int rightHeight = rightNode == null ? 0 : ((AVLNode<E>) rightNode).height;
			if (leftHeight - rightHeight > 0) {
				return leftNode;
			}else if (leftHeight - rightHeight < 0) {
				return rightNode;
			}else {
				return  isLeftChildren() ? leftNode : rightNode;
			}
		}
	}
	

	private boolean isBalance(Node<E> node) {
		return ((AVLNode<E>)node).balanceFactor() <= 1;
	}
	
	private void reBalance(Node<E> node) {
		AVLNode<E> aNode = (AVLNode<E>)node;
		reBalance(aNode);
	}
	
	private void reBalance(AVLNode<E> grandNode) {
		//首先要寻找 grand ,parent ,node 这三个结点 ,grand就是当前传的这个高度最低的不平衡的父结点
		Node<E> parentNode = grandNode.tallerChildren();
		Node<E> node = ((AVLNode<E>)parentNode).tallerChildren();
		if (parentNode.isLeftChildren()) {
			if (node.isLeftChildren()) {//LL
				rotateRight(grandNode);
			}else {//LR
				rotateLeft(parentNode);
				rotateRight(grandNode);
			}
		}else {
			if (node.isLeftChildren()) {//RL
				rotateRight(parentNode);
				rotateLeft(grandNode);
			}else {//RR
				rotateLeft(parentNode);
			}
		}
	}
	
	private void updateHeight(Node<E> node) {
		updateHeight((AVLNode<E>)node);
	}
	
	private void updateHeight(AVLNode<E> node) {
		int leftHeight = node.leftNode == null ? 0 : ((AVLNode<E>)node.leftNode).height;
		int rightHeight = node.rightNode == null ? 0 : ((AVLNode<E>)node.rightNode).height;
		node.height = 1 + Math.max(leftHeight, rightHeight);
	}
	
	
	@Override
	protected void afterBalance(Node<E> grandNode, Node<E> parentNode, Node<E> childrenNode) {
		// TODO Auto-generated method stub
		super.afterBalance(grandNode, parentNode, childrenNode);
		updateHeight(grandNode);
		updateHeight(parentNode);
	}
}
