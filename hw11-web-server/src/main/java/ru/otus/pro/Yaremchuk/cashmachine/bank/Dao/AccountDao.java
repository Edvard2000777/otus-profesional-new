package ru.otus.pro.Yaremchuk.cashmachine.bank.Dao;

import org.springframework.stereotype.Repository;
import ru.otus.pro.Yaremchuk.cashmachine.bank.data.Account;
import ru.otus.pro.Yaremchuk.cashmachine.bank.db.Accounts;

import java.util.HashMap;
import java.util.Map;

public class AccountDao {
  Map<Long, Account> accounts = new HashMap<>();

    public Account getAccount(Long accountId) {
        return Accounts.getAccountById(accountId);
    }

    public Account saveAccount(Account account) {
        if (account.getId() <= 0) {
            account.setId(Accounts.getNextId());
        }
        return Accounts.getAccounts().put(account.getId(), account);
    }
}