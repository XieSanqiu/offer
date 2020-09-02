package other;

public class TwoPointer {
    /**
     * Easy
     * 是否存在两个数的平方和为c
     * @param c 非负
     * @return
     */
    public boolean judgeSquareSum(int c) {
        if (c < 0){
            return false;
        }else if (c == 0) {
            return true;
        }
        int left = 0, right = (int) Math.sqrt(c);
        while (left <= right) {
            int sum = left * left + right * right;
            if (sum == c) {
                return true;
            }else if (sum > c) {
                right--;
            }else {
                left++;
            }
        }
        return false;
    }

    /**
     * Easy
     * 判断一个字符串是否是回文字符串，最多可以删除一个字符
     * @param s
     * @return
     */
    public boolean validPalindrome(String s) {
        if (s == null || s.length() == 0) return false;
        char[] chars = s.toCharArray();
        int left = 0, right = chars.length-1;
        while (left < right) {
            if (chars[left] == chars[right]) {
                left++;
                right--;
            }else {
                return validPalindromeHelper(chars, left, right-1) ||
                        validPalindromeHelper(chars, left+1, right);
            }
        }
        return true;
    }
    private boolean validPalindromeHelper(char[] chars, int left, int right) {
        while (left < right) {
            if (chars[left] != chars[right]){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        String str = "abc";
        TwoPointer twoPointer = new TwoPointer();
        System.out.println(twoPointer.validPalindrome(str));
        String s1 = new String("11");
        String s2 = s1.intern();
        System.out.println(s1 == s2);
    }
}
