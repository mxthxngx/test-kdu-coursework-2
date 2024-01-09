package org.handson;

public class Main {
    public static void main(String[] args) {


        int[] answer = StudentUtil.getStudentsByGPA(Constants.lowerLimitGPA,Constants.higherLimitGPA,Constants.studentIDList,Constants.studentsGrades);

        for(int i = 0; i<answer.length;i++)
        {
            CustomLogger.customLogger('d',"student: "+answer[i]);
        }
    }
}