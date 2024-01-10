package org.handson.question3;

import org.handson.question1and2.Constants;

public class PlatinumPlan extends HealthInsurancePlan {
private static final double coveragePlatinum = Constants.PLATINUMPLAN;
    private static final double premium = Constants.PLATINUMPLAN_PREMIUM;
    public PlatinumPlan() {
        super(coveragePlatinum,premium);
    }
    /**
     * A description of the entire Java function.
     *
     * @param  salary  description of parameter
     * @return         description of return value
     */
    @Override
    double computeMonthlypremium(double salary) {
        return salary*premium;
    }
}
