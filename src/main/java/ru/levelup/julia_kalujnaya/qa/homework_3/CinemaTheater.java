package ru.levelup.julia_kalujnaya.qa.homework_3;

import ru.levelup.julia_kalujnaya.qa.homework_3.entities.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Домашнее задание. Часть 3
 * Реализовано приложение "Кинотеатр", предоставляющее пользователю информацию о сеансах и возможность покупки
 * билетов на определённый сеанс, а также покупки еды в баре. Данные о фильмах и сеансах загружаются из файла.
 *
 * @author Юлия Калюжная
 */
public class CinemaTheater {
    private ConsoleHelper consoleHelper;
    private FileHelper fileHelper;
    private List<Hall> halls;
    private List<Movie> movies;
    private List<Food> foods;

    public static void main(String[] args) {
        CinemaTheater cinemaTheater = new CinemaTheater();
        cinemaTheater.loadDataFromFile();
        cinemaTheater.initMenu();
    }

    private CinemaTheater() {
        consoleHelper = new ConsoleHelper();
        fileHelper = new FileHelper();
        halls = new ArrayList<>();
        movies = new ArrayList<>();
        foods = new ArrayList<>();
    }

    /**
     * Загружает начальные данные для работы приложения из файла и создаёт все необходимые объекты
     */
    private void loadDataFromFile() {
        try {
            List<HashMap<String, String>> dataFromFile = fileHelper.parseFile("./src/main/resources/CinemaTheaterData.txt");
            for (HashMap<String, String> data: dataFromFile) {
                switch (data.get("entity")) {
                    case "Hall":    createHall(data);
                                    break;
                    case "Movie":   createMovie(data);
                                    break;
                    case "Session": createSession(data);
                                    break;
                    case "Food":    createFood(data);
                                    break;
                }
            }
        } catch (IOException | NullPointerException | IllegalArgumentException e) {
            consoleHelper.printlnToConsole("Не удалось загрузить данные из файла.");
            System.exit(1);
        }
    }

    /**
     * Создаёт экземпляр объекта Hall и добавляет его в список залов
     * @param data - информация для создания объекта в виде пар "свойство - значение"
     */
    private void createHall(HashMap<String, String> data) {
        halls.add(new Hall(data.get("name"), Integer.parseInt(data.get("amountOfSeats"))));
    }

    /**
     * Создаёт экземпляр объекта Movie и добавляет его в список фильмов
     * @param data - информация для создания объекта в виде пар "свойство - значение"
     */
    private void createMovie(HashMap<String, String> data) {
        movies.add(new Movie(data.get("title"), Integer.parseInt(data.get("duration")), data.get("description"), data.get("actors")));
    }

    /**
     * Создаёт экземпляр объекта Session (при условии существования заданных зала и фильма)
     * @param data - информация для создания объекта в виде пар "свойство - значение"
     */
    private void createSession(HashMap<String, String> data) {
        Hall hall = hallExists(data.get("hall"));
        Movie movie = movieExists(data.get("movie"));
        if ((hall != null) && (movie != null)) {
            movie.addSession(new Session(movie, hall, data.get("startTime"), Integer.parseInt(data.get("price"))));
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Проверяет существование зала с заданным названием
     * @param name - название зала
     * @return Возвращает найденный зал, если он существует
     */
    private Hall hallExists(String name) {
        for (Hall hall: halls) {
            if (hall.getName().equals(name)) {
                return hall;
            }
        }
        return null;
    }

    /**
     * Проверяет существование фильма с заданным названием
     * @param name - название фильма
     * @return Возвращает найденный фильм, если он существует
     */
    private Movie movieExists(String name) {
        for (Movie movie: movies) {
            if (movie.getTitle().equals(name)) {
                return movie;
            }
        }
        return null;
    }

    /**
     * Создаёт экземпляр объекта Food и добавляет его в список товаров
     * @param data - информация для создания объекта в виде пар "свойство - значение"
     */
    private void createFood(HashMap<String, String> data) {
        foods.add(new Food(data.get("name"), Integer.parseInt(data.get("price")), Integer.parseInt(data.get("amount"))));
    }

    /**
     * Выводит главное меню, запрашивает у пользователя пункт меню и выполняет соответствующее действие
     */
    private void initMenu() {
        while (true) {
            consoleHelper.printlnToConsole("ГЛАВНОЕ МЕНЮ");
            consoleHelper.printlnToConsole("1 - Показать информацию о сеансах");
            consoleHelper.printlnToConsole("2 - Покупка билетов");
            consoleHelper.printlnToConsole("3 - Заказ еды");
            consoleHelper.printlnToConsole("4 - Завершить работу");
            consoleHelper.printToConsole(">> Выберите пункт меню: ");
            int menuItem = consoleHelper.getIntValueFromConsole(4);
            consoleHelper.printlnToConsole("");
            switch (menuItem) {
                case 1: getSessionInformation();
                        break;
                case 2: buyTickets();
                        break;
                case 3: buyFood();
                        break;
                case 4: System.exit(0);
            }
        }
    }

    /**
     * Выводит пользователю информацию обо всех сеансах
     */
    private void getSessionInformation() {
        consoleHelper.printlnToConsole("ИНФОРМАЦИЯ О СЕАНСАХ");
        for (Movie movie: movies) {
            consoleHelper.printlnToConsole(movie.toString());
        }
    }

    /**
     * Покупка билетов: выводит пользователю пронумерованный список фильмов в прокате, после выбора фильма выводит
     * информацию о сеансах для данного фильма, запрашивает номер сеанса и количество покупаемых билетов. Затем
     * проверяет, не заняты ли указанные пользователем места и выводит сообщение об успешной покупке каждого билета
     * или о том, что данное место уже занято
     */
    private void buyTickets() {
        consoleHelper.printlnToConsole("ПОКУПКА БИЛЕТОВ");
        getMovies();
        consoleHelper.printToConsole(">> Выберите фильм: ");
        int movieId = consoleHelper.getIntValueFromConsole(movies.size());
        Movie movie = movies.get(movieId - 1);
        consoleHelper.printlnToConsole("\nИНФОРМАЦИЯ О ФИЛЬМЕ \n" + movie.toString());
        consoleHelper.printToConsole(">> Выберите сеанс для покупки билетов: ");
        int sessionId = consoleHelper.getIntValueFromConsole(movie.getSessions().size());
        Session session = movie.getSessions().get(sessionId - 1);
        if (session.getAvailableTickets().size() > 0) {
            consoleHelper.printlnToConsole("Список свободных мест: " + session.getAvailableSeats());
            consoleHelper.printToConsole(">> Введите количество билетов: ");
            int numberOfTickets = consoleHelper.getIntValueFromConsole(session.getAvailableTickets().size());
            int seatToBuy;
            for (int i = 1; i < numberOfTickets + 1; i++) {
                while (true) {
                    consoleHelper.printToConsole(">> Введите номер места (" + i + "-й билет): ");
                    seatToBuy = consoleHelper.getIntValueFromConsole(session.getHall().getAmountOfSeats());
                    if (session.buyTicket(seatToBuy)) {
                        consoleHelper.printlnToConsole("БИЛЕТ УСПЕШНО КУПЛЕН!");
                        break;
                    } else {
                        consoleHelper.printlnToConsole("К сожалению, место уже занято.");
                    }
                }
            }
        } else {
            consoleHelper.printlnToConsole("К сожалению, на данный сеанс все билеты проданы.");
        }
        consoleHelper.printlnToConsole("");
    }

    /**
     * Выводит пронумерованный список фильмов в прокате
     */
    private void getMovies() {
        for (int i = 1; i < movies.size() + 1; i++) {
            consoleHelper.printlnToConsole(i + " - \"" + movies.get(i - 1).getTitle() + "\"");
        }
    }

    /**
     * Покупка еды: выводит пользователю пронумерованный список товаров, запрашивает вид товара и его количество
     * и выводит сообщение об успешной покупке или об отсутствии товара
     */
    private void buyFood() {
        consoleHelper.printlnToConsole("ЗАКАЗ ЕДЫ");
        getFoodInformation();
        consoleHelper.printToConsole(">> Выберите товар: ");
        int foodId = consoleHelper.getIntValueFromConsole(foods.size());
        Food food = foods.get(foodId - 1);
        if (food.getAmount() > 0) {
            consoleHelper.printToConsole(">> Введите количество товара: ");
            int amount = consoleHelper.getIntValueFromConsole(food.getAmount());
            if (food.buyFood(amount)) {
                consoleHelper.printlnToConsole("ТОВАР УСПЕШНО КУПЛЕН!");
            }
        } else {
            consoleHelper.printlnToConsole("К сожалению, данный товар закончился.");
        }
        consoleHelper.printlnToConsole("");
    }

    /**
     * Выводит пронумерованный список товаров в баре
     */
    private void getFoodInformation() {
        for (int i = 1; i < foods.size() + 1; i++) {
            consoleHelper.printlnToConsole(i + " - " + foods.get(i - 1).toString());
        }
    }
}