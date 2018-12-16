package com.epam.tat21.naverin.steps;

import com.epam.tat21.naverin.driver.DriverSingleton;
import com.epam.ta.pages.*;
import com.epam.tat21.naverin.pages.*;
import com.epam.tat21.naverin.utils.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class Steps {
    private WebDriver driver;

    private final Logger logger = LogManager.getRootLogger();

    public void openBrowser() {
        driver = DriverSingleton.getDriver();
    }

    public void closeBrowser() {
        DriverSingleton.closeDriver();
    }

    public void loginGithub(String username, String password) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        loginPage.login(username, password);
    }

    public String getLoggedInUserName() {
        LoginPage loginPage = new LoginPage(driver);
        return loginPage.getLoggedInUserName().trim().toLowerCase();
    }

    public void createNewRepository(String repositoryName, String repositoryDescription) {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOnCreateNewRepositoryButton();
        CreateNewRepositoryPage createNewRepositoryPage = new CreateNewRepositoryPage(driver);
        createNewRepositoryPage.createNewRepository(repositoryName, repositoryDescription);
    }

    public boolean currentRepositoryIsEmpty() {
        CreateNewRepositoryPage createNewRepositoryPage = new CreateNewRepositoryPage(driver);
        return createNewRepositoryPage.isCurrentRepositoryEmpty();
    }

    public String getCurrentRepositoryName() {
        CreateNewRepositoryPage createNewRepositoryPage = new CreateNewRepositoryPage(driver);
        return createNewRepositoryPage.getCurrentRepositoryName();
    }

    public String generateRandomRepositoryNameWithCharLength(int howManyChars) {
        String repositoryNamePrefix = "testRepo_";
        return repositoryNamePrefix.concat(Utils.getRandomString(howManyChars));
    }

    public void deleteExistingRepository(String repositoryName) {
        ProfilePage settingsPage = new ProfilePage(driver);
        settingsPage.deleteRepository(repositoryName);
    }

    public boolean isRepositoryExists(String repositoryName) {
        ProfilePage repositoryPage = new ProfilePage(driver);
        return repositoryPage.isRepositoryExists(repositoryName);
    }

    public boolean isLoggedIn(String username) {
        LoginPage loginPage = new LoginPage(driver);
        String loggedUsername = loginPage.getLoggedInUserName().trim().toLowerCase();
        return loggedUsername.equalsIgnoreCase(username);
    }

    public void getSignOutGithub() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOnSingOutButton();
    }

    public String setProfilesBIO(String BIO) {
        ProfileSettingsPage profileSettingsPage = new ProfileSettingsPage(driver);
        profileSettingsPage.writeNewBio(BIO);
        return BIO;
    }

    public String getProfilesBIO() {
        ProfilePage profilePage = new ProfilePage(driver);
        return profilePage.getProfilesBIO();
    }


}
