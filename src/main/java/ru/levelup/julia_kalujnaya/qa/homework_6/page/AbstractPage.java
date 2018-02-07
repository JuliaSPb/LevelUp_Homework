package ru.levelup.julia_kalujnaya.qa.homework_6.page;

import ru.levelup.julia_kalujnaya.qa.homework_6.service.WebDriverHelper;

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
}