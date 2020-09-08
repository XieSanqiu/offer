package list;

import common.ListNode;

public class Easy {
    /**
     * 160.找出两个链表的交点，老经典题了
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode p1 = headA;
        ListNode p2 = headB;
        while (p1 != p2) {
            if (p1 == null) {
                p1 = headB;
            }else {
                p1 = p1.next;
            }
            if (p2 == null) {
                p2 = headA;
            }else {
                p2 = p2.next;
            }
        }
        return p1;
    }

    /**
     * 206.反转链表，老经典题了，用头插法
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        ListNode preHead = new ListNode(0);
        ListNode next;
        while (head != null) {
            next = head.next;
            head.next = preHead.next;
            preHead.next = head;
            head = next;
        }
        return preHead.next;
    }

    /**
     * 自己非想试一下不新建一个节点实现头插
     * @param head
     * @return
     */
    public ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode next = head.next;
        head.next = null;
        while (next != null) {
            ListNode nnext = next.next;
            next.next = head;
            head = next;
            next = nnext;
        }
        return head;
    }

    /**
     * 21.合并两条有序链表
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(-1);
        ListNode p = head;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                p.next = l1;
                l1 = l1.next;
            }else {
                p.next = l2;
                l2 = l2.next;
            }
            p = p.next;
        }
        if (l1 != null) {
            p.next = l1;
        }
        if (l2 != null) {
            p.next = l2;
        }
        return head.next;
    }

    /**
     * 83.有序链表中删除重复节点
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        ListNode rem = head;
        while (head != null && head.next != null) {
            if (head.val == head.next.val) {
                head.next = head.next.next;
            }else {
                head = head.next;
            }
        }
        return rem;
    }

    /**
     * 234.回文链表，切成两半，后一半反转，用快慢指针会更优一些（并没有多少）
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        int len = 0;
        ListNode tmp = head;
        while (tmp != null) {
            tmp = tmp.next;
            len++;
        }
        if (len == 1) return true;
        ListNode reverse;
        tmp = head;
        int n = len;
        while (n > (len + 1) / 2) {
            tmp = tmp.next;
            n--;
        }
        reverse = reverseList(tmp);
        while (head != null && reverse != null) {
            if (head.val != reverse.val){
                return false;
            }
            head = head.next;
            reverse = reverse.next;
        }
        return true;
    }
}
