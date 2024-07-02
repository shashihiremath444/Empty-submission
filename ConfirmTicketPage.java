package confirmtktES;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

class ConfirmTicketPage {
    private WebDriver driver;

    public ConfirmTicketPage(WebDriver driver) {
        this.driver = driver;
    }
    public void openHomePage() throws InterruptedException, LoginException {
        driver.get("https://www.confirmtkt.com/rbooking-d/");
        login();
    }

    public class LoginException extends Exception {
        public LoginException(String message) {
            super(message);
        }
    }

    public void login() throws InterruptedException, LoginException {
        try {
            driver.findElement(By.xpath("//span[text()=\"LOGIN\"]")).click();
            Thread.sleep(8000); 
            if (emptyValueCheck()) {
                System.out.println("The phone number is valid.");
            }
            selectDestinations();
            selectTrainAndClass();
            enterPassengerDetails();
            proceedWithBooking();
        } catch (Exception e) {
            //System.out.println("Something went wrong! " + e.getMessage());
        }
    }

    public boolean emptyValueCheck() {
        try {
            WebElement textBox = driver.findElement(By.id("mobile-number"));
            String phoneNumber = textBox.getAttribute("value");

            if (phoneNumber.isEmpty()) {
                System.out.println("The text box is empty.");
                return false; // Return false if the text box is empty
            } else if (!isValidPhoneNumber(phoneNumber)) {
                System.out.println("The phone number is invalid.");
                return false; // Return false if the phone number is invalid
            } else {
                return true; // Return true if the phone number is valid
            }
        } catch (NoSuchElementException e) {
            System.out.println("An error occurred: " + e.getMessage());
            return true; // Continue with the booking process even if element is not found
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            return false; // Return false if an unexpected exception occurs
        }
    }

    private static boolean isValidPhoneNumber(String phoneNumber) {
        
        // Simple phone number validation  (e.g., 10 digits, all numeric)
        return phoneNumber.matches("\\d{10}");
    }

    public void selectDestinations() {
        
        driver.findElement(By.xpath("//label[text()=\"From\"]")).click();
        driver.findElement(By.xpath("//a[text()=\"SBC  - Bangalore City Junction\"]")).click();

        driver.findElement(By.xpath("//label[text()=\"To\"]")).click();
        driver.findElement(By.xpath("//a[text()=\"MMCT  - Mumbai Central\"]")).click();

        driver.findElement(By.xpath("//span[text()=\"SEARCH\"]")).click();
        
    }

    public void selectTrainAndClass() throws InterruptedException {
     
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 1000);");

        driver.findElement(By.xpath("//div[contains(@class, 'train-block')][.//div[contains(text(), '11302 -') and contains(text(), 'udyan exp')]]/following-sibling::div[@style='padding-bottom: 50px;']//div[@class='availability-cache']//div[@class='avail-block']//div[@class='avail-cls' ]//span[text()='3A']")).click();

        driver.findElement(By.xpath("//*[@id=\"3A\"]/div[1]/div/div[3]/div")).click();
        Thread.sleep(10000);
        System.out.println("selectTrainAndClass() ends");
    }

    public void enterPassengerDetails() {
       
        driver.findElement(By.xpath("//span[text()=\"Male\"]")).click();

        driver.findElement(By.id("passengerName")).sendKeys("Shashidhar");
        driver.findElement(By.id("passengerAge")).sendKeys("24");

        Select ddown = new Select(driver.findElement(By.id("berth-pref")));
        ddown.selectByVisibleText("Lower Berth");

        driver.findElement(By.xpath("//span[text()=\"Save\"]")).click();
    }

    public void proceedWithBooking() {
        driver.findElement(By.id("email")).sendKeys("shashihiremath444@gmail.com");
        driver.findElement(By.xpath("//div[text()=\"Pay no charges when ticket is cancelled\"]")).click();
        driver.findElement(By.xpath("//div[text()=\"PROCEED\"]")).click();
    }
}
