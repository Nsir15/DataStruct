package 链表;

/**
 * https://leetcode-cn.com/problems/delete-node-in-a-linked-list/
 * @author nancy
 *
 */
public class _237_删除链表中的节点 {
	
	public void deleteNode(ListNode node) {
        	//将这个节点的值覆盖掉，然后next 指向下一个结点的下一个结点
		node.val = node.next.val;
		node.next = node.next.next;
    }
}
