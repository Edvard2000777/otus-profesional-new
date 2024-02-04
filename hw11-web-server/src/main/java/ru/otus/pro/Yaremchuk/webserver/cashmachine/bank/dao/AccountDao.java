package ru.otus.pro.Yaremchuk.webserver.cashmachine.bank.dao;

import org.springframework.stereotype.Service;
import ru.otus.pro.Yaremchuk.webserver.cashmachine.bank.data.Account;
import ru.otus.pro.Yaremchuk.webserver.cashmachine.bank.db.Accounts;

@Service
public class AccountDao {
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
