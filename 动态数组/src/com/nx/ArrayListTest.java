package com.nx;

public class ArrayListTest {
	
	public static void main(String[] args) {
		ArrayList<Object> list =  new ArrayList<>();
		list.add(1);
		list.add(new Person(20, "name"));
		
		System.out.println(list);
		list.clear();
		//提醒 JVM进行垃圾回收
		System.gc();
		
	}

}
