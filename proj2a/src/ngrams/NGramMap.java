package ngrams;

import java.util.Collection;
import java.util.Map;
import java.util.HashMap;
import edu.princeton.cs.algs4.In;

import static ngrams.TimeSeries.MAX_YEAR;
import static ngrams.TimeSeries.MIN_YEAR;

/**
 * An object that provides utility methods for making queries on the
 * Google NGrams dataset (or a subset thereof).
 *
 * An NGramMap stores pertinent data from a "words file" and a "counts
 * file". It is not a map in the strict sense, but it does provide additional
 * functionality.
 *
 * @author Josh Hug
 */
public class NGramMap {

    private final Map<String, TimeSeries> wordMap; // Allows easy replacement with another Map type (e.g., TreeMap for ordered keys, though with O(log n) access time)
    private final TimeSeries totalCounts;// year-int: appearance-double

    /**
     * Constructs an NGramMap from WORDSFILENAME and COUNTSFILENAME.
     */
    public NGramMap(String wordsFilename, String countsFilename) {
        wordMap = new HashMap<>(); //Hashmap for average constant access time O(1)
        totalCounts = new TimeSeries();

        //Parse the wordCounts:(word, year, count)
        In wordsIn = new In(wordsFilename);
        while (wordsIn.hasNextLine()){
            // word     year     appearances_in_books       no_distinct_books
            String[] parts = wordsIn.readLine().split("\t");
            String word = parts[0];
            int year = Integer.parseInt(parts[1]);
            double count = Integer.parseInt(parts[2]); //Timeseries value uses double but dataset only has ints.

            if (!wordMap.containsKey(word)){
                wordMap.put(word,new TimeSeries());
            }
            wordMap.get(word).put(year, count);
        }

        //Parse the totalCounts:(year, appearances)
        In countsIn = new In(countsFilename);
        while (countsIn.hasNextLine()){
            //year      no_words_recorded       no_pages_of_text        no_distinct_sources
            String[] parts = countsIn.readLine().split(",");
            int year = Integer.parseInt(parts[0]);
            double count = Double.parseDouble(parts[1]);
            totalCounts.put(year, count);
        }
    }

    /**
     * Provides the history of WORD between STARTYEAR and ENDYEAR, inclusive of both ends. The
     * returned TimeSeries should be a copy, not a link to this NGramMap's TimeSeries. In other
     * words, changes made to the object returned by this function should not also affect the
     * NGramMap. This is also known as a "defensive copy". If the word is not in the data files,
     * returns an empty TimeSeries.
     */
    public TimeSeries countHistory(String word, int startYear, int endYear) {
        if (!wordMap.containsKey(word)){
            return new TimeSeries();
        }
        return new TimeSeries(wordMap.get(word),startYear,endYear);
    }

    /**
     * Provides the history of WORD. The returned TimeSeries should be a copy, not a link to this
     * NGramMap's TimeSeries. In other words, changes made to the object returned by this function
     * should not also affect the NGramMap. This is also known as a "defensive copy". If the word
     * is not in the data files, returns an empty TimeSeries.
     */
    public TimeSeries countHistory(String word) {
        return countHistory(word, MIN_YEAR, MAX_YEAR);
    }

    /**
     * Returns a defensive copy of the total number of words recorded per year in all volumes.
     */
    public TimeSeries totalCountHistory() {
        return new TimeSeries(totalCounts, MIN_YEAR, MAX_YEAR);
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD between STARTYEAR
     * and ENDYEAR, inclusive of both ends. If the word is not in the data files, returns an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word, int startYear, int endYear) {
        TimeSeries wordCounts = countHistory(word,startYear,endYear);
        if (wordCounts.isEmpty()){
            return new TimeSeries();
        }
        TimeSeries allWordCounts = new TimeSeries(totalCounts, startYear,endYear);
        return wordCounts.dividedBy(allWordCounts);
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD compared to all
     * words recorded in that year. If the word is not in the data files, returns an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word) {
        return new TimeSeries(weightHistory(word, MIN_YEAR, MAX_YEAR), MIN_YEAR, MAX_YEAR);
    }

    /**
     * Provides the summed relative frequency per year of all words in WORDS between STARTYEAR and
     * ENDYEAR, inclusive of both ends. If a word does not exist in this time frame, ignore it
     * rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words,
                                          int startYear, int endYear) {
        TimeSeries result = new TimeSeries();
        for (String word : words){
            result = result.plus(weightHistory(word, startYear,endYear));
        }
        return result;
    }

    /**
     * Returns the summed relative frequency per year of all words in WORDS. If a word does not
     * exist in this time frame, ignore it rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words) {
        return summedWeightHistory(words,
                MIN_YEAR, MAX_YEAR);
    }

}

