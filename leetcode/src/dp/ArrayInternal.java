package dp;

public class ArrayInternal {
    /**
     * 303.数组区间和
     */
    int[] dp;
    public void NumArray(int[] nums) {
        dp = new int[nums.length+1];
        for (int i = 1; i <= nums.length; i++) {
            dp[i] = nums[i-1] + dp[i-1];
        }
    }

    public int sumRange(int i, int j) {
        return dp[j+1] - dp[i];
    }

    /**
     * 413.等差数列划分
     * @param A
     * @return
     */
    public int numberOfArithmeticSlices(int[] A) {
        if (A == null || A.length == 0) return 0;
        int len = A.length;
        int[] dp = new int[len];
        for (int i = 2; i < len; i++) {
            if (A[i] - A[i-1] == A[i-1] - A[i-2]) {
                dp[i] = dp[i-1] + 1;
            }
        }
        int count = 0;
        for (int i = 0; i < len; i++) {
            count += dp[i];
        }
        return count;
    }
}
