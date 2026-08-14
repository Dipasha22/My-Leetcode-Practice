class Solution {
    public int maximumLengthSubstring(String s) {

        int[] freq = new int[26];

        int left = 0;
        int maxLength = 0;

        for (int right = 0; right < s.length(); right++) {

            char ch = s.charAt(right);

            freq[ch - 'a']++;

            // If current character occurs more than 2 times
            while (freq[ch - 'a'] > 2) {

                char leftChar = s.charAt(left);

                freq[leftChar - 'a']--;

                left++;
            }

            // Current window length
            int length = right - left + 1;

            maxLength = Math.max(maxLength, length);
        }

        return maxLength;
    }
}