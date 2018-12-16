package com.epam.tat21.naverin.pages;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProfilePage extends AbstractPage {

    private final String BASE_URL = "http://www.github.com/testautomationuser/";

    private final Logger logger = LogManager.getRootLogger();

    @FindBy(xpath = "//summary[contains(text(),'Delete this repository')]")
    private WebElement deleteButton;

    @FindBy(xpath = "//input[@aria-label='Type in the name of the repository to confirm that you want to delete this repository.']")
    private WebElement verifyDeleteInput;

    @FindBy(xpath = "//button[text()='I understand the consequences, delete this repository']")
    private WebElement verifyDeleteButton;

    @FindBy(xpath = "//*[@id='js-pjax-container']/div/div[1]/div[4]/div")
    private WebElement bioDiv;


    public ProfilePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @Override
    public void openPage() {
        driver.navigate().to(BASE_URL);
    }

    public void deleteRepository(String repositoryName) {
        driver.navigate().to(BASE_URL + repositoryName + "/settings");
        deleteButton.click();
        verifyDeleteInput.sendKeys("testautomationuser/" + repositoryName);
        verifyDeleteButton.click();
    }

    public boolean isRepositoryExists(String repositoryName) {
        driver.navigate().to(BASE_URL + repositoryName);
        if (driver.getTitle().contains(repositoryName)) {
            logger.warn("Test repository wasn't deleted");
            return true;
        }
        logger.info("Test repository was successfully deleted");
        return false;
    }

    public String getProfilesBIO() {
        openPage();
        return bioDiv.getText();
    }

}
