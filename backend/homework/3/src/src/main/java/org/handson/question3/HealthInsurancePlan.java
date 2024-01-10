package org.handson.question3;

abstract class HealthInsurancePlan {
    private double COVERAGE;
    private double PREMIUM;
    private InsuranceBrand offeredBy;

    public  HealthInsurancePlan(double COVERAGE,double PREMIUM)
    {
        this.COVERAGE = COVERAGE;this.PREMIUM=PREMIUM;
    }
    /**
     * Retrieves the insurance brand that offers this particular insurance.
     *
     * @return  the insurance brand that offers this particular insurance
     */
    public InsuranceBrand getOfferedBy() {
        return offeredBy;
    }
    abstract double computeMonthlyPREMIUM(double salary);
        /**
     * Sets the insurance brand that offers this product.
     *
     * @param  offeredBy  the insurance brand to set
     */
    public void setOfferedBy(InsuranceBrand offeredBy) {
        this.offeredBy = offeredBy;
    }
    /**
     * Retrieves the COVERAGE value.
     *
     * @return the COVERAGE value
     */
    public double getCOVERAGE() {
        return COVERAGE;
    }
    /**
     * Sets the COVERAGE value for the object.
     *
     * @param  COVERAGE  the COVERAGE value to set
     */
    public void setCOVERAGE(double COVERAGE) {
        this.COVERAGE = COVERAGE;
    }
}
