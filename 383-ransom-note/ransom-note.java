class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {

        int[] count = new int[26];

        // Count letters in magazine
        for(int i = 0; i < magazine.length(); i++) {
            char c = magazine.charAt(i);
            count[c - 'a']++;
        }

        // Check ransomNote letters
        for(int i = 0; i < ransomNote.length(); i++) {
            char c = ransomNote.charAt(i);
            count[c - 'a']--;

            if(count[c - 'a'] < 0) {
                return false;
            }
        }

        return true;
    }
}