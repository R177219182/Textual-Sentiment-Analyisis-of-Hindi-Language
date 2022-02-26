package Preprocessing;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class CSVReaderNumeric {
    String filePath;

    public CSVReaderNumeric(String filePath) {
        this.filePath = filePath;
    }

    public List<double[]> csvFileReader() throws IOException {
        String line;
        List<double[]> list = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        while ((line = br.readLine()) != null) {
            double[] numList = Stream.of(line.split(","))
                    .mapToDouble(Double::parseDouble)
                    .toArray();
            list.add(numList);
        }
        return list;
    }

    public double[][] read_csv() throws IOException {
        List<double[]> list = csvFileReader();
        int number_of_rows = list.size();
        int number_of_columns = list.get(1).length;
        double[][] dataArray = new double[number_of_rows][number_of_columns];
        for (int i = 0; i < number_of_rows; i++) {
            for (int j = 0; j < number_of_columns; j++){
                dataArray[i][j] = list.get(i)[j];
            }
        }
        return dataArray;
    }
    public static void main(String[] args) throws IOException {
        double[][] dataArray = new CSVReaderNumeric("Dataset/SplitTfidf/Unigram/Training/x_train.csv").
                read_csv();
        for(int i=0;i< dataArray.length;i++){
            for(int j=0;j< dataArray[0].length;j++){
                System.out.print(dataArray[i][j]+" ");
            }
            System.out.println();
        }
    }
}