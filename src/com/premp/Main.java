package com.premp;

import com.premp.controller.Database;

import java.util.Scanner;

public class Main {

    private static Database mDatabase;

    public static void main(String[] args) {
        String userName;
        String userPassword;
        Scanner scan = new Scanner(System.in);
        mDatabase = new Database();
        boolean flag = true;

        while (flag) {
            System.out.println("====================================");
            System.out.println("\n\t1. LogIn" + "\t2. SignUp" + "\t3. Exit");
            System.out.print("Choose: ");
            switch (scan.nextInt()) {
                case 1:
                    scan.nextLine();
                    System.out.print("User: ");
                    userName = scan.nextLine();
                    System.out.print("Password: ");
                    userPassword = scan.nextLine();
                    if (mDatabase.logIn(userName, userPassword)) {
                        System.out.println("\nLogged In");
                    } else {
                        System.out.println("Incorrect user name or password");
                    }
                    break;
                case 2:
                    // TODO: Add sign up function
                    System.out.println("\n>>>> Oops, Sign up will work shortly.");
                    break;
                case 3:
                    System.out.println("\nGood Bye...");
                    flag = false;
                    break;
            }
            System.out.println("====================================");
        }
    }
}
