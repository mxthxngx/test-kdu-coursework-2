package org.handson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SentimentAnalyzer {
    public static int[] detectProsAndCons(String review, String[][]
            featureSet, String[] posOpinionWords,
                                          String[] negOpinionWords) {
        try {
            CustomLogger.customLogger('i', "Entered detectProsAndCons");
            review = review.toLowerCase();
            int[] featureOpinions = new int[featureSet.length]; // output
            for (int i = 0; i < featureSet.length; i++) {
                for (int j = 0; j < featureSet[i].length; j++) {
                    int output = getOpinionOnFeature(review, featureSet[i][j], posOpinionWords, negOpinionWords);
                    CustomLogger.customLogger('i', "Fetched getOpinionOnFeature");

                    if (output != 0) {
                        featureOpinions[i] = output;
                        break;
                    }
                }
            }
            return featureOpinions;

        } catch (Exception e) {
            CustomLogger.customLogger('e', "Error in function detectProsAndCons " + e.toString());
            return new int[0];

        }
    }


    private static int getOpinionOnFeature(String review, String feature,
                                           String[] posOpinionWords, String[] negOpinionWords) {
        try {
            int isPresentPattern2 = 0;
            CustomLogger.customLogger('i', "Entered getOpinionOnFeature");

            int isPresentPattern1 = checkForOpinionFirstPattern(review, feature, posOpinionWords, negOpinionWords);
            if (isPresentPattern1 == 0)
                isPresentPattern2 = checkForWasPhrasePattern(review, feature, posOpinionWords, negOpinionWords);
            else
                return isPresentPattern1;

            return isPresentPattern2;
        } catch (Exception e) {
            CustomLogger.customLogger('e', "Error in function getOpinionOnFeature " + e.toString());

            return 0;
        }
    }

    public static int checkForWasPhrasePattern(String review, String
            feature, String[] posOpinionWords, String[] negOpinionWords) {

        try {

            CustomLogger.customLogger('i', "Entered checkForWasPhrasePattern");

            int opinion = 0;
            String pattern = feature + " was ";

            boolean ans = review.contains(pattern);
            if (ans) {
                String nextWord = "";

                String[] words = review.split(" ");
                for (int i = 0; i < words.length - 1; i++) {
                    if (words[i].equals(feature) && words[i + 1].equals("was")) {
                        if (i < (words.length - 2)) {
                            nextWord = words[i + 2];
                            if (nextWord.charAt(nextWord.length() - 1) == '!' || nextWord.charAt(nextWord.length() - 1) == '.') {
                                nextWord = nextWord.substring(0, nextWord.length() - 1);
                            }
                            break;
                        }
                    }
                }
                if (nextWord.equals("")) {
                    return 0;
                }
                for (var posOpinion : posOpinionWords) {
                    if (nextWord.equals(posOpinion)) {
                        return 1;
                    }
                }
                for (var negOpinion : negOpinionWords) {
                    if (nextWord.equals(negOpinion)) {
                        return -1;
                    }
                }
            }
            return opinion;
        } catch (Exception e) {
            CustomLogger.customLogger('e', "Error in function checkForWasPhrasePattern " + e.toString());
            return 0;
        }
    }



    private static int checkForOpinionFirstPattern(String review, String
            feature, String[] posOpinionWords,
                                                   String[] negOpinionWords) {
        try {
            CustomLogger.customLogger('i', "Entered checkForOpinionFirstPattern");
            String[] words = review.split(" ");
            List<String> wordsList = Arrays.asList(Arrays.toString(words));
            List<String> posOpList = new ArrayList<String>(Arrays.asList(posOpinionWords));
            List<String> negOpList = new ArrayList<String>(Arrays.asList(negOpinionWords));

            for (int index = 0; index < words.length; index++) {
                String word = words[index];
                if (word.charAt(word.length() - 1) == '!' || word.charAt(word.length() - 1) == '.' || word.charAt(word.length() - 1) == ',') {
                    word = word.substring(0, word.length() - 1);
                }
                if (feature.equals(word)) {
                    if (index - 1 >= 0 && posOpList.contains(words[index - 1])) {
                        return 1;
                    } else if (index - 1 >= 0 && negOpList.contains(words[index - 1])) {
                        return -1;
                    }
                }
            }


            return 0;
        } catch (Exception e) {
            CustomLogger.customLogger('e', "Error in function checkForOpinionFirstPattern " + e.toString());
            return 0;
        }
    }

    public static void main(String[] args) {
        //String review = "Haven't been here in years! Fantastic service and the food was delicious! Definetly will be a frequent flyer! Francisco was very attentive";
        String review = "Sorry OG, but you just lost some loyal customers. Horrible service, no smile or greeting just attitude. The breadsticks were stale and burnt, appetizer was cold and the food came out before the salad.";
        String[][] featureSet = {
                {"ambiance", "ambience", "atmosphere", "decor"},
                {"dessert", "ice cream", "desert"},
                {"food"},
                {"soup"},
                {"service", "management", "waiter", "waitress",
                        "bartender", "staff", "server"}};
        String[] posOpinionWords = {"good", "fantastic", "friendly",
                "great", "excellent", "amazing", "awesome",
                "delicious"};
        String[] negOpinionWords = {"slow", "bad", "horrible",
                "awful", "unprofessional", "poor"};
        int[] featureOpinions = detectProsAndCons(review, featureSet,
                posOpinionWords, negOpinionWords);
       CustomLogger.customLogger('d',"Opinions on Features: " +
                Arrays.toString(featureOpinions));
//System.out.println(checkForOpinionFirstPattern(review,"service",posOpinionWords,negOpinionWords));
    }
}