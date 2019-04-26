package 链表;

import 循环链表.CircleDoubleLinkedList;
import 循环链表.CircleSingleLinkedList;

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
		
		
//		List<Integer> doubleLinkedList = new DoubleLinkeList<Integer>();
//		doubleLinkedList.add(3);
//		doubleLinkedList.insert(0, 10);
//		doubleLinkedList.remove(0);
//		System.out.println("doubleLinkeList"+doubleLinkedList);
		
//		List<Integer> circleSingleList = new CircleSingleLinkedList<Integer>();
//		circleSingleList.add(1);//[1_1]
////		circleSingleList.insert(0, 3);//[3_1,1_3]
////		circleSingleList.add(5);//[3_1,1_5,5_3]
////		circleSingleList.remove(0);
//		circleSingleList.update(0, 12);
//		System.out.println(circleSingleList);
		
		
		List<Integer> circleDoubleList = new CircleDoubleLinkedList<Integer>();
//		circleDoubleList.add(10);
//		circleDoubleList.insert(0, 20);
//		circleDoubleList.add(40);
//		circleDoubleList.add(60);
		circleDoubleList.insert(circleDoubleList.size(), 100);
		circleDoubleList.add(30);
		circleDoubleList.insert(0, 50);
		circleDoubleList.add(70);
		circleDoubleList.insert(2, 90);
		
//		circleDoubleList.remove(1);
//		circleDoubleList.remove(0);
		circleDoubleList.remove(circleDoubleList.size()-1);
		
		System.out.println(circleDoubleList + "\n");
		
		System.out.println(circleDoubleList.get(1));
		
	}

}
