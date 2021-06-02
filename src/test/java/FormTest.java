import models.StudentForm;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FormTest extends TestBase{

    @BeforeMethod
    public void preconditions(){
        app.form().selectItemForms();
        app.form().selectPracticeForm();
        app.form().hideFooter();

    }
    @Test() // group test by tags (annotations "my")
    public void formTest(){
      app.form().fillForm(new StudentForm()
              .withFirstName("Alex")
              .withLastName("English")
              .withEmail("alexenglish@gmail.com")
              .withGender("Male") // radioButton
              .withPhone("1234556765")
              .withBirthday("29 Apr 1979") // parse parameters
              .withSubject("Maths") // autoComplite form
              .withHobbies("Sports Reading") // checkBox
              .withAddress("Haifa, Hazir Bar str. 12")
              .withState("NCR")
              .withCity("Delhi")); // parse parameters

        app.form().submit();
        Assert.assertTrue(app.form().isDialogDisplayed());
        Assert.assertFalse(app.form().isCloseButtonLocated());
        app.form().closeDialog();
        logger.info("Car was created");
    }


}
