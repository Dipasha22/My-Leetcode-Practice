class Solution {
    public int minimumDeletions(int[] nums) {
        int n = nums.length;
        int minIndex = 0;
        int maxIndex = 0;
        
        // Step 1: Find indices of minimum and maximum elements
        for (int i = 0; i < n; i++) {
            if (nums[i] < nums[minIndex]) {
                minIndex = i;
            }
            if (nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }
        }
        
        // Ensure left is the smaller index and right is the larger index
        int left = Math.min(minIndex, maxIndex);
        int right = Math.max(minIndex, maxIndex);
        
        // Option 1: Remove from the front only
        int deleteFromFront = right + 1;
        
        // Option 2: Remove from the back only
        int deleteFromBack = n - left;
        
        // Option 3: Remove from both front and back
        int deleteBothEnds = (left + 1) + (n - right);
        
        // Return the minimum of the three options
        return Math.min(Math.min(deleteFromFront, deleteFromBack), deleteBothEnds);
    }
}
