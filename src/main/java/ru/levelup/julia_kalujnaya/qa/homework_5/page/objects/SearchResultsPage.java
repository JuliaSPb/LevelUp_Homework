package ru.levelup.julia_kalujnaya.qa.homework_5.page.objects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.levelup.julia_kalujnaya.qa.homework_5.page.AbstractPage;
import ru.levelup.julia_kalujnaya.qa.homework_5.service.WebDriverHelper;

/**
 * Класс Page Object, представляющий страницу с результатами поиска:
 * firstFoundMovie - 1-й фильм в списке найденных
 *
 * @author Юлия Калюжная
 */
public class SearchResultsPage extends AbstractPage {
    @FindBy(xpath = "//*[@id=\"block_left_pad\"]/div/div[3]/div/div[2]/p/a")
    private WebElement firstFoundMovie;

    public SearchResultsPage(WebDriverHelper helper) {
        super(helper);
    }

    public String getFirstFoundMovieTitle() {
        return firstFoundMovie.getText();
    }

    /**
     * Выполняет клик на ссылке на 1-й фильм в списке найденных
     * @return Возвращает страницу фильма
     */
    public MoviePage viewFirstFoundMovie() {
        helper.click(firstFoundMovie);
        return new MoviePage(helper);
    }
}