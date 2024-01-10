package org.handson.question3;

/**
 * Represents a nurse, extending the Staff class.
 */
public class Nurse extends Staff {

    private long nurseId;

    /**
     * Retrieves the nurse ID.
     *
     * @return the nurse ID
     */
    public long getNurseId() {
        MyLogger.customLogger("Entered getNurseId", "INFO");
        return nurseId;
    }

    /**
     * Sets the nurse ID.
     *
     * @param nurseId the ID of the nurse
     */
    public void setNurseId(long nurseId) {
        MyLogger.customLogger("Entered setNurseId", "INFO");
        this.nurseId = nurseId;
        MyLogger.customLogger("Nurse ID set", "INFO");
    }
}
