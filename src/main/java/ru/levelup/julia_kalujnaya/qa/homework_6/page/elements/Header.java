package ru.levelup.julia_kalujnaya.qa.homework_6.page.elements;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.levelup.julia_kalujnaya.qa.homework_6.page.AbstractPage;
import ru.levelup.julia_kalujnaya.qa.homework_6.page.objects.ExtendedSearchPage;
import ru.levelup.julia_kalujnaya.qa.homework_6.page.objects.RegisterPage;
import ru.levelup.julia_kalujnaya.qa.homework_6.page.objects.SearchResultsPage;
import ru.levelup.julia_kalujnaya.qa.homework_6.service.WebDriverHelper;

/**
 * Класс Page Object, представляющий заголовок сайта:
 * searchTextField - поле для ввода строки поиска
 * searchButton - кнопка "Найти"
 * categoriesButton - выпадающее меню "Покупки по категориям"
 * cartNumber - количество товаров в корзине (если есть)
 * registerLink - ссылка "зарегистрируйтесь"
 * extendedSearchLink - ссылка "Расширенный"
 *
 * @author Юлия Калюжная
 */
public class Header extends AbstractPage {
    private static final String CATEGORIES_XPATH = "//*[@class=\"gh-sbc-parent\"]/a";
    private static final String SUBCATEGORIES_XPATH = "//*[@class=\"scnd\"]";

    @FindBy(id = "gh-ac")
    private WebElement searchTextField;

    @FindBy(id = "gh-btn")
    private WebElement searchButton;

    @FindBy(id = "gh-shop-a")
    private WebElement categoriesButton;

    @FindBy(id = "gh-cart-n")
    private WebElement cartNumber;

    @FindBy(xpath = "//*[@id=\"gh-ug-flex\"]/a")
    private WebElement registerLink;

    @FindBy(id = "gh-as-a")
    private WebElement extendedSearchLink;

    public Header(WebDriverHelper driverHelper) {
        super(driverHelper);
    }

    /**
     * Вводит заданную строку поиска в текстовое поле
     * @param searchString - заданная строка для поиска
     * @return Возвращает ту же страницу
     */
    public Header sendKeysToSearchTextField(final String searchString) {
        helper.sendKeysToTextField(searchTextField, searchString);
        return this;
    }

    /**
     * Выполняет клик на кнопку "Искать!"
     * @return Возвращает страницу с результатами поиска
     */
    public SearchResultsPage clickSearchButton() {
        helper.click(searchButton);
        return new SearchResultsPage(helper);
    }

    /**
     * Выполняет клик на заданный элемент выпадающего меню "Покупки по категориям"
     * @param menuItemName - название элемента в меню "Покупки по категориям"
     * @return Возвращает страницу со списком товаров
     */
    public SearchResultsPage clickOnCategoriesMenuItem(String menuItemName) {
        helper.clickOnMenuItem(categoriesButton, menuItemName);
        return new SearchResultsPage(helper);
    }

    /**
     * @return Возвращает количество товаров в корзине (если были добавлены)
     */
    public int checkCart() {
        return Integer.parseInt(cartNumber.getText());
    }

    /**
     * Выполняет клик на ссылку "зарегистрируйтесь"
     * @return Возвращает страницу регистрации
     */
    public RegisterPage clickRegisterLink() {
        helper.click(registerLink);
        return new RegisterPage(helper);
    }

    /**
     * Выполняет клики на всех ссылках категорий и затем подкатегорий в меню "Покупки по категориям"
     */
    public void clickAllCategoriesLinks() {
        helper.clickOnAllMenuItems(categoriesButton, CATEGORIES_XPATH);
        helper.clickOnAllMenuItems(categoriesButton, SUBCATEGORIES_XPATH);
    }

    /**
     * Выполняет клик на ссылку "Расширенный"
     * @return Возвращает страницу расширенного поиска
     */
    public ExtendedSearchPage clickExtendedSearchLink() {
        helper.click(extendedSearchLink);
        return new ExtendedSearchPage(helper);
    }
}