import java.util.ArrayList;
import java.util.HashMap;

public class YearlyReport {
    ArrayList<YearlyRecord> yearlyRecords = new ArrayList<>();
    HashMap<Integer, ArrayList<YearlyRecord>> yearlyReport = new HashMap<>();

    void createReportYear(){
        if (!yearlyRecords.isEmpty()) {
            System.out.println("Отчет за 2021 год");
            int[][] profitYear = getProfitYear();
            for (int i = 0; i < profitYear.length; i++){
                System.out.println("Прибыль за месяц " + MonthlyReport.getMonthName(i+1) + ". Составила - " + (profitYear[i][1] - profitYear[i][0]));
            }
            int expense = getAverageExpense();
            System.out.println("Средний расход за все месяцы составил - " + expense/yearlyReport.size());
            int income = getAverageIncome();
            System.out.println("Средний доход за все месяцы составил - " + income/yearlyReport.size());
        }else{
            System.out.println("Годовой отчет не прочитан, сначала выберите команду 2");
        }
    }
    boolean recognizeReportYearIsEmpty(){
        if (yearlyReport.isEmpty()) {
            return false;
        }
        else {
            return true;
        }
    }
    int getSumOutcomeYear(int month){
        int outcome = 0;
        yearlyRecords = yearlyReport.get(month);
        for (int j = 0; j < yearlyRecords.size(); j++) {
            if (yearlyRecords.get(j).isExpense){
                outcome += yearlyRecords.get(j).amount;
            }
        }
        return outcome;
    }
    int getSumIncomeYear(int month){
        int income = 0;
        yearlyRecords = yearlyReport.get(month);
            for (int j = 0; j < yearlyRecords.size(); j++) {
                if (!yearlyRecords.get(j).isExpense){
                    income += yearlyRecords.get(j).amount;
                }
            }
        return income;
    }
    int getAverageIncome(){
        int income = 0;
        for (int i = 0; i < yearlyReport.size(); i++){
            yearlyRecords = yearlyReport.get(i+1);
            for (int j = 0; j < yearlyRecords.size(); j++) {
                if (!yearlyRecords.get(j).isExpense){
                    income += yearlyRecords.get(j).amount;
                }
            }
        }
        return income;
    }
    int getAverageExpense(){
        int expense = 0;
        for (int i = 0; i < yearlyReport.size(); i++) {
            yearlyRecords = yearlyReport.get(i + 1);
            for (int j = 0; j < yearlyRecords.size(); j++) {
                if (yearlyRecords.get(j).isExpense) {
                    expense += yearlyRecords.get(j).amount;
                }
            }
        }
        return expense;
    }

    int[][] getProfitYear(){
        int[][] ProfitYear = new int[3][2];
        for (int i = 0; i < yearlyReport.size(); i++){
            yearlyRecords = yearlyReport.get(i+1);
            int profit = 0;
            int expense = 0;
            for (int j = 0; j < yearlyRecords.size(); j++) {
                if (yearlyRecords.get(j).isExpense){
                    expense += yearlyRecords.get(j).amount;
                }else if (!yearlyRecords.get(j).isExpense){
                    profit += yearlyRecords.get(j).amount;
                }
                ProfitYear[i][0] = expense;
                ProfitYear[i][1] = profit;
            }
        }
        return ProfitYear;
    }
    void readReportYear(){
        getReportYear();
        System.out.println("Отчет за год прочитан");
    }

    void getReportYear(){
        String content = ReadFile.readFileContentsOrNull("resources/y.2021.csv");
        String[] lines = content.split("\n");
        for (int i = 1; i < 4; i++) {
            yearlyRecords = new ArrayList<>();
            yearlyReport.put(i, yearlyRecords);
            for (int j = 1; j < lines.length; j++) {
                String[] line = lines[j].split(",");
                int month = Integer.parseInt(line[0]);
                int amount = Integer.parseInt(line[1]);
                boolean isExpense = Boolean.parseBoolean(line[2]);
                if (month == i){
                    YearlyRecord yearlyRecord = new YearlyRecord(month, amount, isExpense);
                    yearlyRecords.add(yearlyRecord);
                }
            }
        }
    }
}