package at.study.automation.utils;

import io.qameta.allure.Step;
import org.testng.Assert;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CompareUtils {

    private static final Comparator<String> DATE_DESC_COMPARATOR = (s1, s2) -> {
        LocalDateTime d1 = LocalDateTime.parse(s1, DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
        LocalDateTime d2 = LocalDateTime.parse(s2, DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
        return d2.compareTo(d1);
    };

    private static final Comparator<String> DATE_ASC_COMPARATOR = DATE_DESC_COMPARATOR.reversed();

    private static final Comparator<String> USER_NAME_DESC_COMPARATOR = (s1, s2) -> s2.compareToIgnoreCase(s1);
    private static final Comparator<String> USER_NAME_ASC_COMPARATOR = USER_NAME_DESC_COMPARATOR.reversed();

    private static final Comparator<String> USER_FIRST_NAME_DESC_COMPARATOR = (s1, s2) -> s2.compareToIgnoreCase(s1);
    private static final Comparator<String> USER_FIRST_NAME_ASC_COMPARATOR = USER_FIRST_NAME_DESC_COMPARATOR.reversed();

    private static final Comparator<String> USER_LAST_NAME_DESC_COMPARATOR = (s1, s2) -> s2.compareToIgnoreCase(s1);
    private static final Comparator<String> USER_LAST_NAME_ASC_COMPARATOR = USER_LAST_NAME_DESC_COMPARATOR.reversed();

    @Step("Проверка сортировки списка дат по убыванию")
    public static void assertListSortedByDateDesc(List<String> dates) {
        List<String> datesCopy = new ArrayList<>(dates);
        datesCopy.sort(DATE_DESC_COMPARATOR);
        Assert.assertEquals(dates, datesCopy);
    }

    @Step("Проверка сортировки списка дат по врзрастанию")
    public static void assertListSortedByDateAsc(List<String> dates) {
        List<String> datesCopy = new ArrayList<>(dates);
        datesCopy.sort(DATE_ASC_COMPARATOR);
        Assert.assertEquals(dates, datesCopy);
    }

    @Step("Проверка сортировки списка логинов по убыванию")
    public static void assertListSortedByUserNameDesc(List<String> usersNames) {
        List<String> usersNamesCopy = new ArrayList<>(usersNames);
        usersNamesCopy.sort(USER_NAME_DESC_COMPARATOR);
        Assert.assertEquals(usersNames, usersNamesCopy);
    }

    @Step("Проверка сортировки списка логинов по возрастанию")
    public static void assertListSortedByUserNameAsc(List<String> usersNames) {
        List<String> usersNamesCopy = new ArrayList<>(usersNames);
        usersNamesCopy.sort(USER_NAME_ASC_COMPARATOR);
        Assert.assertEquals(usersNames, usersNamesCopy);
    }

    @Step("Проверка сортировки списка имен по убыванию")
    public static void assertListSortedByUserFirstNameDesc(List<String> usersFirstNames) {
        List<String> usersFirstNamesCopy = new ArrayList<>(usersFirstNames);
        usersFirstNamesCopy.sort(USER_FIRST_NAME_DESC_COMPARATOR);
        Assert.assertEquals(usersFirstNames, usersFirstNamesCopy);
    }

    @Step("Проверка сортировки списка имен по возрастанию")
    public static void assertListSortedByUserFirstNameAsc(List<String> usersFirstNames) {
        List<String> usersFirstNamesCopy = new ArrayList<>(usersFirstNames);
        usersFirstNamesCopy.sort(USER_FIRST_NAME_ASC_COMPARATOR);
        Assert.assertEquals(usersFirstNames, usersFirstNamesCopy);
    }

    @Step("Проверка сортировки списка фамилий по убыванию")
    public static void assertListSortedByUserLastNameDesc(List<String> usersLastNames) {
        List<String> usersLastNamesCopy = new ArrayList<>(usersLastNames);
        usersLastNamesCopy.sort(USER_LAST_NAME_DESC_COMPARATOR);
        Assert.assertEquals(usersLastNames, usersLastNamesCopy);
    }

    @Step("Проверка сортировки списка фамилий по возрастанию")
    public static void assertListSortedByUserLastNameAsc(List<String> usersLastNames) {
        List<String> usersLastNamesCopy = new ArrayList<>(usersLastNames);
        usersLastNamesCopy.sort(USER_LAST_NAME_ASC_COMPARATOR);
        Assert.assertEquals(usersLastNames, usersLastNamesCopy);
    }

    //@Step("Проверка признака отсортированности списка")
    public static boolean isListSorted(List<String> list) {
        List<String> listCopy = new ArrayList<>(list);
        Collections.sort(listCopy);
        return listCopy.equals(list);
    }
}
