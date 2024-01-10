package org.handson.question3;

/**
 * Represents a patient, extending the User class.
 */
public class Patient extends User {

    private long patientId;

    /**
     * Retrieves the patient ID.
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
        MyLogger.customLogger("Patient ID set", "INFO");
    }
}
