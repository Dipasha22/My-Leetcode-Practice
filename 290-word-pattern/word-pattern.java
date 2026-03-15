import java.util.HashMap;

class Solution {
    public boolean wordPattern(String pattern, String s) {

        String[] words = s.split(" ");
        if(pattern.length() != words.length) return false;

        HashMap<Character, String> map1 = new HashMap<>();
        HashMap<String, Character> map2 = new HashMap<>();

        for(int i = 0; i < pattern.length(); i++) {

            char p = pattern.charAt(i);
            String w = words[i];

            if(map1.containsKey(p) && !map1.get(p).equals(w)) return false;
            if(map2.containsKey(w) && map2.get(w) != p) return false;

            map1.put(p, w);
            map2.put(w, p);
        }

        return true;
    }
}