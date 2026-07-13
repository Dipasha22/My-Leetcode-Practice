import java.util.*;

class Solution {
    public String[] createGrid(int m, int n, int k) {
        List<char[][]> candidates = new ArrayList<>();

        if (k == 1) {
            candidates.add(new char[][]{{'.'}});
        } else if (k == 2) {
            candidates.add(new char[][]{{'.','.'},{'.','.'}});
        } else if (k == 3) {
            candidates.add(new char[][]{{'.','.','.'},{'.','.','.'}});          // 2x3
            candidates.add(new char[][]{{'.','.'},{'.','.'},{'.','.'}});        // 3x2
        } else if (k == 4) {
            candidates.add(new char[][]{{'.','.','.','.'},{'.','.','.','.'}});  // 2x4
            candidates.add(new char[][]{{'.','.'},{'.','.'},{'.','.'},{'.','.'}}); // 4x2
            candidates.add(new char[][]{{'.','.','#'},{'.','.','.'},{'#','.','.'}}); // 3x3 special
        }

        for (char[][] core : candidates) {
            int a = core.length, b = core[0].length;
            if (a > m || b > n) continue;

            char[][] g = new char[m][n];
            for (char[] row : g) Arrays.fill(row, '#');
            for (int i = 0; i < a; i++)
                for (int j = 0; j < b; j++)
                    g[i][j] = core[i][j];

            // extend forced single-path corridor to bottom-right
            for (int i = a - 1; i < m; i++) g[i][b - 1] = '.';
            for (int j = b - 1; j < n; j++) g[m - 1][j] = '.';

            if (countPaths(g, m, n) == k) {
                String[] ans = new String[m];
                for (int i = 0; i < m; i++) ans[i] = new String(g[i]);
                return ans;
            }
        }
        return new String[0];
    }

    private int countPaths(char[][] g, int m, int n) {
        int[][] dp = new int[m][n];
        if (g[0][0] == '#') return 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (g[i][j] == '#') { dp[i][j] = 0; continue; }
                if (i == 0 && j == 0) { dp[i][j] = 1; continue; }
                int val = 0;
                if (i > 0) val += dp[i - 1][j];
                if (j > 0) val += dp[i][j - 1];
                dp[i][j] = Math.min(val, 10);
            }
        }
        return dp[m - 1][n - 1];
    }
}