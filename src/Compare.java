public class Compare {

    static void compare(YearlyReport yearlyReport, MonthlyReport monthlyReport){
        if (yearlyReport.recognizeReportYearIsEmpty()) {
            if (monthlyReport.recognizeReportMonthlyIsEmpty()) {
                compareSumIncome(yearlyReport, monthlyReport);
                compareSumOutcome(yearlyReport, monthlyReport);
            }else {
                System.out.println("Сначала считайте месячный отчёт");
            }
        }else {
            System.out.println("Сначала считайте годовой отчёт");
        }
    }

    private static void compareSumIncome(YearlyReport yearlyReport, MonthlyReport monthlyReport) {
        for (int i = 1; i < 4; i++) {
            if (yearlyReport.getSumIncomeYear(i) != monthlyReport.getSumIncomeMonth(i)){
                System.out.println("В месяце " + i + " обнаружено несоответствие доходов");
            }
        }
        System.out.println("Сверка доходов закончена");

    }
    private static void compareSumOutcome(YearlyReport yearlyReport, MonthlyReport monthlyReport){
        for (int i = 1; i < 4; i++) {
            if (yearlyReport.getSumOutcomeYear(i) != monthlyReport.getSumOutcomeMonth(i)) {
                System.out.println("В месяце " + i + " обнаружено несоответствие расходов");
            }
        }
        System.out.println("Сверка расходов закончена");
    }


}
