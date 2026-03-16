public class Solution {
    public boolean wordPattern(String pattern, String s) {
        // Split string into words
        String[] words = s.split(" ");
        
        // Check length match
        if (pattern.length() != words.length) {
            return false;
        }
        
        // Arrays for bidirectional mapping (26 lowercase letters)
        String[] charToWord = new String[26];  // char -> word
        int[] wordToChar = new int[26];        // word index -> char index
        int wordCount = 0;                     // unique word counter
        
        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            String word = words[i];
            
            int charIndex = c - 'a';  // 0-25 for a-z
            
            // Check if char already mapped
            if (charToWord[charIndex] != null) {
                // Must map to same word
                if (!charToWord[charIndex].equals(word)) {
                    return false;
                }
            } else {
                // New char mapping
                charToWord[charIndex] = word;
                
                // Check if word already used by different char
                // Find if word already mapped to some char index
                boolean wordUsed = false;
                for (int j = 0; j < wordCount; j++) {
                    if (word.equals(charToWord[wordToChar[j]])) {
                        wordUsed = true;
                        break;
                    }
                }
                
                if (wordUsed) {
                    return false;
                }
                
                // Map word to this char index
                if (wordCount < 26) {
                    wordToChar[wordCount] = charIndex;
                    wordCount++;
                }
            }
        }
        
        return true;
    }
    
    // public static void main(String[] args) {
    //     Solution sol = new Solution();
        
    //     // Test cases
    //     System.out.println(sol.wordPattern("abba", "dog cat cat dog"));  // true
    //     System.out.println(sol.wordPattern("abba", "dog cat cat fish")); // false
    //     System.out.println(sol.wordPattern("aaaa", "dog cat cat dog"));  // false
    // }
}
