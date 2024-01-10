package org.handson.question3;

/**
 * Represents a staff member, extending the User class.
 */
public class Staff extends User {
    private long staffId;
    private int yearsOfExperience;
    private String description;
    private double salary;

    /**
     * Get the staff ID.
     *
     * @return the staff ID
     */
    public long getStaffId() {
        MyLogger.customLogger("Entered getStaffId", "INFO");
        return staffId;
    }

    /**
     * Sets the staff ID for this object.
     *
     * @param staffId the staff ID to set
     */
    public void setStaffId(long staffId) {
        MyLogger.customLogger("Entered setStaffId", "INFO");
        this.staffId = staffId;
        MyLogger.customLogger("Staff ID set", "INFO");
    }

    /**
     * Gets the number of years of experience.
     *
     * @return the number of years of experience
     */
    public int getYearsOfExperience() {
        MyLogger.customLogger("Entered getYearsOfExperience", "INFO");
        return yearsOfExperience;
    }

    /**
     * Sets the years of experience.
     *
     * @param yearsOfExperience the number of years of experience to set
     */
    public void setYearsOfExperience(int yearsOfExperience) {
        MyLogger.customLogger("Entered setYearsOfExperience", "INFO");
        this.yearsOfExperience = yearsOfExperience;
        MyLogger.customLogger("Years of Experience set", "INFO");
    }

    /**
     * Retrieves the description of the entire Java function.
     *
     * @return a string representing the description of the function
     */
    public String getDescription() {
        MyLogger.customLogger("Entered getDescription", "INFO");
        return description;
    }

    /**
     * Sets the description for the object.
     *
     * @param description the new description for the object
     */
    public void setDescription(String description) {
        MyLogger.customLogger("Entered setDescription", "INFO");
        this.description = description;
        MyLogger.customLogger("Description set", "INFO");
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
     * Sets the salary for the object.
     *
     * @param salary the new salary to be set
     */
    public void setSalary(double salary) {
        MyLogger.customLogger("Entered setSalary", "INFO");
        this.salary = salary;
        MyLogger.customLogger("Salary set", "INFO");
    }
}
