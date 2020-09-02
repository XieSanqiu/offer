package classic;

/**
 * 0/1背包
 * S(i, j)：当容量为 j 时，前 i 个物品最佳组合所对应的价值
 * vi:第 i 个物品的价值    wi:第 i 个物品的容量
 * 递推公式：
 *           { S(i-1, j)  wi > j
 * S(i, j) = {
 *           { max(S(i-1, j), S(i-1, j-wi) + vi)  wi <= j
 */
public class Bag01 {
    private static int[] values; //每个物品价值
    private static int[] weights; //每个物品容量
    private static Integer[][] results;
    /**
     * 递归版本
     * @param i 物品剩余个数
     * @param c 背包剩余容量
     * @return 当前剩余物品与容量最优解
     */
    public int knapsack1(int i, int c) {
        if (i == 0 || c == 0) {
            return 0;
        }
        if (c < weights[i-1]) {
            return knapsack1(i-1, c);
        }else {
            int r1 = knapsack1(i-1, c);
            int r2 = knapsack1(i-1, c-weights[i-1]) + values[i-1];
            return Math.max(r1, r2);
        }
    }

    /**
     * 自上而下记忆法，记录每步的最优解
     * @param i
     * @param c
     * @return
     */
    public int knapsack2(int i, int c) {
        if (i == 0 || c == 0) {
            return 0;
        }
        if (results[i][c] != null) {
            return results[i][c];
        }
        if (c < weights[i-1]) {
            return knapsack2(i-1, c);
        }else {
            int r1 = knapsack1(i-1, c);
            int r2 = knapsack1(i-1, c-weights[i-1]) + values[i-1];
            int result = Math.max(r1, r2);
            results[i][c] = result;
            return result;
        }
    }

    public static void main(String[] args) {
        Bag01 bag01 = new Bag01();
        values = new int[]{2, 4, 3, 7};
        weights = new int[]{2, 3, 5, 5};
        System.out.println(bag01.knapsack1(4, 10));

        results = new Integer[5][11];
        System.out.println(bag01.knapsack2(4, 10));
    }
}
