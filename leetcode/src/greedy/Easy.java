package greedy;

import java.util.Arrays;

public class Easy {
    /**
     * 分配饼干
     * 贪心思想：
     * 1、给一个孩子的饼干应当尽量小并且又能满足该孩子，这样大饼干才能拿来给满足度比较大的孩子。
     * 2、因为满足度最小的孩子最容易得到满足，所以先满足满足度最小的孩子。
     * @param g
     * @param s
     * @return
     */
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int i = 0, j = 0;
        while (i < g.length && j < s.length) {
            if (g[i] <= s[j]) {
                i++;
            }
            j++;
        }
        return i;
    }

    /**
     * 股票最大利益：只允许一次交易
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int minPrice = prices[0], maxProfit = 0;
        for (int p: prices) {
            minPrice = Math.min(minPrice, p);
            maxProfit = Math.max(maxProfit, p - minPrice);
        }
        return maxProfit;
    }

    /**
     * 股票最大利益：允许多次交易
     * @param prices
     * @return
     */
    public int maxProfit2(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int ans = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            if (prices[i+1] > prices[i]) {
                ans += prices[i+1] - prices[i];
            }
        }
        return ans;
    }

    /**
     * 判断是否是子序列
     * @param s
     * @param t
     * @return
     */
    public boolean isSubsequence(String s, String t) {
        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();
        int i = 0, j = 0;
        while (i < sChars.length && j < tChars.length) {
            if (sChars[i] == tChars[j]) {
                i++;
            }
            j++;
        }
        return i == sChars.length;
    }

    /**
     * 最多去除一个数，使数组变成非递减的
     * @param nums
     * @return
     */
    public boolean checkPossibility(int[] nums) {
        boolean hasDelete = false;
        int i = 0, len = nums.length;
        while (i < len - 1) {
            if (nums[i] > nums[i+1]) {
                if (hasDelete) return false;
                hasDelete = true;
                if (i == 0 || nums[i-1] <= nums[i+1]) {
                    nums[i] = nums[i+1]; //i:前面一个小于自己后面一个，自己变得跟后面一样高
                }else {
                    nums[i+1] = nums[i]; //i+1:比前面两个都矮，自己要长高
                }
            }
            i++;
        }
        return true;
    }

    /**
     * 子数组最大和
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int curMax = 0, max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            curMax = curMax > 0 ? curMax + nums[i] : nums[i];
            max = Math.max(max, curMax);
        }
        return max;
    }

    public static void main(String[] args) {
        int i = "abc".indexOf('b', 0);
        System.out.println(i);
    }
}
