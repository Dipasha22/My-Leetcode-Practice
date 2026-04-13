/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    int solve(TreeNode root, int val) {
        
        // Base case
        if (root == null) {
            return 0;
        }

        // Update value
        val = (2 * val) + root.val;

        // If leaf node
        if (root.left == null && root.right == null) {
            return val;
        }

        // Recur for left and right
        return solve(root.left, val) + solve(root.right, val);
    }

    public int sumRootToLeaf(TreeNode root) {
        return solve(root, 0);
    }
}