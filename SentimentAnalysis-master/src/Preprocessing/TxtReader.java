package Preprocessing;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TxtReader {
    String filePath;
    public TxtReader(String filePath) {
        this.filePath = filePath;
    }

    public List<String[]> txtFileReader() throws IOException {
        String line;
        List<String[]> list = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        while ((line = br.readLine()) != null) {
            String[] values = line.split(" ");
//            System.out.println(Arrays.toString(values));
            list.add(values);
        }
        return list;
    }
//    public static void main(String[] args) throws IOException {
//        //HindiText, Sentiment --> Two columns
//        String filePath = "Dataset/Hindi Lexicons/HindiSentiWordnet.txt";
//        TxtReader obj = new TxtReader(filePath);
//        List<String[]> list = obj.txtFileReader();
//        System.out.println(list.get(5)[4]);
//    }
}
