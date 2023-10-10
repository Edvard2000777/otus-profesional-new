package org.example;

import java.util.*;

public class ATMEmuliator {
    Map<Integer, Integer> hashmap = new HashMap<Integer, Integer>();

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
                hashmap.put(denomination,hashmap.get(denomination)-result.get(denomination));
            }
            return true;
        }else {return false; }


    }
}
