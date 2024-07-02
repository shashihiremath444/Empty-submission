package confirmtktES;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ConfirmTicket {
    private WebDriver driver;

    public ConfirmTicket() {
        // Initialize WebDriver
        driver = new ChromeDriver();
    }

    public void setUp() {
        driver.manage().window().maximize();
    }

    public void tearDown() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        }
    }

    public static void main(String[] args) {
        ConfirmTicket test = new ConfirmTicket();
        test.setUp();
        try {
            ConfirmTicketPage confirmTicketPage = new ConfirmTicketPage(test.driver);
            confirmTicketPage.openHomePage();
        } catch (Exception e) {
            //System.out.println("Something went wrong! " + e.getMessage());
        } finally {
            test.tearDown();
        }
    }
}

