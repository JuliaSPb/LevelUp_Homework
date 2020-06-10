package ru.levelup.julia_kalujnaya.leetcode;

/**
 * Задание "Two Sum" (https://leetcode.com/problems/two-sum/)
 * Задача: найти индексы 2 элементов целочисленного массива, сумма которых равна заданному числу
 * (предполагается, что решение существует и единственно)
 *
 * @author Юлия Калюжная
 */
public class TwoSum {
    /**
     * Поиск индексов 2 элементов целочисленного массива, сумма которых равна заданному числу
     * @param numbers - массив целых чисел
     * @param target - заданное число
     * @return индексы 2 элементов массива
     */
    private int[] twoSum(int[] numbers, int target) {
        int[] result = new int[2];
        int i, j;
        stop: for (i = 0; i < numbers.length - 1; i++) {
            for (j = i + 1; j < numbers.length; j++) {
                if (numbers[i] + numbers[j] == target) {
                    result[0] = i;
                    result[1] = j;
                    break stop;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        TwoSum app = new TwoSum();
        InputOutputHelper helper = new InputOutputHelper();
        int[] inputArray = helper.getIntArray();
        helper.printMessageAndArray("Вы ввели массив: ", inputArray);
        int target = helper.getInteger("Введите результирующее число:");
        helper.printMessageAndArray("Ответ: ", app.twoSum(inputArray, target));
    }
}