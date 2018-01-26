package ru.levelup.julia_kalujnaya.qa.homework_5.page.objects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.levelup.julia_kalujnaya.qa.homework_5.page.AbstractPage;
import ru.levelup.julia_kalujnaya.qa.homework_5.service.WebDriverHelper;

import java.util.List;

/**
 * Класс Page Object, представляющий страницу фильма:
 * movieTitle - название фильма
 * movieYear - год выпуска фильма
 * movieCountries - перечень стран
 * movieGenres - перечень жанров фильма
 *
 * @author Юлия Калюжная
 */
public class MoviePage extends AbstractPage {
    @FindBy(xpath = "//*[@id=\"headerFilm\"]/h1")
    private WebElement movieTitle;

    @FindBy(xpath = "//*[@id=\"infoTable\"]/table/tbody/tr[1]/td[2]/div/a")
    private WebElement movieYear;

    @FindBy(xpath = "//*[@id=\"infoTable\"]/table/tbody/tr[2]/td[2]/div/a")
    private List<WebElement> movieCountries;

    @FindBy(xpath = "//*[@id=\"infoTable\"]/table/tbody/tr[11]/td[2]/span/a")
    private List<WebElement> movieGenres;

    public MoviePage(WebDriverHelper helper) {
        super(helper);
    }

    /**
     * @return Возвращает название фильма
     */
    public String getMovieTitle() {
        return movieTitle.getText();
    }

    /**
     * @return Возвращает год выпуска фильма
     */
    public String getMovieYear() {
        return movieYear.getText();
    }

    /**
     * Проверяет, есть ли заданная страна в списке стран-производителей фильма
     * @param countryName - заданная страна
     * @return Возвращает true, если данная страна присутствует
     */
    public boolean checkCountry(String countryName) {
        for (WebElement country : movieCountries) {
            if (country.getText().equals(countryName))
                return true;
        }
        return  false;
    }

    /**
     * Проверяет, есть ли заданный жанр в списке жанров фильма
     * @param genreName - заданный жанр
     * @return Возвращает true, если данный жанр присутствует
     */
    public boolean checkGenre(String genreName) {
        for (WebElement genre : movieGenres) {
            if (genre.getText().equals(genreName))
                return true;
        }
        return  false;
    }
}