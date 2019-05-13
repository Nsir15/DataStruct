package come.nx.tree;

import com.mj.printer.BinaryTrees;

public class Main {

	public static void main(String[] args) {
		
		RBTree<Integer> rbTree = new RBTree<Integer>();
		
		Integer[] datas = {55, 87, 56, 74, 96, 22, 62, 20, 70, 68, 90, 50};
		for (Integer integer : datas) {
			rbTree.add(integer);
			
			System.out.println("【" + integer + "】" + "\n");
			BinaryTrees.println(rbTree);
			System.out.println(" \n -------------------------------------");
		}
	}

}
