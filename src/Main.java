import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MonthlyReport monthlyReport = new MonthlyReport();
        YearlyReport yearlyReport = new YearlyReport();

        while (true){
            printMenu();
            byte userInput  = scanner.nextByte();

            if (userInput == 1){
                monthlyReport.readReportMonth();
            }else if (userInput == 2){
                yearlyReport.readReportYear();
            }else if (userInput == 3){
                Compare.compare(yearlyReport,monthlyReport);
            }else if (userInput == 4){
                monthlyReport.createReportMonth();
            }else if (userInput == 5) {
                yearlyReport.createReportYear();
            }else if (userInput == 6){
                System.out.println("Вы вышли из приложения");
                break;
            }else{
                System.out.println("Такой команды не существует, введите от 1 до 5");
            }
        }

    }
    static void printMenu(){
        System.out.println("1. Считать все месячные отчёты");
        System.out.println("2. Считать годовой отчёт");
        System.out.println("3. Сверить отчёты");
        System.out.println("4. Вывести информацию о всех месячных отчётах");
        System.out.println("5. Вывести информацию о годовом отчёте");
        System.out.println("6. Выход из приложения");
        System.out.println("Выберите команду от 1 до 6");
    }
}

