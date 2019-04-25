package 链表;

/**
 * https://leetcode-cn.com/problems/reverse-linked-list/
 * @author nancy
 *
 */
public class _206_反转链表 {

	//递归的形式
	public ListNode reverseList(ListNode head) {
		
		if (head == null || head.next == null) return head;
		ListNode newHeade = reverseList(head.next);
		head.next.next = head;
		head.next = null;
		return newHeade;
	}
	
	//非递归的形式
	public ListNode reverseList2(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode currentListNode = head;
		ListNode previoudListNode = null;
		while (currentListNode != null) {
			ListNode nextTempNode = currentListNode.next;
//			nextTempNode.next = currentListNode;
			currentListNode.next = previoudListNode;
			previoudListNode = currentListNode;
			currentListNode = nextTempNode;
		}
		return previoudListNode;
	}
	
//	非递归的方法2
	public ListNode reverseList3(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode newHeadListNode = null;
		while (head != null) {
			ListNode tempNextNode = head.next;
			head.next = newHeadListNode;
//			将newHeadListNode 指向 head
			newHeadListNode = head;
			head = tempNextNode;
		}
		return newHeadListNode;
	}
}
