package com.revature.Service;

import com.revature.Entity.Account;
import com.revature.exception.AccountSQLException;
import com.revature.repository.AccountDAO;

import java.sql.SQLException;
import java.util.List;

public class AccountService {
    private AccountDAO accountDAO;

    public AccountService(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    public Account createAccount(Account matchUserAccount) {
        return accountDAO.createAccount(matchUserAccount);

    }

    public List<Account> usersAccounts(String username) {
        return accountDAO.getAccountByUser(username);
    }

    public void closeAccount(Account account) {
        accountDAO.closeAccount(account);
        System.out.println(" We have closed this account and have taken all the money inside as payment your welcome");
    }

    public void deposit(int accountId, double amount) {
        accountDAO.deposit(accountId, amount);
    }

    public void withdraw(int accountId, double amount) throws SQLException {
        accountDAO.withdraw(accountId, amount);
    }
}
