package org.handson.question4;

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
     * @param specialization the specialization to be set
     */
    public void setSpecialization(String specialization) {
        MyLogger.customLogger("Entered setSpecialization", "INFO");
        this.specialization = specialization;
        MyLogger.customLogger("Specialization set", "INFO");
    }

    /**
     * Retrieves the doctor ID.
     *
     * @return the doctor ID
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
