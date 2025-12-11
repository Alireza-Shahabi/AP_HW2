package services;

import models.User;
import java.util.HashMap;

public class BankService {

    private HashMap<String, User> usersByUsername;
    private HashMap<String, User> usersByCardNumber;
    private User currentUser;

    public BankService(HashMap<String, User> usersByUsername, HashMap<String, User> usersByCardNumber) {
        this.usersByUsername = usersByUsername;
        this.usersByCardNumber = usersByCardNumber;
        this.currentUser = null;
    }

    public void login(String username, String password) {
        User user = usersByUsername.get(username);
        if (user == null) {
            System.out.println("Error: username not found.");
            return;
        }
        if (!user.getPassword().equals(password)) {
            System.out.println("Error: incorrect password.");
            return;
        }
        currentUser = user;
        System.out.println("Login successful.");
    }

    public void logout() {
        if (currentUser == null) {
            System.out.println("Error: no user is logged in.");
        } else {
            currentUser = null;
            System.out.println("Logout successful.");
        }
    }

    public void showBalance() {
        if (currentUser == null) {
            System.out.println("Error: You should login first.");
            return;
        }
        System.out.println("Current balance: " + currentUser.getBalance());
    }

    public void deposit(double amount) {
        if (currentUser == null) {
            System.out.println("Error: You should login first.");
            return;
        }
        currentUser.setBalance(currentUser.getBalance() + amount);
        System.out.println("Deposit successful. Current balance: " + currentUser.getBalance());
    }

    public void withdraw(double amount) {
        if (currentUser == null) {
            System.out.println("Error: You should login first.");
            return;
        }
        if (currentUser.getBalance() < amount) {
            System.out.println("Error: insufficient balance.");
            return;
        }
        currentUser.setBalance(currentUser.getBalance() - amount);
        System.out.println("Withdrawal successful. Current balance: " + currentUser.getBalance());
    }

    public void transfer(String cardNumber, double amount) {
        if (currentUser == null) {
            System.out.println("Error: You should login first.");
            return;
        }

        User receiver = usersByCardNumber.get(cardNumber);
        if (receiver == null) {
            System.out.println("Error: invalid card number.");
            return;
        }

        if (currentUser.getBalance() < amount) {
            System.out.println("Error: insufficient balance.");
            return;
        }

        currentUser.setBalance(currentUser.getBalance() - amount);
        receiver.setBalance(receiver.getBalance() + amount);

        System.out.println("Transfer successful. Your new balance: " + currentUser.getBalance());
    }

    public User getCurrentUser() {
        return currentUser;
    }
}
