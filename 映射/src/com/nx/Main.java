package com.nx;

import com.nx.map.Map;
import com.nx.map.TreeMap;
import com.nx.map.Map.Visitor;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Map<String, Integer> map = new TreeMap<>();
		map.put("a", 1);
		map.put("b", 2);
		map.put("c", 3);
		map.put("d", 4);
		
		map.remove("c");
		map.traversal(new Visitor<String, Integer>() {
			
			@Override
			public boolean visitor(String key, Integer value) {
				System.out.println("key_" + key +":"+ "value_"+value);
				return false;
			}
		});
		
		System.out.println("\n");
		System.out.println(map.containersKey("c"));
		System.out.println(map.containersKey("t"));
		
		

	}

}
