package com.premp.controller;

import com.premp.model.Constants;
import com.premp.model.LogInTable;
import com.premp.model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Database {
    private Connection mConnection;
    private Statement mStatement;
    private ResultSet rs;

    public Database() {
        try {
            Class.forName(Constants.className);
            mConnection = DriverManager
                    .getConnection(Constants.url, Constants.user, Constants.password);
            mStatement = mConnection.createStatement();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private User getUserData(String userName) {
        User user = new User();
        try {
            rs = mStatement.executeQuery(
                    "SELECT * FROM " + LogInTable.NAME + " "
                            + "WHERE " + LogInTable.Cols.user + " = " + "'" + userName + "'"
            );
            if (rs.next()) {
                user = new User(rs.getString(
                        LogInTable.Cols.user), rs.getString(LogInTable.Cols.password)
                );
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println("In getUserData: " + e);
        }
        return user;
    }


    public boolean logIn(String userName, String password) {
        User user = getUserData(userName);
        if (user != null) {
            return user.getPassword().equals(password);
        }
        return false;
    }
}
