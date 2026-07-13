class Solution {
    public int maxConsistentColumns(int[][] grid, int limit) {
        int m = grid.length;
        int n = grid[0].length;

        boolean[][] ok = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            ok[i][i] = true;
            for (int j = i + 1; j < n; j++) {
                boolean good = true;
                for (int r = 0; r < m; r++) {
                    if (Math.abs(grid[r][j] - grid[r][i]) > limit) {
                        good = false;
                        break;
                    }
                }
                ok[i][j] = good;
            }
        }

        int[] dp = new int[n];
        int ans = 1;

        for (int j = 0; j < n; j++) {
            dp[j] = 1;
            for (int i = 0; i < j; i++) {
                if (ok[i][j]) {
                    dp[j] = Math.max(dp[j], dp[i] + 1);
                }
            }
            ans = Math.max(ans, dp[j]);
        }

        return ans;
    }
}