package ru.levelup.julia_kalujnaya.qa.homework_1.task_1;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * Вспомогательный класс для вывода сообщений пользователю на консоль, запроса данных у пользователя и генерации
 * случайных чисел и массива случайных чисел
 *
 * @author Юлия Калюжная
 */
public class PrintAndRandomHelper {
    /**
     * Выводит сообщение на консоль
     * @param message - текст сообщения
     */
    public static void printMessage(String message) {
        System.out.println(message);
    }

    /**
     * Выводит на консоль сообщение и массив
     * @param message - текст сообщения
     * @param array - выводимый массив
     */
    public static void printMessageAndArray(String message, int[] array) {
        System.out.println(message + ((array != null) ? Arrays.toString(array) : ""));
    }

    /**
     * Запрашивает у пользователя, будет ли тот задавать массив с консоли (ввести "0") либо массив генерируется
     * случайным образом (любое другое значение)
     * @return Возвращает true, если пользователь выбрал ввод массива с консоли
     */
    static boolean isUserArray() {
        printMessage("Would you like to enter integer array from console? (0 - console, 1 - random array)");
        Scanner scanner = new Scanner(System.in);
        return (scanner.hasNextInt() && (scanner.nextInt() == 0));
    }

    /**
     * Запрашивает у пользователя с консоли массив целых чисел
     * @return Возвращает введённый пользователем массив целых чисел
     */
    static int[] getUserArrayToSort() {
        Scanner scanner = new Scanner(System.in);
        int length = getPositiveInteger(scanner, "Enter the array size: ");
        int[] array = new int[length];
        for (int i = 1; i < length + 1; i++) {
            array[i - 1] = getInteger(scanner, "Enter the " + i + " integer: ");
        }
        return array;
    }

    /**
     * Запрашивает у пользователя положительное целое число (в случае ошибочного ввода выдаёт сообщение об ошибке и
     * ждёт правильный ввод)
     * @param scanner - экземпляр Scanner для работы с консолью
     * @param message - сообщение пользователю
     * @return Возвращает введённое пользователем положительное целое число
     */
    private static int getPositiveInteger(Scanner scanner, String message) {
        int number;
        while (true) {
            number = getInteger(scanner, message);
            if (number > 0) {
                return number;
            } else {
                printMessage("Wrong input, only positive integers allowed.");
            }
        }
    }

    /**
     * Запрашивает у пользователя целое число (в случае ошибочного ввода выдаёт сообщение об ошибке и ждёт правильный ввод)
     * @param scanner - экземпляр Scanner для работы с консолью
     * @param message - сообщение пользователю
     * @return Возвращает введённое пользователем целое число
     */
    private static int getInteger(Scanner scanner, String message) {
        while (true) {
            printMessage(message);
            if (scanner.hasNextInt()) {
                return scanner.nextInt();
            } else {
                scanner.next();
                printMessage("Wrong input, only integers allowed.");
            }
        }
    }

    /**
     * Генерирует случайное целое число в диапазоне от 0 до maxValue
     * @param maxValue - максимальное значение случайного числа
     * @return Возвращает случайное целое число
     */
    public static int getRandomInt(int maxValue) {
        Random random = new Random();
        return random.nextInt(maxValue);
    }

    /**
     * Генерирует массив случайных целых чисел в заданном диапазоне, размер массива также выбирается случайным образом
     * @param maxLength - максимальный размер массива
     * @param maxValue - максимальное значение элементов массива
     * @return Возвращает массив случайных целых чисел
     */
    public static int[] getRandomIntArray(int maxLength, int maxValue) {
        Random random = new Random();
        int length = random.nextInt(maxLength);
        int[] array = new int[length];
        for (int i = 0; i < length; i++) {
            array[i] = random.nextInt(maxValue);
        }
        return array;
    }
}
