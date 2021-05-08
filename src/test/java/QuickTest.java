import org.testng.annotations.Test;

public class QuickTest extends TestBase{

    @Test(groups = {"my", "pay"}) // one test can be part of 2 or more groups (array groups)
    public void quicktest(){

    }
}
