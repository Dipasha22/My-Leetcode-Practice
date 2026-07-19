class Solution {
    public String smallestSubsequence(String s) {
        int[] freq = new int[26];
        boolean[] used = new boolean[26];

        // count frequency
        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }

        StringBuilder stack = new StringBuilder();

        for (char ch : s.toCharArray()) {
            int idx = ch - 'a';

            // current character is being processed
            freq[idx]--;

            // already included
            if (used[idx]) continue;

            // maintain increasing lexicographical order
            while (stack.length() > 0) {
                char top = stack.charAt(stack.length() - 1);

                if (top > ch && freq[top - 'a'] > 0) {
                    used[top - 'a'] = false;
                    stack.deleteCharAt(stack.length() - 1);
                } else {
                    break;
                }
            }

            stack.append(ch);
            used[idx] = true;
        }

        return stack.toString();
    }
}