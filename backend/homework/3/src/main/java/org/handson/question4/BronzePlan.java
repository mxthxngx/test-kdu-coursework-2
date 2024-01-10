package org.handson.question4;


import org.handson.question1and2.Constants;

public class BronzePlan extends HealthInsurancePlan {
    private static  double COVERAGE = Constants.BRONZEPLAN;
    private static  double PREMIUM = Constants.BRONZEPLAN_PREMIUM;

    public BronzePlan() {
        super(COVERAGE,PREMIUM);
    }

    /**
     * Computes the monthly PREMIUM based on the given salary, age, and smoking status.
     *
     * @param salary   the salary of the individual
     * @param age      the age of the individual
     * @param smoking  indicates if the individual is a smoker
     * @return         the computed monthly PREMIUM
     */
    @Override
    double computeMonthlyPREMIUM(double salary,int age, boolean smoking) {
        InsuranceBrand insuranceBrand = getOfferedBy();
        return salary*PREMIUM+insuranceBrand.computeMonthlyPREMIUM(this,age,smoking);
    }
}
