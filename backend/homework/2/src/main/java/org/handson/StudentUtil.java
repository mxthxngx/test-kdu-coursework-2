package org.handson;

import java.util.*;

public class StudentUtil
{
    private static int getScore(char grade)
    {
        switch(grade)
        {
            case 'A' -> {
                return 4;
            }
            case 'B' -> {
                return 3;
            }
            case 'C' -> {
                return 2;
            }
        }
        return 0;
    }
    public static double[] calculateGPA(int[] studentIdList, char[][] studentsGrades)
    {
        try {
            CustomLogger.customLogger('i', "Entered calculateGPA");

            int numberStudents = studentIdList.length;
            double[] calcGPA = new double[numberStudents];
            for (int i = 0; i < numberStudents; i++) {
                double score = 0;
                for (var grade : studentsGrades[i]) {
                    score += getScore(grade);
                }
                calcGPA[i] = score/studentsGrades[i].length;
            }
            CustomLogger.customLogger('d', "GPA Calculated");

            return calcGPA;
        }
        catch (Exception e)
        {
            CustomLogger.customLogger('e', "Error in function calculateGPA " + e.toString());
            return new double[0];
        }
    }

    public static int[] getStudentsByGPA(double lower, double higher, int[] studentIdList, char[][] studentsGrade)
    {
        try {
            CustomLogger.customLogger('i', "Entered getStudentsByGPA");
            if (higher < 0 || lower < 0 || higher < lower) {
                return new int[0];
            }
            List<Integer> qualifiedStudents = new ArrayList<>();
            double[] calculatedGPA = calculateGPA(studentIdList, studentsGrade);

            for (int it = 0;it<calculatedGPA.length;it++) {
                if (calculatedGPA[it] >= lower && calculatedGPA[it] <= higher) {
                    qualifiedStudents.add(studentIdList[it]);

                }
            }

            int numberGPA = qualifiedStudents.size();
            int[] finalQualified = new int[numberGPA];
            int index = 0;
            for (var finalStudent : qualifiedStudents) {
                finalQualified[index++] = finalStudent;
            }
            CustomLogger.customLogger('d', "Number of students qualified calculated");

            return finalQualified;
        }
        catch (Exception e)
        {
            CustomLogger.customLogger('e', "Error in function getStudentsByGPA " + e.toString());
            return new int[0];
        }
    }

}
