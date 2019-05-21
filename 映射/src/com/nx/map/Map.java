package com.nx.map;

public interface Map<K,V> {
	int size();
	boolean isEmpty();
	void clear();
	V put(K key,V value);
	V get(K key);
	V remove(K key);
	boolean containersKey(K key);
	boolean containersValue(V value);
	void traversal(Visitor<K, V> visitor);
	public static abstract class Visitor<K ,V>{
		boolean stop;
		public abstract boolean visitor(K key,V value);
	};
}
