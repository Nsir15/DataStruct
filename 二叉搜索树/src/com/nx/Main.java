package com.nx;

import com.mj.printer.BinaryTrees;
import com.nx.BinaryTree.Visitor;

public class Main {

	public static void main(String[] args) {
		
		BinarySearchTree<Integer> binarySearchTree = new BinarySearchTree<Integer>();
		Integer data[] = {7,4,9,2,11,1,3,10,12};
		for (Integer integer : data) {
			binarySearchTree.add(integer);
		}
		BinaryTrees.print(binarySearchTree );
		System.out.println("\n");
		
		binarySearchTree.preorderTraversal(new Visitor<Integer>() {
			
			@Override
			public void visitor(Integer element) {
//				System.out.println(element);
				
			}
		});
		
		
		
		binarySearchTree.inorderTraversal(new Visitor<Integer>() {

			@Override
			public void visitor(Integer element) {
				// TODO Auto-generated method stub
				
//				System.out.println(element);
				
			}
		});
		
		
		binarySearchTree.postorderTraversal(new Visitor<Integer>() {

			@Override
			public void visitor(Integer element) {
				// TODO Auto-generated method stub
//				System.out.println(element);
				
			}
			
		});
		
		binarySearchTree.levelorderTraversal(new Visitor<Integer>() {

			@Override
			public void visitor(Integer element) {
				// TODO Auto-generated method stub
//				System.out.println(element);
				
			}
			
		});
		
		
		System.out.println(binarySearchTree.height2());
		
		
		binarySearchTree.remove(4);
		BinaryTrees.print(binarySearchTree);
	}
	
}
