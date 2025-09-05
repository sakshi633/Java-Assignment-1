import java.util.Scanner;

public class UserInterface {
    // Array to store Account objects. A fixed size is used for this example.
    private Account[] accounts;
    private int accountCount;
    private Scanner scanner;

    // Constructor
    public UserInterface(int maxAccounts) {
        this.accounts = new Account[maxAccounts];
        this.accountCount = 0;
        this.scanner = new Scanner(System.in);
    }

    // Main menu loop
    public void mainMenu() {
        int choice;
        do {
            displayMenu();
            choice = 0;
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline left-over
                handleChoice(choice);
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 6.");
                scanner.nextLine(); // Consume invalid input
            }
        } while (choice != 6);
        System.out.println("Exiting application. Goodbye!");
    }

    // Displays the menu options
    private void displayMenu() {
        System.out.println("\nWelcome to the Banking Application!");
        System.out.println("1. Create a new account");
        System.out.println("2. Deposit money");
        System.out.println("3. Withdraw money");
        System.out.println("4. View account details");
        System.out.println("5. Update contact details");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
    }

    // Handles user's menu choice
    private void handleChoice(int choice) {
        switch (choice) {
            case 1:
                createAccount();
                break;
            case 2:
                performDeposit();
                break;
            case 3:
                performWithdrawal();
                break;
            case 4:
                showAccountDetails();
                break;
            case 5:
                updateContact();
                break;
            case 6:
                // Handled by the loop condition
                break;
            default:
                System.out.println("Invalid choice. Please enter a number between 1 and 6.");
        }
    }

    // Method to create a new account
    private void createAccount() {
        if (accountCount >= accounts.length) {
            System.out.println("Cannot create new account. Maximum account limit reached.");
            return;
        }

        System.out.print("Enter account holder name: ");
        String name = scanner.nextLine();
        System.out.print("Enter initial deposit amount: ");
        double initialDeposit = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter email address: ");
        String email = scanner.nextLine();
        System.out.print("Enter phone number: ");
        String phoneNumber = scanner.nextLine();

        Account newAccount = new Account(name, initialDeposit, email, phoneNumber);
        accounts[accountCount] = newAccount;
        accountCount++;

        System.out.println("Account created successfully with Account Number: " + newAccount.getAccountNumber());
    }

    // Method to find an account by its number
    private Account findAccount(int accountNumber) {
        for (int i = 0; i < accountCount; i++) {
            if (accounts[i].getAccountNumber() == accountNumber) {
                return accounts[i];
            }
        }
        return null;
    }

    // Method to handle deposit operations
    private void performDeposit() {
        System.out.print("Enter account number: ");
        int accountNumber = scanner.nextInt();
        Account account = findAccount(accountNumber);

        if (account != null) {
            System.out.print("Enter amount to deposit: ");
            double amount = scanner.nextDouble();
            account.deposit(amount);
        } else {
            System.out.println("Account not found.");
        }
    }

    // Method to handle withdrawal operations
    private void performWithdrawal() {
        System.out.print("Enter account number: ");
        int accountNumber = scanner.nextInt();
        Account account = findAccount(accountNumber);

        if (account != null) {
            System.out.print("Enter amount to withdraw: ");
            double amount = scanner.nextDouble();
            account.withdraw(amount);
        } else {
            System.out.println("Account not found.");
        }
    }

    // Method to display account details
    private void showAccountDetails() {
        System.out.print("Enter account number: ");
        int accountNumber = scanner.nextInt();
        Account account = findAccount(accountNumber);

        if (account != null) {
            account.displayAccountDetails();
        } else {
            System.out.println("Account not found.");
        }
    }

    // Method to update contact details
    private void updateContact() {
        System.out.print("Enter account number: ");
        int accountNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        Account account = findAccount(accountNumber);

        if (account != null) {
            System.out.print("Enter new email address: ");
            String newEmail = scanner.nextLine();
            System.out.print("Enter new phone number: ");
            String newPhoneNumber = scanner.nextLine();
            account.updateContactDetails(newEmail, newPhoneNumber);
        } else {
            System.out.println("Account not found.");
        }
    }
}

