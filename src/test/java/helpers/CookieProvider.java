package helpers;

import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.Cookie;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.StringTokenizer;

public class CookieProvider {

    public static void saveCookieInFile() throws IOException {
        File file = new File("src/test/resources/cookies.data");
        file.delete();
        file.createNewFile();
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        for(Cookie ck : WebDriverRunner.getWebDriver().manage().getCookies()) {
            bw.write((ck.getName()+";"+ck.getValue()+";"+ck.getDomain()));
            bw.newLine();
        }
        bw.close();
        fw.close();
    }

    public static void addCookieFromFile() throws IOException {
        File file = new File("src/test/resources/cookies.data");
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String strline;
        while((strline=br.readLine())!=null){
            StringTokenizer token = new StringTokenizer(strline,";");
            while(token.hasMoreTokens()){
                String name = token.nextToken();
                String value = token.nextToken();
                String domain = token.nextToken();
                Cookie ck = new Cookie(name,value,domain);
                WebDriverRunner.getWebDriver().manage().addCookie(ck);
            }
        }
    }
}

