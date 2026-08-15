class Solution {
    public int longestSubsequence(int[] nums) {
        int xor = 0;

        // XOR of all elements
        for (int x : nums) {
            xor ^= x;
        }

        // If total XOR is non-zero, take all elements
        if (xor != 0) {
            return nums.length;
        }

        // Total XOR is zero.
        // If there is any non-zero element, remove it.
        for (int x : nums) {
            if (x != 0) {
                return nums.length - 1;
            }
        }

        // All elements are zero
        return 0;
    }
}