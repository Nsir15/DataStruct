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
		
        	ListNode newHead = reverseList(head.next);
        	head.next.next = head;
        	head.next = null
        	return newHead;
    }
}
