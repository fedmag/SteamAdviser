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


//    static String[] cleanName(String[] games) {
//        // Getting rid of the TM or tm at the end of some names
//        for(int i = 0; i< games.length; i++) {
//            if (!games[i].equals("")) {
//                int index = games[i].length() - 2;
//                if (games[i].substring(index).equals("tm") || games[i].substring(index).equals("TM")) {
//                    games[i] = games[i].substring(0, index);
//                }
//            }
//        }
//        return games;
//    }

    static String[] cleanName(String[] games) {
        // Getting rid of the TM or tm at the end of some names
        ArrayList<String> namesNotNull = new ArrayList<>();
        for(String name:games){
            if(name!=null){
                namesNotNull.add(name);
            }
        }
        for(String game:namesNotNull) {
                int index = game.length() - 2;
                if (game != null) {
                if (game.substring(index).equals("tm") || game.substring(index).equals("TM")) {
                    game = game.substring(0, index);
                }
            }else {
                    game = "";
                }
            }return games;
        }
    }
