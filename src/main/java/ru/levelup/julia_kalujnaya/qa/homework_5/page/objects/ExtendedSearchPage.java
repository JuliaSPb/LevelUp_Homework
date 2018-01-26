package ru.levelup.julia_kalujnaya.qa.homework_5.page.objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import ru.levelup.julia_kalujnaya.qa.homework_5.page.AbstractPage;
import ru.levelup.julia_kalujnaya.qa.homework_5.service.WebDriverHelper;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Класс Page Object, представляющий страницу расширенного поиска:
 * yearTextField - поле для ввода года выпуска фильма
 * countrySelect - список для выбора страны
 * genreSelect - список для выбора жанра фильма
 * searchButton - кнопка "Поиск"
 *
 * @author Юлия Калюжная
 */
public class ExtendedSearchPage extends AbstractPage {
    @FindBy(className = "__yearSB__")
    private WebElement yearTextField;

    @FindBy(className = "__countrySB__")
    private WebElement countrySelect;

    @FindBy(className = "__genreSB__")
    private WebElement genreSelect;

    @FindBy(className = "el_18")
    private WebElement searchButton;

    public ExtendedSearchPage(WebDriverHelper helper) {
        super(helper);
    }

    /**
     * Вводит заданный год в текстовое поле
     * @param year - заданный год
     * @return Возвращает ту же страницу
     */
    public ExtendedSearchPage sendKeysToYearTextField(final String year) {
        yearTextField.clear();
        yearTextField.sendKeys(year);
        helper.getWait().until(new ExpectedCondition<Boolean>() {
            @Nullable
            public Boolean apply(@Nullable WebDriver webDriver) {
                return year.equalsIgnoreCase(yearTextField.getAttribute("value"));
            }
        });
        return this;
    }

    /**
     * Выбирает заданную страну в списке стран для поиска
     * @param countryName - название страны
     * @return Возвращает ту же страницу
     */
    public ExtendedSearchPage selectCountry(final String countryName) {
        List<WebElement> options = countrySelect.findElements(By.tagName("option"));
        for (WebElement option : options) {
            if (countryName.equals(option.getText()))
                option.click();
        }
        return this;
    }

    /**
     * Выбирает заданный жанр в списке жанров для поиска
     * @param genreName - название жанра
     * @return Возвращает ту же страницу
     */
    public ExtendedSearchPage selectGenre(final String genreName) {
        List<WebElement> options = genreSelect.findElements(By.tagName("option"));
        for (WebElement option : options) {
            if (genreName.equals(option.getText()))
                option.click();
        }
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