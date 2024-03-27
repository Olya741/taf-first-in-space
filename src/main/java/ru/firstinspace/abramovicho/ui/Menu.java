package ru.firstinspace.abramovicho.ui;

public enum Menu {
    CATALOG("КАТАЛОГ"),
    CAPSULES("КАПСУЛЫ"),
    NEW("НОВИНКИ"),
    SALE("SALE"),
    COMING_SOON("СКОРО В ПРОДАЖЕ"),
    BESTSELLERS("БЕСТСЕЛЛЕРЫ"),
    FOR_BUYERS("ПОКУПАТЕЛЯМ"),
    ABOUT_US("О НАС"),
    SHOPS("МАГАЗИНЫ"),
    FOR_PARTNERS("ПАРТНЕРАМ"),
    CONTACTS("КОНТАКТЫ");

    private String name;

    Menu(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
