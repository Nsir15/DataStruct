package com.nx;

import java.util.LinkedList;
import java.util.Queue;

import com.mj.printer.BinaryTreeInfo;

@SuppressWarnings("unchecked")
public class BinaryTree <E> implements BinaryTreeInfo{
	
	protected static class Node<E>{
		E element;
		Node<E> leftNode;
		Node<E> rightNode;
		Node<E> parentNode;
		
		public Node(E element , Node<E> parentNode) {
			this.element = element;
			this.parentNode = parentNode;
		}
		
		public boolean isLeaf() {
			return leftNode == null && rightNode == null;
		}
		
		public boolean hasChildren() {
			return leftNode != null && rightNode != null;
		}
	}
	
	protected int size;
	protected Node<E> rootNode;

	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public void clear() {
		rootNode = null;
		size = 0;
	}
	
	
	/* 遍历 */
	
	/**
	 * 前序遍历
	 */
	public void preorderTraversal(Visitor<E> visitor) {
		preorderTraversal(rootNode, visitor);
	}
	
	/**
	 * 中序遍历
	 * @param visitor
	 */
	public void inorderTraversal(Visitor<E> visitor) {
		inorderTraversal(rootNode, visitor);
	}
	
	/**
	 * 后序遍历
	 * @param visitor
	 */
	public void postorderTraversal(Visitor<E> visitor) {
		postorderTraversal(rootNode, visitor);
	}
	
	/**
	 * 层序遍历
	 * @param visitor
	 */
	public void levelorderTraversal(Visitor<E> visitor) {
		if (rootNode == null || visitor == null) {
			return;
		}
		
		//层序遍历是使用队列的形式实现
		Queue<Node<E>> queue = new LinkedList<>();
		queue.offer(rootNode);
		while (!queue.isEmpty()) {
			Node<E> node  = queue.poll();
			visitor.visitor(node.element);
			if (node.leftNode != null) {
				queue.offer(node.leftNode);
			}
			
			if (node.rightNode != null) {
				queue.offer(node.rightNode);
			}
		}
	}
	
	
	public static interface Visitor<E>{
		void visitor(E element);
	}
	
	
	
	//通过层序遍历的方式算出高度
	public int height() {
		if (rootNode == null) {
			return 0;
		}
		
		int height = 0;
		int levelItems = 1; // 每一层的元素个数
		Queue<Node<E>> queue = new LinkedList<>();
		queue.offer(rootNode);
		
		while (!queue.isEmpty()) {
			Node<E> node = queue.poll();
			levelItems --;
			if (node.leftNode != null) {
				queue.offer(node.leftNode);
			}
			if (node.rightNode != null) {
				queue.offer(node.rightNode);
			}
			
			if (levelItems == 0) { //当前层访问完了
				levelItems = queue.size();
				height ++;
			}
		}
		
		return height;
	}
	
	
	// 这里主要通过递归计算高度
	public int height2() {
		return height(rootNode);
	}
	
	/*  ==================================  BinaryTreeInfo  ===========================================================   */
	
	@Override
	public Object root() {
		// TODO Auto-generated method stub
		return rootNode;
	}

	@Override
	public Object left(Object node) {
		// TODO Auto-generated method stub
		return ((Node<E>)node).leftNode;
	}

	@Override
	public Object right(Object node) {
		// TODO Auto-generated method stub
		return ((Node<E>)node).rightNode;
	}

	@Override
	public Object string(Object node) {
		// TODO Auto-generated method stub
		return ((Node<E>)node).element;
	}
	
	
	
	private void preorderTraversal(Node<E> node , Visitor<E> visitor) {
		if (node == null || visitor == null) {
			return;
		}
		
		visitor.visitor(node.element);
		preorderTraversal(node.leftNode, visitor);
		preorderTraversal(node.rightNode, visitor);
	}
	
	private void inorderTraversal(Node<E> node , Visitor<E> visitor) {
		if (node == null || visitor == null) {
			return;
		}
		
		inorderTraversal(node.leftNode, visitor);
		visitor.visitor(node.element);
		inorderTraversal(node.rightNode,visitor);
	}
	
	private void postorderTraversal(Node<E> node,Visitor<E> visitor) {
		if (node == null || visitor == null) {
			return;
		}
		
		postorderTraversal(node.leftNode, visitor);
		postorderTraversal(node.rightNode, visitor);
		visitor.visitor(node.element);
	}

	private int height(Node<E> node) {
		if (node == null) {
			return 0;
		}
		return 1 + Math.max(height(node.leftNode), height(node.rightNode));
	}
	
	/**
	 * 获取前继结点 (中序遍历中的前继)
	 * @param node
	 * @return
	 */
	protected Node<E> predecessor(Node<E> node){
		if (node == null) {
			return null;
		}
		
		//node 有左子树，在左子树中找
		Node<E> pNode = node.leftNode;
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
	protected Node<E> successor(Node<E> node){
		if (node == null) {
			return null;
		}
		
		Node<E> pNode = node.rightNode;
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
	
	
}
