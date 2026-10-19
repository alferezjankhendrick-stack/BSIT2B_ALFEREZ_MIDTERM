package com.scc.ui;

import com.scc.dao.JobOrderDao;
import com.scc.model.JobOrderEntity;

import java.util.List;
import java.util.Scanner;

public class JobOrderMenu {
    private final JobOrderDao dao = new JobOrderDao();
    private final Scanner scanner = new Scanner(System.in);

    public void start() {
        int choice;
        do {
            System.out.println("==================================================");
            System.out.println("          LAUNDRY SHOP JOB ORDER SYSTEM           ");
            System.out.println("==================================================");
            System.out.println("[1] Add New Job Order");
            System.out.println("[2] View Active Job Orders");
            System.out.println("[3] Exit System");
            System.out.println("==================================================");
            System.out.print("Enter choice: ");

            while (!scanner.hasNextInt()) {
                System.out.print("Invalid input. Enter choice: ");
                scanner.next();
            }
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    handleAddRecord();
                    break;
                case 2:
                    handleViewRecords();
                    break;
                case 3:
                    System.out.println("\nExiting system... Thank you!");
                    break;
                default:
                    System.out.println("\nInvalid choice. Please choose 1, 2, or 3.\n");
            }
        } while (choice != 3);
    }

    private void handleAddRecord() {
        System.out.println("\n--- ADD NEW JOB ORDER ---");
        System.out.print("Enter Customer Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Weight (KG): ");
        while (!scanner.hasNextDouble()) {
            System.out.print("Invalid weight. Enter Weight (KG): ");
            scanner.next();
        }
        double weight = scanner.nextDouble();

        System.out.print("Enter Total Amount: ");
        while (!scanner.hasNextDouble()) {
            System.out.print("Invalid amount. Enter Total Amount: ");
            scanner.next();
        }
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter Status (RECEIVED / WASHING / READY): ");
        String status = scanner.nextLine().toUpperCase();

        JobOrderEntity newOrder = new JobOrderEntity(name, weight, amount, status);
        if (dao.insertJobOrder(newOrder)) {
            System.out.println("\nSUCCESS: Laundry job order created!\n");
        } else {
            System.out.println("\nERROR: Failed to save record.\n");
        }
    }

    private void handleViewRecords() {
        List<JobOrderEntity> orders = dao.getAllJobOrders();
        System.out.println("\n==================================================");
        System.out.println("              ACTIVE LAUNDRY ORDERS               ");
        System.out.println("==================================================");
        System.out.println("+-----+--------------------+----------+--------------+------------+");
        System.out.println("| ID  | CUSTOMER NAME      | WEIGHT_KG| TOTAL AMOUNT | STATUS     |");
        System.out.println("+-----+--------------------+----------+--------------+------------+");

        for (JobOrderEntity o : orders) {
            System.out.printf("| %-3d | %-18s | %-8.1f | %-12.2f | %-10s |\n",
                    o.getId(), o.getCustomerName(), o.getWeightKg(), o.getTotalAmount(), o.getStatus());
        }

        System.out.println("+-----+--------------------+----------+--------------+------------+");
        System.out.println("Total Records Displayed: " + orders.size());
        System.out.println("==================================================\n");
    }
}