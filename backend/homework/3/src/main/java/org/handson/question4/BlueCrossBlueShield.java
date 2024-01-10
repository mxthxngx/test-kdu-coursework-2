package org.handson.question4;

public class BlueCrossBlueShield implements InsuranceBrand {
    /**
     * Computes the monthly premiumAmount for a given health insurance plan, age, and smoking status.
     *
     * @param insurancePlan the health insurance plan
     * @param age           the age of the individual
     * @param smoking       the smoking status of the individual
     * @return the computed monthly premiumAmount
     */
    public double computeMonthlyPremium(HealthInsurancePlan insurancePlan, int age, boolean smoking) {
        MyLogger.customLogger("Entered computeMonthlyPremium", "INFO");

        Double coverage = insurancePlan.getCoverage();
        MyLogger.customLogger("Coverage retrieved: " + coverage, "INFO");

        double premiumAmount = 0;

        switch (coverage.toString()) {
            case "0.9" -> {
                premiumAmount += age > 55 ? 200 : 0;
                premiumAmount += smoking ? 100 : 0;
            }
            case "0.8" -> {
                premiumAmount += age > 55 ? 150 : 0;
                premiumAmount += smoking ? 90 : 0;
            }
            case "0.7" -> {
                premiumAmount += age > 55 ? 100 : 0;
                premiumAmount += smoking ? 80 : 0;
            }
            case "0.6" -> {
                premiumAmount += age > 55 ? 50 : 0;
                premiumAmount += smoking ? 70 : 0;
            }
            default -> premiumAmount = 0;
        }

        MyLogger.customLogger("Computed premiumAmount: " + premiumAmount, "INFO");

        return premiumAmount;
    }
}
