package ru.levelup.julia_kalujnaya.qa.homework_6.page.objects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.levelup.julia_kalujnaya.qa.homework_6.page.AbstractPage;
import ru.levelup.julia_kalujnaya.qa.homework_6.service.WebDriverHelper;

/**
 * Класс Page Object, представляющий страницу регистрации:
 * emailTextField - текстовое поле "Адрес эл.почты"
 * nameTextField - текстовое поле "Имя"
 * errorMessage - сообщение о вводе уже зарегистрированного адреса эл.почты
 *
 * @author Юлия Калюжная
 */

public class RegisterPage extends AbstractPage {
    @FindBy(id = "email")
    private WebElement emailTextField;

    @FindBy(id = "firstname")
    private WebElement nameTextField;

    @FindBy(id = "email_w")
    private WebElement errorMessage;

    public RegisterPage(WebDriverHelper driverHelper) {
        super(driverHelper);
    }

    /**
     * Вводит заданную строку в текстовое поле для адреса эл.почты
     * @param text - заданная строка
     * @return Возвращает ту же страницу
     */
    public RegisterPage sendKeysToEmailTextField(final String text) {
        helper.sendKeysToTextField(emailTextField, text);
        return this;
    }

    /**
     * Перемещает фокус в поле для ввода имени
     * @return Возвращает ту же страницу
     */
    public RegisterPage moveToNameTextField() {
        nameTextField.clear();
        return this;
    }

    /**
     * @return Возвращает текст сообщения о ошибочном адресе эл.почты
     */
    public String getErrorMessage() {
        helper.getWait().until(ExpectedConditions.visibilityOf(errorMessage));
        return errorMessage.getText();
    }
}