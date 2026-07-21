class Solution {
    public int maxActiveSectionsAfterTrade(String s) {
        int initialOnes = 0;
        for (char c : s.toCharArray()) {
            if (c == '1') initialOnes++;
        }

        // Augment the string
        String t = "1" + s + "1";

        // Run-Length Encoding
        ArrayList<Character> chars = new ArrayList<>();
        ArrayList<Integer> lengths = new ArrayList<>();

        int i = 0;
        while (i < t.length()) {
            char ch = t.charAt(i);
            int j = i;
            while (j < t.length() && t.charAt(j) == ch) {
                j++;
            }
            chars.add(ch);
            lengths.add(j - i);
            i = j;
        }

        int ans = initialOnes;

        // Check every 0-1-0 pattern
        for (int k = 1; k < chars.size() - 1; k++) {
            if (chars.get(k) == '1'
                    && chars.get(k - 1) == '0'
                    && chars.get(k + 1) == '0') {

                int gain = lengths.get(k - 1) + lengths.get(k + 1);
                ans = Math.max(ans, initialOnes + gain);
            }
        }

        return ans;
    }
}