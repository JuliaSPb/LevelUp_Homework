package ru.levelup.julia_kalujnaya.qa.homework_4.test;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.levelup.julia_kalujnaya.qa.homework_4.CinemaTheaterAPI;

/**
 * Класс юнит-тестов для API приложения "Кинотеатр" с использованием TestNG.
 * Компиляция и запуск тестов из командной строки осуществляется командой mvn test
 *
 * @author Юлия Калюжная
 */
public class CinemaTheaterAPITest {
    private CinemaTheaterAPI cinemaTheater;

    private static final String HALL_NAME = "Белый зал";
    private static final int HALL_AMOUNT_OF_SEATS = 20;
    private static final String MOVIE_TITLE = "Последний богатырь";
    private static final int MOVIE_DURATION = 114;
    private static final String MOVIE_DESCRIPTION = "Иван, обычный парень, по воле случая переносится из современной Москвы в фантастическую страну Белогорье.";
    private static final String MOVIE_ACTORS = "Виктор Хориняк, Мила Сивацкая, Екатерина Вилкова, Елена Яковлева, Константин Лавроненко и др.";
    private static final String SESSION_TIME ="10:00";
    private static final int SESSION_PRICE = 200;
    private static final String FOOD_NAME = "Поп-корн солёный";
    private static final int FOOD_PRICE = 150;
    private static final int FOOD_AMOUNT = 50;
    private static final int MOVIE_ID = 0;
    private static final int HALL_ID = 0;
    private static final int SESSION_ID = 0;
    private static final int SEAT_TO_BUY = 1;
    private static final int FOOD_ID = 0;
    private static final int FOOD_AMOUNT_TO_BUY = 10;

    /**
     * Вспомогательный метод для создания зала и фильма
     */
    private void prepareHallAndMovie() {
        cinemaTheater.addHall(HALL_NAME, HALL_AMOUNT_OF_SEATS);
        cinemaTheater.addMovie(MOVIE_TITLE, MOVIE_DURATION, MOVIE_DESCRIPTION, MOVIE_ACTORS);
    }

    /**
     * Вспомогательный метод для создания зала и фильма с сеансом
     */
    private void prepareHallMovieAndSession() {
        prepareHallAndMovie();
        cinemaTheater.addSession(MOVIE_ID, HALL_ID, SESSION_TIME, SESSION_PRICE);
    }

    /**
     * Вспомогательный метод для создания товара
     */
    private void prepareFood() {
        cinemaTheater.addFood(FOOD_NAME, FOOD_PRICE, FOOD_AMOUNT);
    }

    @BeforeMethod
    public void init() {
        cinemaTheater = new CinemaTheaterAPI();
    }

    @AfterMethod
    public void after() {
        cinemaTheater = null;
    }

    @DataProvider
    public Object[][] addHallData() {
        return new Object[][]{
                {0, HALL_NAME, HALL_AMOUNT_OF_SEATS},
                {-1, HALL_NAME, 0},
                {-1, HALL_NAME, -10}
        };
    }

    @Test(dataProvider = "addHallData")
    public void testAddHall(int result, String name, int amountOfSeats) {
        Assert.assertEquals(cinemaTheater.addHall(name, amountOfSeats), result);
    }

    @DataProvider
    public Object[][] addMovieData() {
        return new Object[][]{
                {0, MOVIE_TITLE, MOVIE_DURATION, MOVIE_DESCRIPTION, MOVIE_ACTORS},
                {-1, MOVIE_TITLE, 0, MOVIE_DESCRIPTION, MOVIE_ACTORS},
                {-1, MOVIE_TITLE, -10, MOVIE_DESCRIPTION, MOVIE_ACTORS}
        };
    }

    @Test(dataProvider = "addMovieData")
    public void testAddMovie(int result, String title, int duration, String description, String actors) {
        Assert.assertEquals(cinemaTheater.addMovie(title, duration, description, actors), result);
    }

    @DataProvider
    public Object[][] addSessionData() {
        return new Object[][]{
                {0, MOVIE_ID, HALL_ID, SESSION_TIME, SESSION_PRICE},
                {-1, -1, HALL_ID, SESSION_TIME, SESSION_PRICE},
                {-1, 1, HALL_ID, SESSION_TIME, SESSION_PRICE},
                {-1, MOVIE_ID, -1, SESSION_TIME, SESSION_PRICE},
                {-1, MOVIE_ID, 1, SESSION_TIME, SESSION_PRICE},
                {-1, MOVIE_ID, HALL_ID, SESSION_TIME, 0},
                {-1, MOVIE_ID, HALL_ID, SESSION_TIME, -10}
        };
    }

    @Test(dataProvider = "addSessionData", dependsOnMethods = {"testAddHall", "testAddMovie"})
    public void testAddSession(int result, int movieId, int hallId, String startTime, double price) {
        prepareHallAndMovie();
        Assert.assertEquals(cinemaTheater.addSession(movieId, hallId, startTime, price), result);
    }

    @DataProvider
    public Object[][] buyTicketData() {
        return new Object[][]{
                {0, MOVIE_ID, SESSION_ID, SEAT_TO_BUY},
                {-1, -1, SESSION_ID, SEAT_TO_BUY},
                {-1, 1, SESSION_ID, SEAT_TO_BUY},
                {-1, MOVIE_ID, -1, SEAT_TO_BUY},
                {-1, MOVIE_ID, 1, SEAT_TO_BUY},
                {-1, MOVIE_ID, SESSION_ID, 0},
                {-1, MOVIE_ID, SESSION_ID, -10},
                {-1, MOVIE_ID, SESSION_ID, 21}
        };
    }

    @Test(dataProvider = "buyTicketData", dependsOnMethods = {"testAddHall", "testAddMovie", "testAddSession"})
    public void testBuyTicket(int result, int movieId, int sessionId, int seat) {
        prepareHallMovieAndSession();
        Assert.assertEquals(cinemaTheater.buyTicket(movieId, sessionId, seat), result);
    }

    @DataProvider
    public Object[][] buyAllTicketsData() {
        return new Object[][]{
                {0, -2, MOVIE_ID, SESSION_ID}
        };
    }

    @Test(dataProvider = "buyAllTicketsData", dependsOnMethods = {"testAddHall", "testAddMovie", "testAddSession"})
    public void testBuyAllTickets(int result, int negativeResult, int movieId, int sessionId) {
        prepareHallMovieAndSession();
        for (int i = 1; i <= HALL_AMOUNT_OF_SEATS; i++) {
            Assert.assertEquals(cinemaTheater.buyTicket(movieId, sessionId, i), result);
            Assert.assertEquals(cinemaTheater.buyTicket(movieId, sessionId, i), negativeResult);
        }
        Assert.assertEquals(cinemaTheater.buyTicket(movieId, sessionId, SEAT_TO_BUY), negativeResult);
    }

    @DataProvider
    public Object[][] addFoodData() {
        return new Object[][]{
                {0, FOOD_NAME, FOOD_PRICE, FOOD_AMOUNT},
                {-1, FOOD_NAME, 0, FOOD_AMOUNT},
                {-1, FOOD_NAME, -10, FOOD_AMOUNT},
                {-1, FOOD_NAME, FOOD_PRICE, 0},
                {-1, FOOD_NAME, FOOD_PRICE, -10}
        };
    }

    @Test(dataProvider = "addFoodData")
    public void testAddFood(int result, String name, double price, int amount) {
        Assert.assertEquals(cinemaTheater.addFood(name, price, amount), result);
    }

    @DataProvider
    public Object[][] buyFoodData() {
        return new Object[][]{
                {0, FOOD_ID, FOOD_AMOUNT_TO_BUY},
                {-1, -1, FOOD_AMOUNT_TO_BUY},
                {-1, 1, FOOD_AMOUNT_TO_BUY},
                {-1, FOOD_ID, 0},
                {-1, FOOD_ID, -10},
                {-2, FOOD_ID, 51}
        };
    }

    @Test(dataProvider = "buyFoodData", dependsOnMethods = {"testAddFood"})
    public void testBuyFood(int result, int foodId, int amount) {
        prepareFood();
        Assert.assertEquals(cinemaTheater.buyFood(foodId, amount), result);
    }
}