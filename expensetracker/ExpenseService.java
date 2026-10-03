package expensetracker;



import expensetracker.customexception.*;

import java.time.LocalDateTime;
import java.util.*;

public class ExpenseService {
    public void addExpense(ArrayList<Expense> expenseDetails, int id, String description, double amount, String categoryStr, LocalDateTime date, HashMap<ExpenseCategory, Double> categoryWise) throws InvalidStatus {
        for (Expense expense : expenseDetails){
            if (expense.getId() == id){
                throw new DuplicateIDNotAllowed(" Already Expense recorded with id : "+id);
            }
        }
        if (description.isEmpty()){
            throw new DescriptionCannotBeEmpty("Empty Description not allowed");
        }
        if (amount <= 0){
            throw new AmountShouldBePositive("Amount should be positive");
        }
        ExpenseCategory category;
        try {
            category = ExpenseCategory.valueOf(categoryStr.toUpperCase());
        }
        catch (IllegalArgumentException e) {

            throw new InvalidStatus("Invalid expense status");
        }

        Expense expense = new Expense(id,description,amount,category,date);
        expenseDetails.add(expense);

        categoryWise.put(category,categoryWise.getOrDefault(category,0.0)+amount);
    }

    public void displayAllExpenses(ArrayList<Expense> expenseDetails){

        if (expenseDetails.isEmpty()){
            throw new NoExpenseExists("Nothing to display");
        }

        for (Expense expense : expenseDetails){
            System.out.println(expense);
        }


    }

    public Expense findExpenseById(int id, ArrayList<Expense> expenseDetails){

        for (Expense expense : expenseDetails){
            if (expense.getId() == id){
                return expense;
            }
        }


        throw new NoExpenseExists("Nothing to display");

    }

    public void deleteExpense(int id, ArrayList<Expense> expenseDetails, HashMap<ExpenseCategory, Double> categoryWise){

        boolean fount =false;
        Iterator<Expense> iterator = expenseDetails.iterator();

        while (iterator.hasNext()){
            Expense expense = iterator.next();
            if (expense.getId() == id){

                categoryWise.put(expense.getCategory(), categoryWise.get(expense.getCategory()) - expense.getAmount());
                iterator.remove();
                fount = true;
                break;
            }
        }
        if (!fount){
            throw new NoExpenseExists("No expense exists with id "+id);
        }
    }

    public double calculateTotalExpense(ArrayList<Expense> expenseDetails){
        return expenseDetails.stream()
                .mapToDouble(Expense::getAmount)
                .sum();
    }

    public void findHighestExpense(ArrayList<Expense> expenseDetails){

        if (expenseDetails.isEmpty()) {
            throw new NoExpenseExists("No expenses to check");
        }



        Expense highestExpense = expenseDetails.stream()
                .max(Comparator.comparingDouble(Expense::getAmount))
                .get();

        System.out.println("Highest Expense : "+highestExpense);
    }

    public void calculateCategoryExpense(HashMap<ExpenseCategory, Double> categoryWise, String categoryStr) throws InvalidStatus {
        ExpenseCategory category;
        try {
            category = ExpenseCategory.valueOf(categoryStr.toUpperCase());
        }
        catch (IllegalArgumentException e) {

            throw new InvalidStatus(
                    "Invalid Category entered"
            );
        }
        if (!categoryWise.containsKey(category)){
            System.out.println(categoryStr + " : ₹"+0);
            return;
        }
        System.out.println(categoryStr + " : ₹"+categoryWise.get(category));
    }

    public void displayCategoryWiseExpense(HashMap<ExpenseCategory, Double> categoryWise) {

        for (Map.Entry<ExpenseCategory,Double> expense : categoryWise.entrySet()){
            System.out.println(expense.getKey() + " : ₹"+expense.getValue());
        }

    }
}
