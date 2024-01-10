package org.handson.question1and2;


public class SilverPlan extends HealthInsurancePlan{
    private static final double coverage = Constants.SILVERPLAN;
    public SilverPlan() {
        super(coverage);
    }
}
