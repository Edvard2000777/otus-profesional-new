package ru.otus.pro.Yaremchuk.cashmachine.machine.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.pro.Yaremchuk.cashmachine.machine.data.MoneyBox;
import ru.otus.pro.Yaremchuk.cashmachine.machine.service.MoneyBoxService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class MoneyBoxServiceImpl implements MoneyBoxService {


    @Override
    public int checkSum(MoneyBox moneyBox) {
        return moneyBox.getNote100() * 100 + moneyBox.getNote500() * 500 + moneyBox.getNote1000() * 1000 + moneyBox.getNote5000() * 5000;
    }

    @Override
    public void putMoney(MoneyBox moneyBox, int note100, int note500, int note1000, int note5000) {
        if (moneyBox == null) {
            throw new IllegalStateException("No money box");
        }

        moneyBox.setNote100(moneyBox.getNote100() + note100);
        moneyBox.setNote500(moneyBox.getNote500() + note500);
        moneyBox.setNote1000(moneyBox.getNote1000() + note1000);
        moneyBox.setNote5000(moneyBox.getNote5000() + note5000);
    }
//сократи метод ниже чтобы было меньше if
@Override
public List<Integer> getMoney(MoneyBox moneyBox, int sum) {
    List<Integer> result = new ArrayList<>(Arrays.asList(0, 0, 0, 0));

    int[] notes = {5000, 1000, 500, 100};
    for (int i = 0; i < notes.length; i++) {
        int requiredNotes = sum / notes[i];
        int chargedNotes = Math.min(requiredNotes, getNotesCount(moneyBox, notes[i]));
        sum -= chargedNotes * notes[i];
        result.set(i, chargedNotes);
        updateMoneyBox(moneyBox, notes[i], chargedNotes);
    }

    if (sum > 0) {
        throw new IllegalStateException("Not enough notes");
    }

    return result;
}

    private void updateMoneyBox(MoneyBox moneyBox, int noteValue, int chargedNotes) {
        switch (noteValue) {
            case 5000:
                moneyBox.setNote5000(moneyBox.getNote5000() - chargedNotes);
                break;
            case 1000:
                moneyBox.setNote1000(moneyBox.getNote1000() - chargedNotes);
                break;
            case 500:
                moneyBox.setNote500(moneyBox.getNote500() - chargedNotes);
                break;
            case 100:
                moneyBox.setNote100(moneyBox.getNote100() - chargedNotes);
                break;
        }
    }

    private int getNotesCount(MoneyBox moneyBox, int noteValue) {
        switch (noteValue) {
            case 5000:
                return moneyBox.getNote5000();
            case 1000:
                return moneyBox.getNote1000();
            case 500:
                return moneyBox.getNote500();
            case 100:
                return moneyBox.getNote100();
            default:
                return 0;
        }
    }

    private void updateMoneyBox(MoneyBox moneyBox, List<Integer> result) {
        moneyBox.setNote5000(moneyBox.getNote5000() - result.get(0));
        moneyBox.setNote1000(moneyBox.getNote1000() - result.get(1));
        moneyBox.setNote500(moneyBox.getNote500() - result.get(2));
        moneyBox.setNote100(moneyBox.getNote100() - result.get(3));
    }
}
