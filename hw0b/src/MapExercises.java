import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapExercises {
    /** Returns a map from every lower case letter to the number corresponding to that letter, where 'a' is
     * 1, 'b' is 2, 'c' is 3, ..., 'z' is 26.
     */
    public static Map<Character, Integer> letterToNum() {
        // brute
//        Map<Character, Integer> map = new HashMap<>();
//        map.put(1,"a");   map.put(2,"b") ;  map.put(3,"c");

        //programatically. Java uses UNICODE, where a starts at 97
        Map<Character, Integer> map = new HashMap<>();
        for(char c = 'a'; c <= 'z'; c++) {
            map.put(c, c-'a'+1);
        }

        return map;
    }

    /** Returns a map from the integers in the list to their squares. For example, if the input list
     *  is [1, 3, 6, 7], the returned map goes from 1 to 1, 3 to 9, 6 to 36, and 7 to 49.
     */
    public static Map<Integer, Integer> squares(List<Integer> nums) {
        Map<Integer, Integer> squared_map = new HashMap<Integer, Integer>();

        for(int value: nums){
            squared_map.put(value, value * value); // or  (int) Math.pow(value, 2))
        }

        return squared_map;
    }

    /** Returns a map of the counts of all words that appear in a list of words. */
    public static Map<String, Integer> countWords(List<String> words) {
        Map<String, Integer> wordCounts = new HashMap<>();

        for(String word: words){
            wordCounts.put(word,wordCounts.getOrDefault(word,0)+1 );
        }
        return wordCounts;
    }
}
