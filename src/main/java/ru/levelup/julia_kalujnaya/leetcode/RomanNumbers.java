package ru.levelup.julia_kalujnaya.leetcode;

import java.util.HashMap;

/**
 * Задание "Roman to Integer" (https://leetcode.com/problems/roman-to-integer/)
 * Задача: перевести число, записанное в римской нотации, в целое число
 *
 * @author Юлия Калюжная
 */
public class RomanNumbers {
    /**
     * Конвертация числа, записанного в римской нотации, в целое число
     * @param romanNumber - строка, представляющая число, записанное римскими цифрами
     * @return сконвертированное число или -1 в случае ошибки ввода
     */
    private int romanToInt(String romanNumber) {
        HashMap<Character, Integer> alphabet = new HashMap<>();
        alphabet.put('I', 1);
        alphabet.put('V', 5);
        alphabet.put('X', 10);
        alphabet.put('L', 50);
        alphabet.put('C', 100);
        alphabet.put('D', 500);
        alphabet.put('M', 1000);

        int length = romanNumber.length();
        boolean twoDigitsFound;
        int result = 0;
        for (int i = 0; i < length; i++) {
            if (!alphabet.keySet().contains(romanNumber.charAt(i)))
                return -1;
            twoDigitsFound = false;
            switch (romanNumber.charAt(i)) {
                case 'I':
                    if ((i + 1 < length) && (romanNumber.charAt(i + 1) == 'V')) {
                        result += 4;
                        twoDigitsFound = true;
                    }
                    if ((i + 1 < length) && (romanNumber.charAt(i + 1) == 'X')) {
                        result += 9;
                        twoDigitsFound = true;
                    }
                case 'X':
                    if ((i + 1 < length) && (romanNumber.charAt(i + 1) == 'L')) {
                        result += 40;
                        twoDigitsFound = true;
                    }
                    if ((i + 1 < length) && (romanNumber.charAt(i + 1) == 'C')) {
                        result += 90;
                        twoDigitsFound = true;
                    }
                case 'C':
                    if ((i + 1 < length) && (romanNumber.charAt(i + 1) == 'D')) {
                        result += 400;
                        twoDigitsFound = true;
                    }
                    if ((i + 1 < length) && (romanNumber.charAt(i + 1) == 'M')) {
                        result += 900;
                        twoDigitsFound = true;
                    }
                default:
                    if (twoDigitsFound) {
                        i++;
                        break;
                    }
                    result += alphabet.get(romanNumber.charAt(i));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        RomanNumbers app = new RomanNumbers();
        InputOutputHelper helper = new InputOutputHelper();
        String romanNumber = helper.getString("Введите число в римской нотации:");
        helper.printMessage("Ответ: " + app.romanToInt(romanNumber));
    }
}