package org.handson.question4;


import org.handson.question1and2.Constants;

public class SilverPlan extends HealthInsurancePlan {
    private static final double coverage = Constants.SILVERPLAN;
    private static final double premium = Constants.SILVERPLAN_PREMIUM;
    public SilverPlan() {
        super(coverage,premium);
    }

    /**
     * A description of the entire Java function.
     *
     * @param  salary    description of parameter
     * @param  age       description of parameter
     * @param  smoking   description of parameter
     * @return           description of return value
     */
    @Override
    double computeMonthlypremium(double salary,int age, boolean smoking) {
        InsuranceBrand insuranceBrand = getOfferedBy();
        return salary*premium+insuranceBrand.computeMonthlypremium(this,age,smoking);
    }
}
