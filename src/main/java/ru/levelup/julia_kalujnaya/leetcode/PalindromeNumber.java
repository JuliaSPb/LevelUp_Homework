package ru.levelup.julia_kalujnaya.leetcode;

/**
 * Задание "Palindrome Number" (https://leetcode.com/problems/palindrome-number/)
 * Задача: определить, является ли целое число палиндромом
 *
 * @author Юлия Калюжная
 */
public class PalindromeNumber {
    /**
     * Проверка, является ли целое число палиндромом (путем сравнения символов в строке)
     * @param number - целое число
     * @return true, если число является палиндромом
     */
    private boolean isPalindrome(int number) {
        String stringValue = String.valueOf(number);
        int length = stringValue.length();
        for (int i = 0; i < length/2; i++) {
            if (stringValue.charAt(i) != stringValue.charAt(length - 1 - i))
                return false;
        }
        return true;
    }

    /**
     * Проверка, является ли целое число палиндромом (с использованием StringBuffer)
     * @param number - целое число
     * @return true, если число является палиндромом
     */
    private boolean isPalindromeString(int number) {
        String stringValue = String.valueOf(number);
        return new StringBuffer(stringValue).reverse().toString().equals(stringValue);
    }

    /**
     * Проверка, является ли целое число палиндромом (без использования строк)
     * @param number - целое число
     * @return true, если число является палиндромом
     */
    private boolean isPalindromeNoStrings(int number) {
        int num1 = number;
        int num2 = 0;
        while (number != 0) {
            num2 *= 10;
            num2 += number % 10;
            number /= 10;
        }
        return num1==num2;
    }

    public static void main(String[] args) {
        PalindromeNumber app = new PalindromeNumber();
        InputOutputHelper helper = new InputOutputHelper();
        int number = helper.getInteger("Введите целое число для проверки, является ли оно палиндромом:");
        helper.printMessage("Ответ (путем сравнения символов в строке): " + app.isPalindrome(number));
        helper.printMessage("Ответ (с использованием StringBuffer): " + app.isPalindromeString(number));
        helper.printMessage("Ответ (без использования строк): " + app.isPalindromeNoStrings(number));
    }
}
