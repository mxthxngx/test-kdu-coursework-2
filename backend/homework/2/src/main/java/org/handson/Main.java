package org.handson;

public class Main {
    public static void main(String[] args) {


        int[] answer = StudentUtil.getStudentsByGPA(Constants.LOWERLIMITGPA,Constants.HIGHERLIMITGPA,Constants.STUDENT_ID_LIST,Constants.STUDENTS_GRADES);

        for(int i = 0; i<answer.length;i++)
        {
            CustomLogger.customLogger('d',"student: "+answer[i]);
        }
    }
}