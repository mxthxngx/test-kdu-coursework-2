package org.handson.question4;

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
        return patientId;
    }

    /**
     * Sets the patient ID.
     *
     * @param patientId the new patient ID
     */
    public void setPatientId(long patientId) {
        this.patientId = patientId;
    }
}
