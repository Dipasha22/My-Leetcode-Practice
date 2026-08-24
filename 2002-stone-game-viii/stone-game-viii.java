class Solution {
    public int stoneGameVIII(int[] stones) {
        int n = stones.length;

        int[] prefix = new int[n];
        prefix[0] = stones[0];

        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1] + stones[i];
        }

        // If Alice takes all stones initially
        int dp = prefix[n - 1];

        // Try all possible stopping points
        for (int i = n - 2; i >= 1; i--) {
            dp = Math.max(dp, prefix[i] - dp);
        }

        return dp;
    }
}