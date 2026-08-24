import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class primerejercicio {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://www.selenium.dev/selenium/web/web-form.html");

        WebElement campoTexto = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.name("my-text"))
        );

        campoTexto.sendKeys("Molin");
        driver.findElement(By.name("my-password")).sendKeys("Password123");
        driver.findElement(By.name("my-textarea")).sendKeys("Esta es una prueba automatizada con Selenium");

        Select lista = new Select(driver.findElement(By.name("my-select")));
        lista.selectByVisibleText("Two");

        WebElement checkbox = driver.findElement(By.id("my-check-1"));
        checkbox.click();
        System.out.println("Checkbox seleccionado: " + checkbox.isSelected());

        driver.findElement(By.cssSelector("button")).click();

        WebElement mensaje = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("message"))
        );
        String textoMensaje = mensaje.getText();

        System.out.println("Mensaje recibido: " + textoMensaje);
        System.out.println(textoMensaje.contains("Received!") ? "Prueba exitosa" : "Prueba fallida");

        driver.quit();
    }
}