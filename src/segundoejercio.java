import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class segundoejercio {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            driver.get("https://bstackdemo.com/");

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".shelf-item")));

            List<WebElement> productosIniciales = driver.findElements(By.cssSelector(".shelf-item"));
            int cantidadInicial = productosIniciales.size();
            System.out.println("Cantidad inicial de productos: " + cantidadInicial);

            WebElement filtroSamsung = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath("//span[text()='Samsung']")
            ));

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", filtroSamsung);

            wait.until(ExpectedConditions.numberOfElementsToBeLessThan(By.cssSelector(".shelf-item"), cantidadInicial));

            List<WebElement> productosFiltrados = driver.findElements(By.cssSelector(".shelf-item"));
            int cantidadFinal = productosFiltrados.size();
            System.out.println("Cantidad de productos después del filtro: " + cantidadFinal);

            System.out.println("\n--- Productos visibles de Samsung ---");
            List<WebElement> titulosProductos = driver.findElements(By.cssSelector(".shelf-item__title"));
            for (WebElement titulo : titulosProductos) {
                System.out.println("Producto: " + titulo.getText());
            }

            if (cantidadFinal < cantidadInicial) {
                System.out.println("\nPRUEBA EXITOSA: El filtro redujo la cantidad de productos visibles.");
            } else {
                System.out.println("\nPRUEBA FALLIDA: La cantidad de productos no cambió correctamente.");
            }

        } finally {
            driver.quit();
        }
    }
}