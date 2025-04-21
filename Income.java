
public class Income {
    private String type;
    private static double amount;

    public Income (String type, double amount) {
        this.type = type;
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public static double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Income from " + type + ": $" + amount;
    }


}
