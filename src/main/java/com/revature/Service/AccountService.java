package com.revature.Service;

import com.revature.Entity.Account;
import com.revature.repository.AccountDAO;

public class AccountService {
    private AccountDAO accountDAO;

    public AccountService(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    public Account createAccount(Account matchUserAccount){
       /*
        i want to check if they are making a checking account

        i want to make sure it matches the current user that is using our system,i think it will be the user
       under the newcredentials
        if (createaccount== is selected)
        {
         then we will create account with default balance of 0, foreign key will be the user, account type will defualt to checking for now,and
          we will figure out how to autogenerate the account id
        }

        */




        return accountDAO.createAccount(matchUserAccount);

    }
}
