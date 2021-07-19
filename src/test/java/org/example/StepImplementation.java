package org.example;

import com.thoughtworks.gauge.Gauge;
import com.thoughtworks.gauge.Step;
import driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.assertj.core.api.Assertions.assertThat;

public class StepImplementation {

    @Step("User is on the testPage")
    public void testPage() throws InterruptedException {
        String url = System.getenv("APP_URL");
        Driver.webDriver.get(url);
        Driver.webDriver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(Driver.webDriver, 10);

        WebElement inputForms = Driver.webDriver.findElement(By.xpath("//a[text()='Input Forms']"));
        wait.until(ExpectedConditions.elementToBeClickable(inputForms));
        JavascriptExecutor js = ((JavascriptExecutor) Driver.webDriver);
        js.executeScript("arguments[0].click();", inputForms);

        WebElement inputFormSubmit = Driver.webDriver.findElement(By.xpath("(//*[contains(text(),'Input Form Submit')])[2]"));
        inputFormSubmit.click();
    }

    @Step("User enters <text> in <relatedTextBox> textBox")
    public void enterText(String text, String relatedTextBox) throws InterruptedException{
        WebElement textBox = Driver.webDriver.findElement(By.name(relatedTextBox));
        textBox.sendKeys(text);


    }

    @Step("User chooses <yes> radioButton from hosting")
    public void hosting(String YesOrNo) throws InterruptedException {
        WebElement hostYes = Driver.webDriver.findElement(By.xpath("//*[contains(@value,'yes')]"));
        WebElement hostNo = Driver.webDriver.findElement(By.xpath("//*[contains(@value,'no')]"));
        if (hostNo.getAttribute("value").equalsIgnoreCase(YesOrNo)) {
            hostNo.click();
        } else if
        (hostYes.getAttribute("value").equalsIgnoreCase(YesOrNo)) {
            hostYes.click();
        } else {
            System.out.println("the parameter not exist");
        }
        Thread.sleep(5000);


    }

    @Step("User selects <Virginia> from State DropDown")
    public void selectState(String state) throws InterruptedException{
        WebElement stateDropDown = Driver.webDriver.findElement(By.cssSelector("select[class='form-control selectpicker']"));
        Select select = new Select(stateDropDown);
        select.selectByVisibleText(state);
    }

    @Step("User submits the Input form")
    public void submitForm()throws  InterruptedException {
        WebElement submitButton = Driver.webDriver.findElement(By.cssSelector("button[class='btn btn-default']"));
        submitButton.click();
    }


}
