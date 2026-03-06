class Solution {
    public boolean checkOnesSegment(String s) {
        int segments = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1' && (i == 0 || s.charAt(i - 1) == '0')) {
                segments++;
            }
        }
        return segments <= 1;
    }
}
