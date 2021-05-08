package application;

import models.StudentForm;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

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

        String [] numbers = bday.split(" ");
        int n = Integer.parseInt(numbers[1]);
        String month = Integer.toString(--n);

        // click by text box bday
        // select month
        selectByIndex(By.xpath("//select[@class='react-datepicker__month-select']"), month);
        // select year
        selectByValue(By.xpath("//select[@class='react-datepicker__year-select']"), numbers[2]);
        // select day
        pickUpDayFromTable(By.xpath("//div[@class='react-datepicker__month']/div/div"), numbers[0]);

    }



    private void pickUpDayFromTable(By locator, String number) {
        List<WebElement> days = wd.findElements(locator);
        for (WebElement day : days){
            if (day.getText().equals(number)){
                day.click();
                break;
            }
        }

    }



}
