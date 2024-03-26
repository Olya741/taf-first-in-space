package ru.firstinspace.abramovicho.api;

public class LoginService extends BaseSpecification {
    public final static String endpoint = "/users/auth/login/";

    public static String setBodyForLogin(String email, String password) {
        return String.format("{\n" +
                "\"email\":\"%s\",\n" +
                "\"password\":\"%s\"\n" +
                "}", email, password);
    }
}
