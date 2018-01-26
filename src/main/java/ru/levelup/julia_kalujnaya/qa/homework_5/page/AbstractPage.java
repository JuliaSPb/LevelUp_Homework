package ru.levelup.julia_kalujnaya.qa.homework_5.page;

import ru.levelup.julia_kalujnaya.qa.homework_5.service.WebDriverHelper;

/**
 * Родительский класс Page Object для других страниц сайта
 *
 * @author Юлия Калюжная
 */
public class AbstractPage {
    protected WebDriverHelper helper;

    public AbstractPage(WebDriverHelper driverHelper) {
        helper = driverHelper;
        helper.initPageFactoryElements(this);
    }

    /**
     * @return Возвращает заголовок текущей страницы
     */
    public String getCurrentPageTitle() {
        return helper.getCurrentPageTitle();
    }
}