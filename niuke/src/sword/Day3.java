package sword;

import common.ListNode;
import common.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;

public class Day3 {
    /**
     * 判断一颗二叉树是否是平衡二叉树
     * @param root
     * @return
     */
    public boolean IsBalanced_Solution(TreeNode root) {
        if(root == null) return true;
        return Math.abs(helper1(root.left) - helper1(root.right)) <= 1;
    }
    private int helper1(TreeNode root){
        if(root == null){
            return 0;
        }else {
            return Math.max(helper1(root.left), helper1(root.right)) + 1;
        }
    }

    /**
     * 二叉树的深度
     * @param root
     * @return
     */
    public int TreeDepth(TreeNode root) {
        if(root == null){
            return 0;
        }else {
            return Math.max(TreeDepth(root.left), TreeDepth(root.right)) + 1;
        }
    }

    /**
     * 统计一个数字在升序数组中出现的次数：二分查找
     * @param array
     * @param k
     * @return
     */
    public int GetNumberOfK(int [] array , int k) {
        int low = 0, high = array.length-1, mid = 0;
        while (low <= high){
            mid = (low + high) / 2;
            if(array[mid] == k){
                break;
            }else if(array[mid] > k){
                high = mid - 1;
            }else {
                low = mid + 1;
            }
        }
        int ans = 0;
        if(low > high){
            return ans;
        }
        for(int i = mid; i >= 0; i--){
            if(array[i] == k){
                ans++;
            }
        }
        for(int i = mid + 1; i < array.length; i++){
            if(array[i] == k){
                ans++;
            }
        }
        return ans;
    }

    /**
     * 两个链表的第一个公共节点：两个指针交换着跑
     * @param pHead1
     * @param pHead2
     * @return
     */
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        ListNode tmp1 = pHead1, tmp2 = pHead2;
        while (tmp1 != tmp2){
            tmp1 = tmp1 == null ? pHead2 : tmp1.next;
            tmp2 = tmp2 == null ? pHead1 : tmp2.next;
        }
        return tmp1;
    }

    /**
     * 数组中的逆序对：用归并排序
     * @param array
     * @return
     */
    private int count = 0;
    private int[] tmp;
    public int InversePairs(int[] array) {
        tmp = new int[array.length];
        mergeSort(array, 0, array.length-1);
        return count;
    }
    private void mergeSort(int[] array, int start, int end) {
        if(start >= end) return;
        int mid = (start + end) / 2;
        mergeSort(array, start, mid);
        mergeSort(array, mid+1, end);
        merge(array, start, mid, end);
    }
    private void merge(int[] array, int start, int mid, int end){
        int i = start, j = mid + 1, k = start;
        while (i <= mid || j <= end) {
            if(i > mid){
                tmp[k++] = array[j++];
            }else if (j > end){
                tmp[k++] = array[i++];
            }else if(array[i] <= array[j]){
                tmp[k++] = array[i++];
            }else {
                tmp[k++] = array[j++];
                count += mid - i + 1; // nums[i] > nums[j]，说明 nums[i...mid] 都大于 nums[j]
                count %= 1000000007;
            }
        }
        for(int m = start; m <= end; m++){
            array[m] = tmp[m];
        }
    }

    /**
     * 第一个只出现一次的字符
     * @param str
     * @return
     */
    public int FirstNotRepeatingChar(String str) {
        int[] rec = new int[58];
        for (int i = 0; i < str.length(); i++) {
            rec[str.charAt(i) - 'A']++;
        }
        for(int i = 0; i < str.length(); i++) {
            if(rec[str.charAt(i) - 'A'] == 1){
                return i;
            }
        }
        return -1;
    }

    /**
     * 丑数
     * @param index
     * @return
     */
    public int GetUglyNumber_Solution(int index) {
        if(index < 1) return 0;
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        int i2 = 0, i3 = 0, i5 = 0;
        int m2, m3, m5;
        while (list.size() < index){
            m2 = list.get(i2) * 2;
            m3 = list.get(i3) * 3;
            m5 = list.get(i5) * 5;
            int min = Math.min(Math.min(m2, m3), m5);
            list.add(min);
            if(m2 == min) i2++;
            if(m3 == min) i3++;
            if(m5 == min) i5++;
        }
        return list.get(index-1);
    }

    /**
     * 最长不含重复字符的子字符串
     * 采用动态规划，首先定义函数f(i)表示以第i个字符为结尾的不包含重复字符的子字符串的最长长度,则有一下三种情形
     * 1）第i个字符在之前都没有出现过，则f(i) = f(i-1)+1
     * 2）第i个字符在之前出现过，但是在f(i-1)这个子串的前面出现过，则最长还是f(i-1)+1
     * 3）第i个字符在之前出现过，不过在f(i-1)的这个子串的中间出现过，则f(i)=这两个重复字符的中间值
     * demo: "arabcacfr"
     * @param str
     * @return
     */
    public int longestSubStringWithoutDuplication(String str) {
        int curLen = 0; //所以curLen就是f(i)
        int maxLen = 0;
        int[] preIndexes = new int[26];
        Arrays.fill(preIndexes, -1);
        for (int curIndex = 0; curIndex < str.length(); curIndex++) {
            int c = str.charAt(curIndex) - 'a';
            int preIndex = preIndexes[c];
            //如果在之前没出现过，或者出现过但是在前一个不重复子串的前面
            //因为当前的最长子串是acf，而r前一次出现还是在这个子串之前，所以还是等于子串加1
            if(preIndex == -1 || curIndex - preIndex > curLen) {
                curLen++;
            } else {
                //因为在不重复子串之间，所以当前字符不重复的最长子串为两个字符之间i-prevIndex
                maxLen = Math.max(maxLen, curLen);
                curLen = curIndex - preIndex;
            }
            preIndexes[c] = curIndex;
        }
        maxLen = Math.max(maxLen, curLen);
        return maxLen;
    }
    public int longestSubStringWithoutDuplication2(String str) {
        int[] posIndexes = new int[26];
        Arrays.fill(posIndexes, -1);
        int curLen = 0, maxLen = 0;
        for(int curIndex = 0; curIndex < str.length(); curIndex++) {
            int c = str.charAt(curIndex) - 'a';
            int preIndex = posIndexes[c];
            if(preIndex == -1 || curIndex - preIndex > curLen) {
                curLen++;
            }else {
                maxLen = Math.max(maxLen, curLen);
                curLen = curIndex - preIndex;
            }
            posIndexes[c] = curIndex;
        }
        maxLen = Math.max(maxLen, curLen);
        return maxLen;
    }

    public static void main(String[] args) {
        Day3 day3 = new Day3();
        String str = "arabcacfr";
        System.out.println(day3.longestSubStringWithoutDuplication(str));
    }
}
