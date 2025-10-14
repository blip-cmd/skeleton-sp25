import java.util.ArrayList;
//import java.util.Arrays;
import java.util.List;

public class ListExercises {

    /** Returns the total sum in a list of integers */
    public static int sum(List<Integer> L) {
        int total = 0;
        for(int i: L){
            total = total + i;
        }
        return total;
    }

    /** Returns a list containing the even numbers of the given list */
    public static List<Integer> evens(List<Integer> L) {
        List<Integer> nL = new ArrayList<>();

        for (int i: L) {
            if(i%2==0){
                nL.add(i);
            }
        }

        return nL;
    }

    /** Returns a list containing the common item of the two given lists */
    public static List<Integer> common(List<Integer> L1, List<Integer> L2) {
        // Normal
        List<Integer> nL = new ArrayList<>();

        for(int i: L1){
            for(int j: L2){
                if(i==j) {
                    nL.add(j);
                }
            }
        }
        return nL;

        //Set attempt. I'd like to use two sets for both lists then return an intersection
//        return null;
    }

    /** Returns the number of occurrences of the given character in a list of strings. */
    public static int countOccurrencesOfC(List<String> words, char c) {
        int count = 0;
       for(String w: words){
           count += w.length() - w.replace(String.valueOf(c),"").length(); //Shortcut
           }
       return count;
    }

//       return 0;
    }
