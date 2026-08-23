class Solution {
    public boolean sumGame(String num) {
        int n = num.length();
        int half = n / 2;

        int leftQ = 0;
        int rightQ = 0;

        int leftSum = 0;
        int rightSum = 0;

        for (int i = 0; i < half; i++) {
            char c = num.charAt(i);

            if (c == '?') {
                leftQ++;
            } else {
                leftSum += c - '0';
            }
        }

        for (int i = half; i < n; i++) {
            char c = num.charAt(i);

            if (c == '?') {
                rightQ++;
            } else {
                rightSum += c - '0';
            }
        }

        // Odd difference in number of '?' means Alice can force a win.
        if ((leftQ - rightQ) % 2 != 0) {
            return true;
        }

        // Difference that the extra '?' can compensate for.
        int possibleDifference = (rightQ - leftQ) / 2 * 9;

        // If fixed sums can be exactly balanced, Bob wins.
        return leftSum - rightSum != possibleDifference;
    }
}