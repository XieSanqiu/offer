package stackqueue;

import java.util.Arrays;
import java.util.Stack;

public class Medium {
    /**
     * 739.每日温度
     * @param T
     * @return
     */
    public int[] dailyTemperatures(int[] T) {
        int len = T.length;
        int[] res = new int[len];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < len; i++) {
            while (!stack.isEmpty() && T[i] > T[stack.peek()]) {
                int preIdx = stack.pop();
                res[preIdx] = i - preIdx;
            }
            stack.add(i);
        }
        return res;
    }

    /**
     * 503.下一个更大的元素
     * @param nums
     * @return
     */
    public int[] nextGreaterElements(int[] nums) {
        int len = nums.length;
        int[] res = new int[len];
        Arrays.fill(res, -1);
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < len*2; i++) {
            int num = nums[i % len];
            while (!stack.isEmpty() && num > nums[stack.peek()]) {
                int idx = stack.pop();
                res[idx] = num;
            }
            if (i < len) {
                stack.push(i);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Medium medium = new Medium();
        int[] T = new int[]{73,74,75,71,69,72,76,73};
        int[] res = medium.dailyTemperatures(T);
        for (int i = 0; i < res.length; i++) {
            System.out.print(res[i] + " ");
        }
        System.out.println();
    }
}
