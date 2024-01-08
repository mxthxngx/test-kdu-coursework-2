package org.handson;

import java.util.Scanner;

import static java.lang.System.exit;

public class Main {
    Student getStudentDetail() {

        Student student = new Student();


        int id = 1;
        student.setID(id);

        String name = "mathangi";
        student.setName(name);


        int age = 21;
        student.setAge(age);


        char letter = 'A';
        student.setGrade(letter);
        return student;

    }

    void Update(StudentRepo studentRepoobj) {


        int ID = 1;
        Student student = studentRepoobj.getStudent(ID);
        if (student == null) {
            System.out.println("User not found");
            return;
        }
        int newage = 45;
        studentRepoobj.updateStudentAge(newage, student);

        char letter = 'B';
        studentRepoobj.updateStudentGrade(letter, student);


    }

    void Retrieve(StudentRepo studentRepoObj) {


        int ID = 1;
        Student st1 = studentRepoObj.getStudent(ID);
        if (st1 != null)
            CustomLogging.getLoggerCustom().info("\nID: %d Name %s Grade %s" ,st1.getID(), st1.getName() , st1.getGrade() + " Age:" + st1.getAge());

        else
            System.out.println("Unable to find user");


        String name = "mathangi";
        Student st = studentRepoObj.getStudent(name);
        if (st != null)
            CustomLogging.getLoggerCustom().info("\nID: %d Name %s Grade %s" ,st1.getID(), st1.getName() , st1.getGrade() + " Age:" + st1.getAge());
        else
            CustomLogging.getLoggerCustom().error("Unable to find user");


    }

    public static void main(String[] args) {


        Main driver = new Main();

        try {
            StudentRepo studentRepoobj = new StudentRepo();

            Student student = driver.getStudentDetail();
            studentRepoobj.addStudent(student);

            driver.Update(studentRepoobj);

            driver.Retrieve(studentRepoobj);


        } catch (Exception e) {
            System.out.println("Something went wrong\n");
        }


    }
}
