package easytext.ui;

import easytext.algorithms.Algorithm;
import easytext.api.Analyzer;
import easytext.factory.AnalyzerFactory;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

public class Main extends Application {
    private static ComboBox<Algorithm> algorithm;
    private static TextArea input;
    private static Text output;
    public static void main(String[] args) {
        Iterable<Analyzer> analyzers = ServiceLoader.load(Analyzer.class);
        for (Analyzer analyzer: analyzers) {
            System.out.println(analyzer.getName());
        }
        Application.launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("EasyText");
        Button btn = new Button();
        btn.setText("Calculate");
        btn.setOnAction(event ->
                output.setText(analyze(input.getText(), algorithm.getValue()))
        );
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(3));
        vbox.setSpacing(3);
        Text title = new Text("Choose an algorithm:");
        algorithm = new ComboBox<>();
        algorithm.setItems(FXCollections.observableArrayList(Algorithm.values()));
        vbox.getChildren().add(title);
        vbox.getChildren().add(algorithm);
        vbox.getChildren().add(btn);
        input = new TextArea();
        output = new Text();
        BorderPane pane = new BorderPane();
        pane.setRight(vbox);
        pane.setCenter(input);
        pane.setBottom(output);
        primaryStage.setScene(new Scene(pane, 400, 250));
        primaryStage.show();
    }

    private String analyze(String input, Algorithm algorithm) {
        List<List<String>> sentences = toSentences(input);
        return algorithm + ":" + AnalyzerFactory.getAnalyzer(algorithm).analyze(sentences);
    }

    public static List<List<String>> toSentences(String text) {
        String removedBreaks = text.replaceAll("\\r?\\n", " ");
        ArrayList<List<String>> sentences = new ArrayList<>();
        for(String rawSentence: removedBreaks.split("[\\.\\?\\!]")) {
            List<String> words = toWords(rawSentence);
            if(words.size() > 0) {
                sentences.add(words);
            }
        }

        return sentences;
    }

    public static List<String> toWords(String sentence) {
        String[] rawWords = sentence.split("\\s+");
        List<String> words = new ArrayList<>();
        for(String rawWord: rawWords) {
            String word = rawWord.replaceAll("\\W", "");
            if(word.length() > 0) {
                words.add(word);
            }
        }
        return words;
    }
}
