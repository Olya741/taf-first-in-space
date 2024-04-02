package ru.firstinspace.abramovicho;

import java.util.List;
import java.util.Random;

public class Util {
    public static int getRandomNumber(int maxValue) {
        Random random = new Random();
        return random.nextInt(maxValue);
    }

    public static String getRandomValue(List<String> list, int randomNumber) {
        return list.get(randomNumber);
    }

    public static String getRandomDomain() {
        List<String> domains = PrepareTestData.getListOfDomains();
        int size = domains.size();
        return getRandomValue(domains, getRandomNumber(size));
    }

    public static String getRandomName() {
        List<String> names = PrepareTestData.getListOfNames();
        int size = names.size();
        return getRandomValue(names, getRandomNumber(size));
    }

    private static String getRandomSite() {
        List<String> sites = PrepareTestData.getListOfSites();
        int size = sites.size();
        return getRandomValue(sites, getRandomNumber(size));
    }

    public static String getRandomEmail() {
        return getRandomName() + "@" + getRandomSite() + getRandomDomain();
    }

    public static boolean doesTextContainsFragment(String wholeText, String fragment) {
        return wholeText.toLowerCase().contains(fragment.toLowerCase());
    }
}
