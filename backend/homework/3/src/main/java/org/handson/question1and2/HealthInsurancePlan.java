package org.handson.question1and2;

public class HealthInsurancePlan {
    private double COVERAGE;

    private InsuranceBrand offeredBy;

    public  HealthInsurancePlan(double COVERAGE)
    {
        this.COVERAGE = COVERAGE;
    }
    /**
     * Returns the InsuranceBrand that offers this particular insurance.
     *
     * @return the InsuranceBrand that offers this particular insurance.
     */
    public InsuranceBrand getOfferedBy() {
        return offeredBy;
    }
    /**
     * Sets the insurance brand that offers this insurance.
     *
     * @param  offeredBy  the insurance brand offering this insurance
     */
    public void setOfferedBy(InsuranceBrand offeredBy) {
        this.offeredBy = offeredBy;
    }
    /**
     * Returns the COVERAGE of the function.
     *
     * @return the COVERAGE of the function
     */
    public double getCOVERAGE() {
        return COVERAGE;
    }
    /**
     * Sets the COVERAGE value for the object.
     *
     * @param  COVERAGE  the new COVERAGE value to be set
     */
    public void setCOVERAGE(double COVERAGE) {
        this.COVERAGE = COVERAGE;
    }
}
