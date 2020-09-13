package graph;

/**
 * 二分图：如果可以用两种颜色对图中的节点进行着色，并且保证相邻的节点颜色不同，那么这个图就是二分图。
 */
public class Bipartite {

    /**
     * 785.判断是否是二分图
     * @param graph
     * @return
     */
    public boolean isBipartite(int[][] graph) {
        int[] colors = new int[graph.length];
        for (int i = 0; i < graph.length; i++) {
            if (colors[i] == 0) {
                if (!dfs(graph, colors, i, 1)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 深度优先搜索
     * @param graph 图矩阵
     * @param colors 每个节点着什么色
     * @param curNode 当前是哪个节点
     * @param curColor 当前节点应该着什么色
     * @return
     */
    private boolean dfs(int[][] graph, int[] colors, int curNode, int curColor) {
        if (colors[curNode] != 0) {
            return colors[curNode] == curColor;
        }
        colors[curNode] = curColor;
        for (int nextNode: graph[curNode]) {
            if (!dfs(graph, colors, nextNode, -curColor)){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {

    }
}
