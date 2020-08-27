package sword;

import java.util.Arrays;

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
}
