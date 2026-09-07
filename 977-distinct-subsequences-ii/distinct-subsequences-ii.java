class Solution {
    public int distinctSubseqII(String s) {
        final long MOD = 1_000_000_007;

        long dp = 1; // empty subsequence
        long[] last = new long[26];

        for (char c : s.toCharArray()) {
            int index = c - 'a';

            long oldDp = dp;

            // Add current character to every existing subsequence
            dp = (2 * dp) % MOD;

            // Remove duplicates caused by previous occurrence
            dp = (dp - last[index] + MOD) % MOD;

            // Store the old count for this character
            last[index] = oldDp;
        }

        // Remove empty subsequence
        return (int) ((dp - 1 + MOD) % MOD);
    }
}