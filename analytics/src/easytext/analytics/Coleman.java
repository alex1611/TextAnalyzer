package easytext.analytics;

import easytext.algorithms.Algorithm;
import easytext.api.Analyzer;

import java.util.List;

public class Coleman implements Analyzer {

    @Override
    public String getName() {
        return Algorithm.Coleman.getValue();
    }

    @Override
    public double analyze(List<List<String>> sentences) {
        float totalsentences = sentences.size();
        float words = sentences.stream().mapToInt(sentence -> sentence.size()).sum();
        float letters = sentences.stream().flatMapToInt(sentence -> sentence.stream().mapToInt(word -> word.length())).sum();
        return 0.0588 * (letters / (words / 100)) - 0.296 * (totalsentences / (words / 100)) - 15.8;
    }

}