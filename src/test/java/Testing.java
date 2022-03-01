import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Testing {
    public static void main(String[] args) {
        Base base = new Base();

        base.setup();
        Base.driver.get("https://www.wikipedia.org/");

//        WebElement title = Base.driver.findElement(By.xpath(Base.getElementLocator("wiki_title_xpath")));
//        System.out.println(title.getText());

        // Main Page
        WebElement searchInput = Base.driver.findElement(By.xpath(Base.getElementLocator("wiki_search_input_xpath")));
        WebElement searchButton = Base.driver.findElement(By.xpath(Base.getElementLocator("wiki_search_button")));
        UIActions.sendKeys(searchInput, "Automation");
        UIActions.click(searchButton);

        // Result Page
        WebElement title = Base.driver.findElement(By.xpath(Base.getElementLocator("wiki_search_result_title")));
        System.out.println(title.getText());

        if(title.getText().equals("Automation" )){
            System.out.println("Horrray!");
        }



        base.closeSession();
    }
}
