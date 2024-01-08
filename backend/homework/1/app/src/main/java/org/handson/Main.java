package org.handson;



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

    void update(StudentRepo studentRepoobj) {


        int id = 1;
        Student student = studentRepoobj.getStudent(id);
        if (student == null) {
            System.out.println("User not found");
            return;
        }
        int newage = 45;
        studentRepoobj.updateStudentAge(newage, student);

        char letter = 'B';
        studentRepoobj.updateStudentGrade(letter, student);


    }

    void retrieve(StudentRepo studentRepoObj) {


        int id = 1;
        Student st1 = studentRepoObj.getStudent(id);
        if (st1 != null)
            CustomLogging.getLoggerCustom().info("\nID: %d Name %s Grade %s  Age %d" ,st1.getID(), st1.getName() , st1.getGrade() , st1.getAge());

        else
            System.out.println("Unable to find user");


        String name = "mathangi";
        Student st = studentRepoObj.getStudent(name);
        if (st != null)
            CustomLogging.getLoggerCustom().info("\nID: %d Name %s Grade %s Age %d" ,st.getID(), st.getName() , st.getGrade(),st.getAge());
        else
            CustomLogging.getLoggerCustom().error("Unable to find user");


    }

    public static void main(String[] args) {


        Main driver = new Main();

        try {
            StudentRepo studentRepoobj = new StudentRepo();

            Student student = driver.getStudentDetail();
            studentRepoobj.addStudent(student);

            driver.update(studentRepoobj);

            driver.retrieve(studentRepoobj);


        } catch (Exception e) {
           CustomLogging.getLoggerCustom().error("Something went wrong\n");
        }


    }
}
