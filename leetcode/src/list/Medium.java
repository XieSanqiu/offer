package list;

import common.ListNode;

import java.util.Stack;

public class Medium {
    /**
     * 19.删除链表倒数第 n 个节点
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode fast = head, slow = head;
        //fast先走 n 步
        while (fast != null && n > 0) {
            fast = fast.next;
            n--;
        }
        if (n > 0) return null;
        if (fast == null) return head.next;
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return head;
    }

    /**
     * 24.交换链表中相邻节点，不能修改节点 val 值
     *
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        ListNode tmp = head;
        while (tmp != null && tmp.next != null) {
            int t = tmp.val;
            tmp.val = tmp.next.val;
            tmp.next.val = t;
            tmp = tmp.next.next;
        }
        return head;
    }

    /**
     * 用递归
     *
     * @param head
     * @return
     */
    public ListNode swapPairs2(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode next = head.next;
        head.next = swapPairs2(head.next.next);
        next.next = head;
        return next;
    }

    /**
     * 不用递归
     *
     * @param head
     * @return
     */
    public ListNode swapPairs3(ListNode head) {
        ListNode node = new ListNode(-1);
        node.next = head;
        ListNode pre = node;
        while (pre.next != null && pre.next.next != null) {
            ListNode l1 = pre.next, l2 = pre.next.next;
            l1.next = l2.next;
            l2.next = l1;
            pre.next = l2;
            pre = l1;
        }
        return node.next;
    }

    /**
     * 445.两数相加，数用链表表示
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        l1 = reverseList(l1);
        l2 = reverseList(l2);
        ListNode res = new ListNode(-1);
        ListNode tmp = res;
        int carry = 0;
        while (l1 != null || l2 != null) {
            if (l1 != null) {
                carry += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                carry += l2.val;
                l2 = l2.next;
            }
            ListNode node = new ListNode(carry % 10);
            tmp.next = node;
            tmp = node;
            carry /= 10;
        }
        if (carry > 0) {
            ListNode node = new ListNode(carry);
            tmp.next = node;
        }
        return reverseList(res.next);
    }
    private ListNode reverseList(ListNode head) {
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
     * 题目要求不能修改链表，即不能反转链表，用栈
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        Stack<Integer> s1 = buildStack(l1);
        Stack<Integer> s2 = buildStack(l2);
        ListNode pre = new ListNode(-1);
        int carry = 0;
        while (!s1.isEmpty() || !s2.isEmpty() || carry > 0) {
            int x = s1.isEmpty() ? 0 : s1.pop();
            int y = s2.isEmpty() ? 0 : s2.pop();
            carry += (x + y);
            ListNode node = new ListNode(carry % 10);
            node.next = pre.next;
            pre.next = node;
            carry /= 10;
        }
        return pre.next;
    }
    private Stack<Integer> buildStack(ListNode head) {
        Stack<Integer> stack = new Stack<>();
        while (head != null) {
            stack.push(head.val);
            head = head.next;
        }
        return stack;
    }

    /**
     * 725.分割链表，意思一样，但是写的麻烦了点
     * @param root
     * @param k
     * @return
     */
    public ListNode[] splitListToParts(ListNode root, int k) {
        int len = 0;
        ListNode tmpLen = root;
        while (tmpLen != null) {
            len++;
            tmpLen = tmpLen.next;
        }
        int divisor = len / k;
        int remainder = len % k;
        ListNode[] res = new ListNode[k];
        if (root == null) return res;
        ListNode next;
        for (int i = 0; i < k; i++) {
            ListNode node = root;
            int j;
            if (i < remainder) {
                j = 0;
            }else {
                j = 1;
            }
            for (; j < divisor; j++){
                root = root.next;
            }
            if (root != null) {
                next = root.next;
                root.next = null;
                root = next;
            }
            res[i] = node;
        }
        return res;
    }
    public ListNode[] splitListToParts2(ListNode root, int k) {
        int N = 0;
        ListNode cur = root;
        while (cur != null) {
            N++;
            cur = cur.next;
        }
        int mod = N % k;
        int size = N / k;
        ListNode[] ret = new ListNode[k];
        cur = root;
        for (int i = 0; cur != null && i < k; i++) {
            ret[i] = cur;
            int curSize = size + (mod-- > 0 ? 1 : 0);
            for (int j = 0; j < curSize - 1; j++) {
                cur = cur.next;
            }
            ListNode next = cur.next;
            cur.next = null;
            cur = next;
        }
        return ret;
    }

    /**
     * 328.奇偶链表
     * @param head
     * @return
     */
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode oddHead = head, evenHead = head.next, p = head.next.next;
        ListNode oddTmp = oddHead, evenTmp = evenHead;
        int n = 3;
        while (p != null) {
            if (n % 2 == 1) {
                oddTmp.next = p;
                oddTmp = oddTmp.next;
            }else {
                evenTmp.next = p;
                evenTmp = evenTmp.next;
            }
            n++;
            p = p.next;
        }
        evenTmp.next = null;
        oddTmp.next = evenHead;
        return oddHead;
    }

    /**
     * 艹，真他妈的简洁
     * @param head
     * @return
     */
    public ListNode oddEvenList2(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode odd = head, even = head.next, evenHead = even;
        while (even != null && even.next != null) {
            odd.next = odd.next.next;
            odd = odd.next;
            even.next = even.next.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }
}
