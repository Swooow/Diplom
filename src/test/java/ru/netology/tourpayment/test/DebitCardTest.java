package ru.netology.tourpayment.test;

import io.qameta.allure.selenide.AllureSelenide;
import ru.netology.tourpayment.page.BuyByDebitCardPage;
import ru.netology.tourpayment.page.StartPage;
import ru.netology.tourpayment.data.DataHelper;
import ru.netology.tourpayment.data.SQLHelper;
import com.codeborne.selenide.logevents.SelenideLogger;
import lombok.SneakyThrows;
import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DebitCardTest {
    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @SneakyThrows
    @BeforeEach
    public void setUpEach() {
        String url = System.getProperty("sut.url");
        open(url);
        SQLHelper.clearData();
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    // Заполнить поля валидными данными с одобренной картой (4444 4444 4444 4441)
    @SneakyThrows
    @Test
    void shouldDebitByCardWithApproved() {
        StartPage startPage = new StartPage();
        startPage.openBuyByDebitCardPage();
        val buyByDebit = new BuyByDebitCardPage();
        buyByDebit.fillData(DataHelper.getApprovedCard());
        buyByDebit.waitNotificationOk();
        assertEquals("APPROVED", SQLHelper.getDebitStatus());
    }

    // Заполнить поля валидными данными с отказной картой (4444 4444 4444 4442)
    @SneakyThrows
    @Test
    void shouldDebitByCardWithDecline() {
        StartPage startPage = new StartPage();
        startPage.openBuyByDebitCardPage();
        val buyByDebit = new BuyByDebitCardPage();
        buyByDebit.fillData(DataHelper.getDeclinedCard());
        buyByDebit.waitNotificationError();
        assertEquals("DECLINED", SQLHelper.getDebitStatus());
    }

    // Оставить поля пустыми
    @Test
    void shouldInvalidFieldMessageEmptyForm() {
        StartPage startPage = new StartPage();
        startPage.openBuyByDebitCardPage();
        val debitPage = new BuyByDebitCardPage();
        debitPage.fillData(DataHelper.getEmptyForm());
        debitPage.getInputInvalid();
        assertEquals("Неверный формат", debitPage.getInputInvalid());
    }

    // ***** ТЕСТЫ ПОЛЯ "НОМЕР КАРТЫ" *****

    // Оставить поле Номер карты пустым
    @Test
    void shouldEmptyCardNumber() {
        StartPage startPage = new StartPage();
        startPage.openBuyByDebitCardPage();
        val debitPage = new BuyByDebitCardPage();
        debitPage.fillData(DataHelper.getEmptyFieldCardNumber());
        debitPage.getInputInvalid();
        assertEquals("Неверный формат", debitPage.getInputInvalid());
    }

    // Ввести в поле Номер карты пробел
    @Test
    void shouldSpaceInCardNumber() {
        StartPage startPage = new StartPage();
        startPage.openBuyByDebitCardPage();
        val debitPage = new BuyByDebitCardPage();
        debitPage.fillData(DataHelper.getSpaceInFieldCardNumber());
        debitPage.getInputInvalid();
        assertEquals("Неверный формат", debitPage.getInputInvalid());
    }

    // Ввести в поле Номер карты буквы кириллицы
    @Test
    void shouldCyrillicInCardNumber() {
        StartPage startPage = new StartPage();
        startPage.openBuyByDebitCardPage();
        val debitPage = new BuyByDebitCardPage();
        debitPage.fillData(DataHelper.getCyrillicInFieldCardNumber());
        debitPage.getInputInvalid();
        assertEquals("Неверный формат", debitPage.getInputInvalid());
    }

    // Ввести в поле Номер карты буквы латиницы
    @Test
    void shouldLatinInCardNumber() {
        StartPage startPage = new StartPage();
        startPage.openBuyByDebitCardPage();
        val debitPage = new BuyByDebitCardPage();
        debitPage.fillData(DataHelper.getLatinInFieldCardNumber());
        debitPage.getInputInvalid();
        assertEquals("Неверный формат", debitPage.getInputInvalid());
    }

    // Ввести в поле Номер карты спецсимволы
    @Test
    void shouldSymbolsInCardNumber() {
        StartPage startPage = new StartPage();
        startPage.openBuyByDebitCardPage();
        val debitPage = new BuyByDebitCardPage();
        debitPage.fillData(DataHelper.getSymbolsInFieldCardNumber());
        debitPage.getInputInvalid();
        assertEquals("Неверный формат", debitPage.getInputInvalid());
    }

    // Ввести в поле Номер карты 15 из 16 цифр: 4444 4444 4444 444
    @Test
    void shouldIncompleteFieldCardNumber() {
        StartPage startPage = new StartPage();
        startPage.openBuyByDebitCardPage();
        val debitPage = new BuyByDebitCardPage();
        debitPage.fillData(DataHelper.getIncompleteFieldCardNumber());
        debitPage.getInputInvalid();
        assertEquals("Неверный формат", debitPage.getInputInvalid());
    }

    // Ввести в поле Номер карты 17 из 16 цифр: 4444 4444 4444 4441 4
    @Test
    void shouldCrowdedFieldCardNumber() {
        StartPage startPage = new StartPage();
        startPage.openBuyByDebitCardPage();
        val buyByDebit = new BuyByDebitCardPage();
        buyByDebit.fillData(DataHelper.getCrowdedFieldCardNumber());
        buyByDebit.waitNotificationOk();
    }

    // Ввести в поле Номер карты не валидную карту 1234 5678 1234 5678
    @Test
    void shouldNonExistentCard() {
        StartPage startPage = new StartPage();
        startPage.openBuyByDebitCardPage();
        val debitPage = new BuyByDebitCardPage();
        debitPage.fillData(DataHelper.getNonExistentCard());
        debitPage.waitNotificationError();
    }

    // ***** ТЕСТЫ ПОЛЯ "МЕСЯЦ" *****

    // Оставить поле Месяц пустым
    @Test
    void shouldEmptyMonth() {
        StartPage startPage = new StartPage();
        startPage.openBuyByDebitCardPage();
        val debitPage = new BuyByDebitCardPage();
        debitPage.fillData(DataHelper.getEmptyFieldMonth());
        debitPage.getInputInvalid();
        assertEquals("Неверный формат", debitPage.getInputInvalid());
    }

    // Ввести в поле Месяц пробел
    @Test
    void shouldSpaceInMonth() {
        StartPage startPage = new StartPage();
        startPage.openBuyByDebitCardPage();
        val debitPage = new BuyByDebitCardPage();
        debitPage.fillData(DataHelper.getSpaceInFieldMonth());
        debitPage.getInputInvalid();
        assertEquals("Неверный формат", debitPage.getInputInvalid());
    }

    // Ввести в поле Месяц буквы кириллицы
    @Test
    void shouldCyrillicInMonth() {
        StartPage startPage = new StartPage();
        startPage.openBuyByDebitCardPage();
        val debitPage = new BuyByDebitCardPage();
        debitPage.fillData(DataHelper.getCyrillicInFieldMonth());
        debitPage.getInputInvalid();
        assertEquals("Неверный формат", debitPage.getInputInvalid());
    }

    // Ввести в поле Месяц буквы латиницы
    @Test
    void shouldLatinInMonth() {
        StartPage startPage = new StartPage();
        startPage.openBuyByDebitCardPage();
        val debitPage = new BuyByDebitCardPage();
        debitPage.fillData(DataHelper.getLatinInFieldMonth());
        debitPage.getInputInvalid();
        assertEquals("Неверный формат", debitPage.getInputInvalid());
    }

    // Ввести в поле Месяц спецсимволы
    @Test
    void shouldSymbolsInMonth() {
        StartPage startPage = new StartPage();
        startPage.openBuyByDebitCardPage();
        val debitPage = new BuyByDebitCardPage();
        debitPage.fillData(DataHelper.getSymbolsInFieldMonth());
        debitPage.getInputInvalid();
        assertEquals("Неверный формат", debitPage.getInputInvalid());
    }

    // Ввести в поле Месяц 1 из 2 цифр: 7
    @Test
    void shouldIncompleteFieldMonth() {
        StartPage startPage = new StartPage();
        startPage.openBuyByDebitCardPage();
        val debitPage = new BuyByDebitCardPage();
        debitPage.fillData(DataHelper.getIncompleteFieldMonth());
        debitPage.getInputInvalid();
        assertEquals("Неверный формат", debitPage.getInputInvalid());
    }

    // Ввести в поле Месяц 3 из 2 цифр: 077
    @Test
    void shouldCrowdedFieldMonth() {
        StartPage startPage = new StartPage();
        startPage.openBuyByDebitCardPage();
        val buyByDebit = new BuyByDebitCardPage();
        buyByDebit.fillData(DataHelper.getCrowdedFieldMonth());
        buyByDebit.waitNotificationOk();
    }

    // Ввести в поле Месяц нулевой месяц: 00
    @Test
    void shouldZeroFieldMonth() {
        StartPage startPage = new StartPage();
        startPage.openBuyByDebitCardPage();
        val buyByDebit = new BuyByDebitCardPage();
        buyByDebit.fillData(DataHelper.getZeroFieldMonth());
        assertEquals("Неверно указан срок действия карты", buyByDebit.getInputInvalid());
    }

    // Ввести в поле Месяц тринадцатый месяц: 13
    @Test
    void shouldNonExistentFieldMonth() {
        StartPage startPage = new StartPage();
        startPage.openBuyByDebitCardPage();
        val buyByDebit = new BuyByDebitCardPage();
        buyByDebit.fillData(DataHelper.getNonExistentFieldMonth());
        assertEquals("Неверно указан срок действия карты", buyByDebit.getInputInvalid());
    }

    // Ввести в поле Месяц прошлый месяц этого года: 06/24
    @Test
    void shouldBygoneFieldMonth() {
        StartPage startPage = new StartPage();
        startPage.openBuyByDebitCardPage();
        val debitPage = new BuyByDebitCardPage();
        debitPage.fillData(DataHelper.getBygoneFieldMonth());
        assertEquals("Неверно указан срок действия карты", debitPage.getInputInvalid());
    }

    // ***** ТЕСТЫ ПОЛЯ "ГОД" *****

    // Оставить поле Год пустым
    @Test
    void shouldEmptyYear() {
        StartPage startPage = new StartPage();
        startPage.openBuyByDebitCardPage();
        val debitPage = new BuyByDebitCardPage();
        debitPage.fillData(DataHelper.getEmptyFieldYear());
        debitPage.getInputInvalid();
        assertEquals("Неверный формат", debitPage.getInputInvalid());
    }

    // Ввести в поле Год пробел
    @Test
    void shouldSpaceInYear() {
        StartPage startPage = new StartPage();
        startPage.openBuyByDebitCardPage();
        val debitPage = new BuyByDebitCardPage();
        debitPage.fillData(DataHelper.getSpaceInFieldYear());
        debitPage.getInputInvalid();
        assertEquals("Неверный формат", debitPage.getInputInvalid());
    }

    // Ввести в поле Год буквы кириллицы
    @Test
    void shouldCyrillicInYear() {
        StartPage startPage = new StartPage();
        startPage.openBuyByDebitCardPage();
        val debitPage = new BuyByDebitCardPage();
        debitPage.fillData(DataHelper.getCyrillicInFieldYear());
        debitPage.getInputInvalid();
        assertEquals("Неверный формат", debitPage.getInputInvalid());
    }

    // Ввести в поле Год буквы латиницы
    @Test
    void shouldLatinInYear() {
        StartPage startPage = new StartPage();
        startPage.openBuyByDebitCardPage();
        val debitPage = new BuyByDebitCardPage();
        debitPage.fillData(DataHelper.getLatinInFieldYear());
        debitPage.getInputInvalid();
        assertEquals("Неверный формат", debitPage.getInputInvalid());
    }

    // Ввести в поле Год спецсимволы
    @Test
    void shouldSymbolsInYear() {
        StartPage startPage = new StartPage();
        startPage.openBuyByDebitCardPage();
        val debitPage = new BuyByDebitCardPage();
        debitPage.fillData(DataHelper.getSymbolsInFieldYear());
        debitPage.getInputInvalid();
        assertEquals("Неверный формат", debitPage.getInputInvalid());
    }

    // Ввести в поле Год 1 из 2 цифр: 2
    @Test
    void shouldIncompleteFieldYear() {
        StartPage startPage = new StartPage();
        startPage.openBuyByDebitCardPage();
        val debitPage = new BuyByDebitCardPage();
        debitPage.fillData(DataHelper.getIncompleteFieldYear());
        debitPage.getInputInvalid();
        assertEquals("Неверный формат", debitPage.getInputInvalid());
    }

    // Ввести в поле Год 3 из 2 цифр: 240
    @Test
    void shouldCrowdedFieldYear() {
        StartPage startPage = new StartPage();
        startPage.openBuyByDebitCardPage();
        val buyByDebit = new BuyByDebitCardPage();
        buyByDebit.fillData(DataHelper.getCrowdedFieldYear());
        buyByDebit.waitNotificationOk();
    }

    // Ввести в поле Год тридцатый год: 30
    @Test
    void shouldOverFieldYear() {
        StartPage startPage = new StartPage();
        startPage.openBuyByDebitCardPage();
        val buyByDebit = new BuyByDebitCardPage();
        buyByDebit.fillData(DataHelper.getOverFieldYear());
        assertEquals("Неверно указан срок действия карты", buyByDebit.getInputInvalid());
    }

    // Ввести в поле Год прошлый год: 23
    @Test
    void shouldBygoneFieldYear() {
        StartPage startPage = new StartPage();
        startPage.openBuyByDebitCardPage();
        val debitPage = new BuyByDebitCardPage();
        debitPage.fillData(DataHelper.getBygoneFieldYear());
        assertEquals("Истёк срок действия карты", debitPage.getInputInvalid());
    }

    // ***** ТЕСТЫ ПОЛЯ "ВЛАДЕЛЕЦ" *****

    // Оставить поле Владелец пустым
    @Test
    void shouldEmptyOwner() {
        StartPage startPage = new StartPage();
        startPage.openBuyByDebitCardPage();
        val debitPage = new BuyByDebitCardPage();
        debitPage.fillData(DataHelper.getEmptyFieldOwner());
        debitPage.getInputInvalid();
        assertEquals("Поле обязательно для заполнения", debitPage.getInputInvalid());
    }

    // Ввести в поле Владелец пробел
    @Test
    void shouldSpaceInOwner() {
        StartPage startPage = new StartPage();
        startPage.openBuyByDebitCardPage();
        val debitPage = new BuyByDebitCardPage();
        debitPage.fillData(DataHelper.getSpaceInFieldOwner());
        debitPage.getInputInvalid();
        assertEquals("Поле обязательно для заполнения", debitPage.getInputInvalid());
    }

    // Ввести в поле Владелец буквы кириллицы
    @Test
    void shouldCyrillicInOwner() {
        StartPage startPage = new StartPage();
        startPage.openBuyByDebitCardPage();
        val debitPage = new BuyByDebitCardPage();
        debitPage.fillData(DataHelper.getCyrillicInFieldOwner());
        debitPage.getInputInvalid();
        assertEquals("Неверный формат", debitPage.getInputInvalid());
    }

    // Ввести в поле Владелец цифры
    @Test
    void shouldNumbersInOwner() {
        StartPage startPage = new StartPage();
        startPage.openBuyByDebitCardPage();
        val debitPage = new BuyByDebitCardPage();
        debitPage.fillData(DataHelper.getNumbersInFieldOwner());
        debitPage.getInputInvalid();
        assertEquals("Неверный формат", debitPage.getInputInvalid());
    }

    // Ввести в поле Владелец спецсимволы
    @Test
    void shouldSymbolsInOwner() {
        StartPage startPage = new StartPage();
        startPage.openBuyByDebitCardPage();
        val debitPage = new BuyByDebitCardPage();
        debitPage.fillData(DataHelper.getSymbolsInFieldOwner());
        debitPage.getInputInvalid();
        assertEquals("Поле обязательно для заполнения", debitPage.getInputInvalid());
    }

    // Ввести в поле Владелец одну латинскую букву
    @Test
    void shouldOneLatinInOwner() {
        StartPage startPage = new StartPage();
        startPage.openBuyByDebitCardPage();
        val debitPage = new BuyByDebitCardPage();
        debitPage.fillData(DataHelper.getOneLatinInFieldOwner());
        debitPage.getInputInvalid();
        assertEquals("Неверный формат", debitPage.getInputInvalid());
    }

    // Ввести в поле Владелец 20 латинских букв и 1 пробел (21/20 символов):
    @Test
    void shouldCrowdedFieldOwner() {
        StartPage startPage = new StartPage();
        startPage.openBuyByDebitCardPage();
        val buyByDebit = new BuyByDebitCardPage();
        buyByDebit.fillData(DataHelper.getCrowdedFieldOwner());
        buyByDebit.waitNotificationOk();
    }

    // ***** ТЕСТЫ ПОЛЯ "CVC/CVV" *****

    // Оставить поле CVC/CVV пустым
    @Test
    void shouldEmptyCvc() {
        StartPage startPage = new StartPage();
        startPage.openBuyByDebitCardPage();
        val debitPage = new BuyByDebitCardPage();
        debitPage.fillData(DataHelper.getEmptyFieldCvc());
        debitPage.getInputInvalid();
        assertEquals("Поле обязательно для заполнения", debitPage.getInputInvalid());
    }

    // Ввести в поле CVC/CVV пробел
    @Test
    void shouldSpaceInCvc() {
        StartPage startPage = new StartPage();
        startPage.openBuyByDebitCardPage();
        val debitPage = new BuyByDebitCardPage();
        debitPage.fillData(DataHelper.getSpaceInFieldCvc());
        debitPage.getInputInvalid();
        assertEquals("Поле обязательно для заполнения", debitPage.getInputInvalid());
    }

    // Ввести в поле CVC/CVV буквы кириллицы
    @Test
    void shouldCyrillicInCvc() {
        StartPage startPage = new StartPage();
        startPage.openBuyByDebitCardPage();
        val debitPage = new BuyByDebitCardPage();
        debitPage.fillData(DataHelper.getCyrillicInFieldCvc());
        debitPage.getInputInvalid();
        assertEquals("Поле обязательно для заполнения", debitPage.getInputInvalid());
    }

    // Ввести в поле CVC/CVV буквы латиницы
    @Test
    void shouldLatinInCvc() {
        StartPage startPage = new StartPage();
        startPage.openBuyByDebitCardPage();
        val debitPage = new BuyByDebitCardPage();
        debitPage.fillData(DataHelper.getLatinInFieldCvc());
        debitPage.getInputInvalid();
        assertEquals("Поле обязательно для заполнения", debitPage.getInputInvalid());
    }

    // Ввести в поле CVC/CVV спецсимволы
    @Test
    void shouldSymbolsInCvc() {
        StartPage startPage = new StartPage();
        startPage.openBuyByDebitCardPage();
        val debitPage = new BuyByDebitCardPage();
        debitPage.fillData(DataHelper.getSymbolsInFieldCvc());
        debitPage.getInputInvalid();
        assertEquals("Поле обязательно для заполнения", debitPage.getInputInvalid());
    }

    // Ввести в поле CVC/CVV 1 из 3 цифр: 1
    @Test
    void shouldIncompleteFieldCvc() {
        StartPage startPage = new StartPage();
        startPage.openBuyByDebitCardPage();
        val debitPage = new BuyByDebitCardPage();
        debitPage.fillData(DataHelper.getIncompleteFieldCvc());
        debitPage.getInputInvalid();
        assertEquals("Неверный формат", debitPage.getInputInvalid());
    }

    // Ввести в поле CVC/CVV 4 из 3 цифр: 1234
    @Test
    void shouldCrowdedFieldCvc() {
        StartPage startPage = new StartPage();
        startPage.openBuyByDebitCardPage();
        val buyByDebit = new BuyByDebitCardPage();
        buyByDebit.fillData(DataHelper.getCrowdedFieldCvc());
        buyByDebit.waitNotificationOk();
    }
}
