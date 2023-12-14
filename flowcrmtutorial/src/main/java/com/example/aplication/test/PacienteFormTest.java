package com.example.aplication.test;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import static java.time.Duration.ofSeconds;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class PacienteFormTest {

    public static void main(String[] args) throws InterruptedException {

       
       

        WebDriver driver = new ChromeDriver();

        try {
            driver.get("http://localhost:8080/pacientes");

            
            new WebDriverWait(driver, ofSeconds(30), ofSeconds(1))
                    .until(titleIs("Pacientes | Hospital CRM"));

           
            WebElement addButton = driver.findElement(By.xpath("//vaadin-button[@id='addP']"));
                   
            addButton.click();
            Thread.sleep(3000);
            
            WebElement textDni = driver.findElement(By.xpath("//vaadin-text-field[@id='dNi']/input"));
            textDni.click();
            textDni.sendKeys("159159159");
            Thread.sleep(3000);
            
            WebElement textNombre = driver.findElement(By.xpath("//vaadin-text-field[@id='nombre']/input"));
            textNombre.click();
            textNombre.sendKeys("Paciente");
            Thread.sleep(3000);
            
            WebElement textApellido = driver.findElement(By.xpath("//vaadin-text-field[@id='apellido']/input"));
            textApellido.click();
            textApellido.sendKeys("de Prueba");
            Thread.sleep(3000);
            
            WebElement textDireccion = driver.findElement(By.xpath("//vaadin-text-field[@id='direccion']/input"));
            textDireccion.click();
            textDireccion.sendKeys("San Pedro Sula");
            Thread.sleep(3000);
            
            
            
            
            WebElement cancelButton = driver.findElement(By.xpath("//vaadin-button[@id='cancelar']"));
            cancelButton.click();
            Thread.sleep(3000);
            
            

        } finally {
            
            
        }
        driver.quit();
    }
   
}
