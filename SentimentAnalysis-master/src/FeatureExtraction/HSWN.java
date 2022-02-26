package FeatureExtraction;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class HSWN {
    public static void main(String[] args) throws FileNotFoundException {
        File text3 = new File("Dataset/Hindi Lexicons/HindiSentiWordnet.txt");
        Scanner sc2 = new Scanner(text3);
        File text1 = new File("Dataset/Hindi Stopwords/hindi_stopwords.txt");
        Scanner sc3 = new Scanner(text1);
        File text2 = new File("Dataset/test.txt");
        Scanner sc = new Scanner(text2);
        String line2 = sc.nextLine();
        String[] arr = new String[100];
        String[] arr2 = new String[100];
        String[] words = line2.split(" ");
        for (int i = 0; i < words.length; i++) {
            Scanner scanner = new Scanner(text1);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.contains(words[i])) {
                    arr[i] = words[i];
                }
            }
        }
        List<String> list_without_null = new ArrayList<String>();
        for (String new_string : arr) {
            if (new_string != null && new_string.length() > 0) {
                list_without_null.add(new_string);
            }
        }
        arr = list_without_null.toArray(new String[list_without_null.size()]);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < words.length; i++) {
            sb.append(words[i] + " ");
        }
        String str = sb.toString();
        //System.out.println(str);
        //System.out.print(Arrays.toString(words));
        final List<String> list = new ArrayList<String>();
        Collections.addAll(list, words);
        for (int i = 0; i < arr.length; i++) {
            list.remove(arr[i]);
        }
        list.remove("apple");
        arr2 = list.toArray(new String[list.size()]);
        //System.out.print(Arrays.toString(arr2));
        StringBuffer bs = new StringBuffer();
        for (int i = 0; i < arr2.length; i++) {
            bs.append(arr2[i] + " ");
        }
        String str2 = bs.toString();
        System.out.println("INPUT SENTENCE : " + line2);
        System.out.println(" ");
        System.out.println("AFTER STOPWORDS REMOVAL : " + str2);
        //HSWN
        String words2[] = str2.split(" ");
        double FinPos = 0.00, FinNeg = 0.00;
        String findword;
        System.out.println();
        for (int x = 0; x < words2.length; x++) {
            findword = words2[x];
            int spaces = 0;
            int istartpos = 0, iendpos = 0, istartneg = 0, iendneg = 0;
            double posval = 0.00, negval = 0.00, sumpos = 0.00, sumneg = 0.00;

            while (sc2.hasNextLine()) {
                String line = sc2.nextLine();
                if (line.contains(findword)) {
                    for (int i = 0; i < line.length(); i++) {
                        if (line.charAt(i) == ' ') {
                            spaces++;
                            if (spaces == 2) {
                                istartpos = i;
                            }
                            if (spaces == 3) {
                                iendpos = i;
                                istartneg = i;
                            }
                            if (spaces == 4) {
                                iendneg = i;
                            }
                        }
                    }
                    posval = Double.parseDouble(line.substring(istartpos + 1, iendpos));
                    negval = Double.parseDouble(line.substring(istartneg + 1, iendneg));

                    sumpos = sumpos + posval;
                    sumneg = sumneg + negval;

                    spaces = 0;
                }

            }
            FinPos = FinPos + sumpos;
            FinNeg = FinNeg + sumneg;
            sc2 = new Scanner(text1);
        }
        if (FinPos > FinNeg) {
            System.out.println("Sentence is Positive");
        } else if (FinNeg > FinPos) {
            System.out.println("Sentence is Negative");
        } else {
            System.out.println("Sentence is Neutral");
        }
    }
}
