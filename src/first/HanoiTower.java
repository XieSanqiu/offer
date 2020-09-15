package first;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HanoiTower {
    static int m =0;//标记移动次数
    //实现移动的函数
    public void move(int disks,char N,char M) {
        System.out.println("第" + (++m) +" 次移动 : " +" 把 "+ disks+" 号圆盘从 " + N +" ->移到->  " + M);
    }
    //递归实现汉诺塔的函数
    public void hanoi(int n,char A,char B,char C) {
        if(n == 1) {//圆盘只有一个时，只需将其从A塔移到C塔
            move(1, A, C);//将编b号为1的圆盘从A移到C
        }else {//否则
            hanoi(n - 1, A, C, B);//递归，把A塔上编号1~n-1的圆盘移到B上，以C为辅助塔
            move(n, A, C);//把A塔上编号为n的圆盘移到C上
            hanoi(n - 1, B, A, C);//递归，把B塔上编号1~n-1的圆盘移到C上，以A为辅助塔
        }
    }


    /**
     * 将 A 上的所有盘子，借助 B，移动到C 上
     * @param A 原柱子
     * @param B 辅助柱子
     * @param C 目标柱子
     */
    public void hanota(List<Integer> A, List<Integer> B, List<Integer> C) {
        movePlate(A.size(), A, B, C);
    }

    private void movePlate(int num, List<Integer> original, List<Integer> auxiliary, List<Integer> target) {
        if (num == 1) {    // 只剩一个盘子时，直接移动即可
            target.add(original.remove(original.size() - 1));
            return;
        }
        movePlate(num - 1, original, target, auxiliary);   // 将 size-1 个盘子，从 original 移动到 auxiliary
        target.add(original.remove(original.size() - 1));   // 将 第size个盘子，从 original 移动到 target
        movePlate(num - 1, auxiliary, original, target);   // 将 size-1 个盘子，从 auxiliary 移动到 target
    }

    public static void main(String[] args) {
        HanoiTower hanoiTower = new HanoiTower();
        char A = 'A';
        char B = 'B';
        char C = 'C';
        hanoiTower.hanoi(3, A, B, C);
        System.out.println(">>移动了" + m + "次，把A上的圆盘都移动到了C上");

        ArrayList<Integer> listA = new ArrayList<>();
        listA.addAll(Arrays.asList(2,1,0));
        ArrayList<Integer> listB = new ArrayList<>();
        ArrayList<Integer> listC = new ArrayList<>();
        hanoiTower.hanota(listA, listB, listC);
        System.out.println(listC);
    }
}
