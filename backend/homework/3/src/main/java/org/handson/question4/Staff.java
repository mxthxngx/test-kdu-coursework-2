package org.handson.question4;

import java.util.logging.Logger;

/**
 * Represents a staff member, extending the User class.
 */
public class Staff extends User {
    private static final Logger LOGGER = Logger.getLogger(Staff.class.getName());

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
        LOGGER.info("Entered getStaffId");
        return staffId;
    }

    /**
     * Sets the staff ID for the object.
     *
     * @param staffId the new staff ID to set
     */
    public void setStaffId(long staffId) {
        LOGGER.info("Entered setStaffId");
        this.staffId = staffId;
    }

    /**
     * Retrieves the number of years of experience.
     *
     * @return the number of years of experience
     */
    public int getYearsOfExperience() {
        LOGGER.info("Entered getYearsOfExperience");
        return yearsOfExperience;
    }

    /**
     * Sets the number of years of experience.
     *
     * @param yearsOfExperience the number of years of experience to set
     */
    public void setYearsOfExperience(int yearsOfExperience) {
        LOGGER.info("Entered setYearsOfExperience");
        this.yearsOfExperience = yearsOfExperience;
    }

    /**
     * Retrieves the description of the object.
     *
     * @return description of the object
     */
    public String getDescription() {
        LOGGER.info("Entered getDescription");
        return description;
    }

    /**
     * Sets the description of the object.
     *
     * @param description the new description for the object
     */
    public void setDescription(String description) {
        LOGGER.info("Entered setDescription");
        this.description = description;
    }

    /**
     * Retrieves the salary of the employee.
     *
     * @return the salary of the employee
     */
    public double getSalary() {
        LOGGER.info("Entered getSalary");
        return salary;
    }

    /**
     * Sets the salary of the object.
     *
     * @param salary the new salary to be set
     */
    public void setSalary(double salary) {
        LOGGER.info("Entered setSalary");
        this.salary = salary;
    }
}
