package com.scc.dao;

import com.scc.model.JobOrderEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JobOrderDao {

    public boolean insertJobOrder(JobOrderEntity entity) {
        String sql = "INSERT INTO job_orders (customer_name, weight_kg, total_amount, status) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, entity.getCustomerName());
            pstmt.setDouble(2, entity.getWeightKg());
            pstmt.setDouble(3, entity.getTotalAmount());
            pstmt.setString(4, entity.getStatus());

            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Insert Error: " + e.getMessage());
            return false;
        }
    }

    public List<JobOrderEntity> getAllJobOrders() {
        List<JobOrderEntity> orders = new ArrayList<>();
        String sql = "SELECT * FROM job_orders";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                orders.add(new JobOrderEntity(
                        rs.getInt("id"),
                        rs.getString("customer_name"),
                        rs.getDouble("weight_kg"),
                        rs.getDouble("total_amount"),
                        rs.getString("status")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Fetch Error: " + e.getMessage());
        }
        return orders;
    }
}