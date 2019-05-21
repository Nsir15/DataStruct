package com.nx;

import com.nx.set.ListSet;
import com.nx.set.Set;
import com.nx.set.Set.Visitor;
import com.nx.set.TreeSet;

public class Main {

	public static void main(String[] args) {
		
		Integer datas[] = {11,11,12,10,9,8,12};
		Set<Integer> listSet = new ListSet<Integer>();
		Set<Integer> treeSet = new TreeSet<Integer>();
		test0(treeSet, datas);

	}

	
	static void test0(Set<Integer> set ,Integer[] datas) {
		for (int i = 0; i < datas.length; i++) {
			set.add(datas[i]);
		}
		
		set.traversal(new Visitor<Integer>() {
			
			@Override
			public boolean visitor(Integer element) {
				System.out.println(element);
				return false;
			}
		});
		
	}
}
