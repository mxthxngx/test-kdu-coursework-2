package org.handson.question4;


import org.handson.question1and2.Constants;

public class SilverPlan extends HealthInsurancePlan {
    private static final double COVERAGE = Constants.SILVERPLAN;
    private static final double PREMIUM = Constants.SILVERPLAN_PREMIUM;
    public SilverPlan() {
        super(COVERAGE,PREMIUM);
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
    double computeMonthlyPREMIUM(double salary,int age, boolean smoking) {
        InsuranceBrand insuranceBrand = getOfferedBy();
        return salary*PREMIUM+insuranceBrand.computeMonthlyPREMIUM(this,age,smoking);
    }
}
