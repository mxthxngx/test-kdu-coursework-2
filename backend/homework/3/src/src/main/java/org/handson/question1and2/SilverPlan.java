package org.handson.question1and2;


public class SilverPlan extends HealthInsurancePlan{
    private static final double COVERAGE = Constants.SILVERPLAN;
    public SilverPlan() {
        super(COVERAGE);
    }
}
