package graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TopologicalSort {
    /**
     * 207.课程表
     * tips：只要判断是否有环就行了，其他不用想那么多
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<Integer>[] graphic = new List[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graphic[i] = new ArrayList<>();
        }
        for (int[] pre: prerequisites) {
            graphic[pre[0]].add(pre[1]);
        }
        boolean[] globalMarked = new boolean[numCourses];
        boolean[] localMarked = new boolean[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (hasCycle(globalMarked, localMarked, graphic, i)) {
                return false;
            }
        }
        return true;
    }
    private boolean hasCycle(boolean[] globalMarket, boolean[] localMarked,
                             List<Integer>[] graphic, int curNode) {
        if (localMarked[curNode]) {
            return true;
        }
        if (globalMarket[curNode]) {
            return false;
        }
        globalMarket[curNode] = true;
        localMarked[curNode] = true;
        for (int nextNode: graphic[curNode]) {
            if (hasCycle(globalMarket, localMarked, graphic, nextNode)) {
                return true;
            }
        }
        localMarked[curNode] = false;
        return false;
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<Integer>[] graphic = new List[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graphic[i] = new ArrayList<>();
        }
        for (int[] pre: prerequisites) {
            graphic[pre[0]].add(pre[1]);
        }
        Stack<Integer> postOrder = new Stack<>();
        boolean[] globalMarket = new boolean[numCourses];
        boolean[] localMarket = new boolean[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (hasCycle(globalMarket, localMarket, graphic, i, postOrder)) {
                return new int[0];
            }
        }
        System.out.println(postOrder);
        int[] orders = new int[numCourses];
        for (int i = numCourses - 1; i >= 0; i--) {
            orders[i] = postOrder.pop();
        }
        return orders;
    }
    private boolean hasCycle(boolean[] globalMarket, boolean[] localMarket,
                             List<Integer>[] graphic, int curNode, Stack<Integer> postOrder) {
        if (localMarket[curNode]) return true;
        if (globalMarket[curNode]) return false;
        globalMarket[curNode] = true;
        localMarket[curNode] = true;
        for (int nextNode: graphic[curNode]) {
            if (hasCycle(globalMarket, localMarket, graphic, nextNode, postOrder)) {
                return true;
            }
        }
        localMarket[curNode] = false;
        postOrder.push(curNode);
        return false;
    }
    public static void main(String[] args) {

    }
}
