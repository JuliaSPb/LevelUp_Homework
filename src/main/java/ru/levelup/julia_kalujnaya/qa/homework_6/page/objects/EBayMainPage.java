package ru.levelup.julia_kalujnaya.qa.homework_6.page.objects;

import ru.levelup.julia_kalujnaya.qa.homework_6.page.AbstractPage;
import ru.levelup.julia_kalujnaya.qa.homework_6.page.elements.Header;
import ru.levelup.julia_kalujnaya.qa.homework_6.service.WebDriverHelper;

/**
 * Класс Page Object, представляющий главную страницу EBay, содержащую блок заголовка
 *
 * @author Юлия Калюжная
 */
public class EBayMainPage extends AbstractPage {
    private Header header;

    public EBayMainPage(WebDriverHelper driverHelper) {
        super(driverHelper);
        header = new Header(helper);
    }

    /**
     * Выполняет поиск заданной строки
     * @param searchString - искомая строка
     * @return Возвращает страницу с результатами поиска
     */
    public SearchResultsPage search(String searchString) {
        return header.sendKeysToSearchTextField(searchString).clickSearchButton();
    }

    /**
     * Выполняет клик на заданный элемент выпадающего меню "Покупки по категориям"
     * @param menuItemName - название элемента в меню "Покупки по категориям"
     * @return Возвращает страницу со списком товаров
     */
    public SearchResultsPage clickOnCategoriesMenuItem(String menuItemName) {
        return header.clickOnCategoriesMenuItem(menuItemName);
    }

    /**
     * Выполняет клик на ссылку "зарегистрируйтесь"
     * @return Возвращает страницу регистрации
     */
    public RegisterPage clickRegisterLink() {
        return header.clickRegisterLink();
    }

    /**
     * Выполняет клики на всех ссылках в меню "Покупки по категориям"
     */
    public void clickAllCategoriesLinks() {
        header.clickAllCategoriesLinks();
    }

    /**
     * Выполняет клик на ссылку "Расширенный"
     * @return Возвращает страницу расширенного поиска
     */
    public ExtendedSearchPage clickExtendedSearchLink() {
        header.clickExtendedSearchLink();
        return new ExtendedSearchPage(helper);
    }
}