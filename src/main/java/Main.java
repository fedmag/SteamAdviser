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

    public static void main(String[] args) throws IOException {
        // Getting user URL from which we can then get the info
        ReadInformation infoObject = new ReadInformation();
        String url;
        url = infoObject.getURL();

        URLReader urlObject = new URLReader();
        urlObject.parsePage(url);

        String[] gamesNames;
        String[] cleanNames;
        gamesNames = ReadInformation.deserializeJson();
        cleanNames = infoObject.cleanName(gamesNames).toArray(new String[0]);
        for (String cleanName : cleanNames) {
            String googleURL = urlObject.googleSearchQuery(cleanName, "metacritic");
            System.out.println(googleURL);
        }
    }
    }

