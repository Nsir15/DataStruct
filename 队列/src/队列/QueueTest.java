package 队列;

public class QueueTest {
	public static void main(String[] args) {
		
//		Queue<Integer> queue = new Queue<>();
//		queue.enQueue(10);
//		System.out.println(queue.front() + "\n");
//		queue.enQueue(20);
//		System.out.println(queue.deQueue());
		
		
		/*================================== circleQueue ================================================*/
//		CircleQueue<Integer> circleQueue = new CircleQueue<Integer>();
//		for (int i = 0; i < 10; i++) {
//			circleQueue.enQueue(i);
//		}
//		
//		for (int i = 0; i < 4; i++) {
//			circleQueue.deQueue(); // null,null,null,null,4,5,6,7,8,9
//		}
//		
//		circleQueue.enQueue(10);
//		circleQueue.enQueue(11);  // 10,11,null,null,4,5,6,7,8,9
//		
//		for (int i = 15; i < 20; i++) {
//			circleQueue.enQueue(i);
//		}
//		
//		
//		System.out.println(circleQueue);
//		
//		while (!circleQueue.isEmpty()) {
//			System.out.println(circleQueue.deQueue());
//		}
		
		
		/*=========================================== circleDeque ====================================================*/
		
		CircleDeque<Integer> circleDeque = new CircleDeque<Integer>();
		circleDeque.enQueueRear(1);
		circleDeque.enQueueFront(0); // 1,null,null,null,null,null,null,null,null,0
		
		for (int i = 2; i < 5; i++) {
			circleDeque.enQueueRear(i); // 1,2,3,4,null,null,null,null,null,0
		}
		
		for (int i = 10; i < 15; i++) {
			circleDeque.enQueueFront(i); // 1,2,3,4,14,13,12,11,10,0
		}
		
		circleDeque.enQueueRear(15);
		System.out.println(circleDeque); // { size11, front:0 , [14,13,12,11,10,0,1,2,3,4,15,null,null,null,null]}
	}

}
