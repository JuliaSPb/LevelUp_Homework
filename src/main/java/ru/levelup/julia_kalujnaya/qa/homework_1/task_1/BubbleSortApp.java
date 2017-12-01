package ru.levelup.julia_kalujnaya.qa.homework_1.task_1;

/**
 * Домашнее задание. Часть 1. Задание 1
 * Реализован алгоритм пузырьковой сортировки по возрастанию для массива целых чисел, задаваемого пользователем
 * либо сгенерированного случайным образом. Для изменения порядка элементов массива не используется дополнительное
 * выделение памяти
 *
 * @author Юлия Калюжная
 */
public class BubbleSortApp {
    public static void main(String[] args) {
        BubbleSortApp app = new BubbleSortApp();
        app.startApplication();
    }

    /**
     * Запрашивает у пользователя, будет ли тот задавать массив с консоли либо массив будет сгенерирован случайным образом,
     * создаёт массив выбранным способом, сортирует его и выводит пользователю
     */
    private void startApplication() {
        PrintAndRandomHelper.printMessage("BUBBLE SORT (ascending order)");
        int[] arrayToSort;
        if (PrintAndRandomHelper.isUserArray()) {
            arrayToSort = PrintAndRandomHelper.getUserArrayToSort();
        } else {
            arrayToSort = PrintAndRandomHelper.getRandomIntArray(20, 1000);
        }
        PrintAndRandomHelper.printMessageAndArray("Unsorted integer array: ", arrayToSort);
        bubbleSort(arrayToSort);
        PrintAndRandomHelper.printMessageAndArray("Sorted array: ", arrayToSort);
    }

    /**
     * Сортирует массив целых чисел методом пузырька
     * @param array - сортируемый массив целых чисел
     */
    public static void bubbleSort(int[] array) {
        if ((array == null) || (array.length == 0))
            return;
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1 ]) {
                    array[j] = array[j] + array[j + 1];
                    array[j + 1] = array[j + 1] - array[j];
                    array[j + 1] = -array[j + 1];
                    array[j] = array[j] - array[j + 1];
                }
            }
        }
    }
}