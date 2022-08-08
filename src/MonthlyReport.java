import java.util.ArrayList;
import java.util.HashMap;

public class MonthlyReport {

    HashMap<Integer,ArrayList<MonthlyRecord>> monthlyReport = new HashMap<>();
    ArrayList<MonthlyRecord> monthlyRecords = new ArrayList<>();


    void createReportMonth(){
        if (!monthlyReport.isEmpty()) {
            for (Integer key : monthlyReport.keySet()){
                System.out.println("Отчет за месяц " + key);
                getMaxProfitMonth(key);
                getMaxExpenseMonth(key);
            }
        }else{
            System.out.println("Месячные отчеты не прочитаны, сначала выберите команду 1");
        }
    }
    int getSumOutcomeMonth(int month){
        int outcomeMonth = 0;
        monthlyRecords = monthlyReport.get(month);
        for (int i = 0; i < monthlyRecords.size(); i++){
            if (monthlyRecords.get(i).isExpense){
                outcomeMonth += (monthlyRecords.get(i).quantity * monthlyRecords.get(i).sumOfOne);
            }
        }return outcomeMonth;
    }
    int getSumIncomeMonth(int month){
        int incomeMonth = 0;
        monthlyRecords = monthlyReport.get(month);
        for (int i = 0; i < monthlyRecords.size(); i++){
            if (!monthlyRecords.get(i).isExpense){
                incomeMonth += (monthlyRecords.get(i).quantity * monthlyRecords.get(i).sumOfOne);
            }
        }return incomeMonth;
    }

    void getMaxProfitMonth (int key){
        monthlyRecords = monthlyReport.get(key);
        int saveMax = 0;
        int maxProfit = 0;
        String nameSaveProfit = null;
        String nameMaxProfitMonth = null;
        for(int i = 0; i < monthlyRecords.size(); i++) {
            if (!monthlyRecords.get(i).isExpense){
                saveMax = monthlyRecords.get(i).quantity * monthlyRecords.get(i).sumOfOne;
                nameSaveProfit = monthlyRecords.get(i).itemName;
                if(maxProfit <= saveMax){
                    maxProfit = saveMax;
                    nameMaxProfitMonth = nameSaveProfit;
                }
            }
        }
        System.out.println("Самая большая прибыль - " + nameMaxProfitMonth + ". На сумму - " + maxProfit);
    }

    void getMaxExpenseMonth (int key){
        monthlyRecords = (monthlyReport.get(key));
        int saveExpense = 0;
        int maxExpense = 0;
        String nameSaveExpense = null;
        String nameMaxExpense = null;
        for(int i = 0; i < monthlyRecords.size(); i++) {
            if (monthlyRecords.get(i).isExpense){
                saveExpense = monthlyRecords.get(i).quantity * monthlyRecords.get(i).sumOfOne;
                nameSaveExpense = monthlyRecords.get(i).itemName;
                if(maxExpense <= saveExpense){
                    maxExpense = saveExpense;
                    nameMaxExpense = nameSaveExpense;
                }
            }
        }
        System.out.println("Самая большая трата - " + nameMaxExpense + ". На сумму - " + maxExpense);
    }
    void readReportMonth(){
        for (int i = 1; i < 4; i++) {
            getReportMonth(i);
            monthlyReport.put(i,monthlyRecords);
            System.out.println("Отчет за месяц " + i + " прочитан");
        }
    }

    void getReportMonth(int monthNumber){
        String content = ReadFile.readFileContentsOrNull("resources/m.20210"+monthNumber+".csv");
        String[] lines = content.split("\n");
        monthlyRecords = new ArrayList<>();
        for (int i = 1; i < lines.length; i++){
            String [] line = lines[i].split(",");
            String itemName = line[0];
            boolean isExpense = Boolean.parseBoolean(line[1]);
            int quantity = Integer.parseInt(line[2]);
            int sumOfOne = Integer.parseInt(line[3]);
            MonthlyRecord monthlyRecord = new MonthlyRecord(itemName, isExpense, quantity, sumOfOne, monthNumber);
            monthlyRecords.add(monthlyRecord);

        }
    }

}
