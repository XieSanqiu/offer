package dp;

import java.util.Arrays;

public class BreakInteger {
    /**
     * 拆分一个整数，使得他们的积最大
     * @param n
     * @return
     */
    public int integerBreak(int n) {
        if (n <= 3) {
            return n-1;
        }else if (n == 4) {
            return 4;
        }else {
            if (n % 3 == 0) {
                return pow3(n / 3);
            }else if (n % 3 == 1) {
                return pow3(n/3-1) * 4;
            }else {
                return pow3(n/3) * 2;
            }
        }
    }
    private int pow3(int n) {
        int mul = 1;
        for (int i = 0; i < n; i++) {
            mul *= 3;
        }
        return mul;
    }

    /**
     * 上面没有用到动态规划，其实也用不到，但是还是学习一下
     * @param n
     * @return
     */
    public int integerBreak2(int n) {
        int[] dp = new int[n+1];
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                dp[i] = Math.max(dp[i], Math.max(j * dp[i-j], j * (i - j)));
            }
        }
        return dp[n];
    }

    /**
     * 按平方数分割整数：他妈的，真是又简单又难，自己完全想不到
     * dp[0] = 0
     * dp[1] = dp[0]+1 = 1
     * dp[2] = dp[1]+1 = 2
     * dp[3] = dp[2]+1 = 3
     * dp[4] = Min{ dp[4-1*1]+1, dp[4-2*2]+1 }
     *       = Min{ dp[3]+1, dp[0]+1 }
     *       = 1
     * dp[5] = Min{ dp[5-1*1]+1, dp[5-2*2]+1 }
     *       = Min{ dp[4]+1, dp[1]+1 }
     *       = 2
     * 						.
     * 						.
     * 						.
     * dp[13] = Min{ dp[13-1*1]+1, dp[13-2*2]+1, dp[13-3*3]+1 }
     *        = Min{ dp[12]+1, dp[9]+1, dp[4]+1 }
     *        = 2
     * 						.
     * 						.
     * 						.
     * dp[n] = Min{ dp[n - i*i] + 1 },  n - i*i >=0 && i >= 1
     * @param n
     * @return
     */
    public int numSquares(int n) {
        int[] dp = new int[n+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i-j*j] + 1);
            }
        }
        return dp[n];
    }

    /**
     * 数字能转为多少种字符串
     * @param s
     * @return
     */
    public int numDecodings(String s) {
        char[] numbers = s.toCharArray();
        int[] dp = new int[numbers.length + 1];
        dp[0] = 1;
        dp[1] = numbers[0] == '0' ? 0 : 1;
        for (int i = 2; i <= numbers.length; i++) {
            int n = numbers[i-1] - '0';
            int pre = numbers[i-2] - '0';
            if (n != 0) {
                dp[i] = dp[i-1];
            }
            if (pre == 0) continue;
            if (pre * 10 + n <= 26) {
                dp[i] += dp[i-2];
            }
        }
        return dp[numbers.length];
    }

    public static void main(String[] args) {
        BreakInteger bi = new BreakInteger();
        System.out.println(bi.numSquares(12));
    }
}
