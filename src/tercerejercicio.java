import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class tercerejercicio {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            driver.get("https://www.automationexercise.com/products");

            WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search_product")));

            searchInput.sendKeys("jeans");

            driver.findElement(By.id("submit_search")).click();

            wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector(".title.text-center"), "SEARCHED PRODUCTS"));

            List<WebElement> productos = driver.findElements(By.cssSelector(".productinfo p"));

            int cantidad = productos.size();
            System.out.println("Cantidad de productos encontrados: " + cantidad);

            boolean coincidenTodos = true;

            for (WebElement prod : productos) {
                String nombre = prod.getText();
                System.out.println("Producto: " + nombre);

                if (!nombre.toLowerCase().contains("jean")) {
                    coincidenTodos = false;
                }
            }

            if (cantidad > 0 && coincidenTodos) {
                System.out.println("\nPrueba exitosa");
            } else {
                System.out.println("\nPrueba fallida");
            }

        } finally {
            driver.quit();
        }
    }
}