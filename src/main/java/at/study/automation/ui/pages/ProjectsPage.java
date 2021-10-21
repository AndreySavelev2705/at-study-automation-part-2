package at.study.automation.ui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProjectsPage extends Page {

    @FindBy(xpath = "")
    public WebElement project;


    public ProjectsPage() {
        super();
    }

//    public String getProject(String projectName) {
//        String xpathString = "//div[@id='projects-index']//a[@class='project root parent public ' and text()='" + projectName + "']";
//        @FindBy(xpath = xpathString)
//        this.project
//    }

}
