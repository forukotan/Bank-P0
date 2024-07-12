package com.revature.controller;

import com.revature.Entity.Account;
import com.revature.Entity.User;
import com.revature.Service.AccountService;
import com.revature.Service.UserService;
import com.revature.exception.AccountSQLException;
import com.revature.exception.LoginFail;

import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class UserController {
    private Scanner scanner;
    private UserService userService;
    private AccountService accountService;

    public UserController(Scanner scanner, UserService userService,AccountService accountService){
        this.scanner = scanner;
        this.userService = userService;
        this.accountService = accountService;

    }
public void promptUserForService(Map<String,String> controlMap){

    System.out.println("what you you like to do?");
    System.out.println("1. register an account");
    System.out.println("2. Login");
    System.out.println("0. quit");
    try{
        String userActionIndicated = scanner.nextLine();
        switch (userActionIndicated) {
            case "1":
                registerNewUser();
                break;
            case "2":
                controlMap.put("User", userLogin().getUsername());
                break;
            case "q":
                System.out.println("Goodbye!");
                controlMap.put("Continue Loop", "false");
        }
        // this exception triggers if the user enters invalid credentials
    } catch(LoginFail exception){
        System.out.println(exception.getMessage());
    }


}

public  void registerNewUser(){

    User newCredentials = getUserCredentials();
    User newUser =userService.validateNewCredentials(newCredentials);
    System.out.printf("New account created: %s", newUser);
}
public User userLogin(){
        return userService.checkLoginCredentials(getUserCredentials());
    }
public  User getUserCredentials(){
    String newUsername;
    String newPassword;
    // user needs to provide a username and password
    System.out.print("Please enter a username: ");
    newUsername = scanner.nextLine();
    System.out.print("Please enter a password: ");
    newPassword = scanner.nextLine();
    return new User(newUsername, newPassword);
    }
    public void loginMenu(Map<String,String> controlMap){
        System.out.println("please select what you want to do");
        System.out.println("1. Open a checkings account");
        System.out.println("2.View account details");
       /*
       make a for each loop for all their accounts and display all the info on a new line each
       then makeoption to deposit, withdraw,transfer-which uses withdraw and deposit, and logout feature
        */
        System.out.println("3. Close an account");
        String userSelection = scanner.nextLine();
        switch (userSelection) {
            case "1":
                createAccount(controlMap);
                break;
            case "2":
               viewAccounts(controlMap);



                break;
            case "3":
        }
    }
    public void createAccount(Map<String,String> controlMap){

//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Enter account type (e.g., savings, checking): ");
//        String accountType = scanner.nextLine();
          String accountType  ="Checking";

        String accountHolder = controlMap.get("User");

        Account account = new Account(0, 0, accountType, accountHolder);
        Account createdAccount = accountService.createAccount(account);
        System.out.println("Account created successfully. Account ID: " + createdAccount.getAccountId());
    }

    public void viewAccounts(Map<String,String> controlMap){
        String accountHolder = controlMap.get("User");
        List<Account> accounts = accountService.usersAccounts(accountHolder);
        if (accounts.isEmpty()){
            System.out.println("No Accounts Available");
        }
        else{
            for(Account acct:accounts){
                System.out.println(acct);
            }
        }

    }




    }


