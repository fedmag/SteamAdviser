
import java.util.List;

class Data{
     Response response;


    static class Response {
        int game_count;
        List<Games> games;

        static class Games {
            int appid;
            String name;
            int playtime_forever;
            String img_icon_url;
            String img_logo_url;
            boolean has_community_visible_stats;
            int playtime_windows_forever;
            int playtime_mac_forever;
            int playtime_linux_forever;
        }
    }
}