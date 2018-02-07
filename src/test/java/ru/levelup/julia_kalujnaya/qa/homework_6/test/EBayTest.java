package ru.levelup.julia_kalujnaya.qa.homework_6.test;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import ru.levelup.julia_kalujnaya.qa.homework_6.page.objects.EBayMainPage;
import ru.levelup.julia_kalujnaya.qa.homework_6.page.objects.ProductPage;
import ru.levelup.julia_kalujnaya.qa.homework_6.page.objects.SearchResultsPage;
import ru.levelup.julia_kalujnaya.qa.homework_6.service.DriverParams;
import ru.levelup.julia_kalujnaya.qa.homework_6.service.WebDriverHelper;

/**
 * Контрольная работа
 * Реализованы следующие тесты для сайта EBay с использованием Selenium WebDriver (браузер Chrome):
 * 1) проверка стоимости найденного товара
 * 2) проверка сортировки товаров
 * 3) добавление товара в корзину
 * 4) проверка валидности email при регистрации
 * 5) проверка валидности ссылок в меню
 * 6) проверка расширенного поиска.
 *
 * Для запуска тестов и генерации отчёта Allure выполнить в командной строке команду
 * mvn test site -Dtest=ru.levelup.julia_kalujnaya.qa.homework_6.test.EBayTest
 *
 * @author Юлия Калюжная
 */
public class EBayTest {
    private static final String SEARCH_STRING = "iPhone";
    private static final String CATEGORY_1 = "Гитары";
    private static final String SUBCATEGORY_1 = "Бас-гитары";
    private static final String SORTING = "по цене + доставке: по возрастанию";
    private static final String CATEGORY_2 = "Компьютеры и планшеты";
    private static final String SUBCATEGORY_2 = "Аксессуары для ноутбуков и стационарных ПК";
    private static final String SUBCATEGORY_3 = "Гарнитуры";
    private static final String EMAIL = "juliakalujnaya@gmail.com";
    private static final String MESSAGE = "Ваш адрес электронной почты уже зарегистрирован на eBay";
    private static final String EXTENDED_SEARCH_STRING = "Звездные войны";
    private static final String EXTENDED_SEARCH_CATEGORY = "DVD и фильмы";
    private static final float MIN_PRICE = 500;
    private static final float MAX_PRICE = 1000;

    private WebDriverHelper helper;

    @BeforeSuite
    public void suiteSetUp() {
        helper = new WebDriverHelper();
    }

    @BeforeMethod
    public void init() {
        helper.initDriver();
        helper.getToURL(DriverParams.URL);
    }

    /**
     * Тест выполняет поиск товара по заданному названию, переходит на страницу 1-го товара в списке найденных
     * и проверяет, что цена товара в рублях равна цене, указанной для него на странице результатов поиска
     */
    @Test
    public void searchAndPriceCheckTest() {
        EBayMainPage homePage = new EBayMainPage(helper);
        SearchResultsPage resultsPage = homePage.search(SEARCH_STRING);
        float priceOnSearchResultsPage = resultsPage.getProductPrice(1);
        ProductPage productPage = resultsPage.viewFoundProduct(1);
        Assert.assertTrue(priceOnSearchResultsPage == productPage.getPriceInRoubles());
    }

    /**
     * Тест выбирает заданную категорию товаров в меню "Покупки по категориям", затем заданную подкатегорию, после
     * чего выбирает заданный тип сортировки товаров и проверяет, что суммарная стоимость (цена+доставка) 1-го товара
     * меньше, чем 2-го, а 2-го товара меньше, чем 3-го
     */
    @Test
    public void sortByPriceAndShippingTest() {
        EBayMainPage homePage = new EBayMainPage(helper);
        SearchResultsPage resultsPage = homePage.clickOnCategoriesMenuItem(CATEGORY_1).clickOnSubcategoriesMenuItem(SUBCATEGORY_1)
                .selectSorting(SORTING);
        float firstPrice = resultsPage.getProductTotalPrice(1);
        float secondPrice = resultsPage.getProductTotalPrice(2);
        float thirdPrice = resultsPage.getProductTotalPrice(3);
        Assert.assertTrue(firstPrice <= secondPrice);
        Assert.assertTrue(secondPrice <= thirdPrice);
    }

    /**
     * Тест выбирает заданную категорию товаров в меню "Покупки по категориям", затем 2 заданные подкатегории,
     * переходит на страницу 1-го найденного товара в списке и добавляет его в корзину, после чего проверяет,
     * что количество товаров в корзине равно 1
     */
    @Test
    public void addToCartTest() {
        EBayMainPage homePage = new EBayMainPage(helper);
        int numberInCart = homePage.clickOnCategoriesMenuItem(CATEGORY_2)
                .clickOnSubcategoriesMenuItem(SUBCATEGORY_2).clickOnSubcategoriesMenuItem(SUBCATEGORY_3)
                .viewFoundProduct(1).selectAllSelectFields().clickAddToCartButton().checkCart();
        Assert.assertEquals(numberInCart, 1);
    }

    /**
     * Тест переходит по ссылке "зарегистрируйтесь", вводит уже зарегистрированный адрес электронной почты и
     * проверяет, что выводится сообщение о том, что данный адрес уже зарегистрирован
     */
    @Test
    public void emailCheckTest() {
        EBayMainPage homePage = new EBayMainPage(helper);
        String message = homePage.clickRegisterLink().sendKeysToEmailTextField(EMAIL).moveToNameTextField().getErrorMessage();
        Assert.assertTrue(message.contains(MESSAGE));
    }

    /**
     * Тест проверяет валидность всех ссылок в меню "Покупки по категориям"
     */
    @Test
    public void checkLinksTest() {
        EBayMainPage homePage = new EBayMainPage(helper);
        homePage.clickAllCategoriesLinks();
    }

    /**
     * Тест выполняет расширенный поиск товаров по заданному названию, категории, диапазону цен и ещё 3 параметрам
     * ("Купить сейчас", "Новый" и "Бесплатная международная доставка"), после чего проверяет, что все найденные
     * товары удовлетворяют заданным условиям поиска
     */
    @Test
    public void extendedSearchTest() {
        EBayMainPage homePage = new EBayMainPage(helper);
        SearchResultsPage resultsPage = homePage.clickExtendedSearchLink().sendKeysToSearchTextField(EXTENDED_SEARCH_STRING)
                .selectCategory(EXTENDED_SEARCH_CATEGORY)
                .sendKeysToPriceRangeTextFields(Float.toString(MIN_PRICE), Float.toString(MAX_PRICE))
                .checkBuyNowCheckBox().checkNewCheckBox().checkFreeShippingCheckBox().clickSearchButton();
        new SoftAssert().assertTrue(resultsPage.checkProductNames(EXTENDED_SEARCH_STRING));
        Assert.assertTrue(resultsPage.checkCategory(EXTENDED_SEARCH_CATEGORY));
        Assert.assertTrue(resultsPage.checkProductPrices(MIN_PRICE, MAX_PRICE));
        Assert.assertTrue(resultsPage.checkNewProduct());
        Assert.assertTrue(resultsPage.checkFreeShipping());
        Assert.assertTrue(resultsPage.checkBuyNow()); // Не выполняется, так как один товар имеет значение параметра
                                                    // "или предложение «Лучшая цена»" (вместо "Купить сейчас")
    }

    @AfterMethod
    public void quit() {
        helper.quitDriver();
    }
}