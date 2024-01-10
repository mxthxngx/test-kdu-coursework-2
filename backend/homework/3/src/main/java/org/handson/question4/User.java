package org.handson.question4;

import java.util.logging.Logger;

/**
 * Represents a user.
 */
public class User {

    private static final Logger LOGGER = Logger.getLogger(User.class.getName());

    private String firstName;
    private int age;
    private boolean doesSmoke;
    private String lastName;
    private boolean insured;
    private HealthInsurancePlan healthInsurancePlan;
    private String gender;
    private String email;
    private long id;

    /**
     * Retrieves the ID of the object.
     *
     * @return the ID of the object
     */
    public long getId() {
        LOGGER.info("Entered getId");
        return id;
    }

    /**
     * Sets the ID of the object.
     *
     * @param id the new ID value
     */
    public void setId(long id) {
        LOGGER.info("Entered setId");
        this.id = id;
    }

    /**
     * Retrieves the first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        LOGGER.info("Entered getFirstName");
        return firstName;
    }

    /**
     * Sets the first name of the object.
     *
     * @param firstName the new first name to be set
     */
    public void setFirstName(String firstName) {
        LOGGER.info("Entered setFirstName");
        this.firstName = firstName;
    }

    /**
     * Retrieves the last name of the object.
     *
     * @return the last name of the object
     */
    public String getLastName() {
        LOGGER.info("Entered getLastName");
        return lastName;
    }

    /**
     * Sets the last name of a person.
     *
     * @param lastName the last name to set
     */
    public void setLastName(String lastName) {
        LOGGER.info("Entered setLastName");
        this.lastName = lastName;
    }

    /**
     * Retrieves the gender of the person.
     *
     * @return the gender of the person
     */
    public String getGender() {
        LOGGER.info("Entered getGender");
        return gender;
    }

    /**
     * Sets the gender of the object.
     *
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        LOGGER.info("Entered setGender");
        this.gender = gender;
    }

    /**
     * Returns the email associated with this object.
     *
     * @return the email associated with this object
     */
    public String getEmail() {
        LOGGER.info("Entered getEmail");
        return email;
    }

    /**
     * Sets the email address.
     *
     * @param email the email address to be set
     */
    public void setEmail(String email) {
        LOGGER.info("Entered setEmail");
        this.email = email;
    }

    /**
     * Returns whether the object is insured or not.
     *
     * @return true if the object is insured, false otherwise
     */
    public boolean isInsured() {
        LOGGER.info("Entered isInsured");
        return insured;
    }

    /**
     * Sets the value of the 'insured' property.
     *
     * @param insured the new value for the 'insured' property
     */
    public void setInsured(boolean insured) {
        LOGGER.info("Entered setInsured");
        this.insured = insured;
    }

    /**
     * Retrieves the health insurance plan associated with this object.
     *
     * @return the health insurance plan
     */
    protected HealthInsurancePlan getInsurancePlan() {
        LOGGER.info("Entered getInsurancePlan");
        return healthInsurancePlan;
    }

    /**
     * Sets the health insurance plan for the user.
     *
     * @param healthInsurancePlan the health insurance plan to set
     */
    public void setInsurancePlan(HealthInsurancePlan healthInsurancePlan) {
        LOGGER.info("Entered setInsurancePlan");
        this.healthInsurancePlan = healthInsurancePlan;
    }

    /**
     * Retrieves the age.
     *
     * @return the age
     */
    public int getAge() {
        LOGGER.info("Entered getAge");
        return age;
    }

    /**
     * Sets the age of the object.
     *
     * @param age the new age value
     */
    public void setAge(int age) {
        LOGGER.info("Entered setAge");
        this.age = age;
    }

    /**
     * Returns the value of the doesSmoke variable.
     *
     * @return true if the object smokes, false otherwise
     */
    public boolean isDoesSmoke() {
        LOGGER.info("Entered isDoesSmoke");
        return doesSmoke;
    }

    /**
     * Sets the value of the "doesSmoke" property.
     *
     * @param doesSmoke the new value for the "doesSmoke" property
     */
    public void setDoesSmoke(boolean doesSmoke) {
        LOGGER.info("Entered setDoesSmoke");
        this.doesSmoke = doesSmoke;
    }
}
