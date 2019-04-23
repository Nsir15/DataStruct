package com.nx;

public class Person {
	String name;
	int age;
	
	public Person(int age , String name) {
		this.age = age;
		this.name = name;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}
	
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		System.out.println("Person is finalize");
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null)	 return false;
		if (obj instanceof Person) {
			Person person = (Person)obj;
			return this.age == person.age;
		}
		return false;
	}
}
