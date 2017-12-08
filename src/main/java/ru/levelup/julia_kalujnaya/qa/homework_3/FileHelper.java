package ru.levelup.julia_kalujnaya.qa.homework_3;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * Вспомогательный класс для считывания информации из текстового файла
 *
 * @author Юлия Калюжная
 */
class FileHelper {
    /**
     * Считывает текстовый файл построчно и разбивает каждую строку по разделителю ";" на пары "свойство - значение"
     * @param filePath - путь к файлу
     * @return Возвращает список из объектов HashMap, хранящих пары "свойство - значение"
     * @throws IOException - исключение в случае ошибки работы с файлом
     */
    List<HashMap<String,String>> parseFile(String filePath) throws IOException {
        try (Scanner scanner = new Scanner(new File(filePath))) {
            List<HashMap<String, String>> data = new ArrayList<>();
            while(scanner.hasNextLine()){
                HashMap<String, String> dataElement = new HashMap<>();
                String[] properties = scanner.nextLine().split(";");
                for (String property: properties) {
                    String[] pair = property.split("=");
                    if (pair.length == 2)
                        dataElement.put(pair[0].trim(), pair[1].trim());
                }
                data.add(dataElement);
            }
            return data;
        }
    }
}