package ru.levelup.julia_kalujnaya.qa.homework_5.test;

import org.junit.Assert;
import org.testng.annotations.*;
import ru.levelup.julia_kalujnaya.qa.homework_5.page.AbstractPage;
import ru.levelup.julia_kalujnaya.qa.homework_5.page.objects.KinopoiskMainPage;
import ru.levelup.julia_kalujnaya.qa.homework_5.page.objects.MoviePage;
import ru.levelup.julia_kalujnaya.qa.homework_5.page.objects.SearchResultsPage;
import ru.levelup.julia_kalujnaya.qa.homework_5.page.objects.Top250Page;
import ru.levelup.julia_kalujnaya.qa.homework_5.service.WebDriverHelper;

/**
 * Домашнее задание. Часть 5
 * Реализовано 5 функциональных тестов для сайта "Кинопоиск" с использованием Selenium WebDriver (браузер Chrome)
 *
 * @author Юлия Калюжная
 */
public class KinopoiskTest {
    private static final String URL = "http://www.kinopoisk.ru/";

    private static final String TOP250 = "250 лучших фильмов";
    private static final String BEST_MOVIE = "Побег из Шоушенка (1994)";
    private static final String ARTICLES = "статьи на КиноПоиске";
    private static final String PLAYBILL_MENU = "Сегодня в кино";
    private static final String PLAYBILL_RESULT = "билеты и расписание сеансов";
    private static final String SEARCH_STRING = "Величайший шоумен";
    private static final String SEARCH_RESULT = "Результаты поиска";
    private static final String YEAR = "2017";
    private static final String COUNTRY = "США";
    private static final String GENRE = "мюзикл";

    private WebDriverHelper helper;

    @BeforeSuite
    public void suiteSetUp() {
        helper = new WebDriverHelper();
    }

    @BeforeMethod
    public void init() {
        helper.initDriver();
        helper.getToURL(URL);
    }

    /**
     * Тест открывает страницу "250 лучших фильмов", проверяет заголовок страницы и то, что 1-м в списке идёт
     * заданный фильм
     */
    @Test
    public void viewTop250Test() {
        KinopoiskMainPage homePage = new KinopoiskMainPage(helper);
        Top250Page top250Page = homePage.viewTop250Movies();
        Assert.assertTrue(top250Page.getCurrentPageTitle().contains(TOP250));
        Assert.assertEquals(top250Page.getBestMovieTitle(), BEST_MOVIE);
    }

    /**
     * Тест открывает (в новой вкладке) страницу с предложенной сайтом статьёй и проверяет заголовок новой страницы
     */
    @Test
    public void readSuggestedArticle() {
        KinopoiskMainPage homePage = new KinopoiskMainPage(helper);
        AbstractPage article = homePage.readArticle();
        Assert.assertTrue(article.getCurrentPageTitle().contains(ARTICLES));
    }

    /**
     * Тест открывает афишу, выбрав пункт из выпадающего меню "Афиша", и проверяет заголовок страницы
     */
    @Test
    public void viewPlaybillTest() {
        KinopoiskMainPage homePage = new KinopoiskMainPage(helper);
        AbstractPage article = homePage.viewPlaybill(PLAYBILL_MENU);
        Assert.assertTrue(article.getCurrentPageTitle().contains(PLAYBILL_RESULT));
    }

    /**
     * Тест выполняет поиск фильма по заданному названию, проверяет заголовок страницы, затем переходит на страницу
     * 1-го фильма в списке найденных и проверяет, что его название соответствует искомой строке
     */
    @Test
    public void searchTest() {
        KinopoiskMainPage homePage = new KinopoiskMainPage(helper);
        SearchResultsPage resultsPage = homePage.search(SEARCH_STRING);
        Assert.assertTrue(resultsPage.getCurrentPageTitle().contains(SEARCH_RESULT));
        Assert.assertEquals(resultsPage.getFirstFoundMovieTitle(), SEARCH_STRING);
        MoviePage moviePage = resultsPage.viewFirstFoundMovie();
        Assert.assertEquals(moviePage.getMovieTitle(), SEARCH_STRING);
    }

    /**
     * Тест выполняет расширенный поиск по заданным году, стране и жанру фильма, проверяет заголовок страницы,
     * затем переходит на страницу 1-го фильма в списке найденных и проверяет, что его год, страна-производитель и жанр
     * соответствуют условиям поиска
     */
    @Test
    public void extendedSearchTest() {
        KinopoiskMainPage homePage = new KinopoiskMainPage(helper);
        SearchResultsPage resultsPage = homePage.extendedSearch(YEAR, COUNTRY, GENRE);
        Assert.assertTrue(resultsPage.getCurrentPageTitle().contains(SEARCH_RESULT));
        MoviePage moviePage = resultsPage.viewFirstFoundMovie();
        Assert.assertEquals(moviePage.getMovieYear(), YEAR);
        Assert.assertTrue(moviePage.checkCountry(COUNTRY));
        Assert.assertTrue(moviePage.checkGenre(GENRE));
}

    @AfterMethod
    public void quit() {
        helper.quitDriver();
    }
}