package ru.levelup.julia_kalujnaya.qa.homework_4;

import ru.levelup.julia_kalujnaya.qa.homework_4.entities.Food;
import ru.levelup.julia_kalujnaya.qa.homework_4.entities.Hall;
import ru.levelup.julia_kalujnaya.qa.homework_4.entities.Movie;
import ru.levelup.julia_kalujnaya.qa.homework_4.entities.Session;

import java.util.*;

/**
 * Домашнее задание. Часть 4
 * Реализован API приложения "Кинотеатр", позволяющий добавлять/получать информацию о залах, фильмах, сеансах и
 * продуктах в баре, а также покупать билеты на определённый сеанс и еду в баре. Методы класса CinemaTheaterAPI
 * покрыты юнит-тестами с использованием TestNG.
 * Компиляция и запуск тестов из командной строки осуществляется командой mvn test
 *
 * @author Юлия Калюжная
 */
public class CinemaTheaterAPI {
    private List<Hall> halls;
    private List<Movie> movies;
    private List<Food> foods;

    public CinemaTheaterAPI() {
        halls = new ArrayList<>();
        movies = new ArrayList<>();
        foods = new ArrayList<>();
    }

    public List<Hall> getHalls() {
        return halls;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public List<Food> getFoods() {
        return foods;
    }

    /**
     * Добавляет в список залов новый зал
     * @param name - название зала
     * @param amountOfSeats - количество мест в зале
     * @return Возвращает 0 в случае успешного добавления и -1 при ошибочных входных данных (отрицательное число мест)
     */
    public int addHall(String name, int amountOfSeats) {
        if (amountOfSeats > 0) {
            halls.add(new Hall(name, amountOfSeats));
            return 0;
        }
        return -1;
    }

    /**
     * Добавляет в список фильмов новый фильм
     * @param title - название фильма
     * @param duration - продолжительность в минутах
     * @param description - описание фильма
     * @param actors - перечень актёров
     * @return Возвращает 0 в случае успешного добавления и -1 при ошибочных входных данных (отрицательная
     * продолжительность фильма)
     */
    public int addMovie(String title, int duration, String description, String actors) {
        if (duration > 0) {
            movies.add(new Movie(title, duration, description, actors));
            return 0;
        } else return -1;
    }

    /**
     * Добавляет к соответствующему фильму новый сеанс
     * @param movieId - порядковый номер фильма (начиная с 0)
     * @param hallId - порядковый номер зала (начиная с 0)
     * @param startTime - время начала сеанса
     * @param price - цена билета
     * @return Возвращает 0 в случае успешного добавления и -1 при ошибочных входных данных (неверный номер фильма или зала)
     */
    public int addSession(int movieId, int hallId, String startTime, double price) {
        if ((movieId >= 0) && (movieId < movies.size()) && (hallId >= 0) && (hallId < halls.size()) && (price > 0)) {
            Hall hall = halls.get(hallId);
            Movie movie = movies.get(movieId);
            movie.addSession(new Session(movie, hall, startTime, price));
            return 0;
        }
        return -1;
    }

    /**
     * Покупка билета
     * @param movieId - порядковый номер фильма (начиная с 0)
     * @param sessionId - порядковый номер сеанса (начиная с 0)
     * @param seatToBuy - номер места
     * @return Возвращает 0 в случае успешной покупки, -1 при ошибочных входных данных (неверный номер фильма, сеанса
     * или места) и -2, если билетов на сеанс нет или данное место занято
     */
    public int buyTicket(int movieId, int sessionId, int seatToBuy) {
        if ((movieId >= 0) && (movieId < movies.size()) && (sessionId >= 0) && (sessionId < movies.get(movieId).getSessions().size())
                && (seatToBuy > 0)) {
            Movie movie = movies.get(movieId);
            Session session = movie.getSessions().get(sessionId);
            return session.buyTicket(seatToBuy);
        }
        return -1;
    }

    /**
     * Добавляет в список товаров новый товар
     * @param name - наименование товара
     * @param price - цена товара
     * @param amount - количество товара в наличии
     * @return Возвращает 0 в случае успешного добавления и -1 при ошибочных входных данных (отрицательная цена или
     * количество товара)
     */
    public int addFood(String name, double price, int amount) {
        if ((price > 0) && (amount > 0)) {
            foods.add(new Food(name, price, amount));
            return 0;
        }
        return -1;
    }

    /**
     * Покупка товара в баре
     * @param foodId - порядковый номер товара (начиная с 0)
     * @param amount - покупаемое количество товара
     * @return Возвращает 0 в случае успешной покупки, -1 при ошибочных входных данных (неверный номер товара или
     * отрицательное количество) и -2, если товара недостаточно
     */
    public int buyFood(int foodId, int amount) {
        if ((foodId >= 0) && (foodId < foods.size()) && (amount > 0)) {
            Food food = foods.get(foodId);
            return food.buyFood(amount);
        } else return -1;
    }
}