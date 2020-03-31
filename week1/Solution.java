import java.util.Arrays;
import java.util.HashMap;

class Solution {

    // leetcode 133
    private HashMap<Node, Node> visited = new HashMap<>();
    private static final int[][] dirs = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }
        if (visited.containsKey(node)) {
            return visited.get(node);
        }

        Node n = new Node(node.val, new ArralyList<>());
        visited.put(node, n);
        for (Node child : node.neighbors) {
            n.neighbors.add(cloneGraph(child));
        }
        return n;
    }

    //leetcode 200
    void dfs(char[][] grid, int r, int c) {
        int nr = grid.length;
        int nc = grid[0].length;

        if (r < 0 || c < 0 || r >= nr || c >= nc || grid[r][c] == '0') {
            return;
        }

        grid[r][c] = '0';
        dfs(grid, r - 1, c);
        dfs(grid, r + 1, c);
        dfs(grid, r, c - 1);
        dfs(grid, r, c + 1);
    }

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int nr = grid.length;
        int nc = grid[0].length;
        int num_islands = 0;
        for (int r = 0; r < nr; ++r) {
            for (int c = 0; c < nc; ++c) {
                if (grid[r][c] == '1') {
                    num_islands++;
                    dfs(grid, r, c);
                }
            }
        }
        return num_islands;
    }

    //leetcode 329 矩阵中的最长递增路径
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        int nr = matrix.length;
        int nc = matrix[0].length;
        int max_len = 0;
        for (int r = 0; r < nr; ++r) {
            for (int c = 0; c < nc; ++c) {
                max_len = Math.max(max_len, dfs(matrix, r, c));
            }
        }
        return max_len;

    }
    
    int dfs(int[][] matrix, int r, int c) {
        int ans = 0;
        for (int[] d : dirs) {
            int x = i + d[0], y = j + d[1];
            if (0 <= x && x < m && 0 <= y && y < n && matrix[x][y] > matrix[i][j]) {
                ans = Math.max(ans, dfs(matrix, x, y));
            }
        }
        return ans;
    }

    //leetcode 37 解数独
}

class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {
    }

    public Node(int _val, List<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
};