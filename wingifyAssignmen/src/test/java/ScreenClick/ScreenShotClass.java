package ScreenClick;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class ScreenShotClass {
    public void snopShort(WebDriver driver, String fileWithPath) throws IOException {
        TakesScreenshot screenshot=(TakesScreenshot) driver;
        File srcFile=screenshot.getScreenshotAs(OutputType.FILE);
        File DestFile=new File(fileWithPath);
        FileUtils.copyFile(srcFile,DestFile);
    }


}
