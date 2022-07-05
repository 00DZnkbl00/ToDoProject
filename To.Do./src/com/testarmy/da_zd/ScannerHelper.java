package com.testarmy.da_zd;

import java.util.Scanner;

public class ScannerHelper {
    public static int getInt(Scanner scanner, int from, int to) {
        int result = getInt(scanner);
        while(result < from || result > to ) {
            System.out.println("liczba z poza zakresu!");
            System.out.println("podaj od " + from + " do " + to);
            result = getInt(scanner);
        }
        return result;


    }
    public static int getInt(Scanner scanner) {
        while(!scanner.hasNextInt()) {
            System.out.println("podaj liczbę:");
            scanner.next();
        }
        return scanner.nextInt();
    }
}
