package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.page.CreditPaymentPage;
import ru.netology.page.CardPaymentPage;
import ru.netology.page.PaymentPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaymentByCardTest {

    @BeforeAll
     static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
     static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
     void setUp() {
        open("http://localhost:8080");
    }

    @AfterEach
    void shouldCleanBase() {
        SQLHelper.cleanDatabase();
    }

    @Test
    @DisplayName("Should approved card payment")
     void shouldApprovePaymentWithValidCard() {
        var cardInfo = DataHelper.getValidCardInfo();
        var paymentPage = new PaymentPage();
        paymentPage.verifyHeadingIsVisible();
        paymentPage.proceedToPaymentByCard();
        var cardPaymentPage = new CardPaymentPage();
        cardPaymentPage.fillCardPaymentForm(cardInfo);
        cardPaymentPage.shouldSeeSuccessNotification();
        assertEquals("APPROVED", SQLHelper.getCardPaymentStatus());
    }

    @Test
    @DisplayName("Should declined payment")
    void shouldDeclinedPaymentWithDeclinedCard() {
        var cardInfo = DataHelper.getValidCardInfoCredit();
        var paymentPage = new PaymentPage();
        paymentPage.proceedToPaymentByCard();
        var cardPaymentPage = new CardPaymentPage();
        cardPaymentPage.fillCardPaymentForm(cardInfo);
        cardPaymentPage.shouldSeeErrorNotification();
        assertEquals("DECLINED", SQLHelper.getCardPaymentStatus());
    }

    @Test
    @DisplayName("Should be an error when paying with an unknown card")
    void cardNumberNotRegistered() {
        var cardInfo =  DataHelper.getUnknownCardInfo();
        var paymentPage = new PaymentPage();
        paymentPage.proceedToPaymentByCard();
        var cardPaymentPage = new CardPaymentPage();
        cardPaymentPage.fillCardPaymentForm(cardInfo);
        cardPaymentPage.shouldSeeErrorNotification();
    }

    @Test
    @DisplayName("Should be an error when paying card number is too short")
    void cardNumberIsTooShort() {
        var cardInfo = DataHelper.getCardWithShortNumber();
        var paymentPage = new PaymentPage();
        paymentPage.proceedToPaymentByCard();
        var cardPaymentPage = new CardPaymentPage();
        cardPaymentPage.fillCardPaymentForm(cardInfo);
        cardPaymentPage.shouldSeeCardNumberError("Неверный формат");
    }

    @Test
    @DisplayName("Should show error when card number is empty")
    void fieldCardNumberIsEmpty() {
        var cardInfo = DataHelper.getEmptyCardNumberInfo();
        var paymentPage = new PaymentPage();
        paymentPage.proceedToPaymentByCard();
        var cardPaymentPage = new CardPaymentPage();
        cardPaymentPage.fillCardPaymentForm(cardInfo);
        cardPaymentPage.shouldSeeCardNumberError("Поле обязательно для заполнения");
    }

    @Test
    @DisplayName("Should show error if the month field is empty")
    void fieldMonthIsEmpty() {
        var cardInfo = DataHelper.getEmptyMonthInfo();
        var paymentPage = new PaymentPage();
        paymentPage.proceedToPaymentByCard();
        var cardPaymentPage = new CardPaymentPage();
        cardPaymentPage.fillCardPaymentForm(cardInfo);
        cardPaymentPage.shouldSeeMonthError("Поле обязательно для заполнения");
    }

    @Test
    @DisplayName("Should show error if the month field is filled with a single digit")
    void monthFilledOneNumber() {
        var cardInfo = DataHelper.getMonthOneNumber();
        var paymentPage = new PaymentPage();
        paymentPage.proceedToPaymentByCard();
        var cardPaymentPage = new CardPaymentPage();
        cardPaymentPage.fillCardPaymentForm(cardInfo);
        cardPaymentPage.shouldSeeMonthError("Неверный формат");
    }

    @Test
    @DisplayName("Should show error when month is 00")
    void monthFilledIs00() {
        var cardInfo = DataHelper.getMonthWithZeros();
        var paymentPage = new PaymentPage();
        paymentPage.proceedToPaymentByCard();
        var cardPaymentPage = new CardPaymentPage();
        cardPaymentPage.fillCardPaymentForm(cardInfo);
        cardPaymentPage.shouldSeeMonthError("Неверно указан срок действия карты");
    }

    @Test
    @DisplayName("Should show error when entering invalid month")
    void filledInvalidMonth() {
        var cardInfo = DataHelper.getMonthUnreal();
        var paymentPage = new PaymentPage();
        paymentPage.proceedToPaymentByCard();
        var cardPaymentPage = new CardPaymentPage();
        cardPaymentPage.fillCardPaymentForm(cardInfo);
        cardPaymentPage.shouldSeeMonthError("Неверно указан срок действия карты");
    }

    @Test
    @DisplayName("Should show error when entering the past year when card payment")
    void enteringThePastYear() {
        var cardInfo = DataHelper.getInvalidPastYear();
        var paymentPage = new PaymentPage();
        paymentPage.proceedToPaymentByCard();
        var cardPaymentPage = new CardPaymentPage();
        cardPaymentPage.fillCardPaymentForm(cardInfo);
        cardPaymentPage.shouldSeeYearError("Истек срок действия карты");
    }

    @Test
    @DisplayName("Should show error if the year field is empty")
    void fieldYearIsEmpty() {
        var cardInfo = DataHelper.getEmptyYearInfo();
        var paymentPage = new PaymentPage();
        paymentPage.proceedToPaymentByCard();
        var cardPaymentPage = new CardPaymentPage();
        cardPaymentPage.fillCardPaymentForm(cardInfo);
        cardPaymentPage.shouldSeeYearError("Поле обязательно для заполнения");
    }

    @Test
    @DisplayName("Should show error if the year field is filled with a single digit")
    void yearFilledOneNumber() {
        var cardInfo = DataHelper.getYearOneNumber();
        var paymentPage = new PaymentPage();
        paymentPage.proceedToPaymentByCard();
        var cardPaymentPage = new CardPaymentPage();
        cardPaymentPage.fillCardPaymentForm(cardInfo);
        cardPaymentPage.shouldSeeYearError("Неверный формат");
    }

    @Test
    @DisplayName("Should show error if the holder field is filled numbers")
    void holderFilledNumbers() {
        var cardInfo = DataHelper.getHolderNumbers();
        var paymentPage = new PaymentPage();
        paymentPage.proceedToPaymentByCard();
        var cardPaymentPage = new CardPaymentPage();
        cardPaymentPage.fillCardPaymentForm(cardInfo);
        cardPaymentPage.shouldSeeHolderError("Неверный формат");
    }

    @Test
    @DisplayName("Should show error if the holder field is filled in Cyrillic")
    void holderFilledCyrillic() {
        var cardInfo = DataHelper.getCyrillicHolder();
        var paymentPage = new PaymentPage();
        paymentPage.proceedToPaymentByCard();
        var cardPaymentPage = new CardPaymentPage();
        cardPaymentPage.fillCardPaymentForm(cardInfo);
        cardPaymentPage.shouldSeeHolderError("Неверный формат");

    }

    @Test
    @DisplayName("Should show error if the holder field is filled in special chars")
    void holderFilledSpecialChars() {
        var cardInfo = DataHelper.getHolderSpecialChars();
        var paymentPage = new PaymentPage();
        paymentPage.proceedToPaymentByCard();
        var cardPaymentPage = new CardPaymentPage();
        cardPaymentPage.fillCardPaymentForm(cardInfo);
        cardPaymentPage.shouldSeeHolderError("Неверный формат");
    }

    @Test
    @DisplayName("Should show error if the holder field is empty")
    void fieldHolderIsEmpty() {
        var cardInfo = DataHelper.getEmptyHolderInfo();
        var paymentPage = new PaymentPage();
        paymentPage.proceedToPaymentByCard();
        var cardPaymentPage = new CardPaymentPage();
        cardPaymentPage.fillCardPaymentForm(cardInfo);
        cardPaymentPage.shouldSeeHolderError("Неверный формат");
    }

    @Test
    @DisplayName("Should show error if the CVV field is empty")
    void fieldCVVIsEmpty() {
        var cardInfo = DataHelper.getEmptyCVVInfo();
        var paymentPage = new PaymentPage();
        paymentPage.proceedToPaymentByCard();
        var cardPaymentPage = new CardPaymentPage();
        cardPaymentPage.fillCardPaymentForm(cardInfo);
        cardPaymentPage.shouldSeeCVVError("Поле обязательно для заполнения");
    }

    @Test
    @DisplayName("Should show error if the CVV is short")
    void fieldCVVIsShort() {
        var cardInfo = DataHelper.getInvalidCVVShort();
        var paymentPage = new PaymentPage();
        paymentPage.proceedToPaymentByCard();
        var cardPaymentPage = new CardPaymentPage();
        cardPaymentPage.fillCardPaymentForm(cardInfo);
        cardPaymentPage.shouldSeeCVVError("Неверный формат");
    }
}


























