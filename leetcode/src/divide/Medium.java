package divide;

import common.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Medium {
    /**
     * 241.添加括号的方法数
     * @param input
     * @return
     */
    public List<Integer> diffWaysToCompute(String input) {
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < input.length(); i++){
            char c = input.charAt(i);
            if (c == '+' || c == '-' || c == '*') {
                List<Integer> left = diffWaysToCompute(input.substring(0, i));
                List<Integer> right = diffWaysToCompute(input.substring(i+1));
                for (Integer l: left) {
                    for (Integer r: right) {
                        switch (c) {
                            case '+':
                                res.add(l + r);
                                break;
                            case '-':
                                res.add(l - r);
                                break;
                            case '*':
                                res.add(l * r);
                                break;
                        }
                    }
                }
             }
        }
        if (res.size() == 0) {
            res.add(Integer.valueOf(input));
        }
        return res;
    }

    public List<TreeNode> generateTrees(int n) {
        if (n <= 0){
            return new ArrayList<>();
        }
        return generateTreesHelper(1,n);
    }
    private List<TreeNode> generateTreesHelper(int start, int end) {
        List<TreeNode> res = new ArrayList<>();
        if (start > end) {
            res.add(null);
            return res;
        }else if (start == end) {
            res.add(new TreeNode(start));
            return res;
        }
        for (int i = start; i <= end; i++) {
            List<TreeNode> left = generateTreesHelper(start, i-1);
            List<TreeNode> right = generateTreesHelper(i+1, end);
            for (TreeNode l: left) {
                for (TreeNode r: right) {
                    TreeNode root = new TreeNode(i);
                    root.left = l;
                    root.right = r;
                    res.add(root);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Medium medium = new Medium();
        System.out.println(medium.diffWaysToCompute("2-1-1"));
    }

}
