import java.util.*;

class Solution {

    static final int MOD = 1000000007;

    public int[] pathsWithMaxScore(List<String> board) {

        int n = board.size();

        // score[i][j] = Maximum score from (i,j) to S
        int[][] score = new int[n][n];

        // ways[i][j] = Number of maximum-score paths
        int[][] ways = new int[n][n];

        // Initially every cell is unreachable
        for (int i = 0; i < n; i++) {
            Arrays.fill(score[i], -1);
        }

        // Base case: Start cell (S)
        score[n - 1][n - 1] = 0;
        ways[n - 1][n - 1] = 1;

        // Traverse from bottom-right to top-left
        for (int i = n - 1; i >= 0; i--) {

            for (int j = n - 1; j >= 0; j--) {

                char ch = board.get(i).charAt(j);

                // Skip obstacle
                if (ch == 'X')
                    continue;

                // Skip S (already initialized)
                if (i == n - 1 && j == n - 1)
                    continue;

                int best = -1;

                // Check Down
                if (i + 1 < n)
                    best = Math.max(best, score[i + 1][j]);

                // Check Right
                if (j + 1 < n)
                    best = Math.max(best, score[i][j + 1]);

                // Check Diagonal
                if (i + 1 < n && j + 1 < n)
                    best = Math.max(best, score[i + 1][j + 1]);

                // No reachable neighbour
                if (best == -1)
                    continue;

                int count = 0;

                // Add ways from neighbours having maximum score

                if (i + 1 < n && score[i + 1][j] == best) {
                    count = (count + ways[i + 1][j]) % MOD;
                }

                if (j + 1 < n && score[i][j + 1] == best) {
                    count = (count + ways[i][j + 1]) % MOD;
                }

                if (i + 1 < n && j + 1 < n && score[i + 1][j + 1] == best) {
                    count = (count + ways[i + 1][j + 1]) % MOD;
                }

                int value = 0;

                // Add numeric value only if current cell is a digit
                if (Character.isDigit(ch))
                    value = ch - '0';

                score[i][j] = best + value;
                ways[i][j] = count;
            }
        }

        // If end is unreachable
        if (ways[0][0] == 0)
            return new int[] {0, 0};

        return new int[] {score[0][0], ways[0][0]};
    }
}