class Solution {
    public boolean uniformArray(int[] nums1) {
        final int inf = Integer.MAX_VALUE;
        int mn = inf;
        
        // Find the minimum odd number
        for (int x : nums1) {
            if (x % 2 == 1) {
                mn = Math.min(mn, x);
            }
        }
        
        // Check if any even number is strictly less than the minimum odd number
        for (int x : nums1) {
            if (x % 2 == 0 && mn != inf && x < mn) {
                return false;
            }
        }
        
        return true;
    }
}