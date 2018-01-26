package ru.levelup.julia_kalujnaya.qa.homework_5.page.objects;

import ru.levelup.julia_kalujnaya.qa.homework_5.page.AbstractPage;
import ru.levelup.julia_kalujnaya.qa.homework_5.page.elements.Header;
import ru.levelup.julia_kalujnaya.qa.homework_5.page.elements.RightBlock;
import ru.levelup.julia_kalujnaya.qa.homework_5.service.WebDriverHelper;

/**
 * Класс Page Object, представляющий главную страницу "Кинопоиска", содержащую блок заголовка и правый блок
 *
 * @author Юлия Калюжная
 */
public class KinopoiskMainPage extends AbstractPage {
    private Header header;
    private RightBlock rightBlock;

    public KinopoiskMainPage(WebDriverHelper helper) {
        super(helper);
        header = new Header(helper);
        rightBlock = new RightBlock(helper);
    }

    /**
     * Переходит на страницу с 250 лучшими фильмами
     * @return Возвращает страницу с 250 лучшими фильмами
     */
    public Top250Page viewTop250Movies() {
        return rightBlock.getTop250Movies();
    }

    /**
     * Переходит на предлагаемую статью
     * @return Возвращает страницу со статьёй
     */
    public AbstractPage readArticle() {
        return rightBlock.readArticle();
    }

    /**
     * Переходит на заданный пункт выпадающего меню "Афиша"
     * @param menuItem - название пункта меню "Афиша"
     * @return Возвращает страницу с афишей
     */
    public AbstractPage viewPlaybill(String menuItem) {
        return header.clickOnPlaybillMenuItem(menuItem);
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
     * Выполняет расширенный поиск фильма (по году, названию страны и жанру)
     * @param year - год выпуска
     * @param countryName - страна-производитель
     * @param genreName - жанр фильма
     * @return Возвращает страницу с результатами поиска
     */
    public SearchResultsPage extendedSearch(String year, String countryName, String genreName) {
        return header.clickExtendedSearchLink().sendKeysToYearTextField(year).selectCountry(countryName).selectGenre(genreName)
                .clickSearchButton();
    }
}