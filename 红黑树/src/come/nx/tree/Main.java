package come.nx.tree;

import com.mj.printer.BinaryTrees;

public class Main {

	public static void main(String[] args) {
		
		test();

	}
	
	public static void test() {
		RBTree<Integer> rbTree = new RBTree<Integer>();
		
		Integer[] datas = {55, 87, 56, 74, 96, 22, 62, 20, 70, 68, 90, 50};
		for (Integer integer : datas) {
			rbTree.add(integer);
			
//			System.out.println("【" + integer + "】" + "\n");
//			BinaryTrees.println(rbTree);
//			System.out.println(" \n -------------------------------------");
		}
		
		BinaryTrees.println(rbTree);

//		rbTree.remove(90);
		rbTree.remove(74);
		System.out.println(" \n -------------------------------------");

		BinaryTrees.println(rbTree);
		
		rbTree.remove(62);
		System.out.println(" \n -------------------------------------");

		BinaryTrees.println(rbTree);
		
		rbTree.remove(68);
		System.out.println(" \n -------------------------------------");

		BinaryTrees.println(rbTree);
	}
	
	public static void test0() {
		BinarySearchTree<Integer> binarySearchTree = new BinarySearchTree<Integer>();
		Integer[] datas = {55, 87, 56, 74, 96, 22, 62, 20, 70, 68, 90, 50};
		
		for (Integer integer : datas) {
			binarySearchTree.add(integer);
			
//			System.out.println("【" + integer + "】" + "\n");
//			BinaryTrees.println(rbTree);
//			System.out.println(" \n -------------------------------------");
		}
		
		BinaryTrees.println(binarySearchTree);

	}

}
