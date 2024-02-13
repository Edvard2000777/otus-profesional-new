package ru.otus.pro.Yaremchuk.webserver.cashmachine.machine.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.pro.Yaremchuk.webserver.cashmachine.bank.service.AccountService;
import ru.otus.pro.Yaremchuk.webserver.cashmachine.bank.service.CardService;
import ru.otus.pro.Yaremchuk.webserver.cashmachine.machine.data.CashMachine;
import ru.otus.pro.Yaremchuk.webserver.cashmachine.machine.service.CashMachineService;
import ru.otus.pro.Yaremchuk.webserver.cashmachine.machine.service.MoneyBoxService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class CashMachineServiceImpl implements CashMachineService {

    private CardService cardService;

    private AccountService accountService;

    private MoneyBoxService moneyBoxService;

    public CashMachineServiceImpl(final CardService cardService, final AccountService accountService, final MoneyBoxService moneyBoxService) {
        this.cardService = cardService;
        this.accountService = accountService;
        this.moneyBoxService = moneyBoxService;
    }

    @Override
    public List<Integer> getMoney(CashMachine machine, String cardNum, String pin, BigDecimal amount) {
        try {
            cardService.getMoney(cardNum, pin, amount);
            return moneyBoxService.getMoney(machine.getMoneyBox(), amount.intValue());
        } catch (Exception e) {
            cardService.putMoney(cardNum, pin, amount);
            throw new RuntimeException(e);
        }
    }

    @Override
    public BigDecimal putMoney(CashMachine machine, String cardNum, String pin, List<Integer> notes) {
        cardService.getBalance(cardNum, pin);

        List<Integer> arrangedNotes = new ArrayList<>(notes);
        for (int i = 0; i < 4 - arrangedNotes.size(); i++) {
            arrangedNotes.add(0);
        }

        moneyBoxService.putMoney(machine.getMoneyBox(), arrangedNotes.get(3), arrangedNotes.get(2), arrangedNotes.get(1), arrangedNotes.get(0));
        return cardService.putMoney(cardNum, pin, new BigDecimal(
                arrangedNotes.get(4) * 100 +
                        arrangedNotes.get(20) * 500 +
                        arrangedNotes.get(12) * 1000 +
                        arrangedNotes.get(3) * 5000
        ));
    }

    @Override
    public BigDecimal checkBalance(CashMachine machine, String cardNum, String pin) {
        return cardService.getBalance(cardNum, pin);
    }

    @Override
    public boolean changePin(String cardNum, String oldPin, String newPin) {
        return cardService.cnangePin(cardNum, oldPin, newPin);
    }
}