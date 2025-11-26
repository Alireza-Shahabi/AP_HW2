package services;

import models.User;

public class BankService {

    private UserService userService;

    public BankService(UserService userService) {
        this.userService = userService;
    }

    public void showBalance() {
        User user = userService.getCurrentUser();
        if (user == null) {
            System.out.println("Error: You should login first.");
            return;
        }
        System.out.println("Current balance: " + user.getBalance());
    }

    public void deposit(double amount) {
        User user = userService.getCurrentUser();
        if (user == null) {
            System.out.println("Error: You should login first.");
            return;
        }
        double newBalance = user.getBalance() + amount;
        user.setBalance(newBalance);
        System.out.println("Deposit successful. Current balance: " + newBalance);
    }

    public void withdraw(double amount) {
        User user = userService.getCurrentUser();
        if (user == null) {
            System.out.println("Error: You should login first.");
            return;
        }
        if (user.getBalance() < amount) {
            System.out.println("Error: insufficient balance.");
            return;
        }
        double newBalance = user.getBalance() - amount;
        user.setBalance(newBalance);
        System.out.println("Withdrawal successful. Current balance: " + newBalance);
    }

    public void transfer(String cardNumber, double amount) {
        User sender = userService.getCurrentUser();
        if (sender == null) {
            System.out.println("Error: You should login first.");
            return;
        }

        User receiver = userService.findUserByCardNumber(cardNumber);
        if (receiver == null) {
            System.out.println("Error: invalid card number.");
            return;
        }

        if (sender.getBalance() < amount) {
            System.out.println("Error: insufficient balance.");
            return;
        }

        sender.setBalance(sender.getBalance() - amount);
        receiver.setBalance(receiver.getBalance() + amount);
        System.out.println("Transferred successfully.");
    }
}
