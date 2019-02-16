package easytext.factory;

import easytext.algorithms.Algorithm;
import easytext.analytics.Coleman;
import easytext.analytics.FleschKincaid;
import easytext.api.Analyzer;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AnalyzerFactory {
    public static List<String> getAnalyzer() {
        return Stream
                .of(Algorithm.values())
                .map(Algorithm::getValue)
                .collect(Collectors.toList());
    }

    public static Analyzer getAnalyzer(Algorithm algorithm) {
        switch (algorithm){
            case FleschKincaid: return new FleschKincaid();
            case Coleman: return new Coleman();
            default: throw new IllegalArgumentException("No such analyzer");
        }
    }
}
