package org.handson.question1and2;

/**
 * Represents a staff member.
 */
public class Staff extends User {
    private long staffId;
    private int yearsOfExperience;
    private String description;
    private double salary;

    /**
     * Retrieves the staff ID.
     *
     * @return the staff ID.
     */
    public long getStaffId() {
        MyLogger.customLogger("Entered getStaffId", "INFO");
        return staffId;
    }

    /**
     * Sets the staff ID for the object.
     *
     * @param staffId the new staff ID to set
     */
    public void setStaffId(long staffId) {
        MyLogger.customLogger("Entered setStaffId", "INFO");
        this.staffId = staffId;
    }

    /**
     * Retrieves the number of years of experience.
     *
     * @return the number of years of experience
     */
    public int getYearsOfExperience() {
        MyLogger.customLogger("Entered getYearsOfExperience", "INFO");
        return yearsOfExperience;
    }

    /**
     * Sets the number of years of experience.
     *
     * @param yearsOfExperience the number of years of experience to set
     */
    public void setYearsOfExperience(int yearsOfExperience) {
        MyLogger.customLogger("Entered setYearsOfExperience", "INFO");
        this.yearsOfExperience = yearsOfExperience;
    }

    /**
     * Retrieves the description of the object.
     *
     * @return description
     */
    public String getDescription() {
        MyLogger.customLogger("Entered getDescription", "INFO");
        return description;
    }

    /**
     * Sets the description of the object.
     *
     * @param description the new description for the object
     */
    public void setDescription(String description) {
        MyLogger.customLogger("Entered setDescription", "INFO");
        this.description = description;
    }

    /**
     * Retrieves the salary of the employee.
     *
     * @return the salary of the employee
     */
    public double getSalary() {
        MyLogger.customLogger("Entered getSalary", "INFO");
        return salary;
    }

    /**
     * Sets the salary of the object.
     *
     * @param salary the new salary to be set
     */
    public void setSalary(double salary) {
        MyLogger.customLogger("Entered setSalary", "INFO");
        this.salary = salary;
    }
}
