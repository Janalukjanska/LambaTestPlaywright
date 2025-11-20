package windowHandling;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import java.util.List;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LearnWindowHandling {

    public static void main(String[] args) {

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
        page.navigate("https://www.lambdatest.com/selenium-playground/window-popup-modal-demo");
        System.out.println(page.title());
//        Page popup = page.waitForPopup(() -> {
//            page.getByText("Follow On Twitter").click();
//        });
//        popup.waitForLoadState();
//        assertThat(popup).hasTitle("LambdaTest (@lambdatesting) / X");
//        System.out.println(popup.title());
//        popup.getByText("Log in").click();


        Page tabs = page.waitForPopup(new Page.WaitForPopupOptions().setPredicate(
                p -> p.context().pages().size() == 3),
                () -> {
            page.getByText("Follow All").click();
        });
        List<Page> pages = tabs.context().pages();
        System.out.println(pages.size());
        pages.forEach(tab -> {
            tab.waitForLoadState();
            System.out.println(tab.title());
        });
        Page fbpage = null;
        Page xpage = null;

        if(pages.get(0).title().endsWith("X")){
            xpage = pages.get(1);
        }
        else {
            fbpage = pages.get(0);
        }
        System.out.println(fbpage.url());

    }

}
