package ru.levelup.julia_kalujnaya.qa.homework_3;

import java.util.Scanner;

/**
 * Вспомогательный класс для вывода сообщений пользователю на консоль и запроса данных у пользователя
 *
 * @author Юлия Калюжная
 */
public class ConsoleHelper {
    /**
     * Выводит сообщение на консоль с переводом строки
     * @param message - выводимое сообщение
     */
    public void printlnToConsole(String message) {
        System.out.println(message);
    }

    /**
     * Выводит сообщение на консоль без перевода строки
     * @param message - выводимое сообщение
     */
    public void printToConsole(String message) {
        System.out.print(message);
    }

    /**
     * Запрашивает у пользователя целое число от 1 до maxValue включительно (в случае ошибочного ввода выдаёт сообщение
     * об ошибке и ждёт правильный ввод)
     * @param maxValue - максимальное допустимое целое число
     * @return Возвращает введённое пользователем целое число
     */
    public int getIntValueFromConsole(int maxValue) {
        int value;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            if (scanner.hasNextInt()) {
                value = scanner.nextInt();
                if ((value > 0) && (value <= maxValue))
                    return value;
            } else {
                scanner.next();
            }
            printToConsole(">> Введено недопустимое значение, введите другое: ");
        }
    }
}