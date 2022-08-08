public class MonthlyRecord {
    String itemName;
    boolean isExpense;
    int quantity;
    int sumOfOne;


    MonthlyRecord(String itemName, boolean isExpense, int quantity, int sumOfOne, int monthNumber) {
        this.itemName = itemName;
        this.isExpense = isExpense;
        this.quantity = quantity;
        this.sumOfOne = sumOfOne;
    }
}
