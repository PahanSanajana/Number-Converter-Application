/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.util.Scanner;
import java.util.TreeMap;

/**
 *
 * @author Pahan Sanjana
 */

public class NumberConverter {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            displayMenu();
            choice = getIntInput("Enter Option -> ");
            processChoice(choice);
        } while (choice != 6);
    }

    private static void displayMenu() {
        System.out.println("\nNumber Converter\n");
        System.out.println("[01] Decimal Converter");
        System.out.println("[02] Binary Converter");
        System.out.println("[03] Octal Converter");
        System.out.println("[04] Hexadecimal Converter");
        System.out.println("[05] Roman Number Converter");
        System.out.println("[06] Exit");
    }

    private static void processChoice(int choice) {
        switch (choice) {
            case 1 -> decimalConverter();
            case 2 -> binaryConverter();
            case 3 -> octalConverter();
            case 4 -> hexadecimalConverter();
            case 5 -> romanConverter();
            case 6 -> System.out.println("Exiting...");
            default -> System.out.println("Invalid option, please try again.");
        }
    }

    private static void decimalConverter() {
        while (true) {
            int decimal = getIntInput("Enter a positive decimal number: ");
            if (decimal >= 0) {
                System.out.println("Binary: " + Integer.toBinaryString(decimal));
                System.out.println("Octal: " + Integer.toOctalString(decimal));
                System.out.println("Hexadecimal: " + Integer.toHexString(decimal).toUpperCase());
                break;
            } else {
                System.out.println("Invalid input...");
                if (!requestRepeat()) return;
            }
        }
        if (requestHome()) main(null);
    }

    private static void binaryConverter() {
        while (true) {
            String binary = getStringInput("Enter a binary number: ");
            if (binary.matches("[01]+")) {
                int decimal = Integer.parseInt(binary, 2);
                System.out.println("Decimal: " + decimal);
                System.out.println("Octal: " + Integer.toOctalString(decimal));
                System.out.println("Hexadecimal: " + Integer.toHexString(decimal).toUpperCase());
                break;
            } else {
                System.out.println("Invalid input...");
                if (!requestRepeat()) return;
            }
        }
        if (requestHome()) main(null);
    }

    private static void octalConverter() {
        while (true) {
            String octal = getStringInput("Enter an octal number: ");
            if (octal.matches("[0-7]+")) {
                int decimal = Integer.parseInt(octal, 8);
                System.out.println("Decimal: " + decimal);
                System.out.println("Binary: " + Integer.toBinaryString(decimal));
                System.out.println("Hexadecimal: " + Integer.toHexString(decimal).toUpperCase());
                break;
            } else {
                System.out.println("Invalid input...");
                if (!requestRepeat()) return;
            }
        }
        if (requestHome()) main(null);
    }

    private static void hexadecimalConverter() {
        while (true) {
            String hex = getStringInput("Enter a hexadecimal number: ");
            if (hex.matches("[0-9A-Fa-f]+")) {
                int decimal = Integer.parseInt(hex, 16);
                System.out.println("Decimal: " + decimal);
                System.out.println("Binary: " + Integer.toBinaryString(decimal));
                System.out.println("Octal: " + Integer.toOctalString(decimal));
                break;
            } else {
                System.out.println("Invalid input...");
                if (!requestRepeat()) return;
            }
        }
        if (requestHome()) main(null);
    }

    private static void romanConverter() {
        int option;
        do {
            displayRomanMenu();
            option = getIntInput("Enter an option -> ");
            switch (option) {
                case 1 -> decimalToRomanOption();
                case 2 -> romanToDecimalOption();
                default -> {
                    System.out.println("Invalid option, please try again.");
                    continue;
                }
            }
            if (!requestHome()) break;
        } while (true);
    }

    private static void displayRomanMenu() {
        System.out.println("\nRoman Number Converter\n");
        System.out.println("[01] Decimal Number to Roman Number Converter");
        System.out.println("[02] Roman Number to Decimal Number Converter");
    }

    private static void decimalToRomanOption() {
        int number = getIntInput("Enter a decimal number: ");
        System.out.println("Roman: " + decimalToRoman(number));
    }

    private static String decimalToRoman(int number) {
        TreeMap<Integer, String> map = new TreeMap<>();
        map.put(1000, "M");
        map.put(900, "CM");
        map.put(500, "D");
        map.put(400, "CD");
        map.put(100, "C");
        map.put(90, "XC");
        map.put(50, "L");
        map.put(40, "XL");
        map.put(10, "X");
        map.put(9, "IX");
        map.put(5, "V");
        map.put(4, "IV");
        map.put(1, "I");

        StringBuilder roman = new StringBuilder();
        while (number > 0) {
            int key = map.floorKey(number);
            roman.append(map.get(key));
            number -= key;
        }
        return roman.toString();
    }

    private static void romanToDecimalOption() {
        String roman = getStringInput("Enter a Roman numeral: ");
        System.out.println("Decimal: " + romanToDecimal(roman));
    }

    private static int romanToDecimal(String roman) {
        TreeMap<Character, Integer> map = new TreeMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int result = 0;
        int prev = 0;
        for (int i = roman.length() - 1; i >= 0; i--) {
            int temp = map.get(roman.charAt(i));
            if (temp < prev) {
                result -= temp;
            } else {
                result += temp;
            }
            prev = temp;
        }
        return result;
    }

    private static boolean requestRepeat() {
        return "Y".equalsIgnoreCase(getStringInput("Invalid number. Input 'Y' to try again, 'N' to return to homepage: "));
    }

    private static boolean requestHome() {
        return "Y".equalsIgnoreCase(getStringInput("Do you want to go to homepage (Y/N)? "));
    }

    private static int getIntInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.print("Please enter a valid number: ");
        }
        return scanner.nextInt();
    }

    private static String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.next();
    }
}

