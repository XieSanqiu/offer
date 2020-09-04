package dp;

public class MatrixPath {
    /**
     * 64.最小路径和
     * @param grid
     * @return
     */
    public int minPathSum(int[][] grid) {
        int rowLen = grid.length, colLen = grid[0].length;
        int[][] dp = new int[rowLen][colLen];
        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                if (i == 0) {
                    if (j == 0) {
                        dp[i][j] = grid[i][j];
                    }else {
                        dp[i][j] = grid[i][j] + dp[i][j-1];
                    }
                } else if (j == 0) {
                    dp[i][j] = grid[i][j] + dp[i-1][j];
                }else {
                    dp[i][j] = grid[i][j] + Math.min(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return dp[rowLen-1][colLen-1];
    }

    /**
     * dp使用一维数组
     * @param grid
     * @return
     */
    public int minPathSum2(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0) return 0;
        int rowLen = grid.length, colLen = grid[0].length;
        int[] dp = new int[colLen];
        for (int i = 0; i < rowLen; i++) {
            for (int j = 0; j < colLen; j++) {
                if (j == 0) {
                    dp[j] = dp[j];
                } else if (i == 0) {
                    dp[j] = dp[j-1];
                } else {
                    dp[j] = Math.min(dp[j-1], dp[j]);
                }
                dp[j] += grid[i][j];
            }
        }
        return dp[colLen-1];
    }

    /**
     * 62.矩阵的总路径数
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {
        boolean[][] matrix = new boolean[m][n];
        return paths(matrix, 0, 0);
    }
    private int paths(boolean[][] matrix, int i, int j) {
        int m = matrix.length, n = matrix[0].length;
        if (i >= m || j >= n) return 0;
        if (i == m - 1 && j == n - 1) return 1;
        if (matrix[i][j] == true) return 0;
        matrix[i][j] = true;
        int count = paths(matrix, i + 1, j) + paths(matrix, i, j+1);
        matrix[i][j] = false;
        return count;
    }

    /**
     * 上面结果是对的，但是超时了
     * 想一想也是，到达一个位置必须从它的上面或者左边过来，那到达这个位置的路径数就是两者之和
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths2(int m, int n) {
        int[] dp = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    dp[j] = 1;
                }else {
                    dp[j] = dp[j-1] + dp[j];
                }
            }
        }
        return dp[n-1];
    }

    public static void main(String[] args) {
        MatrixPath mp = new MatrixPath();
        System.out.println(mp.uniquePaths(7, 3));
    }
}
