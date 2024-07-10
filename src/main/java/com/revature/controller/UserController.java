package com.revature.controller;

import com.revature.Entity.User;
import com.revature.Service.UserService;
import com.revature.exception.LoginFail;

import java.util.Map;
import java.util.Scanner;


public class UserController {
    private Scanner scanner;
    private UserService userService;
    public UserController(Scanner scanner, UserService userService){
        this.scanner = scanner;
        this.userService = userService;
    }
public void promptUserForService(Map<String,String> controlMap){

    System.out.println("what you you like to do?");
    System.out.println("1. register an account");
    System.out.println("2. Login");
    System.out.println("0. quit");
    try{
        int userActionIndicated = Integer.parseInt(scanner.nextLine());
        if (userActionIndicated == 1) {
            registerNewUser();
            // System.out.println("Username: " + newUsername + "Password: " + newPassword);
        }
        else if (userActionIndicated == 2){
          controlMap.put("User",userLogin().toString()) ;
        }

        else if(userActionIndicated==0) {
            System.out.println("Goodbye");
            controlMap.put("Continue Loop", "false");

        }
    }catch(LoginFail exception){
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
    System.out.println("Please put username");
    String newUsername = scanner.nextLine();
    System.out.println("Please enter a password");
    String newPassword = scanner.nextLine();
    return new User (newUsername,newPassword);
    }

}
