package dp;

import java.util.Arrays;
import java.util.Comparator;

public class Subsequence {
    /**
     * 300.最长递增子序列
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int len = nums.length;
        int[] dp = new int[len];
        for (int i = 0; i < len; i++) {
            int tmpMax = 0;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    tmpMax = Math.max(tmpMax, dp[j]);
                }
            }
            dp[i] = tmpMax + 1;
        }
        int max = 0;
        for (int n: dp) {
            max = Math.max(max, n);
        }
        return max;
    }

    /**
     * 上面那个时间复杂度为n平方，这个使用二分查找，将时间复杂度降到nlgn，但是感觉这个解法有点怪
     *
     * 定义一个 tails 数组，其中 tails[i] 存储长度为 i + 1 的最长递增子序列的最后一个元素。对于一个元素 x，
     * 如果它大于 tails 数组所有的值，那么把它添加到 tails 后面，表示最长递增子序列长度加 1；
     * 如果 tails[i-1] < x <= tails[i]，那么更新 tails[i] = x。
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int[] tails = new int[nums.length];
        int k = 0;
        for (int n: nums) {
            int idx = bs(tails, k, n);
            tails[idx] = n;
            if (idx == k) {
                k++;
            }
        }
        return k;
    }
    private int bs(int[] tails, int k, int key) {
        int low = 0, high = k-1, mid;
        while (low <= high) {
            mid = (low + high) / 2;
            if (tails[mid] == key) {
                return mid;
            }else if (tails[mid] > key) {
                high = mid - 1;
            }else {
                low = mid + 1;
            }
        }
        return low;
    }

    /**
     * 646.一组整数对能够构成的最长链
     * tips：解法跟上面的一模一样
     * @param pairs
     * @return
     */
    public int findLongestChain(int[][] pairs) {
        if (pairs == null || pairs.length == 0 || pairs[0].length == 0) return 0;
        Arrays.sort(pairs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        int len = pairs.length;
        int[] dp = new int[len];
        for (int i = 0; i < len; i++) {
            int tmpMax = 0;
            for (int j = 0; j < i; j++) {
                if (pairs[i][0] > pairs[j][1]) {
                    tmpMax = Math.max(tmpMax, dp[j]);
                }
            }
            dp[i] = tmpMax + 1;
        }
        int max = 0;
        for (int i = 0; i < len; i++) {
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    /**
     * 上面那个用的是动态规划，这个用的就是贪心了
     * @param pairs
     * @return
     */
    public int findLongestChain2(int[][] pairs) {
        Arrays.sort(pairs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        int count = 0, i = 0, n = pairs.length;
        while (i < n) {
            count++;
            int end = pairs[i][1];
            while (i < n && end >= pairs[i][0]) i++;
        }
        return count;
    }

    /**
     * 376.摆动序列
     * 这方法我真是醉了，没想到可以随意删除元素，就算是想到了，也没想到是这种方法
     * @param nums
     * @return
     */
    public int wiggleMaxLength(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int down = 1, up = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i-1]) {
                up = down + 1;
            }else if (nums[i] < nums[i-1]) {
                down = up + 1;
            }
        }
        return Math.max(up, down);
    }

    /**
     * 1143.最长公共子序列
     * @param text1
     * @param text2
     * @return
     */
    public int longestCommonSubsequence(String text1, String text2) {
        int[][] dp = new int[text1.length()+1][text2.length()+1];
        for (int i = 0; i < text1.length(); i++) {
            for (int j = 0; j < text2.length(); j++) {
                if (text1.charAt(i) == text2.charAt(j)) {
                    dp[i+1][j+1] = dp[i][j] + 1;
                }else {
                    dp[i+1][j+1] = Math.max(dp[i][j+1], dp[i+1][j]);
                }
            }
        }
        return dp[text1.length()][text2.length()];
    }

    public static void main(String[] args) {
        int[] nums = new int[]{10,9,2,5,3,4};
        Subsequence sb = new Subsequence();
        System.out.println(sb.lengthOfLIS2(nums));
    }
}
