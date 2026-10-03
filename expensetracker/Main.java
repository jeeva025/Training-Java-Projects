package expensetracker;

import expensetracker.customexception.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Expense> expenses = new ArrayList<>();
        HashMap<ExpenseCategory, Double> categorywise= new HashMap<>();

        ExpenseService expenseService = new ExpenseService();
        int choice;
        do {
            System.out.println("\n----------------------------------------");
            System.out.println("         Personal Expense Tracker       ");
            System.out.println("----------------------------------------");

            System.out.println("1. Add Expense\n" +
                    "2. Display All Expenses\n" +
                    "3. Delete Expense\n" +
                    "4. Calculate Total Expense\n" +
                    "5. Find Highest Expense\n" +
                    "6. Category-wise Expense\n" +
                    "7. Calculate expense for specific category\n"+
                    "8. Exit");

            System.out.println("Enter Your Choice : ");
            choice = sc.nextInt();

            try {
                switch (choice){
                    case 1:
                        System.out.println("---Add Expense---");
                        System.out.print("\nEnter Id : ");
                        int id = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Enter Description : ");
                        String description = sc.nextLine();

                        System.out.print("Enter Expense amount : ");
                        double amount = sc.nextDouble();
                        sc.nextLine();

                        System.out.println("Available Categories: " + Arrays.toString(ExpenseCategory.values()));
                        System.out.print("Enter Category : ");
                        String category = sc.nextLine();

                        LocalDateTime currentDateTime = LocalDateTime.now();

                        expenseService.addExpense(expenses,id,description,amount,category,currentDateTime,categorywise);

                        System.out.println("Expense Added Successfully");
                        break;

                    case 2:
                        System.out.println("----Display All Expenses-----");
                        expenseService.displayAllExpenses(expenses);
                        break;

                    case 3:
                        System.out.println("-----Removing expense----");
                        System.out.print("Enter expense id : ");
                        int removeId = sc.nextInt();

                        expenseService.deleteExpense(removeId, expenses,categorywise);
                        System.out.println("Removed Successfully");
                        break;

                    case 4:
                        System.out.println("----calculate total Expense---");
                        double total = expenseService.calculateTotalExpense(expenses);
                        System.out.println("Total Expense : ₹"+total);
                        break;

                    case 5:
                        System.out.println("---calculate Highest Expense----");

                        expenseService.findHighestExpense(expenses);
                        break;

                    case 6:
                        System.out.println("----Category wise Expenses-----");
                        expenseService.displayCategoryWiseExpense(categorywise);
                        break;

                    case 7:
                        System.out.println("---Calculate total expense for specific category");
                        System.out.println("Available Categories: " + Arrays.toString(ExpenseCategory.values()));
                        sc.nextLine();
                        System.out.println("Enter the category : ");
                        String categoryExpense= sc.nextLine();

                        expenseService.calculateCategoryExpense(categorywise,categoryExpense);



                    case 8:
                        System.out.println("----Thank you-----");
                        break;

                    default:
                        System.out.println("You Entered invalid choice. Please Try again..");


                }
            }
            catch (AmountShouldBePositive | DescriptionCannotBeEmpty | DuplicateIDNotAllowed | InvalidStatus |
                   NoExpenseExists e){
                System.out.println(e.getMessage());

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        
        }
        while(choice != 8);
    }
}
