import members.*;
import menu.*;
import orders.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Employee chef = new Employee("Maryam", "09120000001", "Chef", 1000000);
        Employee accountant = new Employee("Reza", "09120000002", "Accountant", 900000);
        Employee waiter = new Employee("Ali", "09120000003", "Waiter", 800000);

        chef.addHoursWorked(170);
        accountant.addHoursWorked(160);
        waiter.addHoursWorked(180);

        List<Employee> employees = new ArrayList<>();
        employees.add(chef);
        employees.add(accountant);
        employees.add(waiter);

        Customer c1 = new Customer("Ali", "09010000001");
        Customer c2 = new Customer("Sara", "09010000002");
        Customer c3 = new Customer("Hamed", "09010000003");
        Customer c4 = new Customer("Neda", "09010000004");
        Customer c5 = new Customer("Reza", "09010000005");

        List<Customer> customers = new ArrayList<>();
        customers.add(c1);
        customers.add(c2);
        customers.add(c3);
        customers.add(c4);
        customers.add(c5);

        Food pizza = new Food("F001", "Pizza", 400000, "Medium", 25);
        Food burger = new Food("F002", "Burger", 300000, "Mild", 15);
        Food pasta = new Food("F003", "Pasta", 350000, "Spicy", 20);

        Beverage tea = new Beverage("B001", "Tea", 60000, "Large", "Hot");
        Beverage coffee = new Beverage("B002", "Coffee", 80000, "Medium", "Hot");
        Beverage soda = new Beverage("B003", "Soda", 50000, "Small", "Cold");

        List<Food> foods = new ArrayList<>();
        foods.add(pizza);
        foods.add(burger);
        foods.add(pasta);

        List<Beverage> beverages = new ArrayList<>();
        beverages.add(tea);
        beverages.add(coffee);
        beverages.add(soda);

        List<Order> allOrders = new ArrayList<>();
        for (Customer customer : customers) {
            for (int i = 0; i < 3; i++) {
                Order order = new Order(customer);

                order.addItem(foods.get(0));
                order.addItem(beverages.get(0));

                order.calculateTotal();
                allOrders.add(order);
            }
        }

        
        for (Order order : allOrders) {
            System.out.println(order.getOrderSummary());
        }

    
        for (Employee e : employees) {
            System.out.println(e.getInfo() + ", Salary: " + e.calculateSalary());
        }

        Customer mostLoyal = customers.get(0);
        for (Customer c : customers) {
            if (c.getLoyaltyPoints() > mostLoyal.getLoyaltyPoints()) {
                mostLoyal = c;
            }
        }
        System.out.println("\nMost Loyal Customer: " + mostLoyal.getInfo());

        
        for (Food f : foods)
            System.out.println(f.getDetails());
        for (Beverage b : beverages)
            System.out.println(b.getDetails());
    }
}
