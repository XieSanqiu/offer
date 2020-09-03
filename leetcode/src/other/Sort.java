package other;

import java.util.*;

public class Sort {
    /**
     * 第 K 大个数 ： 堆排序
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest1(int[] nums, int k) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        for (int n: nums) {
            priorityQueue.add(n);
            if (priorityQueue.size() > k) {
                priorityQueue.poll();
            }
        }
        return priorityQueue.peek();
    }

    /**
     * 第 K 大个数 ： 快速排序
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest2(int[] nums, int k) {
        int left = 0, right = nums.length - 1;
        k = nums.length - k;
        while (left < right) {
            int m = partition(nums, left, right);
            if (m == k) {
                return nums[k];
            }else if (m > k){
                right = m - 1;
            }else {
                left = m + 1;
            }
        }
        return nums[k];
    }
    private int partition(int[] nums, int left, int right) {
        int p = nums[left], k = left;
        while (left < right) {
            while (left < right && nums[right] >= p) right--;
            while (left < right && nums[left] <= p) left++;
            if (left == right) {
                swap(nums, k, left);
            }else {
                swap(nums, left, right);
            }
        }
        return left;
    }
    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

    /**
     * 前 k 个最频繁的数
     * @param nums
     * @param k
     * @return
     */
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> count = new HashMap<>();
        for (int n: nums) {
            count.put(n, count.getOrDefault(n, 0) + 1);
        }
        int len = nums.length;
        ArrayList<Integer>[] buckets = new ArrayList[len+1];
        for (int c: count.keySet()) {
            int freq = count.get(c);
            if (buckets[freq] == null) {
                buckets[freq] = new ArrayList<>();
            }
            buckets[freq].add(c);
        }
        int[] res = new int[k];
        int m = 0;
        for (int i = buckets.length - 1; i >= 0 && k > 0; i--) {
            if (buckets[i] == null) continue;
            if (buckets[i].size() <= k) {
                for (int n: buckets[i]) {
                    res[m++] = n;
                }
            }else {
                for (int j = 0; j < k; j++) {
                    res[m++] = buckets[i].get(j);
                }
            }
            k -= buckets[i].size();
        }
        return res;
    }

    /**
     * 荷兰国旗问题
     * @param nums
     */
    public void sortColors(int[] nums) {
        int zero = 0, one = 0, two = nums.length-1;
        while (one <= two) {
            if (nums[one] == 0) {
                swap(nums, zero++, one++);
            }else if (nums[one] == 2) {
                swap(nums, one, two--);
            }else {
                one++;
            }
        }
    }

    public static void main(String[] args) {
        Sort sort = new Sort();
        int[] nums = new int[]{3,2,3,1,2,4,5,5,6};
        System.out.println(sort.findKthLargest2(nums, 4));

        int[] nums2 = new int[]{1,1,1,2,2,3};
        int[] res = sort.topKFrequent(nums2, 2);
        for (int i = 0; i < res.length; i++){
            System.out.print(res[i] + " ");
        }
        System.out.println();

    }
}