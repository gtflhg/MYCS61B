package ngrams;

import edu.princeton.cs.algs4.In;

import java.util.Collection;
import java.util.HashMap;

import static utils.Utils.SHORT_WORDS_FILE;

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
    WordMap wordMap;
    TimeSeries totalTimeSeries;

    /**
     * Constructs an NGramMap from WORDSFILENAME and COUNTSFILENAME.
     */
    public NGramMap(String wordsFilename, String countsFilename) {
        wordMap = new WordMap(wordsFilename);
        totalTimeSeries = new TimeSeries();
        In in = new In(countsFilename);
        while (!in.isEmpty()) {
            String[] splitLine = in.readLine().split(",");
            Integer year = Integer.valueOf(splitLine[0]);
            Double count = Double.valueOf(splitLine[1]);
            totalTimeSeries.put(year, count);
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
        if (!wordMap.containsKey(word)) {
            return new TimeSeries();
        }
        return new TimeSeries(wordMap.get(word), startYear, endYear);
    }

    /**
     * Provides the history of WORD. The returned TimeSeries should be a copy, not a link to this
     * NGramMap's TimeSeries. In other words, changes made to the object returned by this function
     * should not also affect the NGramMap. This is also known as a "defensive copy". If the word
     * is not in the data files, returns an empty TimeSeries.
     */
    public TimeSeries countHistory(String word) {
        if (!wordMap.containsKey(word)) {
            return new TimeSeries();
        }
        TimeSeries returnTimeSeries = new TimeSeries();
        TimeSeries wordTimeSeries = wordMap.get(word);
        for (Integer year : wordTimeSeries.years()) {
            returnTimeSeries.put(year, wordTimeSeries.get(year));
        }
        return returnTimeSeries;
    }

    /**
     * Returns a defensive copy of the total number of words recorded per year in all volumes.
     */
    public TimeSeries totalCountHistory() {
        TimeSeries returnTimeSeries = new TimeSeries();
        for (Integer year : totalTimeSeries.years()) {
            returnTimeSeries.put(year, totalTimeSeries.get(year));
        }
        return returnTimeSeries;
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD between STARTYEAR
     * and ENDYEAR, inclusive of both ends. If the word is not in the data files, returns an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word, int startYear, int endYear) {
        if (!wordMap.containsKey(word)) {
            return new TimeSeries();
        }
        return countHistory(word, startYear, endYear).dividedBy(totalTimeSeries);
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD compared to all
     * words recorded in that year. If the word is not in the data files, returns an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word) {
        if (!wordMap.containsKey(word)) {
            return new TimeSeries();
        }
        return countHistory(word).dividedBy(totalTimeSeries);
    }

    /**
     * Provides the summed relative frequency per year of all words in WORDS between STARTYEAR and
     * ENDYEAR, inclusive of both ends. If a word does not exist in this time frame, ignore it
     * rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words,
                                          int startYear, int endYear) {
        TimeSeries returnTimeSeries = new TimeSeries();
        for (String word : words) {
            if (!wordMap.containsKey(word)) {
                continue;
            }
            TimeSeries wordTimeSeries = weightHistory(word, startYear, endYear);
            returnTimeSeries = wordTimeSeries.plus(returnTimeSeries);
        }
        return returnTimeSeries;
    }

    /**
     * Returns the summed relative frequency per year of all words in WORDS. If a word does not
     * exist in this time frame, ignore it rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words) {
        TimeSeries returnTimeSeries = new TimeSeries();
        for (String word : words) {
            if (!wordMap.containsKey(word)) {
                continue;
            }
            TimeSeries wordTimeSeries = weightHistory(word);
            returnTimeSeries = wordTimeSeries.plus(returnTimeSeries);
        }
        return returnTimeSeries;
    }

    // TODO: Add any private helper methods.
    // TODO: Remove all TODO comments before submitting.

    //Contains the words and its TimeSeries.
    private class WordMap extends HashMap<String, TimeSeries> {
        private WordMap(String wordsFilename) {
            super();
            In in = new In(wordsFilename);
            TimeSeries timeSeries = new TimeSeries();
            String line = null;
            String currentWord = null;
            while ((line = in.readLine())!= null) {
                String[] fields = line.split("\t");
                String word = fields[0];
                Integer year = Integer.valueOf(fields[1]);
                Double weight = Double.valueOf(fields[2]);
                if (currentWord == null) {
                    currentWord = word;
                }else if (!currentWord.equals(word)) {
                    this.put(currentWord, timeSeries);
                    currentWord = word;
                    timeSeries =  new TimeSeries();
                }
                timeSeries.put(year, weight);
            }
            if (currentWord != null) {
                this.put(currentWord, timeSeries);
            }
        }
    }
}
