package com.revature.repository;

import com.revature.Entity.Account;

import java.util.List;

public interface AccountDAO {
    // instead of using an account we can use primitives that will
    Account createAccount(Account account);
    Account getAccountById(Account account);
    // i think this is the only way to make an account view user specific
    List<Account> getAccountByUser(String username);

    Account deposit(int accountId, int amount);
    Account withdraw(int accountId, int amount);
    void closeAccount(int accountId);






}
