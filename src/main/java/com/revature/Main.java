package com.revature;

import com.revature.Service.AccountService;
import com.revature.Service.UserService;
import com.revature.controller.UserController;
import com.revature.repository.AccountDAO;
import com.revature.repository.SQliteAccountDAO;
import com.revature.repository.SQliteDao;
import com.revature.repository.UserDao;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    /* Regitistration steps
        -user needs to be prompt if they want to make an account
        -provide username and password
        - user name need to meet require ments
        -username must be unique
        -system needs to save if they are valid or reject them if they are not valid
        - and let the user know the results
     */

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            UserDao userDao = new SQliteDao();
            UserService userService = new UserService(userDao);
            AccountDAO accountDAO = new SQliteAccountDAO();
            AccountService accountService = new AccountService(accountDAO);
            UserController userController = new UserController(scanner, userService,accountService);
            Map<String,String> controlMap = new HashMap<>();

            controlMap.put("Continue Loop","true");
            while(Boolean.parseBoolean(controlMap.get("Continue Loop"))){
                userController.promptUserForService(controlMap);
                if(controlMap.containsKey("User")){
                    System.out.printf("Banking stuff for %s can happen here! Press any key to continue",controlMap.get("User"));
                     scanner.nextLine();
                     //RUN ACCOUNT CONTROLLER switch statment HERE
                    userController.loginMenu(controlMap);

                }
                   /*
                        NOTE: currently the User information has no means of being removed: when you implement a log out
                        functionality the controlMap needs to have the User key/value pair removed:
                        - controlMap.remove("User");
                     */
            }

        }
    }
}