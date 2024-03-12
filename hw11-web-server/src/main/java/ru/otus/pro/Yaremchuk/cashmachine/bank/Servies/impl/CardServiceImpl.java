package ru.otus.pro.Yaremchuk.cashmachine.bank.Servies.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.pro.Yaremchuk.cashmachine.bank.Dao.CardsDao;
import ru.otus.pro.Yaremchuk.cashmachine.bank.data.Account;
import ru.otus.pro.Yaremchuk.cashmachine.bank.data.Card;
import ru.otus.pro.Yaremchuk.cashmachine.bank.Servies.AccountService;
import ru.otus.pro.Yaremchuk.cashmachine.bank.Servies.CardService;

import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class CardServiceImpl implements CardService {

    private final static String cardNotFound = "No card found";

    private final AccountService accountService;

    @Autowired
    private final CardsDao cardsDao;

    public CardServiceImpl(AccountService accountService, CardsDao cardsDao) {
        this.accountService = accountService;
        this.cardsDao = cardsDao;
    }

    @Override
    public Card createCard(String number, Long accountId, String pinCode) {
        return cardsDao.createCard(number, accountId, pinCode);
    }

    @Override
    public boolean changePin(String number, String oldPin, String newPin) {
        Card card = cardsDao.getCardByNumber(number);

        if (card == null) {
            throw new IllegalArgumentException(cardNotFound);
        }

        try {
            checkPin(card, oldPin);
            card.setPinCode(getHash(newPin));
            cardsDao.saveCard(card);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    @Override
    public BigDecimal getMoney(String number, String pin, BigDecimal sum) {
        Card card = cardsDao.getCardByNumber(number);

        if (card == null) {
            throw new IllegalArgumentException(cardNotFound);
        }

        checkPin(card, pin);
        Account account = accountService.getAccount(card.getAccountId());
        return accountService.getMoney(account.getId(), sum);
    }

    @Override
    public BigDecimal putMoney(String number, String pin, BigDecimal sum) {
        Card card = cardsDao.getCardByNumber(number);

        if (card == null) {
            throw new IllegalArgumentException(cardNotFound);
        }

        checkPin(card, pin);
        Account account = accountService.getAccount(card.getAccountId());
        return accountService.putMoney(account.getId(), sum);
    }

    @Override
    public BigDecimal getBalance(String number, String pin) {
        Card card = cardsDao.getCardByNumber(number);

        if (card == null) {
            throw new IllegalArgumentException(cardNotFound);
        }

        checkPin(card, pin);
        Account account = accountService.getAccount(card.getAccountId());
        return accountService.checkBalance(account.getId());
    }

    private void checkPin(Card card, String pin) {
        if (!getHash(pin).equals(card.getPinCode())) {
            throw new IllegalArgumentException("Pincode is incorrect");
        }
    }

    private String getHash(String value) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(value.getBytes());
            StringBuilder hexString = new StringBuilder();

            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}