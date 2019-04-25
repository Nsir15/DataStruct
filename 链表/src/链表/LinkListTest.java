package 链表;

public class LinkListTest<E> {

	public static void main(String[] args) {
//		List<Object> arrarList = new ArrayList<Object>();
//		arrarList.add(2);
//		arrarList.add(40);
//		System.out.println(arrarList);
		
//		List<Integer> linkedList = new LinkedList<>();
//		linkedList.add(30);
//		linkedList.insert(0, 100);
//		linkedList.insert(1, 50);
//		linkedList.add(90);
//		
//		linkedList.remove(2);
//		linkedList.update(1, 666);
		
//		linkedList.add(6);
//		linkedList.add(1);
//		linkedList.add(3);
//		linkedList.add(2);
//		linkedList.add(6);
//		linkedList.add(1);
//		linkedList.add(2);		
//		linkedList.deleteDuplicates();
//		System.out.println(linkedList);
		
		
		List<Integer> doubleLinkedList = new DoubleLinkeList<Integer>();
		doubleLinkedList.add(3);
		doubleLinkedList.insert(0, 10);
		doubleLinkedList.remove(0);
		System.out.println("doubleLinkeList"+doubleLinkedList);
		
	}

}
