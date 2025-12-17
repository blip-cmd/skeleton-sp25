package ngrams;

import java.sql.Time;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

/**
 * An object for mapping a year number (e.g. 1996) to numerical data. Provides
 * utility methods useful for data analysis.
 *
 * @author Josh Hug
 */
public class TimeSeries extends TreeMap<Integer, Double> {

    /** If it helps speed up your code, you can assume year arguments to your NGramMap
     * are between 1400 and 2100. We've stored these values as the constants
     * MIN_YEAR and MAX_YEAR here. */
    public static final int MIN_YEAR = 1400;
    public static final int MAX_YEAR = 2100;

    /**
     * Constructs a new empty TimeSeries.
     */
    public TimeSeries() {
        super();
    }

    /**
     * Creates a copy of TS, but only between STARTYEAR and ENDYEAR,
     * inclusive of both end points.
     */
    // TO COPY ts:
    //     TimeSeries copy = new TimeSeries(original, Integer.MIN_VALUE, Integer.MAX_VALUE);
    public TimeSeries(TimeSeries ts, int startYear, int endYear) {
        super();
        for (int year : ts.keySet()){
            if(year >= startYear && year <= endYear){
                this.put(year, ts.get(year));
            }
        }
    }

    /**
     *  Returns all years for this time series in ascending order.
     */
    public List<Integer> years() {
//        List<Integer> list  = new LinkedList<>();
//        for(int year : this.keySet()){
//            list.add(year); // preserve order
//        }
//        list.addAll(this.keySet());
//        return list;
        return new LinkedList<>(this.keySet()); //Best JDK-optimized
    }

    /**
     *  Returns all data for this time series. Must correspond to the
     *  order of years().
     */
    public List<Double> data() {
        // TODO: Fill in this method.
        return null;
    }

    /**
     * Returns the year-wise sum of this TimeSeries with the given TS. In other words, for
     * each year, sum the data from this TimeSeries with the data from TS. Should return a
     * new TimeSeries (does not modify this TimeSeries).
     *
     * If both TimeSeries don't contain any years, return an empty TimeSeries.
     * If one TimeSeries contains a year that the other one doesn't, the returned TimeSeries
     * should store the value from the TimeSeries that contains that year.
     */
    public TimeSeries plus(TimeSeries ts) {
        TimeSeries result = new TimeSeries();

        // add current ts to result
        for(int year : this.keySet()){
            result.put(year, this.get(year));
        }

        // add in-coming ts to update result
        for(int year : ts.keySet()){
            result.put(year, this.getOrDefault(year,0.0) + ts.get(year));
        }

        return result;
    }

    /**
     * Returns the quotient of the value for each year this TimeSeries divided by the
     * value for the same year in TS. Should return a new TimeSeries (does not modify this
     * TimeSeries).
     *
     * If TS is missing a year that exists in this TimeSeries, throw an
     * IllegalArgumentException.
     * If TS has a year that is not in this TimeSeries, ignore it.
     */
    public TimeSeries dividedBy(TimeSeries ts) {
        TimeSeries result = new TimeSeries();
        for (int year : this.keySet()){
            if(!ts.containsKey(year)){
                throw new IllegalArgumentException("Your timeseries is missing an existing year");
            }
            result.put(year, this.get(year) / ts.get(year));
        }

        return result;
    }

    // TODO: Add any private helper methods.
    // TODO: Remove all TODO comments before submitting.
}
