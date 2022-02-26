package Preprocessing;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {
    String filePath; // instance variable
    public CSVReader(String filePath) { // parametrized constructor
        this.filePath = filePath;
    }

    public List<String[]> csvFileReader() throws IOException { // function for reading the csv file
        String line; // local variable
        List<String[]> list = new ArrayList<>(); // local variable
        /* Storing the data present in the .csv file */
        BufferedReader br = new BufferedReader(new FileReader(filePath)); // br is the object for reading the file
        while ((line = br.readLine()) != null) { // till the end of the page
            String[] values = line.split(","); // splitting the lines, with "," as the delimiter
            /* output of the split() function is a string array */
            list.add(values); // adding this array to the list
        }
        return list;
    }
}
//    public static void main(String[] args) throws IOException {
//        //HindiText, Sentiment --> Two columns
//        CSVReader obj = new CSVReader();
//        String filePath = "Dataset/Hindi Movie Reviews/Cleaned/ShuffledHindiText.csv";
//        List<String[]> list = obj.csvFileReader(filePath);
//    }
//}
