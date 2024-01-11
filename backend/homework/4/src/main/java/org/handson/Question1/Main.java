package org.handson.Question1;

import org.handson.MyLogger;

public class Main {
    public static void main(String[] args) {


        int[] answer = StudentUtil.getStudentsByGPA(Constants.LOWERLIMITGPA,Constants.HIGHERLIMITGPA,Constants.STUDENT_ID_LIST,Constants.STUDENTS_GRADES);

        for(int i = 0; i<answer.length;i++)
        {
            MyLogger.customLogger("student: "+answer[i],"DEBUG");
        }
    }
}