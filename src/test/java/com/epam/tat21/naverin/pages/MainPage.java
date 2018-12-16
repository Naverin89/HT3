package com.epam.tat21.naverin.pages;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends AbstractPage {
    private final String BASE_URL = "https://github.com/";

    @FindBy(xpath = "//summary[@aria-label='Create new…']")
    private WebElement buttonCreateNew;

    @FindBy(xpath = "//a[contains(text(), 'New repository')]")
    private WebElement linkNewRepository;

    @FindBy(xpath = "//*[@id='user-links']/li[3]/details/summary/img")
    private WebElement navigatorLink;

    @FindBy(xpath = "//button[contains(text(), 'Sign out')]")
    private WebElement signOutButton;

    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    public void clickOnCreateNewRepositoryButton() {
        buttonCreateNew.click();
        linkNewRepository.click();
    }

    public void clickOnSingOutButton() {
        navigatorLink.click();
        signOutButton.click();
    }


    @Override
    public void openPage() {
        driver.navigate().to(BASE_URL);
    }


}
