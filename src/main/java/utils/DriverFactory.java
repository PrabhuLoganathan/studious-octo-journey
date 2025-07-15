package utils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {
	private static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

	public static WebDriver getDriver() {
		return tlDriver.get();
	}

	public static void setDriver() {
		try {
			Path tempProfile = Files.createTempDirectory("chrome-profile");

			ChromeOptions options = new ChromeOptions();


			options.addArguments("--disable-notifications");
			options.addArguments("--disable-infobars");
			options.addArguments("--disable-extensions");
			options.addArguments("--disable-popup-blocking");
			options.addArguments("--remote-allow-origins=*");


			options.addArguments("--user-data-dir=" + tempProfile.toAbsolutePath().toString());


			Map<String, Object> prefs = new HashMap<>();
			prefs.put("credentials_enable_service", false);
			prefs.put("profile.password_manager_enabled", false);
			options.setExperimentalOption("prefs", prefs);

			tlDriver.set(new ChromeDriver(options));

		} catch (Exception e) {
			throw new RuntimeException("Failed to initialize ChromeDriver", e);
		}
	}

	public static void closeDriver() {
		if (getDriver() != null) {
			getDriver().quit();
			tlDriver.remove();
		}
	}
}
