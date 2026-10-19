package com.scc;

import com.scc.dao.DatabaseConnection;
import com.scc.ui.JobOrderMenu;

public class Main {
    public static void main(String[] args) {

        DatabaseConnection.initializeDatabase();


        JobOrderMenu menu = new JobOrderMenu();
        menu.start();
    }
}