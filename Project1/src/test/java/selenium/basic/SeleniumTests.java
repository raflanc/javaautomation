package selenium.basic;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SeleniumTests {

    // This field (variable named 'driver') must be declared on class level (not inside any method) so that it can be shared across all methods
    private WebDriver driver;

    // This will execute before each test case (before each method annotated with @Test is executed)
    @BeforeEach
    public void startDriver() {
        // Start Chrome browser
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    public void canFindCodeSprintersOnGoogle() {

        // Open Google page in browser
        driver.get("https://www.google.com");

        // Find search box by its HTML attribute name that is equal 'q'
        WebElement searchBox = driver.findElement(By.name("q"));

        // Type in search text into search box
        searchBox.sendKeys("code sprinters");

        // Submit query to see google results page
        searchBox.submit();

        // Find all 10 results on a page and create stream ('collection') to be able to filter results easily
        // We search using CSS selector for all HTML div elements that have class equal 'rc'
        Stream<WebElement> searchResults = driver.findElements(By.cssSelector("div.rc")).stream();

        // Filter results so that in new stream only results matching our filtering criteria remain
        // The condition searches within each of 10 elements for HTML div that has class 'r' and HTML anchor (a) inside
        // Then we pull value of HTML attribute href for that anchor and compare it with expected URL "http://agileszkolenia.pl/"
        Stream<WebElement> resultFiltered = searchResults
                .filter(r -> r.findElement(By.cssSelector("div.r > a")).getAttribute("href").equals("http://agileszkolenia.pl/"));

        // Now, let us convert stream to list - so that we can easily get elements out of it
        List<WebElement> resultsList = resultFiltered.collect(Collectors.toList());

        // Check that collection (our list) contains only one element
        Assertions.assertEquals(1, resultsList.size());

        // Pull element out of the list (by its index, 0 for the first and only element)
        WebElement result = resultsList.get(0);

        // Check that element contains proper link inside <cite>
        // That means we have to find inside this web element HTML element cite, pull text out of it and compare it with expected text
        Assertions.assertEquals("agileszkolenia.pl/", result.findElement(By.tagName("cite")).getText());

    }


    @Test
    public void verifyAuthorOfBlogAboutTransformations() {
        driver.get("http://markowicz.pro/");
        Stream<WebElement> listOfNodes = driver.findElements(By.className("entry-title"))
                .stream()
                .filter(n -> n.findElement(By.tagName("a")).getText().equals("O transformacjach"));

        List<WebElement> filteredNodes = listOfNodes.collect(Collectors.toList());
        Assertions.assertEquals(1, filteredNodes.size(), "Only one matching note is found on main page");

        driver.findElement(By.cssSelector("#eu-cookie-law input")).submit();

        filteredNodes.get(0).click();

        WebElement author = driver.findElement(By.cssSelector(".author > a"));
        Assertions.assertEquals("Rafał", author.getText(), "Proper author name is displayed");
        Assertions.assertEquals("http://markowicz.pro/author/rafal-markowicz/",
                author.getAttribute("href"),
                "Valid author URL is displayed");
    }

    // This will execute after each test case (after each method annotated with @Test is executed)
    @AfterEach
    public void closeDriver() {
        // Close Chrome browser
        driver.quit();
    }
}
