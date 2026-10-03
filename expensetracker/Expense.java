package expensetracker;


import java.time.LocalDateTime;

public class Expense {
    private int id;
    private String description;
    private double amount;
    private ExpenseCategory category;
    private LocalDateTime date;

    public Expense(int id, String description, double amount, ExpenseCategory category, LocalDateTime date) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.category = category;
        this.date = date;
    }

    @Override
    public String toString() {
        return "id : "+id+
                ", Description : "+description+
                ", Amount : "+amount+
                ", Category : "+category+
                " Date : "+date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public ExpenseCategory getCategory() {
        return category;
    }

    public void setCategory(ExpenseCategory category) {
        this.category = category;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
