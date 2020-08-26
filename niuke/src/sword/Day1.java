package sword;

public class Day1 {
    /**
     * 把字符串转为整数
     * @param str
     * @return
     */
    public int StrToInt(String str) {
        if(str == null || str.equals("")){
            return 0;
        }
        char[] chars = str.toCharArray();
        boolean isPositive = true;
        int from = 0;
        if(chars[0] == '+'){
            from++;
        }else if(chars[0] == '-'){
            from++;
            isPositive = false;
        }
        int ans = 0;
        for (int i = from; i < chars.length; i++){
            if(i == from && chars[i] == '0'){
                return 0;
            }
            if(chars[i] < '0' || chars[i] > '9'){
                return 0;
            }
            ans = ans * 10 + chars[i] - '0';
        }
        return isPositive ? ans : -ans;
    }

    /**
     * 构建乘积数组
     * @param A
     * @return
     */
    public int[] multiply(int[] A) {
        if(A == null || A.length < 2){
            return new int[0];
        }
        int length = A.length;
        int[] B = new int[length];
        B[0] = 1;
        int leftMul = A[0];
        for(int i = 1; i < length; i++){
            B[i] = leftMul;
            leftMul *= A[i];
        }
        int rightMul = A[length-1];
        for(int i = length - 2; i >= 0; i--){
            B[i] *= rightMul;
            rightMul *= A[i];
        }
        return B;
    }

    /**
     * 不用加减乘除做加法
     * @param num1
     * @param num2
     * @return
     */
    public int Add(int num1,int num2) {
        while (num2 != 0){
            int tmp = num1 ^ num2;
            num2 = (num1 & num2) << 1;
            num1 = tmp;
        }
        return num1;
    }

    /**
     * 求1+2+3+...+n
     * @param n
     * @return
     */
    public int Sum_Solution(int n) {
        int sum = n;
        boolean tmp = (n > 0) && ((sum += Sum_Solution(n-1)) > 0);
        return sum;
    }

    /**
     * f[1] = 0
     * f[2] = (f[1] + m) % 2
     * f[3] = (f[2] + m) % 3
     * ...
     * f[n] = (f[n-1] + m) % n
     * @param n
     * @param m
     * @return
     */
    public int LastRemaining_Solution(int n, int m) {
        if(n <= 0) return -1;
        int index = 0;
        for(int i = 2; i <= n; i++){
            index = (index + m) % i;
        }
        return index;
    }

    public static void main(String[] args) {
        Day1 day1 = new Day1();
        System.out.print(day1.Add(13, 7));
    }
}
