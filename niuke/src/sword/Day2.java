package sword;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Day2 {
    /**
     * 扑克牌顺子
     * @param numbers
     * @return
     */
    public boolean isContinuous(int [] numbers) {
        if(numbers == null || numbers.length != 5){
            return false;
        }
        Arrays.sort(numbers);
        int dif = 0;
        for(int i = 0; i < numbers.length-1; i++){
            if(numbers[i] == 0){
                continue;
            }
            if(numbers[i] == numbers[i+1]){
                return false;
            }
            dif += numbers[i+1] - numbers[i];
        }
        return dif <=  4;
    }

    /**
     * 其实只要满足两个条件
     * 1：除0外没有重复数字
     * 2：max - min < 5
     * 不用sort，用bit
     * @param numbers
     * @return
     */
    public boolean isContinuous2(int [] numbers) {
        if(numbers == null || numbers.length != 5){
            return false;
        }
        int flag = 0;
        int max = -1;
        int min = 14;
        for(int i = 0; i < numbers.length; i++){
            if(numbers[i] == 0){ //过滤0
                continue;
            }
            if(((flag >> numbers[i]) & 1) == 1){ //不能出现重复
                return false;
            }
            flag |= 1 << numbers[i];
            if(numbers[i] > max) max = numbers[i];
            if(numbers[i] < min) min = numbers[i];
            if(max - min >= 5) {
                return false;
            }
        }
        return true;
    }

    /**
     * 使用大顶堆，PriorityQueue默认是小顶堆，但是这个效率好像不行
     * @param num
     * @param size
     * @return
     */
    public ArrayList<Integer> maxInWindows(int [] num, int size) {
        ArrayList<Integer> res = new ArrayList<>();
        if(num == null || num.length < size || size <= 0){
            return res;
        }
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((o1, o2) -> o2-o1);
        for(int i = 0; i < size - 1; i++){
            priorityQueue.add(num[i]);
        }
        for(int i = size - 1; i < num.length; i++){
            priorityQueue.add(num[i]);
            res.add(priorityQueue.peek());
            priorityQueue.remove(num[i-size+1]);
        }
        return res;
    }

    /**
     * 左旋转字符串
     * @param str
     * @param n
     * @return
     */
    public String LeftRotateString(String str,int n) {
        if(str == null || str.length() <= 1){
            return str;
        }
        int rotate = n % str.length();
        return str.substring(rotate, str.length()) + str.substring(0, rotate);
    }

    /**
     * 反转句子
     * 想考察的主要是先翻转单词，再翻转整个句子
     * @param str
     * @return
     */
    public String ReverseSentence(String str) {
        char[] chars = str.toCharArray();
        int ws = 0, we = 0;
        while (we <= chars.length){
            if(we == chars.length || chars[we] == ' '){
                reverse(chars, ws, we-1);
                ws = we + 1;
            }
            we++;
        }
        reverse(chars, 0, chars.length-1);
        return new String(chars);
    }
    private void reverse(char[] chars, int i, int j){
        while (i < j){
            swap(chars, i--, j++);
        }
    }
    private void swap(char[] chars, int i, int j){
        char tmp = chars[i];
        chars[i] = chars[j];
        chars[j] = tmp;
    }

    public static void main(String[] args) {
        Day2 day2 = new Day2();
        System.out.println(day2.maxInWindows(new int[]{2, 3, 4, 2, 6, 2, 5, 1}, 3));
    }

}
