package ru.otus.pro.Yaremchuk.cashmachine.bank.db;



import ru.otus.pro.Yaremchuk.cashmachine.bank.data.Account;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class Accounts {

    static Map<Long, Account> accounts = new HashMap<>();
    static AtomicLong idSequence = new AtomicLong(10L);

    static {
        accounts.put(1L, new Account(1, BigDecimal.valueOf(60000)));
        accounts.put(2L, new Account(2, BigDecimal.valueOf(70000)));
        accounts.put(3L, new Account(3, BigDecimal.valueOf(80000)));
    }

    public static Map<Long, Account> getAccounts() {
        return new HashMap<>(accounts);
    }

    public static Account getAccountById(Long id) {
        return accounts.get(id);
    }

    public static Long getNextId() {
        return idSequence.incrementAndGet();
    }
}