class Solution {
    public void moveZeroes(int[] nums) {
        int j = 0;  // Next position for non-zero
        
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[j++] = nums[i];
            }
        }
        
        // Fill rest with 0s
        for (int i = j; i < nums.length; i++) {
            nums[i] = 0;
        }
    }
}
