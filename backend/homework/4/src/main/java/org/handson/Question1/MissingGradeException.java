package org.handson.Question1;

public class MissingGradeException extends Exception {
    private int studentId;

    public int getStudentId() {
        return studentId;
    }

    public MissingGradeException(int studentId, String errorMessage, Throwable err) {
        super(errorMessage + studentId, err);

        this.studentId = studentId;
    }
}
