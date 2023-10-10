package org.example;

import java.io.BufferedReader;
import java.nio.ByteBuffer;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
       ATMEmuliator priem=new ATMEmuliator();
        priem.addBanknotes(5000, 2);
        priem.addBanknotes(10, 3);
        priem.addBanknotes(50, 44);
        priem.addBanknotes(100, 33);
        priem.addBanknotes(200, 3);
        priem.addBanknotes(500, 23);
        priem.addBanknotes(1000, 23);
        priem.addBanknotes(2000, 3);
          int withdrawalAmount =10000;
        if (priem.withdraw(withdrawalAmount)){
            System.out.println("Выдано " + withdrawalAmount + " рублей");
        }else { System.out.println("Не удалось выдать " + withdrawalAmount + " рублей");}



    }
}