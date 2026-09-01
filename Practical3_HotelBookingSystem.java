/* Practical No. 3
   Develop a Java program that implements a simple hotel room booking system using two
   dimensional arrays. The system allows users to: View available and booked rooms, Book a room
   by selecting a floor and room number and exit the system when finished. */

import java.util.Scanner;

public class Practical3_HotelBookingSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // Initialize 2D array: false = available, true = booked
        boolean[][] rooms = new boolean[5][5];
        int choice;

        do {
            System.out.println("\n===== Hotel Room Booking System =====");
            System.out.println("1. View Room Status");
            System.out.println("2. Book a Room");
            System.out.println("3. Exit");
            System.out.print("Enter your choice (1-3): ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("\nRoom Status (A = Available, B = Booked):");
                    for (int i = 0; i < rooms.length; i++) {
                        for (int j = 0; j < rooms[i].length; j++) {
                            if (rooms[i][j]) {
                                System.out.print("B ");
                            } else {
                                System.out.print("A ");
                            }
                        }
                        System.out.println(" <- Floor " + (i + 1));
                    }
                    break;

                case 2:
                    System.out.print("Enter floor number (1-5): ");
                    int floor = sc.nextInt();
                    System.out.print("Enter room number (1-5): ");
                    int room = sc.nextInt();

                    if (floor < 1 || floor > 5 || room < 1 || room > 5) {
                        System.out.println("Invalid room or floor number!");
                    } else if (rooms[floor - 1][room - 1]) {
                        System.out.println("Room is already booked.");
                    } else {
                        rooms[floor - 1][room - 1] = true;
                        System.out.println("Room successfully booked!");
                    }
                    break;

                case 3:
                    System.out.println("Thank you for using the system. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 3);

        sc.close();
    }
}
