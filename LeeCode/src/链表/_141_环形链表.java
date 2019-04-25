package 链表;

public class _141_环形链表 {

	public boolean hasCycle(ListNode head) {
		if (head == null || head.next == null) {
			return false;
		}
		
        	ListNode fastNode = head.next;
        	ListNode slowNode = head;
        	
        	while (fastNode != slowNode) {
				if (fastNode == null || fastNode.next == null) {
					return false;
				}
				fastNode = fastNode.next.next;
				slowNode = slowNode.next;
			}
        	return true;
    }
}
