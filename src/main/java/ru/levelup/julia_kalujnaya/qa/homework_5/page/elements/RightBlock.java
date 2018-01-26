package ru.levelup.julia_kalujnaya.qa.homework_5.page.elements;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.levelup.julia_kalujnaya.qa.homework_5.page.AbstractPage;
import ru.levelup.julia_kalujnaya.qa.homework_5.page.objects.Top250Page;
import ru.levelup.julia_kalujnaya.qa.homework_5.service.WebDriverHelper;

/**
 * Класс Page Object, представляющий правый блок сайта:
 * top250Link - ссылка "Лучшие фильмы - топ 250"
 * articleLink - ссылка для перехода на предлагаемую статью
 *
 * @author Юлия Калюжная
 */
public class RightBlock extends AbstractPage {
    @FindBy(xpath = "//*[@id=\"block_right\"]/dl[5]/dt/a")
    private WebElement top250Link;

    @FindBy(xpath = "//*[@id=\"block_right\"]/div[2]/a")
    private WebElement articleLink;

    public RightBlock(WebDriverHelper helper) {
        super(helper);
    }

    /**
     * Переходит на страницу с 250 лучшими фильмами
     * @return Возвращает страницу с 250 лучшими фильмами
     */
    public Top250Page getTop250Movies() {
        helper.click(top250Link);
        return new Top250Page(helper);
    }

    /**
     * Переходит на статью из верхней части блока
     * @return Возвращает страницу со статьёй
     */
    public AbstractPage readArticle() {
        helper.click(articleLink);
        helper.switchToSecondTab();
        return new AbstractPage(helper);
    }
}