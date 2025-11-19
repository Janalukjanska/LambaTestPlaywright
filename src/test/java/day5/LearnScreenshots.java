package day5;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.ScreenshotCaret;

import java.nio.file.Paths;

public class LearnScreenshots {

    public static void main(String[] args) {

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch();
        Page page = browser.newPage();
        page.navigate("https://www.lambdatest.com/selenium-playground/simple-form-demo");
        System.out.println(page.title());


        // Screenshots
        Page.ScreenshotOptions screenshotOptions = new Page.ScreenshotOptions();
        page.screenshot(screenshotOptions.setPath(Paths.get("./snaps/scr.png")));

        // Full page screenshots
        page.screenshot(screenshotOptions.setFullPage(true)
                .setPath(Paths.get("./snaps/fullPage.jpg")));

        // Locator screenshot
        Locator bookBtn = page.locator("//button[text()='Book a Demo']");
        Locator.ScreenshotOptions locatorOptions = new Locator.ScreenshotOptions()
                .setPath(Paths.get("./snaps/locator.png"));
        bookBtn.screenshot(locatorOptions);

        Locator header = page.locator("#header");
        header.screenshot(new Locator.ScreenshotOptions()
                .setPath(Paths.get("./snaps/region.png")));

        // Masking locator
        Locator input = page.locator("input#user-message");
        input.type("aaa");
        input.scrollIntoViewIfNeeded();
//        page.screenshot(screenshotOptions.setPath(Paths.get("./snaps/input.png"))
//                .setFullPage(false)
//                .setMask(Arrays.asList(input))
//                );

        // Caret show/hide
        input.click();
        page.screenshot(new Page.ScreenshotOptions().setCaret(ScreenshotCaret.HIDE)
                .setPath(Paths.get("./snaps/caretHIDE.png")));
        page.screenshot(new Page.ScreenshotOptions().setCaret(ScreenshotCaret.INITIAL)
                .setPath(Paths.get("./snaps/caretINITIAL.png")));

        page.close();
        playwright.close();

    }

}
