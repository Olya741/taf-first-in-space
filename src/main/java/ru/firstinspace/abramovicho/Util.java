package ru.firstinspace.abramovicho;

import com.github.javafaker.Faker;

import java.util.Random;

public class Util {
    public static int getRandomNumber(int maxValue) {
        Random random = new Random();
        return random.nextInt(maxValue);
    }

    public static String getRandomEmail() {
        Faker faker = new Faker();
        return faker.internet().emailAddress();
    }

    public static boolean doesTextContainsFragment(String wholeText, String fragment) {
        return wholeText.toLowerCase().contains(fragment.toLowerCase());
    }
}
