package org.handson.question1and2;

/**
 * Represents a patient.
 */
public class Patient extends User {

    private long patientId;
    private boolean insured;
    private HealthInsurancePlan healthInsurancePlan;

    /**
     * Gets the patient ID.
     *
     * @return the patient ID
     */
    public long getPatientId() {
        MyLogger.customLogger("Entered getPatientId", "INFO");
        return patientId;
    }

    /**
     * Sets the patient ID.
     *
     * @param patientId the new patient ID
     */
    public void setPatientId(long patientId) {
        MyLogger.customLogger("Entered setPatientId", "INFO");
        this.patientId = patientId;
    }

    /**
     * Checks if the patient is insured.
     *
     * @return true if the patient is insured, false otherwise
     */
    public boolean isInsured() {
        MyLogger.customLogger("Entered isInsured", "INFO");
        return insured;
    }

    /**
     * Sets the insured status of the patient.
     *
     * @param insured the new insured status
     */
    public void setInsured(boolean insured) {
        MyLogger.customLogger("Entered setInsured", "INFO");
        this.insured = insured;
    }

    /**
     * Gets the health insurance plan associated with the patient.
     *
     * @return the health insurance plan
     */
    protected HealthInsurancePlan getInsurancePlan() {
        MyLogger.customLogger("Entered getInsurancePlan", "INFO");
        return healthInsurancePlan;
    }

    /**
     * Sets the health insurance plan for the patient.
     *
     * @param healthInsurancePlan the health insurance plan to be set
     */
    public void setInsurancePlan(HealthInsurancePlan healthInsurancePlan) {
        MyLogger.customLogger("Entered setInsurancePlan", "INFO");
        this.healthInsurancePlan = healthInsurancePlan;
    }
}
