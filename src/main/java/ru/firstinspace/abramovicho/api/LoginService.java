package ru.firstinspace.abramovicho.api;

public class LoginService {
    public static String wrongLoginOrPasswordError = "Неверные имя пользователя или пароль";
    public static String requiredFieldError = "Обязательное поле.";
    public static String emptyFieldError = "Это поле не может быть пустым.";
    public static String invalidEmailFormatError = "Введите правильный адрес электронной почты.";
    public static String invalidPasswordFormatError = "Not a valid string.";

    public static String setBodyForLogin(String email, String password) {
        return String.format("{\n" +
                "\"email\":\"%s\",\n" +
                "\"password\":\"%s\"\n" +
                "}", email, password);
    }

    public static String setBodyForLogin(String email, boolean password) {
        return String.format("{\n" +
                "\"email\":\"%s\",\n" +
                "\"password\":%b\n" +
                "}", email, password);
    }

    public static String setBodyForLogin(int email, String password) {
        return String.format("{\n" +
                "\"email\":%d,\n" +
                "\"password\":\"%s\"\n" +
                "}", email, password);
    }
}
