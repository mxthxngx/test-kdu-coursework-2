package org.handson.question4;
abstract class HealthInsurancePlan {
    private double coverage;
    private InsuranceBrand offeredBy;

    /**
     * Retrieves the insurance brand that offers this policy.
     *
     * @return the insurance brand that offers this policy
     */
    public InsuranceBrand getOfferedBy() {
        MyLogger.customLogger("Entered getOfferedBy", "INFO");
        return offeredBy;
    }

    /**
     * Sets the insurance brand that offers this insurance.
     *
     * @param offeredBy the insurance brand to set
     */
    public void setOfferedBy(InsuranceBrand offeredBy) {
        MyLogger.customLogger("Entered setOfferedBy", "INFO");
        this.offeredBy = offeredBy;
    }

    /**
     * Gets the coverage of the function.
     *
     * @return the coverage value
     */
    public double getCoverage() {
        MyLogger.customLogger("Entered getCoverage", "INFO");
        return coverage;
    }

    /**
     * Sets the coverage value for the object.
     *
     * @param coverage the coverage value to be set
     */
    public void setCoverage(double coverage) {
        MyLogger.customLogger("Entered setCoverage", "INFO");
        this.coverage = coverage;
    }

    /**
     * Computes the monthly premium for an insurance policy based on the salary, age, and smoking status.
     *
     * @param salary   the salary of the policyholder
     * @param age      the age of the policyholder
     * @param smoking  true if the policyholder is a smoker, false otherwise
     * @return the computed monthly premium for the insurance policy
     */
    public double computeMonthlyPremium(double salary, int age, boolean smoking) {
        MyLogger.customLogger("Entered computeMonthlyPremium", "INFO");
        // Default implementation (you can override this in subclasses)
        return 0.0;
    }
}
