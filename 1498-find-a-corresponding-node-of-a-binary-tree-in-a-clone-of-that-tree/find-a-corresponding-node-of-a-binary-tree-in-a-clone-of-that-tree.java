/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

class Solution {
    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
         if (original == null) return null;
        
        // If target found in original, return corresponding cloned node
        if (original == target) return cloned;
        
        // Search in left subtree
        TreeNode left = getTargetCopy(original.left, cloned.left, target);
        if (left != null) return left;
        
        // Search in right subtree
        return getTargetCopy(original.right, cloned.right, target);
        
    }
}