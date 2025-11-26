package orders;

import members.Customer;
import menu.MenuItem;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private static int nextId = 1;

    private int orderId;
    private Customer customer;
    private List<MenuItem> items;
    private double totalAmount;

    public Order(Customer customer) {
        this.orderId = nextId++;
        this.customer = customer;
        this.items = new ArrayList<>();
        this.totalAmount = 0;
    }

    public void addItem(MenuItem item) {
        items.add(item);
    }


    public void calculateTotal() {
        double sum = 0;
        for (MenuItem item : items) {
            sum += item.getPrice();
        }

        customer.addLoyaltyPoints(sum);
        double discount = customer.getDiscount();
        totalAmount = sum - (sum * discount);
    }

    public int getOrderId() {
        return orderId;
    }
    public Customer getCustomer() {
        return customer;
    }
    public List<MenuItem> getItems() {
        return items;
    }
    public double getTotalAmount() {
        return totalAmount;
    }

    public String getOrderSummary() {
        String itemNames = "";
        for (MenuItem item : items) {
            itemNames += item.getName() + " - ";
        }
        if (!itemNames.isEmpty()) {
            itemNames = itemNames.substring(0, itemNames.length() - 3);
        }

        return "Order ID: " + orderId
                + ", Customer: " + customer.getName()
                + ", Total Amount: " + totalAmount
                + "\nItems: " + itemNames;
    }

}
