package ru.otus.pro.Yaremchuk.webserver.cashmachine.machine.service;

import ru.otus.pro.Yaremchuk.webserver.cashmachine.machine.data.MoneyBox;

import java.util.List;


public interface MoneyBoxService {

    int checkSum(MoneyBox moneyBox);

    void putMoney(MoneyBox moneyBox, int note100, int note500, int note1000, int note5000);

    List<Integer> getMoney(MoneyBox moneyBox, int sum);

}
