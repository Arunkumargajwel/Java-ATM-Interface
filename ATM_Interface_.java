package Brainwave_Projects;

import java.util.Scanner;

public class ATM_Interface_ {
    private static String userName;
    private static String pin;
    private static double balance = 0.00;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean isRegistered = false;
        System.out.println("Welcome to the ATM System!!");

        while (!isRegistered) {
            System.out.println("\n1. Register");
            System.out.println("2. Exit");
            System.out.print("Choose an option: ");

            if (sc.hasNextInt()) {
                int option = sc.nextInt();
                sc.nextLine(); 

                if (option == 1) {
                    register(sc);
                    isRegistered = true;
                } else if (option == 2) {
                    System.out.println("Thank you for using the ATM System!!");
                    sc.close();
                    return;
                } else {
                    System.out.println("Invalid option. Please try again.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                sc.nextLine(); 
            }
        }
        if (isRegistered) {
            System.out.println("-------------------");
            System.out.println("Please log in to your account!!");
            boolean isLoggedIn = false;

            while (!isLoggedIn) {
                isLoggedIn = login(sc);
                if (!isLoggedIn) {
                    System.out.println("Invalid login details. Please try again.");
                }
            }

            System.out.println("Login Successful! Welcome, " + userName + "!");
            boolean isAccess = true;

            while (isAccess) {
                System.out.println("\nATM Menu:");
                System.out.println("1. Check Balance");
                System.out.println("2. Deposit");
                System.out.println("3. Withdraw");
                System.out.println("4. Transfer");
                System.out.println("5. Exit");
                System.out.print("Choose an option: ");

                if (sc.hasNextInt()) {
                    int menuOption = sc.nextInt();
                    sc.nextLine(); 
                    switch (menuOption) {
                        case 1:
                            checkBalance();
                            break;
                        case 2:
                            deposit(sc);
                            break;
                        case 3:
                            withdraw(sc);
                            break;
                        case 4:
                            transfer(sc);
                            break;
                        case 5:
                            System.out.println("Thank you for using the ATM... GOODBYE!!");
                            isAccess = false;
                            break;
                        default:
                            System.out.println("Invalid option. Please try again.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a number.");
                    sc.nextLine(); 
                }
            }
        }
        sc.close();
    }

    public static void register(Scanner sc) {
        System.out.println("\nRegister Your Account");
        System.out.println("----------------------");
        System.out.print("Enter your username: ");
        userName = sc.nextLine();
        System.out.print("Set your PIN: ");
        pin = sc.nextLine();
        System.out.println("Registered successfully! Please log in...");
    }

    private static boolean login(Scanner sc) {
        System.out.print("\nEnter your username: ");
        String enteredUser = sc.nextLine();
        System.out.print("Enter your PIN: ");
        String enteredPin = sc.nextLine();
        return enteredUser.equals(userName) && enteredPin.equals(pin);
    }

    private static void checkBalance() {
        System.out.printf("Your current balance is: Rs%.2f%n", balance);
    }

    private static void deposit(Scanner sc) {
        System.out.print("Enter the amount to deposit: ");
        if (sc.hasNextDouble()) {
            double depositAmount = sc.nextDouble();
            sc.nextLine(); 
            if (depositAmount > 0) {
                balance += depositAmount;
                System.out.printf("Successfully deposited Rs%.2f%n", depositAmount);
                checkBalance();
            } else {
                System.out.println("Invalid deposit amount. Please enter a positive value.");
            }
        } else {
            System.out.println("Invalid input. Please enter a valid amount.");
            sc.nextLine(); 
        }
    }

    private static void withdraw(Scanner sc) {
        System.out.print("Enter the amount to withdraw: ");
        if (sc.hasNextDouble()) {
            double withdrawAmount = sc.nextDouble();
            sc.nextLine(); 
            if (withdrawAmount > 0 && withdrawAmount <= balance) {
                balance -= withdrawAmount;
                System.out.printf("Successfully withdrew Rs%.2f%n", withdrawAmount);
                checkBalance();
            } else if (withdrawAmount > balance) {
                System.out.println("Insufficient funds.");
            } else {
                System.out.println("Invalid withdraw amount. Please enter a positive value.");
            }
        } else {
            System.out.println("Invalid input. Please enter a valid amount.");
            sc.nextLine(); 
        }
    }

    private static void transfer(Scanner sc) {
        System.out.print("Enter the recipient's name: ");
        String recipient = sc.nextLine();
        System.out.print("Enter the amount to transfer: ");
        if (sc.hasNextDouble()) {
            double transferAmount = sc.nextDouble();
            sc.nextLine(); 
            if (transferAmount > 0 && transferAmount <= balance) {
                balance -= transferAmount;
                System.out.printf("Successfully transferred Rs%.2f to %s%n", transferAmount, recipient);
                checkBalance();
            } else if (transferAmount > balance) {
                System.out.println("Insufficient funds for the transfer.");
            } else {
                System.out.println("Invalid transfer amount. Please enter a positive value.");
            }
        } else {
            System.out.println("Invalid input. Please enter a valid amount.");
            sc.nextLine(); 
        }
    }
}
