package sword;

import common.ListNode;
import common.RandomListNode;
import common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Day5 {
    /**
     * 序列化二叉树：这个序列化不难，但是反序列化确实要掌握方法
     * @param root
     * @return
     */
    String Serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        if (root == null) {
            sb.append("#,");
            return sb.toString();
        }
        sb.append(root.val + ",");
        sb.append(Serialize(root.left));
        sb.append(Serialize(root.right));
        return sb.toString();
    }
    private int index = -1;
    TreeNode Deserialize(String str) {
        index++;
        String[] strings = str.split(",");
        TreeNode node = null;
        if(!"#".equals(strings[index])) {
            node = new TreeNode(Integer.valueOf(strings[index]));
            node.left = Deserialize(str);
            node.right = Deserialize(str);
        }
        return node;
    }

    /**
     * 不创建新节点将二叉搜索树转为排序的双向链表：这应该会是一个很巧妙的算法
     * 前序遍历
     * @param pRootOfTree
     * @return
     */
    private TreeNode head;
    private TreeNode curNode;
    public TreeNode Convert(TreeNode pRootOfTree) {
        if(pRootOfTree == null) return null;
        Convert(pRootOfTree.left);
        if(head == null) {
            head = pRootOfTree;
            curNode = pRootOfTree;
        }else {
            curNode.right = pRootOfTree;
            pRootOfTree.left = curNode;
            curNode = pRootOfTree;
        }
        Convert(pRootOfTree.right);
        return head;
    }

    /**
     * 深拷贝复杂链表：分三步
     * step 1：首先复制每个节点插入相对应的节点后面
     * step 2：赋值克隆节点的随机next
     * step 3：将链拆分，返回复制的链的头
     * @param pHead
     * @return
     */
    public RandomListNode Clone(RandomListNode pHead) {
        if(pHead == null) return null;
        //step 1：首先复制每个节点插入相对应的节点后面
        RandomListNode tmpNode = pHead;
        while (tmpNode != null) {
            RandomListNode cloneNode = new RandomListNode(tmpNode.label);
            cloneNode.next = tmpNode.next;
            tmpNode.next = cloneNode;
            tmpNode = tmpNode.next.next;
        }
        //step 2：赋值克隆节点的随机next
        tmpNode = pHead;
        while (tmpNode != null) {
            if(tmpNode.random != null){
                tmpNode.next.random = tmpNode.random.next;
            }
            tmpNode = tmpNode.next.next;
        }
        //step 3：将链拆分，返回复制的链的头
        RandomListNode cloneHead = pHead.next;
        RandomListNode cloneTmpNode = cloneHead;
        tmpNode = pHead;
        while (tmpNode != null) {
            tmpNode.next = tmpNode.next.next;
            if (cloneTmpNode.next != null) {
                cloneTmpNode.next = cloneTmpNode.next.next;
            }
            tmpNode = tmpNode.next;
            cloneTmpNode = cloneTmpNode.next;
        }
        return cloneHead;
    }

    /**
     * 二叉树中和为某一值的路径，该路径为从根节点到叶节点：用回溯
     * @param root
     * @param target
     * @return
     */
    ArrayList<ArrayList<Integer>> res = new ArrayList<>();
    ArrayList<Integer> list = new ArrayList<>();
    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        if (root == null) return res;
        list.add(root.val);
        target -= root.val;
        if(target == 0 && root.left == null && root.right == null) {
            res.add(new ArrayList<>(list));
        }
        FindPath(root.left, target);
        FindPath(root.right, target);
        list.remove(list.size()-1);
        return res;
    }

    /**
     * 判断一个数组是不是一个二叉搜索树的后序遍历序列
     * tips:抓住后序遍历的特性，找到位置，左边都是小于父节点的，右边都是大于父节点的
     * @param sequence
     * @return
     */
    public boolean VerifySquenceOfBST(int [] sequence) {
        if(sequence == null || sequence.length == 0) return false;
        return helper1(sequence, 0, sequence.length-1);
    }
    private boolean helper1(int[] sequences, int left, int right) {
        if(right - left < 3) return true;
        int last = sequences[right];
        int idx = left;
        for(int i = left; i <= right; i++){
            if(sequences[i] >= last) {
                idx = i;
                break;
            }
        }
        for(int i = idx; i < right; i++){
            if(sequences[i] <= last) {
                return false;
            }
        }
        if(idx == left || idx == right){
            return helper1(sequences, left, right-1);
        }else {
            return helper1(sequences, left, idx-1) && helper1(sequences, idx, right-1);
        }
    }

    /**
     * 从上往下打印二叉树
     * @param root
     * @return
     */
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        ArrayList<TreeNode> nodes = new ArrayList<>();
        if(root == null) return res;
        nodes.add(root);
        while (nodes.size() != 0) {
            TreeNode node = nodes.remove(0);
            res.add(node.val);
            if(node.left != null) nodes.add(node.left);
            if(node.right != null) nodes.add(node.right);
        }
        return res;
    }

    /**
     * 同上一题
     * @param pRoot
     * @return
     */
    ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        ArrayList<TreeNode> nodes = new ArrayList<>();
        if(pRoot == null) return res;
        nodes.add(pRoot);
        while (!nodes.isEmpty()){
            ArrayList<Integer> ans = new ArrayList<>();
            ArrayList<TreeNode> tmp = new ArrayList<>();
            for (TreeNode node: nodes) {
                ans.add(node.val);
                if(node.left != null) tmp.add(node.left);
                if(node.right != null) tmp.add(node.right);
            }
            nodes = tmp;
            res.add(ans);
        }
        return res;
    }

    /**
     * 之字形打印二叉树
     * @param pRoot
     * @return
     */
    public ArrayList<ArrayList<Integer> > PrintAsZ(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        ArrayList<TreeNode> nodes = new ArrayList<>();
        if(pRoot == null) return res;
        nodes.add(pRoot);
        boolean forward = true;
        while (!nodes.isEmpty()) {
            int size = nodes.size();
            ArrayList<Integer> oneLevel = new ArrayList<>();
            while (size-- > 0){
                TreeNode node = nodes.remove(0);
                if(node.left != null) nodes.add(node.left);
                if(node.right != null) nodes.add(node.right);
                if(forward){
                    oneLevel.add(node.val);
                }else {
                    oneLevel.add(0, node.val);
                }
            }
            res.add(oneLevel);
            forward = !forward;
        }
        return res;
    }

    /**
     * 判断一个序列是否是另一个序列的栈压入、弹出
     * @param pushA
     * @param popA
     * @return
     */
    public boolean IsPopOrder(int [] pushA,int [] popA) {
        if(pushA.length != popA.length) return false;
        Stack<Integer> stack = new Stack<>();
        int j = 0;
        for(int i = 0; i < pushA.length; i++) {
            stack.push(pushA[i]);
            while (!stack.isEmpty() && stack.peek() == popA[j]){
                stack.pop();
                j++;
            }
        }
        return stack.isEmpty();
    }

    /**
     * 重新实现栈，并实现一个min函数
     * @param node
     */
    Stack<Integer> dataStack = new Stack<>();
    Stack<Integer> minStack = new Stack<>();
    public void push(int node) {
        dataStack.push(node);
        if(minStack.isEmpty()){
            minStack.push(node);
        }else {
            minStack.push(Math.min(minStack.peek(), node));
        }
    }
    public void pop() {
        dataStack.pop();
        minStack.pop();
    }
    public int top() {
        return dataStack.peek();
    }
    public int min() {
        return minStack.peek();
    }

    /**
     * 顺时针打印矩阵：下面这个方法真好，简单好理解，很优雅
     * @param matrix
     * @return
     */
    public ArrayList<Integer> printMatrix(int [][] matrix) {
        ArrayList<Integer> ret = new ArrayList<>();
        int r1 = 0, r2 = matrix.length - 1, c1 = 0, c2 = matrix[0].length - 1;
        while (r1 <= r2 && c1 <= c2) {
            for (int i = c1; i <= c2; i++)
                ret.add(matrix[r1][i]);
            for (int i = r1 + 1; i <= r2; i++)
                ret.add(matrix[i][c2]);
            if (r1 != r2) //防止重复添加
                for (int i = c2 - 1; i >= c1; i--)
                    ret.add(matrix[r2][i]);
            if (c1 != c2) //防止重复添加
                for (int i = r2 - 1; i > r1; i--)
                    ret.add(matrix[i][c1]);
            r1++; r2--; c1++; c2--;
        }
        return ret;
    }

    /**
     * 判断一颗二叉树是否是对称的
     * @param pRoot
     * @return
     */
    boolean isSymmetrical(TreeNode pRoot) {
        if(pRoot == null) return true;
        return helper2(pRoot.left, pRoot.right);
    }
    private boolean helper2(TreeNode treeNode1, TreeNode treeNode2) {
        if(treeNode1 == null && treeNode2 == null) return true;
        if(treeNode1 == null || treeNode2 == null) return false;
        return treeNode1.val == treeNode2.val &&
                helper2(treeNode1.left, treeNode2.right) &&
                helper2(treeNode1.right, treeNode2.left);
    }

    /**
     * 将二叉树翻转
     * @param root
     */
    public void Mirror(TreeNode root) {
        if(root == null) return;
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        Mirror(root.left);
        Mirror(root.right);
    }

    /**
     * 判断一棵树是否是另一棵树的子结构
     * @param root1
     * @param root2
     * @return
     */
    public boolean HasSubtree(TreeNode root1, TreeNode root2) {
        if(root1 == null || root2 == null) return false;
        return helper3(root1, root2) || HasSubtree(root1.left, root2) || HasSubtree(root1.right, root2);
    }
    private boolean helper3(TreeNode node1, TreeNode node2) {
        if(node2 == null) return true;
        if(node1 == null) return false;
        if(node1.val != node2.val) return false;
        return helper3(node1.left, node2.left) && helper3(node1.right, node2.right);
    }

    /**
     * 合并两个有序链表
     * @param list1
     * @param list2
     * @return
     */
    public ListNode Merge(ListNode list1, ListNode list2) {
        ListNode head = new ListNode(0);
        ListNode tmp = head;
        while (list1 != null && list2 != null) {
            if(list1.val <= list2.val) {
                tmp.next = list1;
                list1 = list1.next;
            }else {
                tmp.next = list2;
                list2 = list2.next;
            }
            tmp = tmp.next;
        }
        if (list1 != null) {
            tmp.next = list1;
        }
        if (list2 != null) {
            tmp.next = list2;
        }
        return head.next;
    }

    /**
     * 反转链表：采用头插法
     * @param head
     * @return
     */
    public ListNode ReverseList(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode reverseHead = new ListNode(0);
        ListNode next;
        while (head != null) {
            next = head.next;
            head.next = reverseHead.next;
            reverseHead.next = head;
            head = next;
        }
        return reverseHead.next;
    }

    /**
     * 链表中环的入口节点
     * @param pHead
     * @return
     */
    public ListNode EntryNodeOfLoop(ListNode pHead) {
        if(pHead == null || pHead.next == null) return null;
        ListNode slow = pHead.next, fast = pHead.next.next;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next.next;
        }
        fast = pHead;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return fast;
    }

    public static void main(String[] args) {
        Day5 day5 = new Day5();
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        node1.next = node2;
        ListNode rNode = day5.ReverseList(node1);
        while (rNode != null) {
            System.out.println(rNode.val);
            rNode = rNode.next;
        }
    }
}
