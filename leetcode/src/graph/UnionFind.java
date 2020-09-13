package graph;

public class UnionFind {

    /**
     * 684.冗余连接
     * @param edges
     * @return
     */
    public int[] findRedundantConnection(int[][] edges) {
        UF uf = new UF(edges.length);
        for (int[] edge: edges) {
            int s = edge[0];
            int e = edge[1];
            uf.print();
            if (uf.connect(s, e)) {
                return edge;
            }
            uf.union(s, e);
        }
        return new int[]{-1, -1};
    }

    public static void main(String[] args) {
        int[][] edges = new int[][]{{1,4}, {3,4}, {1,3}, {1,2}, {4,5}};
        UnionFind unionFind = new UnionFind();
        int[] res = unionFind.findRedundantConnection(edges);
        for (int i = 0; i < res.length; i++) {
            System.out.print(res[i] + " ");
        }
        System.out.println();
    }

    private class UF {
        private int[] id;
        UF(int n) {
            id = new int[n+1];
            for (int i = 0; i < id.length; i++) {
                id[i] = i;
            }
        }

        void union(int u, int v) {
            int uid = find(u);
            int vid = find(v);
            if (uid == vid) return;
            for (int i = 0; i < id.length; i++) {
                if (id[i] == vid) {
                    id[i] = uid;
                }
            }
        }

        int find(int p) {
             return id[p];
        }

        boolean connect(int u, int v) {
            return find(u) == find(v);
        }

        void print(){
            for (int i = 0; i < id.length; i++) {
                System.out.print(id[i] + " ");
            }
            System.out.println();
        }
    }
}
