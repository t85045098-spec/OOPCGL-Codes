/* Practical No. 4
   Develop a Java program for simulation of any real time application with required
   functionalities. For eg. ATM machine with functionalities like checking account balance,
   withdrawing, and depositing money. Use try, catch, and finally blocks to handle potential
   exceptions such as insufficient funds (throwing ArithmeticException) and invalid input
   (throwing IllegalArgumentException). Ensure that the application continues to run smoothly
   after handling exceptions. */

import java.util.Scanner;

public class Practical4_ATM {
    private static double balance = 1000.00; // Initial balance

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("Welcome to Simple ATM!");

        while (running) {
            System.out.println("\nMenu:");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Exit");

            System.out.print("Enter your choice (1-4): ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        checkBalance();
                        break;
                    case 2:
                        System.out.print("Enter amount to deposit: ");
                        double depositAmount = Double.parseDouble(scanner.nextLine());
                        deposit(depositAmount);
                        break;
                    case 3:
                        System.out.print("Enter amount to withdraw: ");
                        double withdrawAmount = Double.parseDouble(scanner.nextLine());
                        withdraw(withdrawAmount);
                        break;
                    case 4:
                        System.out.println("Thank you for using the ATM!");
                        running = false;
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid menu option.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter numeric values only.");
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (ArithmeticException e) {
                System.out.println("Transaction failed: " + e.getMessage());
            } finally {
                System.out.println("Transaction completed.\n");
            }
        }

        scanner.close();
    }

    public static void checkBalance() {
        System.out.printf("Your current balance is: Rs.%.2f\n", balance);
    }

    public static void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be greater than zero.");
        }
        balance += amount;
        System.out.printf("Rs.%.2f deposited successfully.\n", amount);
    }

    public static void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdraw amount must be greater than zero.");
        }
        if (amount > balance) {
            throw new ArithmeticException("Insufficient balance.");
        }
        balance -= amount;
        System.out.printf("Rs.%.2f withdrawn successfully.\n", amount);
    }
}
