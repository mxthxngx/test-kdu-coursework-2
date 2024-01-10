package org.handson.question4;


import org.handson.question1and2.Constants;

public class SilverPlan extends HealthInsurancePlan {
    private static final double COVERAGE_SILVER_PLAN = Constants.SILVERPLAN;
    private static final double PREMIUM_PLATINUM_PLANSilverPlan = Constants.SILVERPLAN_PREMIUM;
    public SilverPlan() {
        super(COVERAGE_SILVER_PLAN,PREMIUM_PLATINUM_PLANSilverPlan);
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
    double computeMonthlyPREMIUM_PLATINUM_PLAN(double salary,int age, boolean smoking) {
        InsuranceBrand insuranceBrand = getOfferedBy();
        return salary*PREMIUM_PLATINUM_PLANSilverPlan+insuranceBrand.computeMonthlyPREMIUM_PLATINUM_PLAN(this,age,smoking);
    }
}
