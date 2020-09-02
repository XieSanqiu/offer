package dp;

public class Fibonacci {
    /**
     * Easy
     * 70. 爬楼梯
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        if (n <= 3) {
            return n;
        }
        int a = 1, b = 2, c;
        for (int i = 1; i < n; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return a;
    }

    /**
     * Easy
     * 198.强盗抢劫
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        int len = nums.length;
        int pre1 = 0, pre2 = 0, t;
        for (int i = 0; i < len; i++) {
            t = Math.max(pre1, pre2 + nums[i]);
            pre2 = pre1;
            pre1 = t;
        }
        return pre1;
    }

    /**
     * Medium
     * 213.强盗抢劫Ⅱ
     * tips:把
     * @param nums
     * @return
     */
    public int rob2(int[] nums) {
        int len = nums.length;
        if (len == 1) return nums[0];
        return Math.max(rob2Helper(nums, 0, len-2),
                rob2Helper(nums, 1, len-1));
    }
    private int rob2Helper(int[] nums, int left, int right) {
        int pre1 = 0, pre2 = 0, t;
        for (int i = left; i <= right; i++) {
            t = Math.max(pre2 + nums[i], pre1);
            pre2 = pre1;
            pre1 = t;
        }
        return pre1;
    }
}
