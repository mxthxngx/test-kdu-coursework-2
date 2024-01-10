package org.handson.question4;


import org.handson.question1and2.Constants;

public class SilverPlan extends HealthInsurancePlan {
    private static final double COVERAGE_SILVER_PLAN = Constants.SILVERPLAN;
    private static final double PREMIUM_SILVER_PLAN = Constants.SILVERPLAN_PREMIUM;
    @Override
public double getCoverage()
    {
        return COVERAGE_SILVER_PLAN;
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
   public double computeMonthlyPremium(double salary,int age, boolean smoking) {
        InsuranceBrand insuranceBrand = getOfferedBy();
        return salary*PREMIUM_SILVER_PLAN+insuranceBrand.computeMonthlyPremium(this,age,smoking);
    }
}
