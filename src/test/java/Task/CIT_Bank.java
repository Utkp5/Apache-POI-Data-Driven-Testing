package Task;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import Excel.Excel_Utility;

public class CIT_Bank {

	public static void main(String[] args) throws IOException, InterruptedException {
		
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://www.cit.com/cit-bank/resources/calculators/certificate-of-deposit-calculator/");
		
		String file = System.getProperty("user.dir")+"\\testdata\\caldata2.xlsx";
		
		int rows = Excel_Utility.getRowCount(file,"sheet1");
		
		for(int i=0;i<rows;i++)
		{
			//read data from excel
			String dpt_amt = Excel_Utility.getCellData(file,"sheet1",i,0); //here 0 represents column no.
			String interest_rt = Excel_Utility.getCellData(file,"sheet1",i,1);
			String length = Excel_Utility.getCellData(file,"sheet1",i,2);
			String compound = Excel_Utility.getCellData(file,"sheet1",i,3);
			String total = Excel_Utility.getCellData(file,"sheet1",i,4);
			String exp_value = Excel_Utility.getCellData(file,"sheet1",i,5);
			
			//before passing clr all the fields then pass data to website
			WebElement ini_dpt_amt = driver.findElement(By.id("mat-input-0"));
			WebElement lngth =  driver.findElement(By.id("mat-input-1"));
			WebElement intrst_rt = driver.findElement(By.id("mat-input-2"));
			WebElement cal_btn =  driver.findElement(By.xpath("//button[@id='CIT-chart-submit']/div"));
			
			ini_dpt_amt.clear();
			lngth.clear();
			intrst_rt.clear();
			Thread.sleep(2000);
			ini_dpt_amt.sendKeys(dpt_amt);
			lngth.sendKeys(interest_rt);
			intrst_rt.sendKeys(length);
			

			//Dropdown (Boostrap) - Not having Select Tag
			WebElement compoundrp = driver.findElement(By.xpath("//mat-select[@id='mat-select-0']"));  
			compoundrp.click();
			
            List<WebElement> options=driver.findElements(By.xpath("//div[@id='mat-select-0-panel']//mat-option"));
			
			for(WebElement option:options)
			{
				if(option.getText().equals(compound))
					option.click();
			}
			
			//cal_btn.click();
			
			//validation & update results in excel
			String act_value = driver.findElement(By.id("displayTotalValue")).getText();
			System.out.println(act_value);
			
			Thread.sleep(2000);
			
			//clear all the fields
			driver.findElement(By.id("mat-input-0")).clear();
			driver.findElement(By.id("mat-input-1")).clear();
			driver.findElement(By.id("mat-input-2")).clear();
			
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
