import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Test {
    public static void main(String[] args) {
        System.setProperty("webdriver.driver.chrome", "C:\\Program Files\\Web Drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        //go to web site
        driver.get("https://www.cricbuzz.com/live-cricket-scorecard/22618/hhs-vs-kutw-1st-match-saurashtra-premier-league-2019");

        WebElement table = driver.findElement(By.cssSelector("div[class='cb-col cb-col-100 cb-ltst-wgt-hdr']"));
        //now search only inside of table
        int rowCount = table.findElements(By.cssSelector("div[class='cb-col cb-col-100 cb-scrd-itms']")).size();
        //from cssSelector go to third child with: div:nth-child(3)
        int count = table.findElements(By.cssSelector("div[class='cb-col cb-col-100 cb-scrd-itms'] div:nth-child(3)")).size();

        int sum=0;
        for(int i=0;i<count-2;i++) {
            String value = table.findElements(By.cssSelector("div[class='cb-col cb-col-100 cb-scrd-itms'] div:nth-child(3)")).get(i).getText();
            int valueInteger = Integer.parseInt(value);
            sum = sum + valueInteger;
        }
        System.out.println("Sum is: " + sum);

        String extras = driver.findElement(By.xpath("//div[text()='Extras']/following-sibling::div")).getText();
        int extrasValue = Integer.parseInt(extras);
        int totalSumValue = sum+extrasValue;
        System.out.println("My sum: " + totalSumValue);
        String total = driver.findElement(By.xpath("//div[text()='Total']/following-sibling::div")).getText();
        int actualTotalValue = Integer.parseInt(total);
        System.out.println("Actual total: " + actualTotalValue);

        if(totalSumValue==actualTotalValue) {
            System.out.println("Count Matches!");
        }else {
            System.out.println("Count fails!");
        }
    }
}
