package Testes;

import Suporte.Generator;
import Suporte.Screenshot;
import org.apache.http.util.Asserts;
import org.junit.*;
import org.junit.rules.TestName;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class LogarAprender {


    private WebDriver navegador;
   @Rule

   public TestName arquivo = new TestName();


    @Before
       public void setup(){


        System.setProperty("webdriver.chrome.driver","C:\\test\\chromedriver.exe");
        navegador = new ChromeDriver();
        navegador.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        navegador.manage().window().maximize();
        navegador.get("https://juliodeLima.com.br/taskit");
        //Assert.assertEquals("","");
        navegador.findElement(By.linkText("Task it!")).click();
        navegador.findElement(By.linkText("Sign in")).click();

        WebElement formulario = navegador.findElement(By.id("signinbox"));

        formulario.findElement(By.name("login")).sendKeys("julio0001");
        formulario.findElement(By.name("password")).sendKeys("123456");
        navegador.findElement(By.linkText("SIGN IN")).click();
        navegador.findElement(By.className("me")).click();
        navegador.findElement(By.linkText("MORE DATA ABOUT YOU")).click();
    }


    @Test
    public void cadastrarContaUsuario(){



           navegador.findElement(By.xpath("//button[@data-target=\"addmoredata\"]")).click();
           WebElement popup = navegador.findElement(By.id("addmoredata"));
           WebElement campoType = popup.findElement(By.name("type"));
            new Select(campoType).selectByValue("phone");
           navegador.findElement(By.name("contact")).sendKeys("+553112345658");
           navegador.findElement(By.linkText("SAVE")).click();
        //aqui foi cadastrado com sucesso
         WebElement texto = navegador.findElement(By.id("toast-container"));
          String miguel = texto.getText();
           System.out.print(miguel);
            Assert.assertEquals("Your contact has been added!",miguel);

            //pegando o print da tela após cadastrar

        String screanshotArquivo1 = "/Users/hudson/Pictures/aqui/ " + Generator.dataHoraParaArquivo()
                + arquivo.getMethodName() + ".png";
        Screenshot.tirarScreanshot(navegador,screanshotArquivo1);

           navegador.quit();

       }

       @Test

       public void removerContaUsuario() {
        navegador.findElement(By.xpath("//span[text()=\"+553112345658\"]/following-sibling::a")).click();
        navegador.switchTo().alert().accept();
           WebElement texto = navegador.findElement(By.id("toast-container"));
           String miguel = texto.getText();
           System.out.print(miguel);
           Assert.assertEquals("Rest in peace, dear phone!",miguel);

           String screanshotArquivo = "/Users/hudson/Pictures/aqui/" + Generator.dataHoraParaArquivo()
                   + arquivo.getMethodName() + ".png";
           Screenshot.tirarScreanshot(navegador,screanshotArquivo);


           WebDriverWait aguardar = new WebDriverWait(navegador,10);
           aguardar.until(ExpectedConditions.stalenessOf(texto));

           navegador.findElement(By.linkText("Logout")).click();

       }


    @After

    public void fechar(){
        navegador.quit();
    }


    }












