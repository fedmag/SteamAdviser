import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.internal.$Gson$Types;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Array;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        // Getting user URL from which we can then get the info
        ReadInformation infoObject = new ReadInformation();
        String url;
        url = infoObject.getURL();

        URLReader urlObject = new URLReader();
        urlObject.parsePage(url);

        String[] gamesNames;
        String[] cleanNames;
        gamesNames = ReadInformation.deserializeJson();
        for(String game:gamesNames) {
            if (game != null) {
                System.out.println(game);
            }
        }
        cleanNames = ReadInformation.cleanName(gamesNames);
        HTMLParser html = new HTMLParser();
        for(String cleanName : cleanNames){
            html.getMetacriticURL(cleanName + "1");
        }

    }
}
