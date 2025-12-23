package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import ngrams.NGramMap;
import ngrams.TimeSeries;
import plotting.Plotter;
import org.knowm.xchart.XYChart;

import java.util.ArrayList;
import java.util.List;

public class HistoryHandler extends NgordnetQueryHandler {
    private final NGramMap ngm;

    public HistoryHandler(NGramMap map){
        this.ngm = map;
    }

    @Override
    public String handle(NgordnetQuery q) {
        //Acquire list of words, styr,enyr from query
        List<String> words = q.words();
        int startYear = q.startYear();
        int endYear = q.endYear();


        List<TimeSeries> seriesList = new ArrayList<>();
        List<String> labels = new ArrayList<>();

        for(String word:words){
            TimeSeries ts = ngm.weightHistory(word,startYear,endYear);
            if (!ts.isEmpty()){
                seriesList.add(ts);
                labels.add(word);
            }
        }

        XYChart chart = Plotter.generateTimeSeriesChart(labels, seriesList);
        String encodedImage = Plotter.encodeChartAsString(chart);
        return encodedImage;
    }
}
