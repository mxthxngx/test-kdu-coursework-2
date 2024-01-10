package org.handson.question3;

import org.handson.question1and2.Constants;

public class PlatinumPlan extends HealthInsurancePlan {
private static final double COVERAGE = Constants.PLATINUMPLAN;
    private static final double PREMIUM = Constants.PLATINUMPLAN_PREMIUM;
    public PlatinumPlan() {
        super(COVERAGE,PREMIUM);
    }
    /**
     * A description of the entire Java function.
     *
     * @param  salary  description of parameter
     * @return         description of return value
     */
    @Override
    double computeMonthlyPREMIUM(double salary) {
        return salary*PREMIUM;
    }
}
