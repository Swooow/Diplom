package ru.netology.tourpayment.data;

import com.github.javafaker.Faker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Random;

public class DataHelper {

    private DataHelper() {
    }

    private static String getMonth() {
        LocalDate localDate = LocalDate.now();
        int month = localDate.getMonthValue();
        return String.format("%02d", month);
    }

    private static String getBygoneMonth() {
        LocalDate localDate = LocalDate.now();
        int month = localDate.minusMonths(1).getMonthValue();
        return String.format("%02d", month);
    }

    private static String getYear() {
        DateFormat df = new SimpleDateFormat("yy");
        return df.format(Calendar.getInstance().getTime());
    }

    private static String getBygoneYear() {
        LocalDate localDate = LocalDate.now();
        int year = localDate.minusYears(1).getYear();
        return String.format("%02d", year);
    }

    private static String getName() {
        Faker faker = new Faker();
        return faker.name().firstName() + " " + faker.name().lastName();
    }

    private static String getCvc() {
        Random random = new Random();
        int cvc = random.nextInt((1000 - 1));
        return String.format("%03d", cvc);
    }

    private static String getApprovedCardNumber() {
        return "4444 4444 4444 4441";
    }

    private static String getDeclinedCardNumber() {
        return "4444 4444 4444 4442";
    }

    public static CardInfo getApprovedCard() {
        return new CardInfo(getApprovedCardNumber(), getMonth(), getYear(), getName(), getCvc());
    }

    public static CardInfo getDeclinedCard() {
        return new CardInfo(getDeclinedCardNumber(), getMonth(), getYear(), getName(), getCvc());
    }

    public static CardInfo getEmptyForm() {
        return new CardInfo();
    }

    // ***** ТЕСТЫ ПОЛЯ "НОМЕР КАРТЫ" *****

    public static CardInfo getEmptyFieldCardNumber() {
        return new CardInfo( null, getMonth(), getYear(), getName(), getCvc());
    }

    public static CardInfo getSpaceInFieldCardNumber() {
        return new CardInfo(" ", getMonth(), getYear(), getName(), getCvc());
    }

    public static CardInfo getCyrillicInFieldCardNumber() {
        return new CardInfo("ОЛЕГ ТИНЬКОФФ", getMonth(), getYear(), getName(), getCvc());
    }

    public static CardInfo getLatinInFieldCardNumber() {
        return new CardInfo(getName(), getMonth(), getYear(), getName(), getCvc());
    }

    public static CardInfo getSymbolsInFieldCardNumber() {
        return new CardInfo("<#%^*&$@>", getMonth(), getYear(), getName(), getCvc());
    }

    public static CardInfo getIncompleteFieldCardNumber() {
        return new CardInfo("4444 4444 4444 444", getMonth(), getYear(), getName(), getCvc());
    }

    public static CardInfo getCrowdedFieldCardNumber() {
        return new CardInfo("4444 4444 4444 4441 4", getMonth(), getYear(), getName(), getCvc());
    }

    public static CardInfo getNonExistentCard() {
        return new CardInfo("1234 5678 1234 5678", getMonth(), getYear(), getName(), getCvc());
    }

    // ***** ТЕСТЫ ПОЛЯ "МЕСЯЦ" *****

    public static CardInfo getEmptyFieldMonth() {
        return new CardInfo(getApprovedCardNumber(), null, getYear(), getName(), getCvc());
    }

    public static CardInfo getSpaceInFieldMonth() {
        return new CardInfo(getApprovedCardNumber(), " ", getYear(), getName(), getCvc());
    }

    public static CardInfo getCyrillicInFieldMonth() {
        return new CardInfo(getApprovedCardNumber(), "июль", getYear(), getName(), getCvc());
    }

    public static CardInfo getLatinInFieldMonth() {
        return new CardInfo(getApprovedCardNumber(), "july", getYear(), getName(), getCvc());
    }

    public static CardInfo getSymbolsInFieldMonth() {
        return new CardInfo(getApprovedCardNumber(), "<#%^*&$@>", getYear(), getName(), getCvc());
    }

    public static CardInfo getIncompleteFieldMonth() {
        return new CardInfo(getApprovedCardNumber(), "7", getYear(), getName(), getCvc());
    }

    public static CardInfo getCrowdedFieldMonth() {
        return new CardInfo(getApprovedCardNumber(), "077", getYear(), getName(), getCvc());
    }

    public static CardInfo getZeroFieldMonth() {
        return new CardInfo(getApprovedCardNumber(), "00", getYear(), getName(), getCvc());
    }

    public static CardInfo getNonExistentFieldMonth() {
        return new CardInfo(getApprovedCardNumber(), "13", getYear(), getName(), getCvc());
    }

    public static CardInfo getBygoneFieldMonth() {
        return new CardInfo(getApprovedCardNumber(), getBygoneMonth(), getYear(), getName(), getCvc());
    }

    // ***** ТЕСТЫ ПОЛЯ "ГОД" *****

    public static CardInfo getEmptyFieldYear() {
        return new CardInfo(getApprovedCardNumber(), getMonth(), null, getName(), getCvc());
    }

    public static CardInfo getSpaceInFieldYear() {
        return new CardInfo(getApprovedCardNumber(), getMonth(), " ", getName(), getCvc());
    }

    public static CardInfo getCyrillicInFieldYear() {
        return new CardInfo(getApprovedCardNumber(), getMonth(), "ДЧ", getName(), getCvc());
    }

    public static CardInfo getLatinInFieldYear() {
        return new CardInfo(getApprovedCardNumber(), getMonth(), "TF", getName(), getCvc());
    }

    public static CardInfo getSymbolsInFieldYear() {
        return new CardInfo(getApprovedCardNumber(), getMonth(), "<#%^*&$@>", getName(), getCvc());
    }

    public static CardInfo getIncompleteFieldYear() {
        return new CardInfo(getApprovedCardNumber(), getMonth(), "2", getName(), getCvc());
    }

    public static CardInfo getCrowdedFieldYear() {
        return new CardInfo(getApprovedCardNumber(), getMonth(), "240", getName(), getCvc());
    }

    public static CardInfo getBygoneFieldYear() {
        return new CardInfo(getApprovedCardNumber(), getMonth(), getBygoneYear(), getName(), getCvc());
    }

    public static CardInfo getOverFieldYear() {
        return new CardInfo(getApprovedCardNumber(), getMonth(), "30", getName(), getCvc());
    }

    // ***** ТЕСТЫ ПОЛЯ "ВЛАДЕЛЕЦ" *****

    public static CardInfo getEmptyFieldOwner() {
        return new CardInfo(getApprovedCardNumber(), getMonth(), getYear(), null, getCvc());
    }

    public static CardInfo getSpaceInFieldOwner() {
        return new CardInfo(getApprovedCardNumber(), getMonth(), getYear(), " ", getCvc());
    }

    public static CardInfo getCyrillicInFieldOwner() {
        return new CardInfo(getApprovedCardNumber(), getMonth(), getYear(), "Олег Тинькофф", getCvc());
    }

    public static CardInfo getNumbersInFieldOwner() {
        return new CardInfo(getApprovedCardNumber(), getMonth(), getYear(), "1234 567891", getCvc());
    }

    public static CardInfo getSymbolsInFieldOwner() {
        return new CardInfo(getApprovedCardNumber(), getMonth(), getYear(), "<#%^*&$@>", getCvc());
    }

    public static CardInfo getOneLatinInFieldOwner() {
        return new CardInfo(getApprovedCardNumber(), getMonth(), getYear(), "T", getCvc());
    }

    public static CardInfo getCrowdedFieldOwner() {
        return new CardInfo(getApprovedCardNumber(), getMonth(), getYear(), "AAAAAAAAAA AAAAAAAAAA", getCvc());
    }

    // ***** ТЕСТЫ ПОЛЯ "CVC/CVV" *****

    public static CardInfo getEmptyFieldCvc() {
        return new CardInfo(getApprovedCardNumber(), getMonth(), getYear(), getName(), null);
    }

    public static CardInfo getSpaceInFieldCvc() {
        return new CardInfo(getApprovedCardNumber(), getMonth(), getYear(), getName(), " ");
    }

    public static CardInfo getCyrillicInFieldCvc() {
        return new CardInfo(getApprovedCardNumber(), getMonth(), getYear(), getName(), "код");
    }

    public static CardInfo getLatinInFieldCvc() {
        return new CardInfo(getApprovedCardNumber(), getMonth(), getYear(), getName(), "cod");
    }

    public static CardInfo getSymbolsInFieldCvc() {
        return new CardInfo(getApprovedCardNumber(), getMonth(), getYear(), getName(), "<#%^*&$@>");
    }

    public static CardInfo getIncompleteFieldCvc() {
        return new CardInfo(getApprovedCardNumber(), getMonth(), getYear(), getName(), "1");
    }

    public static CardInfo getCrowdedFieldCvc() {
        return new CardInfo(getApprovedCardNumber(), getMonth(), getYear(), getName(), "1234");
    }
}
