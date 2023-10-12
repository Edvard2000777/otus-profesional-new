package org.example;

import java.util.*;

public class ATMEmuliator {
    private Map<Integer, Integer> hashmap = new HashMap<Integer, Integer>();

    public void addBanknotes(int denomination, int count) {
        hashmap.put(denomination, count);
    }

    public boolean withdraw(int amount) {
        Map<Integer, Integer> result = new HashMap<>();
        int remainigAmount = amount;
        List<Integer> sortedDenominations = new ArrayList<>(hashmap.keySet());
        Collections.sort(sortedDenominations, Collections.reverseOrder());

        for (int denomination : sortedDenominations) {
            int availableNotes = hashmap.get(denomination);
            int notesToWithdraw = remainigAmount / denomination;

            if (notesToWithdraw > 0 && availableNotes >= notesToWithdraw) {
                result.put(denomination, notesToWithdraw);
                remainigAmount -= notesToWithdraw * denomination;
            }
        }

        if (remainigAmount == 0) {
            for (int denomination : result.keySet()) {
                hashmap.put(denomination, hashmap.get(denomination) - result.get(denomination));
            }
            return true;
        } else {
            return false;
        }


    }

    public void demonstration()
    {
        hashmap.put(5000, 2);
        hashmap.put(10, 3);
        hashmap.put(50, 44);
        hashmap.put(100, 33);
        hashmap.put(200, 3);
        hashmap.put(500, 23);
        hashmap.put(1000, 23);
        hashmap.put(2000, 3);
        int withdrawalAmount = 10000;
        if (withdraw(withdrawalAmount)) {
            System.out.println("Выдано " + withdrawalAmount + " рублей");
        } else {
            System.out.println("Не удалось выдать " + withdrawalAmount + " рублей");
        }
    }
}
