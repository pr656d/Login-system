package com.premp;

import com.premp.controller.Database;

import java.util.Scanner;

public class Main {

    private static Database mDatabase;
    static String userName;
    static String userPassword;
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        mDatabase = new Database();
        boolean flag = true;

        while (flag) {
            System.out.println("====================================");
            System.out.println("\n\t1. LogIn" + "\t2. SignUp" + "\t3. Delete" + "\t4. Exit");
            System.out.print("Choose: ");
            switch (scan.nextInt()) {
                case 1:
                    setUserDetails();
                    if (mDatabase.logIn(userName, userPassword)) {
                        System.out.println("\nLogged In");
                    } else {
                        System.out.println("\nIncorrect user name or password");
                    }
                    break;
                case 2:
                    setUserDetails();
                    if (mDatabase.signUp(userName, userPassword)) {
                        System.out.println("\nSigned up successfully");
                    } else {
                        System.out.println("Something is wrong...");
                    }
                    break;
                case 3:
                    setUserDetails();
                    if (mDatabase.deleteUser(userName, userPassword)) {
                        System.out.println("\n"+ userName +" deleted successfully");
                    } else {
                        System.out.println("\nIncorrect user name or password");
                    }
                    break;
                case 4:
                    System.out.println("\nGood Bye...");
                    flag = false;
                    break;

                    default:
                        System.out.println("Choose from options only.");
            } // switch() end.

        } // while() end.
    }

    // Setting up user name and password.
    private static void setUserDetails() {
        scan.nextLine();
        System.out.print("User: ");
        userName = scan.nextLine();
        System.out.print("Password: ");
        userPassword = scan.nextLine();
    }
}
