class Solution {
    public char findKthBit(int n, int k) {
        if (n == 1) return '0';
        
        long len = (1L << (n - 1)) - 1;  // Length of S(n-1)
        long mid = len + 1;              // Position of middle '1'
        
        if (k == mid) return '1';
        else if (k < mid) return findKthBit(n - 1, k);
        else {
            // Map suffix position to prefix: 2*len+2-k
            char prefixBit = findKthBit(n - 1, (int)(2 * len + 2 - k));
            return prefixBit == '0' ? '1' : '0';
        }
    }
}
