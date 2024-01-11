package org.handson.question1;

public class MissingGradeException extends Exception {
    private final int studentId; // Change here

    public int getStudentId() {
        return studentId;
    }

    public MissingGradeException(int studentId, String errorMessage, Throwable err) {
        super(errorMessage + studentId, err);

        this.studentId = studentId;
    }
}
