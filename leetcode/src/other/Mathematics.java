package other;

import java.util.Arrays;

public class Mathematics {
    /**
     * 204.生成素数序列
     * tips：埃拉托斯特尼筛法在每次找到一个素数时，将能被素数整除的数排除掉
     * @param n
     * @return
     */
    public int countPrimes(int n) {
        if (n <= 2){
            return 0;
        }
        boolean[] notPrimes = new boolean[n];
        for (int i = 2; i < n; i++) {
            if (!notPrimes[i]) {
                for (int j = 2; j * i < n; j++) {
                    notPrimes[j*i] = true;
                }
            }
        }
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (!notPrimes[i]) {
                count++;
            }
        }
        return count;
    }

    /**
     * 最大公约数
     * @param a
     * @param b
     * @return
     */
    public int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    /**
     * 最小公倍数：两数的乘积除以最大公约数
     * @param a
     * @param b
     * @return
     */
    public int lcm(int a, int b) {
        return (a * b) / gcd(a, b);
    }

    /**
     * 504.进制转换，转成 7 进制
     * @param num
     * @return
     */
    public String convertToBase7(int num) {
        if (num == 0) return "0";
        boolean isNeg = false;
        if (num < 0) {
            num = -num;
            isNeg = true;
        }
        StringBuilder sb = new StringBuilder();
        while (num != 0) {
            char c = (char)(num % 7 + '0');
            sb.append(c);
            num /= 7;
        }
        String s = sb.reverse().toString();
        return isNeg ? "-" + s : s;
    }

    /**
     * 405.进制转换，转换成 16 进制，负数用补码表示
     * @param num
     * @return
     */
    public String toHex(int num) {
        if (num == 0) return "0";
        char[] values = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        StringBuilder stringBuilder = new StringBuilder();
        while (num != 0) {
            stringBuilder.append(values[15&num]);
            num = num >>> 4; //无符号右移
        }
        return stringBuilder.reverse().toString();
    }

    /**
     * 168.进制转换，转换成26进制
     * @param n
     * @return
     */
    public String convertToTitle(int n) {
        StringBuilder sb = new StringBuilder();
        n--;
        while (n != 0) {
            sb.append((char)(n % 26 + 'A'));
            n /= 26;
        }
        return sb.reverse().toString();
    }

    /**
     * 67.二进制字符串加法
     * @param a
     * @param b
     * @return
     */
    public String addBinary(String a, String b) {
        int i = a.length() - 1, j = b.length() - 1, carry = 0;
        StringBuilder str = new StringBuilder();
        while (carry == 1 || i >= 0 || j >= 0) {
            if (i >= 0 && a.charAt(i--) == '1') {
                carry++;
            }
            if (j >= 0 && b.charAt(j--) == '1') {
                carry++;
            }
            str.append(carry % 2);
            carry /= 2;
        }
        return str.reverse().toString();
    }

    /**
     * 10进制字符串加法
     * @param num1
     * @param num2
     * @return
     */
    public String addStrings(String num1, String num2) {
        int i = num1.length() - 1, j = num2.length() - 1;
        int carry = 0;
        StringBuilder sb = new StringBuilder();
        while (carry > 0 || i >= 0 || j >= 0) {
            if (i >= 0){
                carry += num1.charAt(i) - '0';
                i--;
            }
            if (j >= 0) {
                carry += num2.charAt(j) - '0';
                j--;
            }
            sb.append(carry % 10);
            carry /= 10;
        }
        return sb.reverse().toString();
    }

    /**
     * 462.改变数组元素使所有的数组元素都相等
     * tips:不是找平均数，而是中位数
     * @param nums
     * @return
     */
    public int minMoves2(int[] nums) {
        /*int count = 0;
        for (int n: nums) {
            count += n;
        }
        int avg = count / nums.length;
        int move = 0;
        for (int n: nums) {
            move += Math.abs(avg - n);
        }
        return move;*/
        Arrays.sort(nums);
        int move = 0;
        int l = 0, h = nums.length-1;
        while (l <= h) {
            move += nums[h--] - nums[l++];
        }
        return move;
    }

    /**
     * 169.数组中出现次数多于 n / 2 的元素
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        int count = 0, me = 0;
        for (int i = 0; i < nums.length; i++) {
            if (count == 0) {
                me = nums[i];
            }
            if (me == nums[i]) {
                count++;
            }else {
                 count--;
            }
        }
        return me;
    }

    /**
     * 367.平方数，超时了
     * @param num
     * @return
     */
    public boolean isPerfectSquare(int num) {
        int low = 0, high = num, mid;
        while (low <= high) {
            mid = (low + high) / 2;
            int squ = mid * mid;
            if (squ == num){
                return true;
            }else if (squ > num) {
                high = mid - 1;
            }else {
                low = mid + 1;
            }
        }
        return false;
    }
    /**
     * 牛顿法
     * @param num
     * @return
     */
    public boolean isPerfectSquare2(int num) {
        long newton = num;
        while (newton * newton > num) {
            newton = (newton + num / newton) / 2;
        }
        return newton * newton == num;
    }

    /**
     * 还有一种骚方法
     * 平方序列：1,4,9,16,..
     * 间隔：3,5,7,...
     * 间隔为等差数列，使用这个特性可以得到从 1 开始的平方序列。
     * @param num
     * @return
     */
    public boolean isPerfectSquare3(int num) {
        int diff = 1;
        while (num > 0) {
            num -= diff;
            diff += 2;
        }
        return num == 0;
    }

    /**
     * 判断 n 是否是 3 的 x 次方，3的20次方大于 int
     * 这算啥？
     * @param n
     * @return
     */
    public boolean isPowerOfThree(int n) {
        return n > 0 && (Math.pow(3, 19) % n == 0);
    }

    /**
     * 238.除自身以外数组的乘积
     * @param nums
     * @return
     */
    public int[] productExceptSelf(int[] nums) {
        if (nums.length <= 1) return nums;
        int len = nums.length;
        int[] res = new int[len];
        res[0] = 1;
        int left = 1, right = 1;
        for (int i = 1; i < len; i++){
            left *= nums[i-1];
            res[i] = left;
        }
        for (int i = len - 2; i >= 0; i--) {
            right *= nums[i+1];
            res[i] = res[i] * right;
        }
        return res;
    }

    /**
     * 628.找出数组中的乘积最大的三个数
     * @param nums
     * @return
     */
    public int maximumProduct(int[] nums) {
        if (nums == null || nums.length < 3) {
            return 0;
        }
        Arrays.sort(nums);
        int len = nums.length;
        return Math.max(nums[0]*nums[1]*nums[len-1], nums[len-3]*nums[len-2]*nums[len-1]);
    }

    /**
     * 这是真的牛，反正就三个数，我全记下来就是了
     * @param nums
     * @return
     */
    public int maximumProduct2(int[] nums) {
        if (nums == null || nums.length < 3) {
            return 0;
        }
        int min1 = 1001, min2 = 1001;
        int max1 = -1001, max2 = -1001, max3 = -1001;
        for (int num: nums) {
            if (num < min1) {
                min2 = min1;
                min1 = num;
            } else if (num < min2) {
                min2 = num;
            }
            if (num > max1) {
                max3 = max2;
                max2 = max1;
                max1 = num;
            }else if (num > max2) {
                max3 = max2;
                max2 = num;
            }else if (num > max3) {
                max3 = num;
            }
        }
        int m1 = min1 * min2 * max1;
        int m2 = max1 * max2 * max3;
        return Math.max(m1, m2);
    }

    public static void main(String[] args) {
        Mathematics mathematics = new Mathematics();
//        System.out.println(mathematics.convertToTitle(701));
//        System.out.println(mathematics.addBinary("11011", "11101"));
        System.out.println(mathematics.isPerfectSquare2(808201));
    }

}

