package Suporte;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;

public class Screenshot {

    public static void tirarScreanshot(WebDriver nave, String arquivo) {
        File screanshot = ((TakesScreenshot)nave).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screanshot, new File(arquivo));
        } catch (Exception e){

         System.out.println("Erro ao tentar copiar o arquivo para a pasta" + e.getMessage());

        }
    }
}
