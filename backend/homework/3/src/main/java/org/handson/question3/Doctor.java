package org.handson.question3;

/**
 * Represents a doctor, extending the Staff class.
 */
public class Doctor extends Staff {

    private long doctorId;
    private String specialization;

    /**
     * Retrieves the specialization of the object.
     *
     * @return the specialization of the object
     */
    public String getSpecialization() {
        MyLogger.customLogger("Entered getSpecialization", "INFO");
        return specialization;
    }

    /**
     * Sets the specialization of the object.
     *
     * @param specialization the new specialization for the object
     */
    public void setSpecialization(String specialization) {
        MyLogger.customLogger("Entered setSpecialization", "INFO");
        this.specialization = specialization;
        MyLogger.customLogger("Specialization set", "INFO");
    }

    /**
     * Retrieves the ID of the doctor.
     *
     * @return the ID of the doctor
     */
    public long getDoctorId() {
        MyLogger.customLogger("Entered getDoctorId", "INFO");
        return doctorId;
    }

    /**
     * Sets the doctor ID.
     *
     * @param doctorId the ID of the doctor
     */
    public void setDoctorId(long doctorId) {
        MyLogger.customLogger("Entered setDoctorId", "INFO");
        this.doctorId = doctorId;
        MyLogger.customLogger("Doctor ID set", "INFO");
    }
}
