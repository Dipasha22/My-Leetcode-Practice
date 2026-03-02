class Solution {
    public int minSwaps(int[][] grid) {
        int n = grid.length;
        int[] zeros = new int[n];
        
        // Count trailing zeros for each row (from right)
        for (int i = 0; i < n; i++) {
            zeros[i] = 0;
            for (int j = n - 1; j >= 0; j--) {
                if (grid[i][j] == 0) {
                    zeros[i]++;
                } else {
                    break;
                }
            }
        }
        
        int swaps = 0;
        
        for (int pos = 0; pos < n; pos++) {
            int req = n - 1 - pos;  // Required trailing zeros
            
            // Find first suitable row at/after pos
            int target = -1;
            for (int i = pos; i < n; i++) {
                if (zeros[i] >= req) {
                    target = i;
                    break;
                }
            }
            
            if (target == -1) return -1;
            
            // Count swaps to bubble target to pos
            swaps += target - pos;
            
            // Swap in zeros array
            int temp = zeros[target];
            for (int k = target; k > pos; k--) {
                zeros[k] = zeros[k - 1];
            }
            zeros[pos] = temp;
        }
        
        return swaps;
    }
}

