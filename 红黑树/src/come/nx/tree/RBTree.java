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
		
		//能来到这里说明父节点是红色的
		
		Node<E> grandNode = parentNode.parentNode;
		Node<E> uncleNode = parentNode.siblingNode();
		
		//uncle节点是红色的
		if (isRed(uncleNode)) {
			black(parentNode);
			black(uncleNode);
			red(grandNode);
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
		
		Node<E> parentNode = node.parentNode;
		//删除的是根节点
		if (parentNode == null) {
			return;
		}
		
		//删除的是黑色节点
		if (isRed(node)) { //删除的是红色节点 或者 删除拥有一个RED子节点的黑色节点
			black(node); //将替代的节点染成黑色即可
		}else {//删除的是黑色叶子节点
//			Node<E> siblingNode = node.siblingNode(); //这种方式有问题，因为node 已经被删除了，所以得到的一直是 NULL.
			Boolean isLeft = parentNode.leftNode == null || node.isLeftChildren(); //判断被删除节点是左还是右.
			Node<E> siblingNode = isLeft ? parentNode.rightNode : parentNode.leftNode;
			if (isLeft) { //被删除的叶子节点是左节点
				
				//和右的情况完全对称
				
				if (isRed(siblingNode)) { //兄弟节点是红色  需要将侄子变为自己的兄弟。
					black(siblingNode);
					red(parentNode);
					rotateLeft(parentNode);
					siblingNode = parentNode.rightNode;
				}
				
				//来到这里兄弟节点是黑色的
				if (isBlack(siblingNode.leftNode) && isBlack(siblingNode.rightNode)) { //没有一个RED子节点
					//兄弟节点没法借，需要父节点下来合并
					black(parentNode);
					red(siblingNode);
					if (isBlack(parentNode)) {
						dealBalanceWithRemove(parentNode);
					}
				}else { //至少有一个RED子节点
					//如果是两个RED子节点的话，就用左边，这里就是RR情况，只需要进行一次旋转就可以了
					if (isBlack(siblingNode.rightNode)) { //RED子节点在左侧，先对parent进行右旋
						rotateRight(siblingNode);
						siblingNode = parentNode.rightNode;
					}
					
					color(siblingNode, colorOf(parentNode));
					black(siblingNode.rightNode);
					black(parentNode);
					rotateLeft(parentNode);
				}
				
			}else {
				if (isRed(siblingNode)) { //兄弟节点是红色  需要将侄子变为自己的兄弟。
					black(siblingNode);
					red(parentNode);
					rotateRight(parentNode);
					siblingNode = parentNode.leftNode;
				}
				
				//来到这里兄弟节点是黑色的
				if (isBlack(siblingNode.leftNode) && isBlack(siblingNode.rightNode)) { //没有一个RED子节点
					//兄弟节点没法借，需要父节点下来合并
					black(parentNode);
					red(siblingNode);
					if (isBlack(parentNode)) {
						dealBalanceWithRemove(parentNode);
					}
				}else { //至少有一个RED子节点
					//如果是两个RED子节点的话，就用左边，这里就是LL情况，只需要进行一次旋转就可以了
					if (isBlack(siblingNode.leftNode)) { //RED子节点在右侧，先对parent进行左旋
						rotateLeft(siblingNode);
						siblingNode = parentNode.leftNode;
					}
					
					color(siblingNode, colorOf(parentNode));
					black(siblingNode.leftNode);
					black(parentNode);
					rotateRight(parentNode);
				}
			}
		}
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
	
	private boolean colorOf(Node<E> node) {
		return node == null ? BLACK :((RBNode<E>)node).color;
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
