package services;

import models.User;
import java.util.ArrayList;

public class UserService {

    private ArrayList<User> users;
    private User currentUser;

    public UserService() {
        users = new ArrayList<>();
        currentUser = null;
    }

    public void register(String username, String password, String fullName, String phoneNumber, String email) {

        if (findUserByUsername(username) != null) {
            System.out.println("Error: username already exists.");
            return;
        }

        if (!isValidPhoneNumber(phoneNumber)) {
            System.out.println("Error: invalid phone number.");
            return;
        }

        if (!isValidEmail(email)) {
            System.out.println("Error: invalid email.");
            return;
        }

        if (!isValidPassword(password)) {
            System.out.println("Error: invalid password.");
            return;
        }

        String cardNumber = generateUniqueCardNumber();

        User user = new User(username, password, fullName, phoneNumber, email, cardNumber);
        users.add(user);

        System.out.println("Registered successfully.");
        System.out.println("Assigned card number: " + cardNumber);
    }

    public void login(String username, String password) {
        User user = findUserByUsername(username);
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

    public User findUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public User findUserByCardNumber(String cardNumber) {
        for (User user : users) {
            if (user.getCardNumber().equals(cardNumber)) {
                return user;
            }
        }
        return null;
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        if (phoneNumber.length() != 11) return false;
        if (!phoneNumber.startsWith("09")) return false;
        for (int i = 0; i < phoneNumber.length(); i++) {
            if (!Character.isDigit(phoneNumber.charAt(i))) return false;
        }
        return true;
    }

    private boolean isValidEmail(String email) {
        int atIndex = email.indexOf("@");
        if (atIndex <= 0) return false;
        if (email.startsWith(".")) return false;
        String domain = email.substring(atIndex + 1);
        if (!domain.equals("aut.com")) return false;
        return true;
    }

    private boolean isValidPassword(String password) {
        if (password.length() < 8) return false;

        boolean hasUpper = false, hasLower = false, hasDigit = false, hasSpecial = false;
        String specialChars = "@!&$؟";

        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            if (Character.isUpperCase(c)) hasUpper = true;
            else if (Character.isLowerCase(c)) hasLower = true;
            else if (Character.isDigit(c)) hasDigit = true;
            else if (specialChars.indexOf(c) != -1) hasSpecial = true;
        }

        return hasUpper && hasLower && hasDigit && hasSpecial;
    }

    private String generateUniqueCardNumber() {
        String card;
        do {
            card = "6037";
            for (int i = 0; i < 12; i++) {
                int digit = (int)(Math.random() * 10);
                card += digit;
            }
        } while (findUserByCardNumber(card) != null);
        return card;
    }

    public User getCurrentUser() {
        return currentUser;
    }
}
