package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.page.CreditPaymentPage;
import ru.netology.page.PaymentPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaymentOnCreditTest {

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
    @DisplayName("Should approved card payment on credit")
    void shouldApprovePaymentInCreditWithValidCard() {
        var cardInfo = DataHelper.getValidCardInfo();
        var paymentPage = new PaymentPage();
        paymentPage.proceedToPaymentOnCredit();
        var creditPaymentPage = new CreditPaymentPage();
        creditPaymentPage.fillCardPaymentForm(cardInfo);
        creditPaymentPage.shouldSeeSuccessNotification();
        assertEquals("APPROVED", SQLHelper.getCreditPaymentStatus());
    }

    @Test
    @DisplayName("Should declined payment in credit")
    void  shouldDeclinedPaymentInCreditWithDeclinedCard() {
        var cardInfo = DataHelper.getValidCardInfoCredit();
        var paymentPage = new PaymentPage();
        paymentPage.proceedToPaymentOnCredit();
        var creditPaymentPage = new CreditPaymentPage();
        creditPaymentPage.fillCardPaymentForm(cardInfo);
        creditPaymentPage.shouldSeeErrorNotification();
        assertEquals("DECLINED", SQLHelper.getCreditPaymentStatus());
    }

    @Test
    @DisplayName("Should be an error when paying on credit with an unknown card")
    void cardNumberNotRegisteredPaymentOnCredit() {
        var cardInfo =  DataHelper.getUnknownCardInfo();
        var paymentPage = new PaymentPage();
        paymentPage.proceedToPaymentOnCredit();
        var creditPaymentPage = new CreditPaymentPage();
        creditPaymentPage.fillCardPaymentForm(cardInfo);
        creditPaymentPage.shouldSeeErrorNotification();
    }

    @Test
    @DisplayName("Should be an error when paying card number is too short when paying on credit")
    void cardNumberTooShortPaymentOnCredit() {
        var cardInfo = DataHelper.getCardWithShortNumber();
        var paymentPage = new PaymentPage();
        paymentPage.proceedToPaymentOnCredit();
        var creditPaymentPage = new CreditPaymentPage();
        creditPaymentPage.fillCardPaymentForm(cardInfo);
        creditPaymentPage.shouldSeeCardNumberError("Неверный формат");
    }

    @Test
    @DisplayName("Should show error when card number is empty when paying on credit")
    void cardNumberIsEmptyPaymentOnCredit() {
        var cardInfo = DataHelper.getEmptyCardNumberInfo();
        var paymentPage = new PaymentPage();
        paymentPage.proceedToPaymentOnCredit();
        var creditPaymentPage = new CreditPaymentPage();
        creditPaymentPage.fillCardPaymentForm(cardInfo);
        creditPaymentPage.shouldSeeCardNumberError("Поле обязательно для заполнения");
    }

    @Test
    @DisplayName("Should show error if the month field is empty when paying on credit")
    void monthIsEmptyPaymentOnCredit() {
        var cardInfo = DataHelper.getEmptyMonthInfo();
        var paymentPage = new PaymentPage();
        paymentPage.proceedToPaymentOnCredit();
        var creditPaymentPage = new CreditPaymentPage();
        creditPaymentPage.fillCardPaymentForm(cardInfo);
        creditPaymentPage.shouldSeeMonthError("Поле обязательно для заполнения");
    }

    @Test
    @DisplayName("Should show error if the month field is filled with a single digit on paying on credit")
    void monthFilledOneNumberPaymentOnCredit() {
        var cardInfo = DataHelper.getMonthOneNumber();
        var paymentPage = new PaymentPage();
        paymentPage.proceedToPaymentOnCredit();
        var creditPaymentPage = new CreditPaymentPage();
        creditPaymentPage.fillCardPaymentForm(cardInfo);
        creditPaymentPage.shouldSeeMonthError("Неверный формат");
    }

    @Test
    @DisplayName("Should show error when month is 00 when paying on credit")
    void monthFilledIs00PaymentOnCredit() {
        var cardInfo = DataHelper.getMonthWithZeros();
        var paymentPage = new PaymentPage();
        paymentPage.proceedToPaymentOnCredit();
        var creditPaymentPage = new CreditPaymentPage();
        creditPaymentPage.fillCardPaymentForm(cardInfo);
        creditPaymentPage.shouldSeeMonthError("Неверно указан срок действия карты");
    }

    @Test
    @DisplayName("Should show error when entering invalid month when paying on credit")
    void filledInvalidMonthPaymentOnCredit() {
        var cardInfo = DataHelper.getMonthUnreal();
        var paymentPage = new PaymentPage();
        paymentPage.proceedToPaymentOnCredit();
        var creditPaymentPage = new CreditPaymentPage();
        creditPaymentPage.fillCardPaymentForm(cardInfo);
        creditPaymentPage.shouldSeeMonthError("Неверно указан срок действия карты");
    }

    @Test
    @DisplayName("Should show error when entering the past year when payment on credit")
    void enteringThePastYearPaymentOnCredit() {
        var cardInfo = DataHelper.getInvalidPastYear();
        var paymentPage = new PaymentPage();
        paymentPage.proceedToPaymentOnCredit();
        var creditPaymentPage = new CreditPaymentPage();
        creditPaymentPage.fillCardPaymentForm(cardInfo);
        creditPaymentPage.shouldSeeYearError("Истек срок действия карты");
    }

    @Test
    @DisplayName("Should show error if the year field is empty  when paying on credit")
    void fieldYearIsEmptyPaymentOnCredit() {
        var cardInfo = DataHelper.getInvalidPastYear();
        var paymentPage = new PaymentPage();
        paymentPage.proceedToPaymentOnCredit();
        var creditPaymentPage = new CreditPaymentPage();
        creditPaymentPage.fillCardPaymentForm(cardInfo);
        creditPaymentPage.shouldSeeYearError("Поле обязательно для заполнения");
    }

    @Test
    @DisplayName("Should show error if the year field is filled with a single digit on paying on credit")
    void yearFilledOneNumberPaymentOnCredit() {
        var cardInfo = DataHelper.getYearOneNumber();
        var paymentPage = new PaymentPage();
        paymentPage.proceedToPaymentOnCredit();
        var creditPaymentPage = new CreditPaymentPage();
        creditPaymentPage.fillCardPaymentForm(cardInfo);
        creditPaymentPage.shouldSeeYearError("Неверный формат");
    }

    @Test
    @DisplayName("Should show error if the holder field is filled numbers on paying on credit")
    void holderFilledNumbersPaymentOnCredit() {
        var cardInfo = DataHelper.getHolderNumbers();
        var paymentPage = new PaymentPage();
        paymentPage.proceedToPaymentOnCredit();
        var creditPaymentPage = new CreditPaymentPage();
        creditPaymentPage.fillCardPaymentForm(cardInfo);
        creditPaymentPage.shouldSeeHolderError("Неверный формат");
    }

    @Test
    @DisplayName("Should show error if the holder field is filled in Cyrillic on paying on credit")
    void holderFilledCyrillicPaymentOnCredit() {
        var cardInfo = DataHelper.getCyrillicHolder();
        var paymentPage = new PaymentPage();
        paymentPage.proceedToPaymentOnCredit();
        var creditPaymentPage = new CreditPaymentPage();
        creditPaymentPage.fillCardPaymentForm(cardInfo);
        creditPaymentPage.shouldSeeHolderError("Неверный формат");
    }

    @Test
    @DisplayName("Should show error if the holder field is filled in special chars on paying on credit")
    void holderFilledSpecialCharsPaymentOnCredit() {
        var cardInfo = DataHelper.getHolderSpecialChars();
        var paymentPage = new PaymentPage();
        paymentPage.proceedToPaymentOnCredit();
        var creditPaymentPage = new CreditPaymentPage();
        creditPaymentPage.fillCardPaymentForm(cardInfo);
        creditPaymentPage.shouldSeeHolderError("Неверный формат");
    }

    @Test
    @DisplayName("Should show error if the holder field is empty  when paying on credit")
    void fieldHolderIsEmptyPaymentOnCredit() {
        var cardInfo = DataHelper.getEmptyHolderInfo();
        var paymentPage = new PaymentPage();
        paymentPage.proceedToPaymentOnCredit();
        var creditPaymentPage = new CreditPaymentPage();
        creditPaymentPage.fillCardPaymentForm(cardInfo);
        creditPaymentPage.shouldSeeHolderError("Неверный формат");
    }

    @Test
    @DisplayName("Should show error if the CVV field is empty on paying on credit")
    void fieldCVVIsEmptyPaymentOnCredit() {
        var cardInfo = DataHelper.getEmptyCVVInfo();
        var paymentPage = new PaymentPage();
        paymentPage.proceedToPaymentOnCredit();
        var creditPaymentPage = new CreditPaymentPage();
        creditPaymentPage.fillCardPaymentForm(cardInfo);
        creditPaymentPage.shouldSeeCVVError("Поле обязательно для заполнения");
    }

    @Test
    @DisplayName("Should show error if the CVV is short on paying on credit")
    void fieldCVVIsShortPaymentOnCredit() {
        var cardInfo = DataHelper.getInvalidCVVShort();
        var paymentPage = new PaymentPage();
        paymentPage.proceedToPaymentOnCredit();
        var creditPaymentPage = new CreditPaymentPage();
        creditPaymentPage.fillCardPaymentForm(cardInfo);
        creditPaymentPage.shouldSeeCVVError("Неверный формат");
    }

}
