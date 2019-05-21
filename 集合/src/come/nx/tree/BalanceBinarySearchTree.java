/**
 * 平衡二叉搜索树
 */
package come.nx.tree;

import java.util.Comparator;


public class BalanceBinarySearchTree<E> extends BinarySearchTree<E> {
	
	
	public BalanceBinarySearchTree() {
		this(null);
	}

	public BalanceBinarySearchTree(Comparator<E> comparator) {
		super(comparator);
	}

	protected void rotateRight(Node<E> grandNode) {
		Node<E> parentNode = grandNode.leftNode;
		Node<E> childrenNode = parentNode.rightNode;
		grandNode.leftNode = childrenNode;
		parentNode.rightNode = grandNode;
		afterBalance(grandNode, parentNode, childrenNode);
	}
	
	protected void rotateLeft(Node<E> grandNode) {
		Node<E> parentNode = grandNode.rightNode;
		Node<E> childrenNode = parentNode.leftNode;
		grandNode.rightNode = childrenNode;
		parentNode.leftNode = grandNode;
		afterBalance(grandNode, parentNode, childrenNode);
	}
	
	protected void afterBalance(Node<E> grandNode,Node<E> parentNode,Node<E> childrenNode) {
		parentNode.parentNode = grandNode.parentNode;
		if (grandNode.isLeftChildren()) {
			grandNode.parentNode.leftNode = parentNode;
		}else if (grandNode.isRightChildren()) {
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
