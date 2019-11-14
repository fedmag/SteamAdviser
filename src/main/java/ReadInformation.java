import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

class ReadInformation {
    private String id = "76561198077512418";
    private String key = "DBBDC8EAB25ED2721D3986C8577790DA";

    String getURL(){

        String url = "http://api.steampowered.com/IPlayerService/GetOwnedGames/v0001/?key="+ key +"&steamid="+ id +"&include_appinfo=true&format=json";
        return(url);
    }


    static String[] deserializeJson() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("games.json"));
            Gson gson = new Gson();
            Data data = gson.fromJson(bufferedReader, Data.class);
            bufferedReader.close();

            int gameCount = data.response.game_count;
//            List<Data.Response.Games> games = data.response.games;  potremmo poi passare games nel for loop, ma non ha molto senso
            int i = 0;
            String[] games = new String[gameCount];
            for (Data.Response.Games game : data.response.games) {
                String name = game.name;
                name = name.toLowerCase();
                if (!name.contains("test")) {
                    games[i] = name;
                    i++;
                } else{
                    games[i] = "ciccio";
                }
            }
            return games;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    static ArrayList<String> cleanName(String[] gamesNames) {
        ArrayList<String> namesNotNull = new ArrayList<>();
        // Optimizing the name from the game to get a proper Google call
        char chr = '+';

        for(String name :gamesNames){
            if(name!=null){
                name = name.replace(' ', chr);
                name = name.replace("'", "");
                name = name.replace("-", "");
                name = name.replace("++", "");
                name = name.replace(":", "");
                name = name.replace(".", "");
                name = name.replace("™", "");
                name = name.replace("®", "");
                name = name.replace("©", "");
                namesNotNull.add(name);
            }
        }
        // Getting rid of the TM or tm at the end of some names
        for(String gameName :namesNotNull) {
                int index = gameName.length() - 2;
            if (gameName.substring(index).equals("tm") || gameName.substring(index).equals("TM")) {
                gameName = gameName.substring(0, index);
            }
        }return namesNotNull;
        }
    }
