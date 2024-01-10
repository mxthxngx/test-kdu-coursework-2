package org.handson.question3;

/**
 * Represents a user.
 */
public class User {

    private String firstName;
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
        MyLogger.customLogger("Entered getId", "INFO");
        return id;
    }

    /**
     * Sets the ID of the object.
     *
     * @param id the new ID value
     */
    public void setId(long id) {
        MyLogger.customLogger("Entered setId", "INFO");
        this.id = id;
        MyLogger.customLogger("ID set", "INFO");
    }

    /**
     * Retrieves the first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        MyLogger.customLogger("Entered getFirstName", "INFO");
        return firstName;
    }

    /**
     * Sets the first name of the object.
     *
     * @param firstName the new first name to be set
     */
    public void setFirstName(String firstName) {
        MyLogger.customLogger("Entered setFirstName", "INFO");
        this.firstName = firstName;
        MyLogger.customLogger("First name set", "INFO");
    }

    /**
     * Retrieves the last name of the object.
     *
     * @return the last name of the object
     */
    public String getLastName() {
        MyLogger.customLogger("Entered getLastName", "INFO");
        return lastName;
    }

    /**
     * Sets the last name of the object.
     *
     * @param lastName the last name to set
     */
    public void setLastName(String lastName) {
        MyLogger.customLogger("Entered setLastName", "INFO");
        this.lastName = lastName;
        MyLogger.customLogger("Last name set", "INFO");
    }

    /**
     * Retrieves the gender of the person.
     *
     * @return the gender of the person
     */
    public String getGender() {
        MyLogger.customLogger("Entered getGender", "INFO");
        return gender;
    }

    /**
     * Sets the gender of the object.
     *
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        MyLogger.customLogger("Entered setGender", "INFO");
        this.gender = gender;
        MyLogger.customLogger("Gender set", "INFO");
    }

    /**
     * Returns the email associated with this object.
     *
     * @return the email associated with this object
     */
    public String getEmail() {
        MyLogger.customLogger("Entered getEmail", "INFO");
        return email;
    }

    /**
     * Sets the email address.
     *
     * @param email the email address to be set
     */
    public void setEmail(String email) {
        MyLogger.customLogger("Entered setEmail", "INFO");
        this.email = email;
        MyLogger.customLogger("Email set", "INFO");
    }

    /**
     * Returns whether the object is insured or not.
     *
     * @return true if the object is insured, false otherwise
     */
    public boolean isInsured() {
        MyLogger.customLogger("Entered isInsured", "INFO");
        return insured;
    }

    /**
     * Sets the value of the 'insured' property.
     *
     * @param insured the new value for the 'insured' property
     */
    public void setInsured(boolean insured) {
        MyLogger.customLogger("Entered setInsured", "INFO");
        this.insured = insured;
        MyLogger.customLogger("Insured set", "INFO");
    }

    /**
     * Retrieves the health insurance plan associated with this object.
     *
     * @return the health insurance plan
     */
    protected HealthInsurancePlan getInsurancePlan() {
        MyLogger.customLogger("Entered getInsurancePlan", "INFO");
        return healthInsurancePlan;
    }

    /**
     * Sets the health insurance plan for the user.
     *
     * @param healthInsurancePlan the health insurance plan to set
     */
    public void setInsurancePlan(HealthInsurancePlan healthInsurancePlan) {
        MyLogger.customLogger("Entered setInsurancePlan", "INFO");
        this.healthInsurancePlan = healthInsurancePlan;
        MyLogger.customLogger("Insurance plan set", "INFO");
    }
}
