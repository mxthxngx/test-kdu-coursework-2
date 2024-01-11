package org.handson.Question1;

import java.util.*;

import org.handson.MyLogger;

public class StudentUtil
{
    private StudentUtil(){}
    /**
     * Get the score value based on the given grade.
     *
     * @param  grade  the grade to get the score for
     * @return        the score value corresponding to the grade
     */
    private static int getScore(char grade)
    {
        switch(grade)
        {
            case 'A' -> {
                return Constants.GRADE_A_VALUE;
            }
            case 'B' -> {
                return Constants.GRADE_B_VALUE;
            }
            case 'C' -> {
                return Constants.GRADE_C_VALUE;
            }
            default -> {
                return 0;
            }
        }
    }
    /**
     * Calculates the GPA for a list of students based on their grades.
     *
     * @param  studentIdList      an array of student IDs
     * @param  studentsGrades     a 2D array of student grades
     * @throws MissingGradeException  if a grade is missing for a student
     * @return                    an array of calculated GPAs for each student
     */
    public static double[] calculateGPA(int[] studentIdList, char[][] studentsGrades) throws MissingGradeException
    {
        
            MyLogger.customLogger("Entered calculateGPA","INFO");
            if(studentIdList.length != studentsGrades.length)
            {
                throw new IllegalArgumentException("studentIdList & studentsGrades are out-of-sync. studentIdList.length: " + studentIdList.length + ", studentsGrades.length: " + studentsGrades.length);
            }
            int numberStudents = studentIdList.length;
            double[] calcGPA = new double[numberStudents];
            for (int i = 0; i < numberStudents; i++) {
                double score = 0;
                for (var grade : studentsGrades[i]) {
                    if(grade==' ')
                    {
                        throw  new MissingGradeException(studentIdList[i],"Grade Missing of Student ID: ",new Exception());
                    }
                    score += getScore(grade);
                }
                calcGPA[i] = score/studentsGrades[i].length;
            }
            MyLogger.customLogger( "GPA Calculated","DEBUG");

            return calcGPA;
        
        
    }

    /**
     * Retrieves an array of student IDs whose GPA falls within the specified range.
     *
     * @param  lower            the lower bound of the GPA range
     * @param  higher           the upper bound of the GPA range
     * @param  studentIdList    the array of student IDs
     * @param  studentsGrade    the 2D array representing students' grades
     * @return                  an array of student IDs whose GPA falls within the specified range
     */
    public static int[] getStudentsByGPA(double lower, double higher, int[] studentIdList, char[][] studentsGrade)
    {
        try {
            MyLogger.customLogger( "Entered getStudentsByGPA","INFO");
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
            MyLogger.customLogger( "Number of students qualified calculated","DEBUG");

            return finalQualified;
        }
        catch (Exception e) {
            MyLogger.customLogger("Error in function getStudentsByGPA " + e.toString(), "ERROR");
            throw new InvalidDataException("Invalid Data encountered while calculating GPA", e);
        }
    }

}
