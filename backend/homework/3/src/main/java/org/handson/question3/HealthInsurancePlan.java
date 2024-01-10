package org.handson.question3;

abstract class HealthInsurancePlan {
    private double coverage;
    private double PREMIUM_PLATINUM_PLAN;
    private InsuranceBrand offeredBy;

    public  HealthInsurancePlan(double coverage,double PREMIUM_PLATINUM_PLAN)
    {
        this.coverage = coverage;this.PREMIUM_PLATINUM_PLAN=PREMIUM_PLATINUM_PLAN;
    }
    /**
     * Retrieves the insurance brand that offers this particular insurance.
     *
     * @return  the insurance brand that offers this particular insurance
     */
    public InsuranceBrand getOfferedBy() {
        return offeredBy;
    }
    abstract double computeMonthlyPREMIUM_PLATINUM_PLAN(double salary);
        /**
     * Sets the insurance brand that offers this product.
     *
     * @param  offeredBy  the insurance brand to set
     */
    public void setOfferedBy(InsuranceBrand offeredBy) {
        this.offeredBy = offeredBy;
    }
    /**
     * Retrieves the coverage value.
     *
     * @return the coverage value
     */
    public double getCoverage() {
        return coverage;
    }
    /**
     * Sets the coverage value for the object.
     *
     * @param  coverage  the coverage value to set
     */
    public void setCoverage(double coverage) {
        this.coverage = coverage;
    }
}
