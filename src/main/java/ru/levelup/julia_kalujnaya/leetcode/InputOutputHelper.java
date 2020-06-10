package ru.levelup.julia_kalujnaya.leetcode;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Вспомогательный класс для вывода сообщений на консоль и запроса данных с консоли
 *
 * @author Юлия Калюжная
 */
class InputOutputHelper {
    private Scanner scanner;

    InputOutputHelper() {
        scanner = new Scanner(System.in);
    }

    /**
     * Вывод сообщения на консоль
     * @param message - текст сообщения
     */
    void printMessage(String message) {
        System.out.println(message);
    }

    /**
     * Вывод на консоль сообщения и массива целых чисел
     * @param message - текст сообщения
     * @param array - массив целых чисел
     */
    void printMessageAndArray(String message, int[] array) {
        System.out.println(message + ((array != null) ? Arrays.toString(array) : ""));
    }

    /**
     * Запрос с консоли массива целых чисел
     * @return введённый пользователем массив целых чисел
     */
    int[] getIntArray() {
        int length = getInteger(">> Введите размер массива целых чисел: ");
        int[] array = new int[length];
        for (int i = 1; i < length + 1; i++) {
            array[i - 1] = getInteger(">> Введите " + i + "-й элемент массива: ");
        }
        return array;
    }

    /**
     * Запрос с консоли целого число (в случае ошибочного ввода выдаёт сообщение об ошибке и ждёт правильный ввод)
     * @param message - сообщение пользователю
     * @return введённое пользователем целое число
     */
    int getInteger(String message) {
        while (true) {
            printMessage(message);
            if (scanner.hasNextInt()) {
                return scanner.nextInt();
            } else {
                scanner.next();
                printMessage("Можно вводить только целые числа (int)");
            }
        }
    }
}