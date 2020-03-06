package awsDesktopBrowsersample;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import Utils.UtilityMethods;
import software.amazon.awssdk.auth.credentials.AwsSessionCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.devicefarm.DeviceFarmClient;
import software.amazon.awssdk.services.devicefarm.model.CreateTestGridUrlRequest;
import software.amazon.awssdk.services.devicefarm.model.CreateTestGridUrlResponse;

public class DesktopBrowserAWS extends UtilityMethods{

	RemoteWebDriver driver;	
	
	String deviceFarmProjectARN = getPropKeyValue("ProjectARN"); //Device Farm Desktop Project ARN
	String accessKey = getPropKeyValue("AccessKeyId"); // Access Key ID from aws sts get-session-token command
	String secretAccessKey = getPropKeyValue("SecretAccessKey"); // Secret Key from aws sts get-session-token command
	String sessionToken = getPropKeyValue("SessionToken"); // Session Token from aws sts get-session-token command
	
	@BeforeSuite
	public void setup() {
		AwsSessionCredentials awsSessionCred = AwsSessionCredentials.create(accessKey, secretAccessKey,sessionToken);
		DeviceFarmClient client = DeviceFarmClient.builder().credentialsProvider(StaticCredentialsProvider.create(awsSessionCred)).region(Region.US_WEST_2).build();
		
		CreateTestGridUrlRequest request = CreateTestGridUrlRequest.builder().expiresInSeconds(300).projectArn(deviceFarmProjectARN).build();
		CreateTestGridUrlResponse response = client.createTestGridUrl(request);
		URL testGridUrl = null;
		try {
			testGridUrl = new URL(response.url());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DesiredCapabilities oCap = new DesiredCapabilities();
		oCap.setCapability("browserName","chrome");
		oCap.setCapability("browserVersion", "latest");
		oCap.setCapability("platform", "windows");
		driver = new RemoteWebDriver(testGridUrl, oCap);
		driver.get("https://www.google.com");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	@Test
	public void myTest() {
		String title = driver.getTitle();
		System.out.println(title);
		System.out.println(driver.getTitle());
	}

	public void tesrDown() {
		driver.quit();
	}

}
