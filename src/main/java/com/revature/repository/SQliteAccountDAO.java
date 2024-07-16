package com.revature.repository;

import com.revature.Entity.Account;
import com.revature.Utility.DatabaseConnector;
import com.revature.Entity.User;
import com.revature.exception.AccountSQLException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
        String sql = "select * from account where account_holder =?";
        try (Connection connection= DatabaseConnector.createConnection()){
          PreparedStatement preparedStatement= connection.prepareStatement(sql);
          preparedStatement.setString(1, username);
          ResultSet rs = preparedStatement.executeQuery();
          List<Account> accounts = new ArrayList<>();
          while (rs.next()){
              Account accountRecord = new Account(
                rs.getInt("account_Id"),
                rs.getInt("balance")  ,
                rs.getString("account_type") ,
                rs.getString("account_holder")
              );
              accounts.add(accountRecord);
          }
            return accounts;
        }catch (SQLException exception)
        {
            try {
                throw new AccountSQLException(exception.getMessage());
            } catch (AccountSQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

    @Override
    public Account deposit(int accountId, double amount) {
        // cant deposit negative money

        String sql = "update account set balance = balance + ? where account_id =?";
        try (Connection connection = DatabaseConnector.createConnection()){
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setDouble(1,amount);
                preparedStatement.setInt(2,accountId);

                int rs = preparedStatement.executeUpdate();

        }
        catch (SQLException exception)
        {
            try {
                throw new AccountSQLException(exception.getMessage());
            } catch (AccountSQLException e) {
                throw new RuntimeException(e);
            }
        }


        return null;
    }

    @Override
    public Account withdraw(int accountId, double amount) throws SQLException {
        String balanceCheck ="SELECT balance FROM account WHERE account_id = ?";
        String sql = "update account set balance = balance - ? where account_id =?";
        try (Connection connection = DatabaseConnector.createConnection()){

            PreparedStatement checkBalanceStmt = connection.prepareStatement(balanceCheck);
            checkBalanceStmt.setInt(1, accountId);
            ResultSet rs2 = checkBalanceStmt.executeQuery();

            if (rs2.next()) {
                double currentBalance = rs2.getDouble("balance");


                if (amount > currentBalance) {
                    throw new SQLException("insufficient funds. withdrawal amount exceeds current balance.");
                }



            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1,amount);
            preparedStatement.setInt(2,accountId);

            int rs = preparedStatement.executeUpdate();

        }
//        catch (SQLException exception)
//        {
//            try {
//                throw new AccountSQLException(exception.getMessage());
//            } catch (AccountSQLException e) {
//                throw new RuntimeException(e);
//            }
//        }

        return null;
    }
    }

        @Override
    public void closeAccount(Account account) {
        String sql = "delete from account where account_id =? and account_holder= ? ";
        try(Connection connection = DatabaseConnector.createConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, account.getAccountId());
            preparedStatement.setString(2,account.getAccountHolder());
           int rs = preparedStatement.executeUpdate();
            if (rs== 0) {
                throw new SQLException("Deleting account failed, no rows affected.");
            }
        } catch(SQLException exception){
            try {
                throw new AccountSQLException(exception.getMessage());
            } catch (AccountSQLException e) {
                throw new RuntimeException(e);
            }
        }


    }
}
