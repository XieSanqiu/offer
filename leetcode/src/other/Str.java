package other;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Str {
    /**
     * 242.有效的字母异位词
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i)-'a']++;
            count[t.charAt(i)-'a']--;
        }
        for (int i = 0; i < count.length; i++) {
            if (count[i] != 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 409.最长回文串，艹，题理解错了，这个不算
     * @param s
     * @return
     */
    public int longestPalindrome(String s) {
        if (s == null || s.length() == 0) return 0;
        return helper1(s, 0, s.length()-1);
    }
    private int helper1(String s, int left, int right) {
        if (left > right){
            return 0;
        }else if (left == right) {
            return 1;
        }
        int ans = 0;
        while (left < right) {
            if (s.charAt(left) == s.charAt(right)) {
                ans += 2;
                left++;
                right--;
            }else {
                return Math.max(helper1(s, left+1, right), helper1(s, left, right-1));
            }
        }
        if (left == right) {
            ans += 1;
        }
        return ans;
    }

    /**
     * 统计字符的个数，偶数个全加起来，奇数减一个加上
     * @param s
     * @return
     */
    public int longestPalindrome2(String s) {
        int[] rec = new int[128];
        for (char c: s.toCharArray()) {
            rec[c]++;
        }
        int ans = 0;
        for (int n: rec) {
            if (n % 2 == 0) {
                ans += n;
            }else {
                ans += n-1;
            }
        }
        if (ans < s.length()) {
            ans += 1;
        }
        return ans;
    }

    /**
     * 205.同构字符串
     * @param s
     * @param t
     * @return
     */
    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) return false;
        Map<Character, Character> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);
            if (map.containsKey(c1)) {
                if (c2 != map.get(c1)){
                    return false;
                }
            }else {
                if (map.containsValue(c2)){
                    return false;
                }
                map.put(c1, c2);
            }
        }
        return true;
    }
    public boolean isIsomorphic2(String s, String t) {
        int[] indexOfS = new int[128];
        int[] indexOfT = new int[128];
        for (int i = 0; i < s.length(); i++) {
            char c1 = s.charAt(i), c2 = t.charAt(i);
            if (indexOfS[c1] != indexOfT[c2]) {
                return false;
            }
            indexOfS[c1] = i + 1;
            indexOfT[c2] = i + 1;
        }
        return true;
    }

    /**
     * 647.回文子串
     * @param s
     * @return
     */
    int count = 0;
    public int countSubstrings(String s) {
        if(s == null || s.length() == 0) return 0;
        for(int i = 0; i < s.length(); i++) {
            helper(s, i, i);
            helper(s, i, i+1);
        }
        return count;
    }
    public void helper(String s, int i, int j) {
        while(i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
            System.out.println(i + " " + j);
            count++;
            i--;
            j++;
        }
    }

    /**
     * 9.回文数，要求不能使用额外空间，这个明显不行
     * @param x
     * @return
     */
    public boolean isPalindrome(int x) {
        if (x < 0) return false;
        List<Integer> list = new ArrayList<>();
        while (x > 0) {
            list.add(x % 10);
            x /= 10;
        }
        int[] nums = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            nums[i] = list.get(i);
        }
        int left = 0, right = nums.length - 1;
        while (left < right) {
            if (nums[left] != nums[right]) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    /**
     * 真是妙啊！
     * @param x
     * @return
     */
    public boolean isPalindrome2(int x) {
        if (x == 0) return true;
        if (x < 0 || x % 10 == 0) return false;
        int right = 0;
        while (x > right) {
            right = right * 10 + x % 10;
            x /= 10;
        }
        return x == right || x == right / 10;
    }

    /**
     * 696.统计二进制字符串中连续 1 和连续 0 数量相同的子字符串个数
     * @param s
     * @return
     */
    public int countBinarySubstrings(String s) {
        int preLen = 0, curLen = 1, count = 0;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i-1)) {
                curLen++;
            }else {
                preLen = curLen;
                curLen = 1;
            }
            if (preLen >= curLen) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Str str = new Str();
//        System.out.println(str.countSubstrings("aaa"));
        System.out.println(str.countBinarySubstrings("000111"));
    }
}
