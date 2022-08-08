public class Compare {

    void compareSumIncome(YearlyReport yearlyReport,MonthlyReport monthlyReport) {
        if (!yearlyReport.yearlyReport.isEmpty()) {
            for (int i = 1; i < 4; i++) {
                if (yearlyReport.getSumIncomeYear(i) != monthlyReport.getSumIncomeMonth(i)){
                    System.out.println("В месяце " + i + " обнаружено несоответствие доходов");
                    break;
                }
            }
            System.out.println("Сверка доходов прошла успешно");
        }else{
            System.out.println("Для сравнения, сначала считайте месячный и годовой отчеты.");
        }
    }
    void compareSumOutcome(YearlyReport yearlyReport,MonthlyReport monthlyReport){
        if (!yearlyReport.yearlyReport.isEmpty()) {
            for (int i = 1; i < 4; i++) {
                if (yearlyReport.getSumOutcomeYear(i) != monthlyReport.getSumOutcomeMonth(i)) {
                    System.out.println("В месяце " + i + " обнаружено несоответствие расходов");
                    break;
                }
            }
            System.out.println("Сверка расходов прошла успешно");
        }
    }

}