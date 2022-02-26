package Preprocessing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Tokens {
    String filePath; // instance variable

    public Tokens(String filePath) {
        this.filePath = filePath;
    }

    public List<String[]> readFile() throws IOException {
        CSVReader obj = new CSVReader(filePath);
        return obj.csvFileReader();
    }

    public List<List<String[]>> tokenizeText() throws IOException {
        List<String[]> file = readFile();
        file.remove(0); // HindiText and Sentiment Label
        List<String[]> sentences = new ArrayList<>();
        List<List<String[]>> completeTextData = new ArrayList<>();
        for (String[] sentence : file) {
            String textData = sentence[0];
            String[] textDataArray = textData.split(" ");
            sentences.add(textDataArray);
            completeTextData.add(sentences); // List<List<String[]>>
        }
        return completeTextData;
    }

    public List<Integer> tokenizeLabels() throws IOException {
        List<String[]> file = readFile();
        file.remove(0);
        List<Integer> labelsData = new ArrayList<>();
        for (String[] sentence : file) {
            String labels = sentence[1];
            labelsData.add(Integer.parseInt(labels)); // read as a string, then stored as an integer.
        }
        return labelsData;
    }
}
