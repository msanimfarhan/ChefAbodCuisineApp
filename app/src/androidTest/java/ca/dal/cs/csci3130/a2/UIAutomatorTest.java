package ca.dal.cs.csci3130.a2;

import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import android.content.Context;
import android.content.Intent;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.uiautomator.By;
import androidx.test.uiautomator.UiDevice;
import androidx.test.uiautomator.UiObject;
import androidx.test.uiautomator.UiObject2;
import androidx.test.uiautomator.UiObjectNotFoundException;
import androidx.test.uiautomator.UiSelector;
import androidx.test.uiautomator.Until;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith(AndroidJUnit4.class)
public class UIAutomatorTest {

    private static final int LAUNCH_TIMEOUT = 5000;
    final String launcherPackage = "ca.dal.cs.csci3130.a2";
    private UiDevice device;

    @Before
    public void setup() {
        device = UiDevice.getInstance(getInstrumentation());
        Context context = ApplicationProvider.getApplicationContext();
        final Intent appIntent = context.getPackageManager().getLaunchIntentForPackage(launcherPackage);
        appIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(appIntent);
        device.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)), LAUNCH_TIMEOUT);
    }

    @Test
    public void checkIfLandingPageIsVisible() {
        UiObject netIDBox = device.findObject(new UiSelector().textContains("Net ID"));
        assertTrue(netIDBox.exists());
        UiObject emailIDBox = device.findObject(new UiSelector().textContains("Email"));
        assertTrue(emailIDBox.exists());
        UiObject roleSpinner = device.findObject(new UiSelector().textContains("Select your role"));
        assertTrue(roleSpinner.exists());
        UiObject registerButton = device.findObject(new UiSelector().text("REGISTER"));
        assertTrue(registerButton.exists());
    }

    @Test
    public void checkIfMoved2WelcomePage() throws UiObjectNotFoundException {
        UiObject netIDBox = device.findObject(new UiSelector().textContains("Net ID"));
        netIDBox.setText("xy881245");
        UiObject emailIDBox = device.findObject(new UiSelector().textContains("Email"));
        emailIDBox.setText("abc123@dal.ca");
        UiObject roleSpinner = device.findObject(new UiSelector().textContains("Select your role"));
        roleSpinner.click();
        List<UiObject2> roles = device.findObjects(By.res("android:id/text1"));
        roles.get(1).click();
        UiObject registerButton = device.findObject(new UiSelector().text("REGISTER"));
        registerButton.clickAndWaitForNewWindow();
        UiObject welcomeLabel = device.findObject(new UiSelector().textContains("Welcome"));
        assertTrue(welcomeLabel.exists());
    }
}
