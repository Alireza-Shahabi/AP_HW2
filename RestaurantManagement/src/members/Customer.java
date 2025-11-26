package members;

public class Customer extends Person {
    private static int nextId = 1;
    private String customerId;
    private int loyaltyPoints;

    public Customer(String name, String phoneNumber) {
        super(name, phoneNumber);
        this.customerId = String.format("C%03d", nextId++);
        this.loyaltyPoints = 0;
    }

    public String getCustomerId() {
        return customerId;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }
    public void setLoyaltyPoints(int loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }

    public void addLoyaltyPoints(double totalPurchase) {
        if (totalPurchase > 1000000) {
            loyaltyPoints += 2;
        } else if (totalPurchase > 500000) {
            loyaltyPoints += 1;
        }
    }

    public double getDiscount() {
        if (loyaltyPoints > 5) {
            return 0.10;
        } else if (loyaltyPoints > 3) {
            return 0.05;
        } else {
            return 0.0;
        }
    }

    @Override
    public String getInfo() {
        return "ID: " + customerId
                + ", Name: " + getName()
                + ", Phone: " + getPhoneNumber()
                + ", Loyalty Points: " + loyaltyPoints;
    }
}
