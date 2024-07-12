package com.revature.Entity;

import java.util.Objects;

public class Account {
    private int accountId;
    private int balance;
    private String accountType;
    private String accountHolder;

    public Account(){}

    public Account(int accountId, int balance, String accountType, String accountHolder) {
        this.accountId = accountId;
        this.balance = balance;
        this.accountType = accountType;
        this.accountHolder = accountHolder;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return accountId == account.accountId && balance == account.balance && Objects.equals(accountType, account.accountType) && Objects.equals(accountHolder, account.accountHolder);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, balance, accountType, accountHolder);
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", balance=" + balance +
                ", accountType='" + accountType + '\'' +
                ", accountHolder='" + accountHolder + '\'' +
                '}';
    }
}
