package ML;

import Preprocessing.CSVReaderNumeric;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class KNN {
    String UnigramTraining, UnigramTesting, BigramTraining, BigramTesting, TrigramTraining, TrigramTesting;
    List<double[]> x_trainUnigram, x_trainBigram, x_trainTrigram;
    List<double[]> y_trainUnigram, y_trainBigram, y_trainTrigram;
    List<double[]> x_testUnigram, x_testBigram, x_testTrigram;
    List<double[]> y_testUnigram, y_testBigram, y_testTrigram;
    int k;

    KNN(String Unigram, String Bigram, String Trigram, int n_neigbors) {
        this.UnigramTraining = Unigram + "/Training";
        this.UnigramTesting = Unigram + "/Testing";
        this.BigramTraining = Bigram + "/Training";
        this.BigramTesting = Bigram + "/Testing";
        this.TrigramTraining = Trigram + "/Training";
        this.TrigramTesting = Trigram + "/Testing";
        this.k = n_neigbors;
    }

    public void loadData() throws IOException {
        CSVReaderNumeric obj1 = new CSVReaderNumeric(UnigramTraining + "/x_train.csv");
        x_trainUnigram = obj1.csvFileReader();
        CSVReaderNumeric obj2 = new CSVReaderNumeric(UnigramTraining + "/y_train.csv");
        y_trainUnigram = obj2.csvFileReader();
        CSVReaderNumeric obj3 = new CSVReaderNumeric(UnigramTesting + "/x_test.csv");
        x_testUnigram = obj3.csvFileReader();
        CSVReaderNumeric obj4 = new CSVReaderNumeric(UnigramTesting + "/y_test.csv");
        y_testUnigram = obj4.csvFileReader();
        CSVReaderNumeric obj5 = new CSVReaderNumeric(BigramTraining + "/x_train.csv");
        x_trainBigram = obj5.csvFileReader();
        CSVReaderNumeric obj6 = new CSVReaderNumeric(BigramTraining + "/y_train.csv");
        y_trainBigram = obj6.csvFileReader();
        CSVReaderNumeric obj7 = new CSVReaderNumeric(BigramTesting + "/x_test.csv");
        x_testBigram = obj7.csvFileReader();
        CSVReaderNumeric obj8 = new CSVReaderNumeric(BigramTesting + "/y_test.csv");
        y_testBigram = obj8.csvFileReader();
        CSVReaderNumeric obj9 = new CSVReaderNumeric(TrigramTraining + "/x_train.csv");
        x_trainTrigram = obj9.csvFileReader();
        CSVReaderNumeric obj10 = new CSVReaderNumeric(TrigramTraining + "/y_train.csv");
        y_trainTrigram = obj10.csvFileReader();
        CSVReaderNumeric obj11 = new CSVReaderNumeric(TrigramTesting + "/x_test.csv");
        x_testTrigram = obj11.csvFileReader();
        CSVReaderNumeric obj12 = new CSVReaderNumeric(TrigramTesting + "/y_test.csv");
        y_testTrigram = obj12.csvFileReader();
    }

    public double euclidean(double[] data, double[] ngram) {
        double sum = 0.0;
        for (int i = 0; i < data.length; i++) {
            double difference = Math.pow(ngram[i], 2) - Math.pow(data[i], 2);
            sum = sum + difference;
        }
        return Math.sqrt(sum);
    }

    public List<Double> kNearestNeighborUnigram() {
        int idx = 0;
        int classLabelOne = 0, classLabelZero = 0;
        List<Double> unigramList = new ArrayList<>();
        List<Double> eucList = new ArrayList<>();
        for (double[] doubles : x_testUnigram) {
            for (double[] value : x_trainUnigram) {
                double sum = euclidean(doubles, value);
                eucList.add(sum);
            }
            // Sort eucList in ascending order
            Double[] eucArray = new Double[eucList.size()];
            eucList.toArray(eucArray);
            double[] ytrain = new double[y_trainUnigram.size()];
            for (double[] element : y_trainUnigram) {
                ytrain[idx] = element[0];
                ++idx;
            }
            for (int i = 0; i < eucArray.length - 1; i++) {
                for (int j = 0; j < eucArray.length - i - 1; j++) {
                    if (eucArray[j] > eucArray[j + 1]) {
                        double temp1 = eucArray[j];
                        eucArray[j] = eucArray[j + 1];
                        eucArray[j + 1] = temp1;

                        double temp2 = ytrain[j];
                        ytrain[j] = ytrain[j + 1];
                        ytrain[j + 1] = temp2;
                    }
                }
            }
            for (int i = 0; i < k; i++) {
                if (ytrain[i] == 0.0) {
                    classLabelZero = classLabelZero + 1;
                } else {
                    classLabelOne = classLabelOne + 1;
                }
            }
            if (classLabelOne > classLabelZero) {
                unigramList.add(1.0);
            } else {
                unigramList.add(0.0);
            }
            eucList.clear();
            idx = 0;
            classLabelOne = 0;
            classLabelZero = 0;
        }
        return unigramList;
    }

    public double accuracy_score_unigram() {
        List<Double> knnList = kNearestNeighborUnigram();
        int score = 0;
        for (int i = 0; i < y_testUnigram.size(); i++) {
            if (knnList.get(i) == y_testUnigram.get(i)[0]) {
                ++score;
            }
        }
        return ((double)score / y_testUnigram.size());
    }

    public List<Double> kNearestNeighborBigram() {
        int idx = 0;
        int classLabelOne = 0, classLabelZero = 0;
        List<Double> bigramList = new ArrayList<>();
        List<Double> eucList = new ArrayList<>();
        for (double[] doubles : x_testBigram) {
            for (double[] value : x_trainBigram) {
                double sum = euclidean(doubles, value);
                eucList.add(sum);
            }
            // Sort eucList in ascending order
            Double[] eucArray = new Double[eucList.size()];
            eucList.toArray(eucArray);
            double[] ytrain = new double[y_trainBigram.size()];
            for (double[] element : y_trainBigram) {
                ytrain[idx] = element[0];
                ++idx;
            }
            for (int i = 0; i < eucArray.length - 1; i++) {
                for (int j = 0; j < eucArray.length - i - 1; j++) {
                    if (eucArray[j] > eucArray[j + 1]) {
                        double temp1 = eucArray[j];
                        eucArray[j] = eucArray[j + 1];
                        eucArray[j + 1] = temp1;

                        double temp2 = ytrain[j];
                        ytrain[j] = ytrain[j + 1];
                        ytrain[j + 1] = temp2;
                    }
                }
            }
            for (int i = 0; i < k; i++) {
                if (ytrain[i] == 0.0) {
                    classLabelZero = classLabelZero + 1;
                } else {
                    classLabelOne = classLabelOne + 1;
                }
            }
            if (classLabelOne > classLabelZero) {
                bigramList.add(1.0);
            } else {
                bigramList.add(0.0);
            }
            eucList.clear();
            idx = 0;
            classLabelOne = 0;
            classLabelZero = 0;
        }
        return bigramList;
    }

    public double accuracy_score_bigram() {
        List<Double> knnList = kNearestNeighborBigram();
        double score = 0.0;
        for (int i = 0; i < y_testBigram.size(); i++) {
            if (knnList.get(i) == y_testBigram.get(i)[0]) {
                ++score;
            }
        }
        return score / y_testBigram.size();
    }

    public List<Double> kNearestNeighborTrigram() {
        int idx = 0;
        int classLabelOne = 0, classLabelZero = 0;
        List<Double> trigramList = new ArrayList<>();
        List<Double> eucList = new ArrayList<>();
        for (double[] doubles : x_testTrigram) {
            for (double[] value : x_trainTrigram) {
                double sum = euclidean(doubles, value);
                eucList.add(sum);
            }
            // Sort eucList in descending order
            Double[] eucArray = new Double[eucList.size()];
            eucList.toArray(eucArray);
            double[] ytrain = new double[y_trainTrigram.size()];
            for (double[] element : y_trainTrigram) {
                ytrain[idx] = element[0];
                ++idx;
            }
            for (int i = 0; i < eucArray.length - 1; i++) {
                for (int j = 0; j < eucArray.length - i - 1; j++) {
                    if (eucArray[j] > eucArray[j + 1]) {
                        double temp1 = eucArray[j];
                        eucArray[j] = eucArray[j + 1];
                        eucArray[j + 1] = temp1;

                        double temp2 = ytrain[j];
                        ytrain[j] = ytrain[j + 1];
                        ytrain[j + 1] = temp2;
                    }
                }
            }
            for (int i = 0; i < k; i++) {
                if (ytrain[i] == 0.0) {
                    classLabelZero = classLabelZero + 1;
                } else {
                    classLabelOne = classLabelOne + 1;
                }
            }
            if (classLabelOne > classLabelZero) {
                trigramList.add(1.0);
            } else {
                trigramList.add(0.0);
            }
            eucList.clear();
            idx = 0;
            classLabelOne = 0;
            classLabelZero = 0;
        }
        return trigramList;
    }

    public double accuracy_score_trigram() {
        List<Double> knnList = kNearestNeighborTrigram();
        double score = 0.0;
        for (int i = 0; i < y_testTrigram.size(); i++) {
            if (knnList.get(i) == y_testTrigram.get(i)[0]) {
                ++score;
            }
        }
        return score / y_testTrigram.size();
    }

    public static void main(String[] args) throws IOException {
        String filePathUnigram = "Dataset/SplitTfidf/Unigram";
        String filePathBigram = "Dataset/SplitTfidf/Bigram";
        String filePathTrigram = "Dataset/SplitTfidf/Trigram";
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter the value of k: ");
        int k = Integer.parseInt(bufferedReader.readLine().trim());
        KNN obj = new KNN(filePathUnigram, filePathBigram, filePathTrigram, k);
        obj.loadData();
        System.out.println("The accuracy score of Unigram: " + obj.accuracy_score_unigram());
        System.out.println("The accuracy score of Bigram: " + obj.accuracy_score_bigram());
        System.out.println("The accuracy score of Trigram: " + obj.accuracy_score_trigram());
    }
}