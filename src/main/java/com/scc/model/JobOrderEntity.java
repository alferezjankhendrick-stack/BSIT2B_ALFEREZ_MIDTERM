package com.scc.model;

public class JobOrderEntity {
    private int id;
    private String customerName;
    private double weightKg;
    private double totalAmount;
    private String status;

    public JobOrderEntity(int id, String customerName, double weightKg, double totalAmount, String status) {
        this.id = id;
        this.customerName = customerName;
        this.weightKg = weightKg;
        this.totalAmount = totalAmount;
        this.status = status;
    }

    public JobOrderEntity(String customerName, double weightKg, double totalAmount, String status) {
        this.customerName = customerName;
        this.weightKg = weightKg;
        this.totalAmount = totalAmount;
        this.status = status;
    }

    public int getId() { return id; }
    public String getCustomerName() { return customerName; }
    public double getWeightKg() { return weightKg; }
    public double getTotalAmount() { return totalAmount; }
    public String getStatus() { return status; }
}