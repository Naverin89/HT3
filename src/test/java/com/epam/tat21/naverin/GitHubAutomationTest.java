package com.epam.tat21.naverin;

import com.epam.tat21.naverin.steps.Steps;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GitHubAutomationTest {
    private Steps steps;
    String repositoryName;
    private final String USERNAME = "testautomationuser";
    private final String PASSWORD = "Time4Death!";
    private final String BIO = "Far from their land, as they made their stand";
    private final int REPOSITORY_NAME_POSTFIX_LENGTH = 6;

    @BeforeMethod(description = "Init browser")
    public void setUp() {
        steps = new Steps();
        steps.openBrowser();
    }

    @AfterMethod(description = "Stop Browser")
    public void stopBrowser() {
        steps.closeBrowser();
    }

    @Test(description = "Login to Github")
    public void oneCanLoginGithub() {
        steps.loginGithub(USERNAME, PASSWORD);
        Assert.assertEquals(USERNAME, steps.getLoggedInUserName());
    }

    @Test(description = "Creating a repository on Github")
    public void oneCanCreateProject() {
        steps.loginGithub(USERNAME, PASSWORD);
        repositoryName = steps.generateRandomRepositoryNameWithCharLength(REPOSITORY_NAME_POSTFIX_LENGTH);
        steps.createNewRepository(repositoryName, "auto-generated test repo");
        Assert.assertEquals(steps.getCurrentRepositoryName(), repositoryName);
    }

    @Test(description = "Delete repository on Github")
    public void oneCanDeleteRepositoryGithub() {
        steps.loginGithub(USERNAME, PASSWORD);
        repositoryName = steps.generateRandomRepositoryNameWithCharLength(REPOSITORY_NAME_POSTFIX_LENGTH);
        steps.createNewRepository(repositoryName, "auto-generated test repo for delete test");
        steps.deleteExistingRepository(repositoryName);
        Assert.assertFalse(steps.isRepositoryExists(repositoryName));
    }

    @Test(description = "Editing profile's BIO")
    public void oneCanEditBIO() {
        steps.loginGithub(USERNAME, PASSWORD);
        steps.setProfilesBIO(BIO);
        Assert.assertEquals(steps.getProfilesBIO(), BIO, "Profiles BIO wasn't changed for some reason");
    }

    @Test(description = "Logging out from Github")
    public void oneCanLogoutGithub() {
        steps.loginGithub(USERNAME, PASSWORD);
        steps.getSignOutGithub();
        Assert.assertFalse(steps.isLoggedIn(USERNAME), "Can't perform login out");
    }

}
