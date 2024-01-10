package org.handson.question4;


import org.handson.question1and2.Constants;

public class SilverPlan extends HealthInsurancePlan {
    private static final double coverageSilverPlan = Constants.SILVERPLAN;
    private static final double premiumSilverPlan = Constants.SILVERPLAN_PREMIUM;
    public SilverPlan() {
        super(coverageSilverPlan,premiumSilverPlan);
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
        return salary*premiumSilverPlan+insuranceBrand.computeMonthlypremium(this,age,smoking);
    }
}
