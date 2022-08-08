import java.util.ArrayList;
import java.util.HashMap;

public class YearlyReport {
    ArrayList<YearlyRecord> yearlyRecords = new ArrayList<>();
    HashMap<Integer, ArrayList<YearlyRecord>> yearlyReport = new HashMap<>();

    void createReportYear(){
        if (!yearlyRecords.isEmpty()) {
            System.out.println("Отчет за 2021 год");
            getProfitYear();
            getAverageExpense();
            getAverageIncome();
        }else{
            System.out.println("Годовой отчет не прочитан, сначала выберите команду 2");
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
    void getAverageIncome(){
        int income = 0;
        for (int i = 0; i < yearlyReport.size(); i++){
            yearlyRecords = yearlyReport.get(i+1);
            int expense = 0;
            for (int j = 0; j < yearlyRecords.size(); j++) {
                if (!yearlyRecords.get(j).isExpense){
                    income += yearlyRecords.get(j).amount;
                }
            }
        }
        System.out.println("Средний доход за все месяцы составил - " + income/yearlyReport.size());
    }
    void getAverageExpense(){
        int expense = 0;
        for (int i = 0; i < yearlyReport.size(); i++) {
            yearlyRecords = yearlyReport.get(i + 1);
            for (int j = 0; j < yearlyRecords.size(); j++) {
                if (yearlyRecords.get(j).isExpense) {
                    expense += yearlyRecords.get(j).amount;
                }
            }
        }
        System.out.println("Средний расход за все месяцы составил - " + expense/yearlyReport.size());
    }

    void getProfitYear(){
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
            }
            System.out.println("Прибыль за месяц " + (i+1) + ". Составила - " + (profit - expense));
        }
    }
    void readReportYear(){
        for (int i = 1; i < 4; i++) {
            getReportYear(i);
            yearlyReport.put(i,yearlyRecords);
        }
        System.out.println("Отчет за год прочитан");
    }

    void getReportYear(int key){
        String content = ReadFile.readFileContentsOrNull("resources/y.2021.csv");
        String[] lines = content.split("\n");
        yearlyRecords = new ArrayList<>();
        for (int i = 1; i < lines.length; i++){
            String [] line = lines[i].split(",");
            int month = Integer.parseInt(line[0]);
            int amount = Integer.parseInt(line[1]);
            boolean isExpense = Boolean.parseBoolean(line[2]);
            if (month == key) {
                YearlyRecord yearlyRecord = new YearlyRecord(month, amount, isExpense);
                yearlyRecords.add(yearlyRecord);
            }
        }
    }
}