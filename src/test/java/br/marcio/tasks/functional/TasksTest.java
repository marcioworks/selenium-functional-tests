package br.marcio.tasks.functional;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class TasksTest {

    private WebDriver callWebdrive() {
        System.setProperty("webdriver.chrome.driver", "D:/Documents/Jenkis course/chromedriver-win64/chromedriver.exe");
        WebDriver webDriver = new ChromeDriver();
        webDriver.navigate().to("http://localhost:8001/tasks");
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return webDriver;
    }

    @Test
    public void deveSalvarTarefa() {
        WebDriver webDriver = callWebdrive();
        try {
            //click add todo
            webDriver.findElement(By.id("addTodo")).click();
            //fill task input
            webDriver.findElement(By.id("task")).sendKeys("Selenium teste");
            //fill date
            webDriver.findElement(By.id("dueDate")).sendKeys("10/10/2024");
            //save task
            webDriver.findElement(By.id("saveButton")).click();
            //validate Message
            String message = webDriver.findElement(By.id("message")).getText();
            Assert.assertEquals("Success!", message);

        } finally {
            //close o browser
            webDriver.quit();

        }
    }

    @Test
    public void shouldNotCreateTaskWithoutDescription() {
        WebDriver webDriver = callWebdrive();
        try {
            //click add todo
            webDriver.findElement(By.id("addTodo")).click();

            //fill date
            webDriver.findElement(By.id("dueDate")).sendKeys("10/10/2024");
            //save task
            webDriver.findElement(By.id("saveButton")).click();
            //validate Message
            String message = webDriver.findElement(By.id("message")).getText();
            Assert.assertEquals("Fill the task description", message);

        } finally {

            //close o browser
            webDriver.quit();
        }
    }

    @Test
    public void shouldNotCreateTaskWithoutDate() {
        WebDriver webDriver = callWebdrive();
        try {
            //click add todo
            webDriver.findElement(By.id("addTodo")).click();
            //fill task input
            webDriver.findElement(By.id("task")).sendKeys("Selenium teste");
            //save task
            webDriver.findElement(By.id("saveButton")).click();
            //validate Message
            String message = webDriver.findElement(By.id("message")).getText();
            Assert.assertEquals("Fill the due date", message);
        } finally {
            //close o browser
            webDriver.quit();

        }
    }


    @Test
    public void shouldNotCreateTaskWithPastDate() {
        WebDriver webDriver = callWebdrive();
        try {
            //click add todo
            webDriver.findElement(By.id("addTodo")).click();
            //fill task input
            webDriver.findElement(By.id("task")).sendKeys("Selenium teste");
            //fill date
            webDriver.findElement(By.id("dueDate")).sendKeys("10/10/2023");
            //save task
            webDriver.findElement(By.id("saveButton")).click();
            //validate Message
            String message = webDriver.findElement(By.id("message")).getText();
            Assert.assertEquals("Due date must not be in past", message);
        } finally {
            //close o browser
            webDriver.quit();
        }

    }
}
