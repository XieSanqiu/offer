package dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Bag01 {

    /**
     * 416.将数组划分成和相同的两部分
     * result：方法是对的，但是会超时
     * @param nums
     * @return
     */
    public boolean canPartition(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            count += nums[i];
        }
        if (count % 2 != 0) return false;
        return canPartitionHelper(nums, 0, count / 2);
    }
    private boolean canPartitionHelper(int[] nums, int idx, int value) {
        if (value == 0) return true;
        if (idx >= nums.length) return false;
        return canPartitionHelper(nums, idx + 1, value) ||
                canPartitionHelper(nums, idx + 1, value - nums[idx]);
    }

    /**
     * 要用背包的思想
     * @param nums
     * @return
     */
    public boolean canPartition2(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            count += nums[i];
        }
        if (count % 2 != 0) return false;
        int W = count / 2;
        boolean[][] dp = new boolean[nums.length][W+1]; //dp[i][j]为[0,i]这个区间是否能挑选一些数使他们的和等于j
        if (nums[0] <= W) {
            dp[0][nums[0]] = true;
        }
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j <= W; j++) {
                dp[i][j] = dp[i-1][j];
                if (nums[i] == j) {
                    dp[i][j] = true;
                    continue;
                }
                if (nums[i] < j) {
                    dp[i][j] = dp[i-1][j] || dp[i-1][j-nums[i]];
                }
            }
        }
        return dp[nums.length-1][W];
    }

    /**
     * 更简洁的一种实现
     * @param nums
     * @return
     */
    public boolean canPartition3(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            count += nums[i];
        }
        if (count % 2 != 0) return false;
        int W = count / 2;
        boolean[] dp = new boolean[W+1];
        dp[0] = true;
        for (int num: nums) {
            for (int i = W; i >= num; i--) { //从高到底为了防止高层被底层污染如[1,2,5]
                dp[i] = dp[i] || dp[i-num];
            }
        }
        return dp[W];
    }

    /**
     * 改变一组数的正负号使得它们的和为一给定数
     * 可以将这组数看成两部分，P 和 N，其中 P 前面使用正号，N 前面使用负号
     *                   sum(P) - sum(N) = target
     * sum(P) + sum(N) + sum(P) - sum(N) = target + sum(P) + sum(N)
     *                        2 * sum(P) = target + sum(nums)
     * 因此只要找到一个子集，令它们都取正号，并且和等于 (target + sum(nums))/2
     * （想不到啊，想不到，跟上面的一题搞的一样了）
     * @param nums
     * @param S
     * @return
     */
    public int findTargetSumWays(int[] nums, int S) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            count += nums[i];
        }
        if (count < S || (count + S) % 2 == 1) return 0;
        int W = (count + S) / 2;
        int[] dp = new int[W+1];
        dp[0] = 1;
        for (int num: nums) {
            for (int i = W; i >= num; i--) {
                dp[i] = dp[i] + dp[i-num];
            }
            for (int n: dp) {
                System.out.print(n + " ");
            }
            System.out.println();
        }
        return dp[W];
    }

    /**
     * 还好这个能看懂
     * @param nums
     * @param S
     * @return
     */
    public int findTargetSumWays2(int[] nums, int S) {
        return findTargetSumWays(nums, 0, S);
    }
    private int findTargetSumWays(int[] nums, int idx, int target) {
        if (idx == nums.length) {
            return target == 0? 1 : 0;
        }
        return findTargetSumWays(nums, idx+1, target - nums[idx]) +
                findTargetSumWays(nums, idx+1, target + nums[idx]);
    }

    /**
     * 474.1 和 0：
     * @param strs
     * @param m
     * @param n
     * @return
     */
    public int findMaxForm(String[] strs, int m, int n) {
        if(strs == null || strs.length == 0) {
            return 0;
        }
        int[][] dp = new int[m+1][n+1];
        for (String str: strs) {
            int zero = 0, one = 0;
            for (char c: str.toCharArray()) {
                if (c == '0') {
                    zero++;
                }else {
                    one++;
                }
            }
            for (int i = m; i >= zero; i--) {
                for (int j = n; j >= one; j--) {
                    dp[i][j] = Math.max(dp[i][j], dp[i-zero][j-one] + 1);
                }
            }
            print(dp);
        }
        return dp[m][n];
    }

    /**
     * 322.零钱兑换
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        if (amount <= 0) return 0;
        int[] dp = new int[amount+1];
        for (int coin: coins) {
            for (int i = coin; i <= amount; i++) {
                if (i == coin) {
                    dp[i] = 1;
                } else if (dp[i] == 0 && dp[i-coin] != 0) {
                    dp[i] = dp[i-coin] + 1;
                }else if (dp[i-coin] != 0) {
                    dp[i] = Math.min(dp[i], dp[i-coin] + 1);
                }
            }
            for (int i = 0; i < dp.length; i++){
                System.out.print(dp[i] + " ");
            }
            System.out.println();
        }
        return dp[amount] == 0? -1 : dp[amount];
    }
    /**
     * 这个方法才是自己想的，但是自己没实现
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange2(int[] coins, int amount) {
        int[] dp = new int[amount+1];
        Arrays.fill(dp, amount+1);
        dp[0] = 0;
        for (int i = 0; i <= amount; i++){
            for (int coin: coins) {
                if (i >= coin) {
                    dp[i] = Math.min(dp[i], dp[i-coin] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1: dp[amount];
    }

    private void print(int[][] dp) {
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Bag01 bag01 = new Bag01();
        int[] nums = new int[]{1,1,1,1,1};
        System.out.println(bag01.findTargetSumWays(nums, 3));
        String s1 = "123";
        String s2 = "123";
        System.out.println(s1 == s2);
    }
}
