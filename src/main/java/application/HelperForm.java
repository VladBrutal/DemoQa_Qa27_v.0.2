package application;

import models.StudentForm;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class HelperForm extends HelperBase {
    public HelperForm(WebDriver wd) {
        super(wd);
    }

    public void selectItemForms() {
        click(By.xpath("//div[@class='category-cards']/div[2]"));
        pause(500);
    }

    public void selectPracticeForm() {
        click(By.xpath("//span[.='Practice Form']"));
    }

    public void fillForm(StudentForm form) {
        type(By.id("firstName"), form.getFirstName());
        type(By.id("lastName"), form.getLastName());
        type(By.id("userEmail"), form.getEmail());
        selectGenderRadioButton(form.getGender());
        type(By.id("userNumber"), form.getPhone());
       // typeBirthday(form.getBirthday());
        selectBDay(form.getBirthday());
        pause(500);
        selectSubject(form.getSubject());
        pause(500);
        selectHobbiesCheckBox(form.getHobbies());
        uploadPicture(By.id("uploadPicture"), "/Users/vladmac/Documents/Pictures/alex_english.jpeg");
        type(By.id("currentAddress"), form.getAddress());
        selectState(form.getState());
        selectCity(form.getCity());
    }

    private void selectCity(String city) {
        WebElement elementState = wd.findElement(By.id("react-select-4-input"));
        new Actions(wd).sendKeys(elementState, city).perform(); // takes element and  sending keys (text) from keyboard
        elementState.sendKeys(Keys.ENTER);
    }

    private void selectState(String state) {
        /*type(By.id("react-select-3-input"), state);
        wd.findElement(By.id("react-select-3-input")).sendKeys(Keys.ENTER);*/
        WebElement elementState = wd.findElement(By.id("react-select-3-input"));
        new Actions(wd).sendKeys(elementState, state).perform(); // takes element and  sending keys (text) from keyboard
        elementState.sendKeys(Keys.ENTER);
    }

   /* private void selectStateAndCity(By locatorState, By locatorCity, String input) {
        String [] stateAndCity = input.split(" ");
    }*/

    private void uploadPicture(By locator, String path) {
        if (path != null){
            WebElement el = wd.findElement(locator);
            el.sendKeys(path);
        }
    }

    private void selectHobbiesCheckBox(String hobby) {
        String [] hobbies = hobby.split(" ");
        for (String s : hobbies) {
            switch (s) {
                case "Sports":
                    click(By.xpath("//label[.='Sports']"));
                    break;
                case "Reading":
                    click(By.xpath("//label[.='Reading']"));
                    break;
                case "Music":
                    click(By.xpath("//label[.='Music']"));
                    break;
            }
        }

    }

    private void selectSubject(String subject) {
        type(By.id("subjectsInput"),subject);
        wd.findElement(By.id("subjectsInput")).sendKeys(Keys.ENTER);
    }

    private void typeBirthday(String birthday) {
        WebElement bDay = wd.findElement(By.id("dateOfBirthInput"));
        bDay.click();

        String os = System.getProperty("os.name");
        if (os.startsWith("Mac")) {
            bDay.sendKeys(Keys.chord(Keys.COMMAND, "a"));
        } else {
            bDay.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        }
        bDay.sendKeys(birthday);
        bDay.sendKeys(Keys.ENTER);
    }

    private void selectGenderRadioButton(String gender) {
        if (gender.equals("Male")) {
            click(By.xpath("//label[.='Male']"));
        } else if (gender.equals("Female")) {
            click(By.xpath("//label[.='Female']"));
        } else {
            click(By.xpath("//label[.='Other']"));
        }
    }


    public void submit() {
        click(By.xpath("//button[.='Submit']"));
    }

    public boolean isDialogDisplayed() {
        return wd.findElement(By.xpath("//div[@class='modal-title h4']")).getText().contains("Thanks");
    }

    public boolean isCloseButtonLocated() {
        return wd.findElement(By.id("closeLargeModal")).getText().contains("Close");
    }

    public void closeDialog(){
        click(By.id("closeLargeModal"));
    }

    private void selectBDay(String bday){
        WebElement bDay = wd.findElement(By.id("dateOfBirthInput"));
        bDay.click();

        // Checking format of the month in string (letters or numbers)
        String [] dates = bday.split(" ");
        String exactMonth = null;
        List<WebElement> options = wd.findElements(By.xpath("//select[@class='react-datepicker__month-select']"));
        for (WebElement option : options) {
            if (option.getText().contains(dates[1])) {
                exactMonth = option.getText();
                break;
                /*option.click();
                break;*/
            }
        }
        System.out.println(exactMonth);
        new Select(wd.findElement
                (By.xpath("//select[@class='react-datepicker__month-select']")))
                .selectByVisibleText(exactMonth);



        // click by text box bday
        // select month

        // select year
        selectByValue(By.xpath("//select[@class='react-datepicker__year-select']"), dates[2]);
        // select day
        pickUpDayFromTable(By.xpath("//div[@class='react-datepicker__month']/div/div"), dates[0]);

    }

    private void selectMonthByText(By locator, String month) {
        //Checking if we receiving mane of the month in format of 3 letters (exp: Mar, Apr ect.)
        List<WebElement> nameOfMonths = wd.findElements(locator);
        for (WebElement el : nameOfMonths){
            String subMonth = el.getText().substring(0,2);
            if (subMonth.contains(month)){
                new Select(wd.findElement(locator)).selectByVisibleText(el.getText());
            }
        }
    }


    private void pickUpDayFromTable(By locator, String number) {
        List<WebElement> days = wd.findElements(locator);
        for (WebElement day : days){

        }
    }

    private void selectBDay3(String bDay) {
        String[] date = bDay.split(" ");
        click(By.id("dateOfBirthInput"));
        new Select(wd.findElement(By.xpath("//select[@class='react-datepicker__month-select']"))).selectByVisibleText(date[1]);
        new Select(wd.findElement(By.xpath("//select[@class='react-datepicker__year-select']"))).selectByVisibleText(date[2]);
        List<WebElement> ar = wd.findElements(By.xpath("//div[text()='" + date[0] + "']"));
        for (WebElement el : ar) {
            if (el.getAttribute("aria-label").contains(date[1])) {
                el.click();
            }
        }
    }

    private void selectBday4(String bDay){
        String [] date = bDay.split(" ");
        click(By.id("dateOfBirthInput"));

        new Select(wd.findElement(By.xpath("//select[@class='react-datepicker__month-select']"))).selectByVisibleText(date[1]);
        new Select(wd.findElement(By.xpath("//select[@class='react-datepicker__year-select']"))).selectByVisibleText(date[2]);

        int day = Integer.parseInt(date[0]);
        List<WebElement> days = wd.findElements(By.xpath(String.format("//div[text()='%s']", date[0])));
        WebElement element;
        if (days.size()>1 && day > 15){
            element=days.get(1);
        }else {
            element=days.get(0);
        }
        element.click();

    }
}




