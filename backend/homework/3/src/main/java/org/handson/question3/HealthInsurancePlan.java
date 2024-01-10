package org.handson.question3;

/**
 * Represents an abstract health insurance plan.
 */
abstract class HealthInsurancePlan {

    private double coverage;
    private InsuranceBrand offeredBy;

    /**
     * Retrieves the insurance brand that offers this particular insurance.
     *
     * @return the insurance brand that offers this particular insurance
     */
    public InsuranceBrand getOfferedBy() {
        MyLogger.customLogger("Entered getOfferedBy", "INFO");
        return offeredBy;
    }

    /**
     * Sets the insurance brand that offers this product.
     *
     * @param offeredBy the insurance brand to set
     */
    public void setOfferedBy(InsuranceBrand offeredBy) {
        MyLogger.customLogger("Entered setOfferedBy", "INFO");
        this.offeredBy = offeredBy;
        MyLogger.customLogger("Offered by set", "INFO");
    }

    /**
     * Retrieves the coverage value.
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
     * @param coverage the coverage value to set
     */
    public void setCoverage(double coverage) {
        MyLogger.customLogger("Entered setCoverage", "INFO");
        this.coverage = coverage;
        MyLogger.customLogger("Coverage set", "INFO");
    }

    /**
     * Computes the monthly premium based on the given salary.
     *
     * @param salary the salary to compute the monthly premium
     * @return the computed monthly premium
     */
    abstract double computeMonthlyPremium(double salary);
}
