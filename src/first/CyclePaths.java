package first;

import java.util.ArrayList;

public class CyclePaths {
    private static ArrayList<ArrayList<Integer>> paths = new ArrayList<>();

    private static void dfs(int[][] matrix, boolean[][] rem, int start, ArrayList<Integer> path){
        for (int i = 0; i < matrix[0].length; i++){
            if (matrix[start][i] == 1){
                if (rem[start][i]){
                    //只有头和尾都是同一个才算环，路径里又环的不算
                    if (start == path.get(0).intValue()){
                        ArrayList<Integer> ans = new ArrayList<>(path);
                        paths.add(ans);
                    }
                    return;
                }else {
                    rem[start][i] = true;
                    path.add(start);
                    dfs(matrix, rem, i, path);
                    rem[start][i] = false;
                    path.remove(path.size()-1);
                }
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                {0,1,1,0,0,0,0},
                {0,0,0,1,0,0,0},
                {0,0,0,0,0,1,0},
                {0,0,0,0,1,0,0},
                {0,0,1,0,0,0,0},
                {0,0,0,0,1,0,1},
                {1,0,1,0,0,0,0}};
        boolean[][] rem = new boolean[7][7];
        ArrayList<Integer> p = new ArrayList<>();
        dfs(matrix, rem, 2, p);
        for (ArrayList<Integer> list: paths) {
            for (Integer n: list){
                System.out.print(n);
            }
            System.out.println();
        }
    }
}
