package com.premp.controller;

import com.premp.model.Constants;
import com.premp.model.LogInTable;
import com.premp.model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private Connection mConnection;
    private Statement mStatement;
    private ResultSet mResultSet;

    public Database() {
        try {
            // classpath selected automatically if classpath added to environment variable.
//            Class.forName(Constants.className);
            mConnection = DriverManager
                    .getConnection(Constants.url, Constants.user, Constants.password);
            mStatement = mConnection.createStatement();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Searching in database using user name
    // Returns user object if found else null.
    private User getUserData(String userName) {
        User user = new User();

        try {
            mResultSet = mStatement.executeQuery(
                    "SELECT * FROM " + LogInTable.NAME + " "
                            + "WHERE " + LogInTable.Cols.user + " = " + "'" + userName + "'"
            );
            if (mResultSet.next()) {
                user = new User(mResultSet.getString(
                        LogInTable.Cols.user), mResultSet.getString(LogInTable.Cols.password)
                );
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println("In getUserData: " + e);
        }

        return user;

    } // getUserData() end.


    // Gets user object from getUserData() and
    // returns true if password matches otherwise false.
    public boolean logIn(String userName, String password) {
        User user = getUserData(userName);
        if (user != null) {
            return user.getPassword().equals(password);
        }
        return false;
    } // logIn() end.

    // To insert new row into login table database.
    public boolean signUp(String userName, String password) {
        try {
            mStatement.execute(
                    "INSERT INTO login VALUES " +
                            "(" + "'" + userName + "', '" + password  + "'" + " )"
            );
            return true;
        } catch (SQLException e) {
            System.out.println("In signUp: " + e);
            return false;
        }
    } // signUp() end.

    // Deleting user from login table database.
    public boolean deleteUser(String userName, String password) {
        try {
            if (getUserData(userName) != null) {
                mStatement.execute(
                        "DELETE FROM login WHERE " +
                                "user = " + "'" + userName + "'" + " AND " +
                                "password = " + "'" + password + "'"
                );
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("In deleteUser: " + e);
            return false;
        }
    }
}
