package ru.levelup.julia_kalujnaya.qa.homework_6.page.objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.levelup.julia_kalujnaya.qa.homework_6.page.AbstractPage;
import ru.levelup.julia_kalujnaya.qa.homework_6.service.WebDriverHelper;

import java.util.List;

/**
 * Класс Page Object, представляющий страницу расширенного поиска:
 * searchTextField - поле для ввода строки поиска
 * categorySelect - выпадающий список для выбора категории поиска
 * priceRange - текстовые поля для ввода минимального и максимального значения цены
 * buyNowCheckBox - чекбокс "Купить сейчас"
 * newCheckBox - чекбокс "Новый"
 * freeShippingCheckBox - чекбокс "Бесплатная международная доставка"
 * searchButton - кнопка "Поиск"
 *
 * @author Юлия Калюжная
 */

public class ExtendedSearchPage extends AbstractPage {
    @FindBy(id = "_nkw")
    private WebElement searchTextField;

    @FindBy(id = "e1-1")
    private WebElement categorySelect;

    @FindBy(className = "price")
    private List<WebElement> priceRange;

    @FindBy(id = "LH_BIN")
    private WebElement buyNowCheckBox;

    @FindBy(id = "LH_ItemConditionNew")
    private WebElement newCheckBox;

    @FindBy(id = "LH_FS")
    private WebElement freeShippingCheckBox;

    @FindBy(className = "btn-prim")
    private WebElement searchButton;

    public ExtendedSearchPage(WebDriverHelper driverHelper) {
        super(driverHelper);
    }

    /**
     * Вводит заданную строку поиска в текстовое поле
     * @param searchString - заданная строка для поиска
     * @return Возвращает ту же страницу
     */
    public ExtendedSearchPage sendKeysToSearchTextField(final String searchString) {
        helper.sendKeysToTextField(searchTextField, searchString);
        return this;
    }

    /**
     * Выбирает из выпадающего списка заданную категорию товаров
     * @param category - категория товаров
     * @return Возвращает ту же страницу
     */
    public ExtendedSearchPage selectCategory(final String category) {
        List<WebElement> options = categorySelect.findElements(By.tagName("option"));
        for (WebElement option : options) {
            if (category.equals(option.getText()))
                option.click();
        }
        return this;
    }

    /**
     * Вводит заданный диапазон цены в соответствующие текстовые поля
     * @param priceMin - минимальное значение цены
     * @param priceMax - максимальное значение цены
     * @return Возвращает ту же страницу
     */
    public ExtendedSearchPage sendKeysToPriceRangeTextFields(final String priceMin, final String priceMax) {
        helper.sendKeysToTextField(priceRange.get(0), priceMin);
        helper.sendKeysToTextField(priceRange.get(1), priceMax);
        return this;
    }

    /**
     * Ставит галочку в чекбоксе "Купить сейчас"
     * @return Возвращает ту же страницу
     */
    public ExtendedSearchPage checkBuyNowCheckBox() {
        helper.click(buyNowCheckBox);
        return this;
    }

    /**
     * Ставит галочку в чекбоксе "Новый"
     * @return Возвращает ту же страницу
     */
    public ExtendedSearchPage checkNewCheckBox() {
        helper.click(newCheckBox);
        return this;
    }

    /**
     * Ставит галочку в чекбоксе "Бесплатная международная доставка"
     * @return Возвращает ту же страницу
     */
    public ExtendedSearchPage checkFreeShippingCheckBox() {
        helper.click(freeShippingCheckBox);
        return this;
    }

    /**
     * Выполняет клик на кнопку "Поиск"
     * @return Возвращает страницу с результатами поиска
     */
    public SearchResultsPage clickSearchButton() {
        helper.click(searchButton);
        return new SearchResultsPage(helper);
    }
}