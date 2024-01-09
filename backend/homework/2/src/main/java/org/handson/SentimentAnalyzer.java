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
            CustomLogger.customLogger('e', "Error in function detectProsAndCons " + e);
            return new int[0];

        }
    }


    private static int getOpinionOnFeature(String review, String feature,
                                           String[] posOpinionWords, String[] negOpinionWords) {
        try {
            int isPresentPattern2 ;
            CustomLogger.customLogger('i', "Entered getOpinionOnFeature");

            int isPresentPattern1 = checkForOpinionFirstPattern(review, feature, posOpinionWords, negOpinionWords);
            if (isPresentPattern1 == 0)
                isPresentPattern2 = checkForWasPhrasePattern(review, feature, posOpinionWords, negOpinionWords);
            else
                return isPresentPattern1;

            return isPresentPattern2;
        } catch (Exception e) {
            CustomLogger.customLogger('e', "Error in function getOpinionOnFeature " + e);

            return 0;
        }
    }

    public static int checkForWasPhrasePattern(String review, String
            feature, String[] posOpinionWords, String[] negOpinionWords) {

        try {

            CustomLogger.customLogger('i', "Entered checkForWasPhrasePattern");

            String pattern = feature + " was ";

            boolean ans = review.contains(pattern);
            if (ans) {
                String nextWord = "";

                String[] words = review.split(" ");
                for (int i = 0; i < words.length - 1; i++) {
                    if (words[i].equals(feature) && words[i + 1].equals("was") && i < (words.length - 2)) {

                             nextWord = words[i + 2].replaceAll("[.!]", "");

                            break;

                    }
                }
                if (nextWord.isEmpty()) {
                    return 0;
                }
                List<String> positiveOpinionList = new ArrayList<>(Arrays.stream(posOpinionWords).toList());
                List<String> negativeOpinionList = new ArrayList<>(Arrays.stream(negOpinionWords).toList());

                return positiveOpinionList.contains(nextWord)?1:(negativeOpinionList.contains(nextWord)?-1:0);
            }
            else {
                return 0;
            }

        } catch (Exception e) {
            CustomLogger.customLogger('e', "Error in function checkForWasPhrasePattern " + e);
            return 0;
        }
    }


    private static int checkForOpinionFirstPattern(String review, String
            feature, String[] posOpinionWords,
                                                   String[] negOpinionWords) {
        try {
            CustomLogger.customLogger('i', "Entered checkForOpinionFirstPattern");
            String[] words = review.split(" ");
            List<String> posOpList = new ArrayList<>(Arrays.asList(posOpinionWords));
            List<String> negOpList = new ArrayList<>(Arrays.asList(negOpinionWords));

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
            CustomLogger.customLogger('e', "Error in function checkForOpinionFirstPattern " + e);
            return 0;
        }
    }

    public static void main(String[] args) {

        int[] featureOpinions = detectProsAndCons(Constants.REVIEW_SENTIMENT_ANALYSER, Constants.FEATURE_SET_SENTIMENT_ANALYSER,
                Constants.POSITIVE_OPINION_WORDS, Constants.NEGATIVE_OPINION_WORDS);
        CustomLogger.customLogger('d', "Opinions on Features: " +
                Arrays.toString(featureOpinions));
    }
}