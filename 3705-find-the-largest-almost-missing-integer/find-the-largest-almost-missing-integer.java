class Solution {
    public int largestInteger(int[] nums, int k) {
        int[] count = new int[51];

        // Check every subarray of size k
        for (int i = 0; i <= nums.length - k; i++) {

            // To avoid counting the same number twice
            // in one subarray
            boolean[] seen = new boolean[51];

            for (int j = i; j < i + k; j++) {
                int x = nums[j];

                if (!seen[x]) {
                    count[x]++;
                    seen[x] = true;
                }
            }
        }

        // Find the largest number appearing
        // in exactly one subarray
        for (int x = 50; x >= 0; x--) {
            if (count[x] == 1) {
                return x;
            }
        }

        return -1;
    }
}