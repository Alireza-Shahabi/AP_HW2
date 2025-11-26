
import services.UserService;
import services.BankService;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        UserService userService = new UserService();
        BankService bankService = new BankService(userService);


        boolean running = true;
        while (running) {
            String line = input.nextLine().trim();
            if (line.isEmpty()) continue;
            String[] parts = line.split(" ");
            String command = parts[0].toLowerCase();

            switch (command) {
                case "register":
                    if (parts.length < 5) {
                        System.out.println("Error: invalid register command.");
                        break;
                    }

                    int phoneIndex = -1;
                    for (int i = 3; i < parts.length; i++) {
                        String t = parts[i];
                        if (t.startsWith("09") && t.length() == 11) {
                            phoneIndex = i;
                            break;
                        }
                    }

                    if (phoneIndex == -1 || phoneIndex == parts.length - 1) {
                        System.out.println("Error: invalid register command.");
                        break;
                    }

                    String fullName = parts[3];
                    for (int i = 4; i < phoneIndex; i++) {
                        fullName = fullName + " " + parts[i];
                    }

                    String username = parts[1];
                    String password = parts[2];
                    String phone = parts[phoneIndex];
                    String email = parts[phoneIndex + 1];

                    userService.register(username, password, fullName, phone, email);
                    break;

                case "login":
                    if (parts.length != 3) {
                        System.out.println("Error: invalid login command.");
                        break;
                    }
                    userService.login(parts[1], parts[2]);
                    break;

                case "logout":
                    userService.logout();
                    break;

                case "show":
                    if (parts.length == 2 && parts[1].toLowerCase().equals("balance")) {
                        bankService.showBalance();
                    } else {
                        System.out.println("Error: invalid show command.");
                    }
                    break;

                case "deposit":
                    if (parts.length != 2) {
                        System.out.println("Error: invalid deposit command.");
                        break;
                    }
                    double depositAmount = Double.parseDouble(parts[1]);
                    bankService.deposit(depositAmount);
                    break;

                case "withdraw":
                    if (parts.length != 2) {
                        System.out.println("Error: invalid withdraw command.");
                        break;
                    }
                    double withdrawAmount = Double.parseDouble(parts[1]);
                    bankService.withdraw(withdrawAmount);
                    break;

                case "transfer":
                    if (parts.length != 3) {
                        System.out.println("Error: invalid transfer command.");
                        break;
                    }
                    String card = parts[1];
                    double transferAmount = Double.parseDouble(parts[2]);
                    bankService.transfer(card, transferAmount);
                    break;

                case "exit":
                    running = false;
                    break;

                default:
                    System.out.println("Error: unknown command.");
            }
        }
    }
}
