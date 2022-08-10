import java.util.ArrayList;
import java.util.HashMap;

/*  Антон, Привет!
    Спасибо за положительную оценку! :)
    Вроде все указанные тобой моменты поправил, надеюсь код стал только чище!
*/
public class MonthlyReport {

    HashMap<Integer,ArrayList<MonthlyRecord>> monthlyReport = new HashMap<>();


    void createReportMonth(){
        if (recognizeReportMonthlyIsEmpty()) {
            for (Integer key : monthlyReport.keySet()){
                System.out.println("Отчет за " + getMonthName(key));
                String[]maxProfitMonth  = getMaxProfitMonth(key);
                System.out.println("Самая большая прибыль - " + maxProfitMonth[0] + ". На сумму - " + maxProfitMonth[1]);
                String[] maxExpenseMonth = getMaxExpenseMonth(key);
                System.out.println("Самая большая трата - " + maxExpenseMonth[0] + ". На сумму - " + maxExpenseMonth[1]);
            }
        }else{
            System.out.println("Месячные отчеты не прочитаны, сначала выберите команду 1");
        }
    }

    static String getMonthName(int numberMonth){
        String monthName = null;
        if (numberMonth == 1){
            monthName = "Январь";
        }else if (numberMonth == 2){
            monthName = "Февраль";
        }else if (numberMonth == 3){
            monthName = "Март";
        }return monthName;
    }
    boolean recognizeReportMonthlyIsEmpty(){
        if (monthlyReport.isEmpty()) {
            return false;
        }
        else {
            return true;
        }
    }

    int getSumOutcomeMonth(int month){
        int outcomeMonth = 0;
        ArrayList<MonthlyRecord> monthlyRecords = monthlyReport.get(month);
        for (int i = 0; i < monthlyRecords.size(); i++){
            if (monthlyRecords.get(i).isExpense){
                outcomeMonth += (monthlyRecords.get(i).quantity * monthlyRecords.get(i).sumOfOne);
            }
        }return outcomeMonth;
    }
    int getSumIncomeMonth(int month){
        int incomeMonth = 0;
        ArrayList<MonthlyRecord> monthlyRecords = monthlyReport.get(month);
        for (int i = 0; i < monthlyRecords.size(); i++){
            if (!monthlyRecords.get(i).isExpense){
                incomeMonth += (monthlyRecords.get(i).quantity * monthlyRecords.get(i).sumOfOne);
            }
        }return incomeMonth;
    }

    String[] getMaxProfitMonth (int key){
        ArrayList<MonthlyRecord> monthlyRecords = monthlyReport.get(key);
        String[] maxProfitMonth = new String[2];
        int maxProfit = 0;
        String nameMaxProfitMonth = null;
        for(int i = 0; i < monthlyRecords.size(); i++) {
            if (!monthlyRecords.get(i).isExpense){
                int saveMax = monthlyRecords.get(i).quantity * monthlyRecords.get(i).sumOfOne;
                String nameSaveProfit = monthlyRecords.get(i).itemName;
                if(maxProfit <= saveMax){
                    maxProfit = saveMax;
                    nameMaxProfitMonth = nameSaveProfit;
                }
            }
        }
        maxProfitMonth[0] = nameMaxProfitMonth;
        maxProfitMonth[1] = Integer.toString(maxProfit);
        return maxProfitMonth;
    }

    String[] getMaxExpenseMonth (int key){
        ArrayList<MonthlyRecord> monthlyRecords = (monthlyReport.get(key));
        String[] maxExpenseMonth = new String[2];
        int maxExpense = 0;
        String nameMaxExpense = null;
        for(int i = 0; i < monthlyRecords.size(); i++) {
            if (monthlyRecords.get(i).isExpense){
                int saveExpense = monthlyRecords.get(i).quantity * monthlyRecords.get(i).sumOfOne;
                String nameSaveExpense = monthlyRecords.get(i).itemName;
                if(maxExpense <= saveExpense){
                    maxExpense = saveExpense;
                    nameMaxExpense = nameSaveExpense;
                }
            }
        }
        maxExpenseMonth[0] = nameMaxExpense;
        maxExpenseMonth[1] = Integer.toString(maxExpense);
        return maxExpenseMonth;
    }
    void readReportMonth(){
        for (int i = 1; i < 4; i++) {
            ArrayList<MonthlyRecord> monthlyRecords = getReportMonth(i);
            monthlyReport.put(i,monthlyRecords);
            System.out.println("Отчет за " + getMonthName(i) + " прочитан");
        }
    }

    ArrayList getReportMonth(int monthNumber){
        String content = ReadFile.readFileContentsOrNull("resources/m.20210"+monthNumber+".csv");
        String[] lines = content.split("\n");
        ArrayList<MonthlyRecord> monthlyRecords = new ArrayList<>();
        for (int i = 1; i < lines.length; i++){
            String [] line = lines[i].split(",");
            String itemName = line[0];
            boolean isExpense = Boolean.parseBoolean(line[1]);
            int quantity = Integer.parseInt(line[2]);
            int sumOfOne = Integer.parseInt(line[3]);
            MonthlyRecord monthlyRecord = new MonthlyRecord(itemName, isExpense, quantity, sumOfOne);
            monthlyRecords.add(monthlyRecord);
        }
        return monthlyRecords;
    }

}
