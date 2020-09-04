package other;

public class BinarySearch {
    /**
     * 求开方：牛顿法，其实就是二分查找
     * @param x
     * @return
     */
    public int mySqrt(int x) {
        int left = 1, right = x;
        while (left <= right) {
            int mid = (left + right) / 2;
            int sqrt = x / mid;
            if (sqrt == mid) {
                return mid;
            }else if (sqrt > mid) {
                left = mid + 1;
            }else {
                right = mid - 1;
            }
        }
        return right;
    }

    /**
     * 大于给定元素的最小元素
     * @param letters
     * @param target
     * @return
     */
    public char nextGreatestLetter(char[] letters, char target) {
        int low = 0, high = letters.length - 1;
        int mid = 0;
        while (low <= high) {
            mid = (low + high) / 2;
            if (letters[mid] <= target) {
                low = mid + 1;
            }else {
                high = mid - 1;
            }
        }
        return low < letters.length ? letters[low] : letters[0];
    }

    /**
     * 在O(log n)时间里在一个有序数组中找到只出现一次的数
     * @param nums
     * @return
     */
    public int singleNonDuplicate(int[] nums) {
        int low = 0, high = nums.length-1, mid;
        while (low < high) {
            mid = (low + high) / 2;
            if (mid % 2 == 1) mid--;
            if (nums[mid] == nums[mid+1]){
                low = mid + 2;
            }else {
                high = mid;
            }
        }
        return nums[low];
    }

    /**
     * 34.在数组中找到一个元素第一个和最后一个的位置
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {
        int[] res = new int[]{-1, -1};
        int first = findFirst(nums, target);
        if (first == nums.length || nums[first] != target) {
            return res;
        }
        int second = findFirst(nums, target + 1) - 1;
        res[0] = first;
        res[1] = Math.max(first, second);
        return res;
    }
    private int findFirst(int[] nums, int target) {
        int low = 0, high = nums.length, mid;
        while (low < high) {
            mid = (low + high) / 2;
            if (nums[mid] >= target) {
                high = mid;
            }else {
                low = mid + 1;
            }
        }
        return low;
    }

    public static void main(String[] args) {
        BinarySearch bs = new BinarySearch();
        int[] nums = new int[]{5,7,7,8,8,10};
        int[] res = bs.searchRange(nums, 8);
        System.out.println(res[0] + " " + res[1]);

    }
}
