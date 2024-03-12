package ru.otus.pro.Yaremchuk.cashmachine.bank.Servies.impl;

import org.springframework.stereotype.Service;
import ru.otus.pro.Yaremchuk.cashmachine.bank.Dao.AccountDao;
import ru.otus.pro.Yaremchuk.cashmachine.bank.data.Account;
import ru.otus.pro.Yaremchuk.cashmachine.bank.Servies.AccountService;



import java.math.BigDecimal;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountDao accountDao;

    public AccountServiceImpl(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public Account createAccount(BigDecimal amount) {
        Account newAccount = new Account(0, amount);
        return accountDao.saveAccount(newAccount);
    }

    @Override
    public Account getAccount(Long id) {
        return accountDao.getAccount(id);
    }

    @Override
    public BigDecimal getMoney(Long id, BigDecimal amount) {
        if (accountDao == null) {
            throw new IllegalStateException("AccountDao is not initialized");
        }

        Account account = accountDao.getAccount(id);
        BigDecimal currentAmount = account.getAmount();
        BigDecimal newAmount = currentAmount.subtract(amount);
        if (newAmount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Not enough money");
        }
        account.setAmount(newAmount);
        return account.getAmount();
    }

    @Override
    public BigDecimal putMoney(Long id, BigDecimal amount) {
        if (accountDao == null) {
            throw new IllegalStateException("AccountDao is not initialized");
        }

        Account account = accountDao.getAccount(id);
        BigDecimal currentAmount = account.getAmount();
        BigDecimal newAmount = currentAmount.add(amount);
        account.setAmount(newAmount);
        return account.getAmount();
    }

    @Override
    public BigDecimal checkBalance(Long id) {
        if (accountDao == null) {
            throw new IllegalStateException("AccountDao is not initialized");
        }

        Account account = accountDao.getAccount(id);
        return account.getAmount();
    }
}