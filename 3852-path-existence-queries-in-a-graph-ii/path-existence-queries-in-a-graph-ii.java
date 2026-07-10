import java.util.*;

public class Solution {
    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        int[] ans = new int[queries.length];
        
        // Edge Case: If maxDiff is 0, nodes can only connect if they have identical values.
        if (maxDiff == 0) {
            for (int i = 0; i < queries.length; i++) {
                int u = queries[i][0];
                int v = queries[i][1];
                if (u == v) {
                    ans[i] = 0;
                } else if (nums[u] == nums[v]) {
                    ans[i] = 1;
                } else {
                    ans[i] = -1;
                }
            }
            return ans;
        }

        // Find the maximum value in nums to size our lookup arrays appropriately
        int maxVal = 0;
        for (int num : nums) {
            maxVal = Math.max(maxVal, num);
        }

        // Track which values actually exist in the input array
        boolean[] exists = new boolean[maxVal + 1];
        for (int num : nums) {
            exists[num] = true;
        }

        // Precompute the nearest existing value to the left of (or at) any index i
        int[] leftExisting = new int[maxVal + 1];
        int lastSeen = -1;
        for (int i = 0; i <= maxVal; i++) {
            if (exists[i]) {
                lastSeen = i;
            }
            leftExisting[i] = lastSeen;
        }

        // Binary Lifting table initialization
        // 2^17 = 131,072 which is greater than our maximum constraint of 100,000
        int LOG = 18; 
        int[][] up = new int[LOG][maxVal + 1];
        for (int i = 0; i < LOG; i++) {
            Arrays.fill(up[i], -1);
        }

        // Base case for binary lifting: 1-step jumps
        // For any value x, the furthest optimal jump we can make towards a larger number 
        // is the largest existing value <= x + maxDiff.
        for (int x = 0; x <= maxVal; x++) {
            if (exists[x]) {
                int target = Math.min(maxVal, x + maxDiff);
                int furthest = leftExisting[target];
                if (furthest > x) {
                    up[0][x] = furthest;
                }
            }
        }

        // Fill out the binary lifting table
        for (int i = 1; i < LOG; i++) {
            for (int x = 0; x <= maxVal; x++) {
                if (up[i - 1][x] != -1) {
                    up[i][x] = up[i - 1][up[i - 1][x]];
                }
            }
        }

        // Process each query
        for (int i = 0; i < queries.length; i++) {
            int u = queries[i][0];
            int v = queries[i][1];

            if (u == v) {
                ans[i] = 0;
                continue;
            }

            int valU = nums[u];
            int valV = nums[v];

            if (valU == valV) {
                ans[i] = 1;
                continue;
            }

            // Always jump from the smaller value to the larger value
            int start = Math.min(valU, valV);
            int end = Math.max(valU, valV);

            ans[i] = getDistance(start, end, up, LOG);
        }

        return ans;
    }

    private int getDistance(int start, int end, int[][] up, int LOG) {
        int jumps = 0;
        int curr = start;

        // Use binary lifting to make the largest possible jumps without overshooting 'end'
        for (int i = LOG - 1; i >= 0; i--) {
            if (up[i][curr] != -1 && up[i][curr] < end) {
                curr = up[i][curr];
                jumps += (1 << i);
            }
        }

        // Make one final jump to see if we can reach or surpass 'end'
        if (up[0][curr] != -1 && up[0][curr] >= end) {
            return jumps + 1;
        }

        return -1; // Unreachable
    }
}