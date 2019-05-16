package com.nx;

import com.mj.printer.BinaryTrees;
import com.nx.BinaryTree.Visitor;

public class Main {

	public static void main(String[] args) {
		
		BinarySearchTree<Integer> binarySearchTree = new BinarySearchTree<Integer>();
		Integer[] data = {55, 87, 56, 74, 96, 22, 62, 20, 70, 68, 90, 50};
		for (Integer integer : data) {
			binarySearchTree.add(integer);
		}
		BinaryTrees.print(binarySearchTree );
		System.out.println("\n");
		
		binarySearchTree.preorderTraversal(new Visitor<Integer>() {

			@Override
			public boolean visitor(Integer element) {
//				System.out.println(element);
				return element == 56;
			}
		});
		
		
		
		binarySearchTree.inorderTraversal(new Visitor<Integer>() {

			@Override
			public boolean visitor(Integer element) {
				// TODO Auto-generated method stub
				
//				System.out.println(element);
				return element == 22;
				
			}
		});
		
		
		binarySearchTree.postorderTraversal(new Visitor<Integer>() {

			@Override
			public boolean visitor(Integer element) {
				// TODO Auto-generated method stub
				System.out.println(element);
				return element == 22;
			}
			
		});
		
		binarySearchTree.levelorderTraversal(new Visitor<Integer>() {

			@Override
			public boolean visitor(Integer element) {
				// TODO Auto-generated method stub
//				System.out.println(element);
				return element == 56;
			}
			
		});
		
		
//		System.out.println(binarySearchTree.height2());
		
		
//		binarySearchTree.remove(74);
//		BinaryTrees.print(binarySearchTree);
	}
	
}
