package tree;

import common.ListNode;
import common.TreeNode;

public class Recursion {
    /**
     * 104.二叉树的深度
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    /**
     * 110.判断是否是平衡二叉树
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        return isBalancedHelper(root) != -1;
    }
    private int isBalancedHelper(TreeNode node) {
        if (node == null) return 0;
        int left = isBalancedHelper(node.left);
        int right = isBalancedHelper(node.right);
        if (left == -1 || right == -1 || Math.abs(left - right) > 1) {
            return -1;
        }
        return Math.max(left, right) + 1;
    }

    /**
     * 543.两节点的最长路径
     * @param root
     * @return
     */
    private int max = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        diameterOfBinaryTreeHelper(root);
        return max;
    }
    private int diameterOfBinaryTreeHelper(TreeNode root) {
        if (root == null) return 0;
        int left = diameterOfBinaryTreeHelper(root.left);
        int right = diameterOfBinaryTreeHelper(root.right);
        int diameter = left + right;
        max = Math.max(max, diameter);
        return Math.max(left, right) + 1;
    }

    /**
     * 226.翻转树
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        TreeNode left = root.left;
        root.left = invertTree(root.right);
        root.right = invertTree(left);
        return root;
    }

    /**
     * 617.合并两个二叉树
     * @param t1
     * @param t2
     * @return
     */
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return null;
        }else if (t1 == null) {
            return t2;
        }else if (t2 == null){
            return t1;
        }
        t1.val += t2.val;
        t1.left = mergeTrees(t1.left, t2.left);
        t1.right = mergeTrees(t1.right, t2.right);
        return t1;
    }

    /**
     * 112.判断树中是否有一条路径的和等于 sum
     * @param root
     * @param sum
     * @return
     */
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        if (root.left == null && root.right == null && root.val == sum) {
            return true;
        }
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }

    /**
     * 437.判断树中是否有一条路径（不必必须从根到叶）的和等于 sum
     * @param root
     * @param sum
     * @return
     */
    public int pathSum(TreeNode root, int sum) {
        return pathSumHelper(root, sum, false);
    }
    private int pathSumHelper(TreeNode root, int sum, boolean isBegin) {
        if (root == null) return 0;
        int ans = 0;
        if (root.val == sum) {
            ans += 1;
        }
        if (isBegin) {
            ans += pathSumHelper(root.left, sum - root.val, true);
            ans += pathSumHelper(root.right, sum - root.val, true);
        }else {
            ans += pathSumHelper(root.left, sum - root.val, true);
            ans += pathSumHelper(root.left, sum, false);
            ans += pathSumHelper(root.right, sum - root.val, true);
            ans += pathSumHelper(root.right, sum, false);
        }
        return ans;
    }

    /**
     * 572.判断一棵树是否是另一棵树的子树
     * @param s
     * @param t
     * @return
     */
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null) return false;
        return isEqual(s, t) || isSubtree(s.left, t) || isSubtree(s.right, t);
    }
    private boolean isEqual(TreeNode a, TreeNode b) {
        if (a == null && b == null){
            return true;
        }else if (a == null || b == null){
            return false;
        } else if (a.val != b.val) {
            return false;
        }
        return isEqual(a.left, b.left) && isEqual(a.right, b.right);
    }

    /**
     * 101.判断树是否对称
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isSymmetricHelper(root.left, root.right);
    }
    private boolean isSymmetricHelper(TreeNode a, TreeNode b) {
        if (a == null && b == null){
            return true;
        }else if (a == null || b == null){
            return false;
        } else if (a.val != b.val) {
            return false;
        }
        return isSymmetricHelper(a.left, b.right) && isSymmetricHelper(a.right, b.left);
    }

    /**
     * 111.二叉树的最小深度
     * @param root
     * @return
     */
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        //根节点到叶节点
        if (left == 0 || right == 0){
            return left + right + 1;
        }
        return Math.min(left, right) + 1;
    }

    /**
     * 404.所有左叶子节点的和
     * @param root
     * @return
     */
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) return 0;
        if (root.left != null && root.left.left == null && root.left.right == null) {
            return root.left.val + sumOfLeftLeaves(root.right);
        }
        return sumOfLeftLeaves(root.left) + sumOfLeftLeaves(root.right);
    }

    /**
     *
     * @param root
     * @return
     */
    private int longest = 0;
    public int longestUnivaluePath(TreeNode root) {
        longestUnivaluePathHelper(root);
        return longest;
    }
    private int longestUnivaluePathHelper(TreeNode root) {
        if (root == null) return 0;
        int left = longestUnivaluePathHelper(root.left);
        int right = longestUnivaluePathHelper(root.right);
        int leftPath, rightPath;
        if (root.left != null && root.val == root.left.val) {
            leftPath = left + 1;
        }else {
            leftPath = 0;
        }
        if (root.right != null && root.val == root.right.val) {
            rightPath = right + 1;
        }else {
            rightPath = 0;
        }
        longest = Math.max(longest, leftPath + rightPath);
        return Math.max(leftPath, rightPath);
    }

    /**
     * 337.强盗抢劫Ⅲ
     * 这个效率好像不行，有更优的方法
     * @param root
     * @return
     */
    public int rob(TreeNode root) {
        if (root == null) return 0;
        int val1 = root.val;
        if (root.left != null) {
            val1 += rob(root.left.left) + rob(root.left.right);
        }
        if (root.right != null) {
            val1 += rob(root.right.left) + rob(root.right.right);
        }
        int val2 = rob(root.left) + rob(root.right);
        return Math.max(val1, val2);
    }

    /**
     * 671.二叉树中第二小的节点
     * @param root
     * @return
     */
    public int findSecondMinimumValue(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return -1;
        }
        int left = root.left.val;
        int right = root.right.val;
        if (left == root.val) {
            left = findSecondMinimumValue(root.left);
        }
        if (right == root.val) {
            right = findSecondMinimumValue(root.right);
        }
        if (left != -1 && right != -1) {
            return Math.min(left, right);
        }
        if (left != -1) {
            return left;
        }
        return right;
    }
}
