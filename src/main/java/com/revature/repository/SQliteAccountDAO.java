package com.revature.repository;

import com.revature.Entity.Account;
import com.revature.Utility.DatabaseConnector;
import com.revature.Entity.User;
import com.revature.exception.AccountSQLException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SQliteAccountDAO implements AccountDAO {
    // need sql statement
    // need a connection object
    // need to return if there is a return
    @Override
    public Account createAccount(Account account){
       String sql ="insert into account(account_holder,balance,account_type) values(?,?,?);";
       try(Connection connection= DatabaseConnector.createConnection()) {
           PreparedStatement preparedStatement= connection.prepareStatement(sql);
            preparedStatement.setString(1, account.getAccountHolder());
            preparedStatement.setInt(2,account.getBalance());
            preparedStatement.setString(3,account.getAccountType());

           int affectedRows = preparedStatement.executeUpdate();
           ResultSet rs =preparedStatement.getGeneratedKeys();
            if(rs.next()){
                account.setAccountId(rs.getInt(1));
            }
            return account;
       } catch (SQLException exception){
           //todo make AccountSQLException
           try {
               throw new AccountSQLException("We can't make this account recheck your credentials");
           } catch (AccountSQLException e) {
               throw new RuntimeException(e);
           }
           // I USED THE AI intellisense to fix this error idk if it works
       }

    }

    @Override
    public Account getAccountById(Account account) {
        return null;
    }

    @Override
    public List<Account> getAccountByUser(String username) {
        return null;
    }

    @Override
    public Account deposit(int accountId, int amount) {
        return null;
    }

    @Override
    public Account withdraw(int accountId, int amount) {
        return null;
    }

    @Override
    public void closeAccount(int accountId) {

    }
}
