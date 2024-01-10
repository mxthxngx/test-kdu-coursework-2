package org.handson.question4;

abstract class HealthInsurancePlan {
    private double COVERAGE;
private double PREMIUM;
    private InsuranceBrand offeredBy;

    public  HealthInsurancePlan(double COVERAGE,double PREMIUM)
    {
        this.COVERAGE = COVERAGE;this.PREMIUM=PREMIUM;
    }

    /**
     * Retrieves the insurance brand that offers this policy.
     *
     * @return  the insurance brand that offers this policy
     */
    public InsuranceBrand getOfferedBy() {
        return offeredBy;
    }
    abstract double computeMonthlyPREMIUM(double salary,int age,boolean smoking);
    /**
     * Sets the insurance brand that offers this insurance.
     *
     * @param  offeredBy  the insurance brand to set
     */
    public void setOfferedBy(InsuranceBrand offeredBy) {
        this.offeredBy = offeredBy;
    }

    /**
     * Gets the COVERAGE of the function.
     *
     * @return the COVERAGE value
     */
    public double getCOVERAGE() {
        return COVERAGE;
    }

    /**
     * Sets the COVERAGE value for the object.
     *
     * @param  COVERAGE   the COVERAGE value to be set
     */
    public void setCOVERAGE(double COVERAGE) {
        this.COVERAGE = COVERAGE;
    }
}
