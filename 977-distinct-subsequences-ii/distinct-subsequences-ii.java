class Solution {
    public int distinctSubseqII(String s) {
        int MOD = 1_000_000_007;
        
        // Tracks the number of unique subsequences ending in each letter ('a' through 'z')
        int[] endWith = new int[26];
        
        // Total count of distinct subsequences found so far
        int currentTotal = 0;
        
        for (char c : s.toCharArray()) {
            int index = c - 'a';
            
            // The new subsequences we can form ending with character `c` is:
            // (all existing unique subsequences) + (the single character `c` itself)
            // minus whatever subsequences we previously counted ending in `c`.
            int newAdded = (currentTotal + 1 - endWith[index] + MOD) % MOD;
            
            // Update the total unique subsequences
            currentTotal = (currentTotal + newAdded) % MOD;
            
            // Update the record for subsequences ending in character `c`
            endWith[index] = (endWith[index] + newAdded) % MOD;
        }
        
        return currentTotal;
    }
}
