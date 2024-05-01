package ru.firstinspace.abramovicho.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;

public class UIWait {
    public static void waitElementIsClickable(WebDriver driver, By locator) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(ElementNotInteractableException.class, NoSuchElementException.class);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static void waitElementIsVisible(WebDriver driver, By locator) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(ElementNotInteractableException.class, NoSuchElementException.class);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static void waitElementIsPresents(WebDriver driver, By locator) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(ElementNotInteractableException.class, NoSuchElementException.class);
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public static void waitListIsNotEmpty(WebDriver driver, By locator) {
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(15))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(IndexOutOfBoundsException.class);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(locator, 0));
    }
}