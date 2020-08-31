package sword;

import common.ListNode;
import common.TreeNode;

public class Day6 {
    /**
     * 链表中倒数第k个节点：非快慢指针，而是双指针，一个在前面
     * @param head
     * @param k
     * @return
     */
    public ListNode FindKthToTail(ListNode head, int k) {
        ListNode fast = head;
        int stepCount = 0;
        while (fast != null && stepCount < k) {
            stepCount++;
            fast = fast.next;
        }
        if(stepCount != k) {
            return null;
        }
        ListNode slow = head;
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    /**
     * 调整数组顺序使奇数位于偶数前面
     * tips：使用辅助数组的话很简单，但是这题应该是不想我们用额外空间的，可以采用冒泡的原理
     * @param array
     */
    public void reOrderArray(int [] array) {
        for(int i = array.length - 2; i >= 0; i--) {
            for(int j = i; j < array.length; j++){
                if(array[j] % 2 == 0 && array[j+1] % 2 == 1) {
                    int tmp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = tmp;
                }
            }
        }
    }

    /**
     * 判断字符串是否表示数值：这种题想考的是正则吧
     * []  ： 字符集合
     * ()  ： 分组
     * ?   ： 重复 0 ~ 1 次
     * +   ： 重复 1 ~ n 次
     * *   ： 重复 0 ~ n 次
     * .   ： 任意字符
     * \\. ： 转义后的 .
     * \\d ： 数字
     * @param str
     * @return
     */
    public boolean isNumeric(char[] str) {
        if(str == null || str.length == 0) return false;
        String s = new String(str);
        return s.matches("[+-]?\\d*(\\.\\d+)?([eE][+-]?\\d+)?");
    }

    /**
     * 实现正则表达式
     * @param str
     * @param pattern
     * @return
     */
    public boolean match(char[] str, char[] pattern) {
        if(str == null || pattern == null) return false;
        return matchHelper(str, 0, pattern, 0);
    }
    private boolean matchHelper(char[] str, int strIdx, char[] pattern, int patternIdx) {
        if (patternIdx == pattern.length) {
            return strIdx == str.length;
        }
        //模式后一个为 '*'
        if(patternIdx + 1 < pattern.length && pattern[patternIdx+1] == '*') {
            if(strIdx < str.length && (pattern[patternIdx] == str[strIdx] || pattern[patternIdx] == '.')){
                return matchHelper(str, strIdx, pattern, patternIdx+2) ||
                        matchHelper(str, strIdx+1, pattern, patternIdx) ||
                        matchHelper(str, strIdx+1, pattern, patternIdx+2);
            }else {
                return matchHelper(str, strIdx, pattern, patternIdx+2);
            }
        }
        if(strIdx < str.length && (pattern[patternIdx] == str[strIdx] || pattern[patternIdx] == '.')) {
            return matchHelper(str, strIdx+1, pattern, patternIdx+1);
        }
        return false;
    }

    /**
     * 删除链表中重复的节点
     * notice:重复的节点不保留
     * @param pHead
     * @return
     */
    public ListNode deleteDuplication(ListNode pHead) {
        ListNode dHead = new ListNode(-1);
        ListNode pre = dHead;
        while (pHead != null) {
            int count = 0;
            while (pHead.next != null && pHead.val == pHead.next.val) {
                pHead = pHead.next;
                count++;
            }
            if(count > 0) {
                pre.next = pHead.next;
            }else {
                pre.next = pHead;
                pre = pHead;
            }
            pHead = pHead.next;
        }
        return dHead.next;
    }

    /**
     * 递归写法
     * @param pHead
     * @return
     */
    public ListNode deleteDuplication2(ListNode pHead) {
        if(pHead == null || pHead.next == null) {
            return pHead;
        }
        ListNode next = pHead.next;
        if(pHead.val == next.val) {
            while (next != null && pHead.val == next.val) {
                next = next.next;
            }
            return deleteDuplication2(next);
        }else {
            pHead.next = deleteDuplication2(pHead.next);
            return pHead;
        }
    }

    /**
     * 二进制中1的个数
     * @param n
     * @return
     */
    public int NumberOf1(int n) {
        int count = 0;
        while (n != 0) {
            count += n & 1;
            n = n>> 1;
        }
        return count;
    }

    /**
     * 旋转数组的最小值：值有重复的情况
     * @param nums
     * @return
     */
    public int minNumberInRotateArray(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int left = 0, right = nums.length-1;
        while (left < right) {
            int mid = (left + right) / 2;
            if(nums[mid] == nums[left] && nums[mid] == nums[right]) {
                return miniValue(nums, left, right);
            }else if(nums[mid] <= nums[right]) {
                right = mid;
            }else {
                left = mid + 1;
            }
        }
        return nums[left];
    }
    private int miniValue(int[] nums, int left, int right) {
        int min = nums[left];
        for(int i = left; i <= right; i++) {
            if(nums[i] < min){
                min = nums[i];
            }
        }
        return min;
    }

    /**
     * 根据前序遍历和中序遍历重建树
     * @param pre
     * @param in
     * @return
     */
    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        return helper(pre, in, 0, pre.length-1, 0, in.length-1);
    }
    private TreeNode helper(int[] pre, int[] in, int preStart, int preEnd, int inStart, int inEnd) {
        if(preStart > preEnd || inStart > inEnd) return null;
        TreeNode node = new TreeNode(pre[preStart]);
        for(int i = inStart; i <= inEnd; i++) {
            if(in[i] == pre[preStart]) {
                node.left = helper(pre, in, preStart+1, preStart+i-inStart, inStart, i-1);
                node.right = helper(pre, in, preStart + i - inStart + 1, preEnd, i + 1, inEnd);
                break;
            }
        }
        return node;
    }

    /**
     * %20 替换 空格
     * @param str
     * @return
     */
    public String replaceSpace(StringBuffer str) {
        int len1 = str.length();
        for (int i = 0; i < len1; i++){
            if(str.charAt(i) == ' '){
                str.append("  ");
            }
        }
        int len2 = str.length();
        while (len1 > 0 && len1 < len2) {
            char c = str.charAt(--len1);
            if(c == ' '){
                str.setCharAt(--len2, '0');
                str.setCharAt(--len2, '2');
                str.setCharAt(--len2, '%');
            }else {
                str.setCharAt(--len2, c);
            }
        }
        return str.toString();
    }

    /**
     * 在二维数组中查找一个数：从右上角开始（不是左上角），这样一来，往下是大，往左是小
     * @param target
     * @param array
     * @return
     */
    public boolean Find(int target, int [][] array) {
        int rowLen = array.length, colLen = array[0].length;
        int rowIdx = 0, colIdx = colLen - 1;
        while (rowIdx < rowLen && colIdx >= 0) {
            if(array[rowIdx][colIdx] == target) {
                return true;
            }else if (array[rowIdx][colIdx] > target){
                colIdx--;
            }else {
                rowIdx++;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Day6 day6 = new Day6();
        StringBuffer sb = new StringBuffer("ab c ");
        System.out.println(day6.replaceSpace(sb));
    }
}

