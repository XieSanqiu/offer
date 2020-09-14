package other;

import java.util.*;

public class HashTable {
    /**
     * 1.两数之和
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int sub = target - nums[i];
            if (map.containsKey(sub)) {
                return new int[]{map.get(sub), i};
            }
            map.put(nums[i], i);
        }
        return null;
    }

    /**
     * 217.数组中是否包含重复数据
     * @param nums
     * @return
     */
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                return true;
            }
            set.add(nums[i]);
        }
        return false;
    }

    /**
     * 594.最长和谐子序列（注意，不是连续子序列）
     * notes：和谐数组是指一个数组里元素的最大值和最小值之间的差别正好是1。
     * @param nums
     * @return
     */
    public int findLHS(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int v = nums[i];
            map.put(v, map.getOrDefault(v, 0) +1);
        }
        int max = 0;
        for (int key: map.keySet()) {
            if (map.containsKey(key+1)) {
                int tmp = map.get(key) + map.get(key+1);
                max = Math.max(max, tmp);
            }
        }
        return max;
    }

    /**
     * 128.最长连续序列，要求时间复杂度为O(n)，超时了
     * @param nums
     * @return
     */
    public int longestConsecutive(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int maxValue = Integer.MIN_VALUE, minValue = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int v = nums[i];
            map.put(v, map.getOrDefault(v, 0) +1);
            maxValue = Math.max(maxValue, v);
            minValue = Math.min(minValue, v);
        }
        int max = 0;
        int k = minValue;
        while (k <= maxValue) {
            if (!map.containsKey(k)) {
                k++;
                continue;
            }
            int curMax = 0;
            while (map.containsKey(k)) {
                curMax += 1;
                k++;
            }
            k++;
            max = Math.max(max, curMax);
        }
        return max;
    }
    public int longestConsecutive2(int[] nums) {
        Map<Integer, Integer> ranges = new HashMap<>();
        int max = 0;
        for (int num : nums) {
            if (ranges.containsKey(num)) {
                continue;
            }
            int left = ranges.getOrDefault(num-1, 0);
            int right = ranges.getOrDefault(num+1, 0);
            int sum = left + right + 1;
            max = Math.max(max, sum);
            //只更新边界，因为中间的用不到了
            if (left > 0) {
                ranges.put(num-left, sum);
            }
            if (right > 0) {
                ranges.put(num+right, sum);
            }
            ranges.put(num, sum);
            System.out.println(ranges);
        }
        return max;
    }

    public static void main(String[] args) {
        HashTable hashTable = new HashTable();
        int[] nums = new int[]{100, 4, 200, 1, 3, 2,5};
        System.out.println(hashTable.longestConsecutive2(nums));
    }
}
