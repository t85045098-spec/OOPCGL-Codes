/* Practical No. 2
   Develop a Java program for an E-commerce order processing where some products are
   initialized through multiple constructors, overloaded constructors, where users can input some
   product details manually, the system computes total order cost dynamically, applies discount
   policies based on conditions, and presents a detailed invoice summarizing the purchase. */

import java.util.Scanner;

class Product {
    String name;
    int quantity;
    double pricePerUnit;

    // Default constructor
    Product() {
        this.name = "Unknown";
        this.quantity = 0;
        this.pricePerUnit = 0.0;
    }

    // Constructor with name and price
    Product(String name, double pricePerUnit) {
        this.name = name;
        this.pricePerUnit = pricePerUnit;
        this.quantity = 1; // default quantity
    }

    // Constructor with all fields
    Product(String name, int quantity, double pricePerUnit) {
        this.name = name;
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
    }

    double getTotalPrice() {
        return quantity * pricePerUnit;
    }
}

public class Practical2_ECommerceApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Product[] cart;
        int n;

        System.out.print("Enter number of products to purchase: ");
        n = sc.nextInt();

        cart = new Product[n];

        for (int i = 0; i < n; i++) {
            System.out.println("\nEnter details for Product " + (i + 1) + ":");
            System.out.print("Name: ");
            sc.nextLine(); // Consume newline
            String name = sc.nextLine();

            System.out.print("Quantity: ");
            int qty = sc.nextInt();

            System.out.print("Price per unit: ");
            double price = sc.nextDouble();

            // Using constructor with all fields
            cart[i] = new Product(name, qty, price);
        }

        // Calculate total cost
        double total = 0;
        for (Product p : cart) {
            total += p.getTotalPrice();
        }

        // Apply discount
        double discount = 0;
        if (total > 5000) {
            discount = total * 0.10; // 10% discount
        }

        double finalAmount = total - discount;

        // Print Invoice
        System.out.println("\n===== INVOICE =====");
        System.out.printf("%-20s %-10s %-10s %-10s\n", "Product Name", "Quantity", "Unit Price", "Total");
        for (Product p : cart) {
            System.out.printf("%-20s %-10d %-10.2f %-10.2f\n",
                    p.name, p.quantity, p.pricePerUnit, p.getTotalPrice());
        }
        System.out.println("-------------------------------");
        System.out.printf("Total: Rs.%.2f\n", total);
        System.out.printf("Discount: Rs.%.2f\n", discount);
        System.out.printf("Amount Payable: Rs.%.2f\n", finalAmount);
        System.out.println("===============================");

        sc.close();
    }
}
