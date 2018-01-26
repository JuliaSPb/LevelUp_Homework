package ru.levelup.julia_kalujnaya.qa.homework_5.page.objects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.levelup.julia_kalujnaya.qa.homework_5.page.AbstractPage;
import ru.levelup.julia_kalujnaya.qa.homework_5.service.WebDriverHelper;

/**
 * Класс Page Object, представляющий страницу со списком 250 лучших фильмов:
 * firstMovie - 1-й фильм в списке 250 лучших фильмов
 *
 * @author Юлия Калюжная
 */
public class Top250Page extends AbstractPage {
    @FindBy(xpath = "//*[@id=\"top250_place_1\"]/td[2]/a")
    private WebElement firstMovie;

    public Top250Page(WebDriverHelper helper) {
        super(helper);
    }

    /**
     * @return Возвращает название 1-го фильма в списке 250 лучших фильмов
     */
    public String getBestMovieTitle() {
        return firstMovie.getText();
    }
}