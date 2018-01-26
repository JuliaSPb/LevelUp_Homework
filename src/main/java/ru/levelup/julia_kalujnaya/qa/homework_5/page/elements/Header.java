package ru.levelup.julia_kalujnaya.qa.homework_5.page.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.levelup.julia_kalujnaya.qa.homework_5.page.AbstractPage;
import ru.levelup.julia_kalujnaya.qa.homework_5.page.objects.ExtendedSearchPage;
import ru.levelup.julia_kalujnaya.qa.homework_5.page.objects.SearchResultsPage;
import ru.levelup.julia_kalujnaya.qa.homework_5.service.WebDriverHelper;

import javax.annotation.Nullable;

/**
 * Класс Page Object, представляющий заголовок сайта:
 * playbill - выпадающее меню "Афиша"
 * searchTextField - поле для ввода строки поиска
 * searchButton - кнопка "Искать!"
 * extendedSearchLink - ссылка "Расширенный поиск"
 *
 * @author Юлия Калюжная
 */
public class Header extends AbstractPage {
    @FindBy(className = "header-menu-partial-component__item-name")
    private WebElement playbill;

    @FindBy(className = "header-search-partial-component__search-field")
    private WebElement searchTextField;

    @FindBy(className = "header-search-partial-component__button")
    private WebElement searchButton;

    @FindBy(className = "header-search-partial-component__link")
    private WebElement extendedSearchLink;

    public Header(WebDriverHelper helper) {
        super(helper);
    }

    /**
     * Выполняет клик на заданный элемент выпадающего меню "Афиша"
     * @param menuItemName - название элемента в меню "Афиша"
     * @return Возвращает страницу с афишей
     */
    public AbstractPage clickOnPlaybillMenuItem(String menuItemName) {
        helper.moveToElement(playbill);
        String menuItemXPath = "//a[contains(., '" + menuItemName + "')]";
        WebElement menuItem = helper.findElement(By.xpath(menuItemXPath));
        helper.getWait().until(ExpectedConditions.visibilityOf(menuItem));
        helper.click(menuItem);
        return new AbstractPage(helper);
    }

    /**
     * Вводит заданную строку поиска в текстовое поле
     * @param searchString - заданная строка для поиска
     * @return Возвращает ту же страницу
     */
    public Header sendKeysToSearchTextField(final String searchString) {
        searchTextField.clear();
        searchTextField.sendKeys(searchString);
        helper.getWait().until(new ExpectedCondition<Boolean>() {
            @Nullable
            public Boolean apply(@Nullable WebDriver webDriver) {
                return searchString.equalsIgnoreCase(searchTextField.getAttribute("value"));
            }
        });
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
     * Выполняет клик на ссылку "Расширенный поиск"
     * @return Возвращает страницу расширенного поиска
     */
    public ExtendedSearchPage clickExtendedSearchLink() {
        helper.click(extendedSearchLink);
        return new ExtendedSearchPage(helper);
    }
}