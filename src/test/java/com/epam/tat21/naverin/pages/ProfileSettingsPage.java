package com.epam.tat21.naverin.pages;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProfileSettingsPage extends AbstractPage {

    private final String BASE_URL = "https://github.com/settings/profile";

    private final Logger logger = LogManager.getRootLogger();

    @FindBy(xpath = "//*[@id='user_profile_bio']")
    private WebElement changeBioTextarea;

    @FindBy(xpath = "//button[text()= 'Update profile']")
    private WebElement updateProfileButton;




    public ProfileSettingsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public void openPage() {
        driver.get(BASE_URL);
    }

    public void writeNewBio(String BIO){
        openPage();
        changeBioTextarea.clear();
        changeBioTextarea.sendKeys(BIO);
        updateProfileButton.click();
        logger.info("Profiles BIO was changed on ["+ BIO + "]");
    }















}
