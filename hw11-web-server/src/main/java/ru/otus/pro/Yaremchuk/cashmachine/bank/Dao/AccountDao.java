package ru.otus.pro.Yaremchuk.cashmachine.bank.Dao;

import org.springframework.stereotype.Repository;
import ru.otus.pro.Yaremchuk.cashmachine.bank.data.Account;
import ru.otus.pro.Yaremchuk.cashmachine.bank.db.Accounts;

import java.util.HashMap;
import java.util.Map;

@Repository
public class AccountDao {
    private final Map<String, Account> accounts = new HashMap<>();

    public Account getAccount(Long accountId) {
        if (!Accounts.accounts.containsKey(accountId)) {
            throw new IllegalArgumentException("Account not found");
        }
        return Accounts.accounts.get(accountId);
    }

    public Account saveAccount(Account account) {
        if (account.getId() <= 0) {
            account.setId(Accounts.getNextId());
        }
        Accounts.accounts.put(account.getId(), account);
        return account;
    }
}