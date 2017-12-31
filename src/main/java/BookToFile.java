import com.codeborne.selenide.Configuration;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;
import static com.codeborne.selenide.WebDriverRunner.getAndCheckWebDriver;

public class BookToFile {

    public static void main(String[] args){
        BookToFile btf = new BookToFile();
        Configuration.browser = "chrome";
        try {
            btf.start();
            btf.openUrl("https://play.google.com/books");
            btf.authorize("email", "password"); //todo pass through sys prop
//            btf.openUrl("https://play.google.com/books");
            btf.openBook("bookname"); //e.g. Соционика + работа над ошибками = инструментальная соционика. Пособие по инструментальной соционике
            sleep(5000);

//            btf.spizdi("filename");

            String str = "Hello";
            String fileName = System.getProperty("user.dir") + File.separator + "target" + File.separator + "googlebook.html";
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            writer.write(str);
            writer.append(str);

            writer.close();
        }
        finally {
            btf.close();
        }
    }

    private void openBook(String nameOfTheBook) {
        $("[title='" + nameOfTheBook + "']").shouldBe(visible).click();
    }

    private void authorize(String email, String password) {
        $("#identifierId").shouldBe(visible).val(email).pressEnter();
        $("[name='password']").shouldBe(visible).val(password).pressEnter();
    }

    private void openUrl(String url) {
        open(url);
    }

    private void close() {
        closeWebDriver();
    }

    private void start() {
        getAndCheckWebDriver();
    }
}
