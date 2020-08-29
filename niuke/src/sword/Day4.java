package sword;

import java.util.*;

public class Day4 {
    /**
     * 年终奖：礼物最大价值
     * solution1: 深度优选，复杂度比较高
     * @param board
     * @return
     */
    public int getMost(int[][] board) {
        return helper1(board, 0, 0, 0);
    }
    private int helper1(int[][] board, int i, int j, int tmpCount) {
        if(i == board.length - 1 && j == board[0].length - 1) {
            return tmpCount + board[i][j];
        }else if (i == board.length - 1) {
            return helper1(board, i, j + 1, tmpCount + board[i][j]);
        }else if (j == board[0].length - 1) {
            return helper1(board, i + 1, j, tmpCount + board[i][j]);
        }else {
            int downCount = helper1(board, i + 1, j, tmpCount + board[i][j]);
            int rightCount = helper1(board, i, j + 1, tmpCount + board[i][j]);
            return Math.max(downCount, rightCount);
        }
    }

    /**
     * 这是一个非常常见的路径dp问题，求最大或最小都是一样的
     * dp[i] 为走到当前位置的最大值
     * @param board
     * @return
     */
    public int getMost2(int[][] board) {
        int colLen = board[0].length;
        int[] dp = new int[colLen];
        for(int[] row: board) {
            dp[0] += row[0];
            for(int i = 1; i < colLen; i++) {
                dp[i] = Math.max(dp[i], dp[i-1]) + row[i]; //此时的dp[i]相当于上面一个最大值，dp[i-1]相当于左边一个最大值
            }
        }
        return dp[colLen-1];
    }

    /**
     * 把数组排成最小的数：核心思想比较 S1 + S2 与 S2 + S1
     * @param numbers
     * @return
     */
    public String PrintMinNumber(int [] numbers) {
        int len = numbers.length;
        for(int i = 0; i < len; i++) {
            for(int j = i + 1; j < len; j++) {
                int s1 = Integer.valueOf(numbers[i] + "" + numbers[j]);
                int s2 = Integer.valueOf(numbers[j] + "" + numbers[i]);
                if(s1 > s2) {
                    int tmp = numbers[i];
                    numbers[i] = numbers[j];
                    numbers[j] = tmp;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < len; i++) {
            sb.append(numbers[i]);
        }
        return sb.toString();
    }

    /**
     * 连续子数组最大和：经典题目之一
     * solution1:这样写太low了
     * @param array
     * @return
     */
    public int FindGreatestSumOfSubArray(int[] array) {
        if(array == null || array.length < 1) return 0;
        int[] dp = new int[array.length];
        int max = array[0];
        dp[0] = array[0];
        for(int i = 1; i < array.length; i++) {
            if(dp[i-1] >= 0) {
                dp[i] = dp[i-1] + array[i];
            }else {
                dp[i] = array[i];
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }
    public int FindGreatestSumOfSubArray2(int[] array) {
        if(array == null || array.length < 1) return 0;
        int curMax = array[0], max = array[0];
        for(int i = 1; i < array.length; i++) {
            curMax = Math.max(curMax + array[i], array[i]); //curMax = dp[i]
            max = Math.max(max, curMax);
        }
        return max;
    }

    /**
     * 字符流中第一个不重复的字符
     * @param ch
     */
    private int[] count = new int[256];
    private Queue<Character> queue = new LinkedList<>();
    public void Insert(char ch) {
        count[ch]++;
        queue.add(ch);
        while (!queue.isEmpty() && count[queue.peek()] > 1) {
            queue.poll();
        }
    }
    //return the first appearence once char in current stringstream
    public char FirstAppearingOnce() {
        return queue.isEmpty() ? '#' : queue.peek();
    }

    /**
     * 数据流中的中位数：使用两个大小堆，左边大顶堆，右边小顶堆，右边一半大于左边一半
     * @param num
     */
    PriorityQueue<Integer> left = new PriorityQueue<>((o1, o2) -> o2 - o1);
    PriorityQueue<Integer> right = new PriorityQueue<>();
    int N = 0;
    public void Insert(Integer num) {
        if(N % 2 == 0) {
            left.add(num);
            right.add(left.poll());
        }else {
            right.add(num);
            left.add(right.poll());
        }
        N++;
    }
    public Double GetMedian() {
        if(N % 2 == 0) {
            return (left.peek() + right.peek()) / 2.0;
        }else {
            return (double)right.peek();
        }
    }

    /**
     * 最小k个数：用大顶堆
     * @param input
     * @param k
     * @return
     */
    public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        ArrayList<Integer> res = new ArrayList<>();
        if(input == null || input.length < k) return res;
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(((o1, o2) -> o2 - o1));
        for(int n : input) {
            priorityQueue.add(n);
            if(priorityQueue.size() > k) {
                priorityQueue.poll();
            }
        }
        res.addAll(priorityQueue);
        return res;
    }

    /**
     * 最小k个数：用快排解决
     */
    public ArrayList<Integer> GetLeastNumbers_Solution2(int [] input, int k) {
        ArrayList<Integer> res = new ArrayList<>();
        if(input == null || input.length < k) {
            return res;
        }
        findKthSmallest(input, k);
        for(int i = 0; i < k; i++) {
            res.add(input[i]);
        }
        return res;
    }
    private void findKthSmallest(int[] nums, int k) {
        int l = 0, h = nums.length - 1;
        while (l < h) {
            int j = partition(nums, l, h);
            if(j == k){
                break;
            }else if(j > k) {
                h = j - 1;
            }else {
                l = j + 1;
            }
        }
    }
    private int partition(int[] nums, int l, int h) {
        int p = nums[l];
        int i = l, j = h + 1;
        while (true) {
            while (i != h && nums[++i] < p);
            while (j != l && nums[--j] > p);
            if(i >= j) break;
            swap(nums, i, j);
        }
        swap(nums, l, j);
        return j;
    }
    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

    /**
     * 数组中出现次数超过一半的数字
     * @param array
     * @return
     */
    public int MoreThanHalfNum_Solution(int [] array) {
        if(array == null || array.length < 1) return 0;
        int ans = array[0];
        int cnt = 1;
        for(int i = 1; i < array.length; i++) {
            if(ans == array[i]) {
                cnt++;
            }else {
                cnt--;
            }
            if(cnt == 0) {
                ans = array[i];
                cnt = 1;
            }
        }
        cnt = 0;
        for(int i = 0; i < array.length; i++) {
            if(array[i] == ans) {
                cnt++;
            }
        }
        return cnt > array.length / 2 ? ans : 0;
    }

    /**
     * 字符串的排列：比较常见的回溯，要好好看看
     * @param str
     * @return
     */
    private ArrayList<String> res = new ArrayList<>();
    public ArrayList<String> Permutation(String str) {
        if(str == null || str.length() == 0) return res;
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        backtracking(chars, new boolean[chars.length], new StringBuilder());
        return res;
    }
    private void backtracking(char[] chars, boolean[] hasUsed, StringBuilder s) {
        if(s.length() == chars.length) {
            res.add(s.toString());
            return;
        }
        for(int i = 0; i < chars.length; i++) {
            if (hasUsed[i]) {
                continue;
            }
            if (i != 0 && chars[i] == chars[i-1] && !hasUsed[i-1]) {
                continue;
            }
            hasUsed[i] = true;
            s.append(chars[i]);
            backtracking(chars, hasUsed, s);
            hasUsed[i] = false;
            s.deleteCharAt(s.length()-1);
        }
    }

    public static void main(String[] args) {
        Day4 day4 = new Day4();
    }
}
