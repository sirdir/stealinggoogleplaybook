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
            btf.openBook("bookname"); //e.g. Соционика + работа над ошибками = инструментальная соционика. Пособие по инструментальной соционике
            sleep(5000);

            String fileName = System.getProperty("user.dir") + File.separator + "target" + File.separator + "googlebook.html";
//            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
//            writer.write(str);
//            writer.append(str);
//
//            writer.close();
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

    public void addStartOfHtmlDoc(String bookTitle){
        System.out.println("<!doctype html>\n" +
                "<html lang=\"ru\">\n" +
                "<head>\n" +
                " <meta charset=\"utf-8\" />\n" +
                " <title>" + bookTitle + "</title>\n" +
                " <link rel=\"stylesheet\" href=\"style.css\"  type=\"text/css\" />\n" +
                "</head>\n" +
                "<body>");
    }
    public void addEndOfHtmlDoc(){
        System.out.println("</body>\n" +
                "</html>");
    }
    public void addTableOfContent(String... chapters){//todo some dynamic chapter definition
        System.out.println("<div id=\"toc\">\n" +
                " <h2>\n" +
                " Table of Contents <br />\n" +
                " </h2>\n" +
                " <ul>\n" +
                " <li><a href=\"#pro\">Prologue</a></li>\n" +
                " <li><a href=\"#ch1\">Chapter 1</a></li>\n" +
                " <li><a href=\"#ch2\">Chapter 2</a></li>\n" +
                " <li><a href=\"#ch3\">Chapter 3</a></li>\n" +
                " <li><a href=\"#ch4\">Chapter 4</a></li>\n" +
                " <li><a href=\"#ch5\">Chapter 5</a></li>\n" +
                " <li><a href=\"#ch6\">Chapter 6</a></li>\n" +
                " <li><a href=\"#ch7\">Chapter 7</a></li>\n" +
                " <li><a href=\"#ch8\">Chapter 8</a></li>\n" +
                " <li><a href=\"#epi\">Epilogue</a></li>\n" +
                " </ul>\n" +
                "</div>\n" +
                "<div class=\"pagebreak\"></div>\n");
    }
}
