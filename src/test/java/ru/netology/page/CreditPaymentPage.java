package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import ru.netology.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CreditPaymentPage {
    // Элемент заголовка страницы
    private SelenideElement heading = $(withText("Кредит по данным карты"));

    // Проверка видимости заголовка
    public CreditPaymentPage() {
        heading.shouldBe(visible); // Проверяем, что заголовок виден
    }

    // Элементы страницы оплаты по дебетовой карте
    private final SelenideElement cardNumberField = $("[placeholder='0000 0000 0000 0000']");
    private final SelenideElement monthField = $("[placeholder='08']");
    private final SelenideElement yearField = $("[placeholder='22']");
    private final SelenideElement ownerField = $$(".input__inner").findBy(text("Владелец")).$(".input__control");
    private final SelenideElement codeField = $("[placeholder='999']");
    private final SelenideElement continueButton = $$(".button__content").findBy(text("Продолжить"));

    private final SelenideElement successNotification = $(".notification_status_ok .notification__content");
    private final SelenideElement errorNotification = $(".notification_status_error .notification__content");


    private final SelenideElement cardNumberError = $$(".input__inner").findBy(text("Номер карты")).parent().$(".input__sub");
    private final SelenideElement monthError = $$(".input__inner").findBy(text("Месяц")).parent().$(".input__sub");
    private final SelenideElement yearError = $$(".input__inner").findBy(text("Год")).parent().$(".input__sub");
    private final SelenideElement holderError = $$(".input__inner").findBy(text("Владелец")).parent().$(".input__sub");
    private final SelenideElement codeError = $$(".input__inner").findBy(text("CVC/CVV")).parent().$(".input__sub");

    // заполняем форму
    public void fillCardPaymentForm(DataHelper.CardInfo cardInfo) {
        cardNumberField.setValue(cardInfo.getCardNumber());
        monthField.setValue(cardInfo.getMonth());
        yearField.setValue(cardInfo.getYear());
        ownerField.setValue(cardInfo.getHolder());
        codeField.setValue(cardInfo.getCvc());
        continueButton.click();
    }

    // Позитивный сценарий: операция одобрена
    public void shouldSeeSuccessNotification() {
        successNotification.shouldBe(visible, Duration.ofSeconds(20)).shouldHave(text("Операция одобрена Банком."));
    }

    // Негативный сценарий: банк отказал
    public void shouldSeeErrorNotification() {
        errorNotification.shouldBe(visible, Duration.ofSeconds(20)).shouldHave(text("Ошибка! Банк отказал в проведении операции."));
    }

    // Проверки ошибок валидации
    public void shouldSeeCardNumberError(String expectedText) {
        cardNumberError.shouldBe(visible).shouldHave(text(expectedText));
    }

    public void shouldSeeMonthError(String expectedText) {
        monthError.shouldBe(visible).shouldHave(text(expectedText));
    }

    public void shouldSeeYearError(String expectedText) {
        yearError.shouldBe(visible).shouldHave(text(expectedText));
    }

    public void shouldSeeHolderError(String expectedText) {
        holderError.shouldBe(visible).shouldHave(text(expectedText));
    }

    public void shouldSeeCVVError(String expectedText) {
        codeError.shouldBe(visible).shouldHave(text(expectedText));
    }
    // очистка полей
    public void clearAllFields() {
        cardNumberField.doubleClick().sendKeys(Keys.BACK_SPACE);
        monthField.doubleClick().sendKeys(Keys.BACK_SPACE);
        yearField.doubleClick().sendKeys(Keys.BACK_SPACE);
        ownerField.doubleClick().sendKeys(Keys.BACK_SPACE);
        codeField.doubleClick().sendKeys(Keys.BACK_SPACE);
    }
}
