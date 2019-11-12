class HTMLParser {

    String url = "https://www.metacritic.com/game/pc/";

    void getMetacriticURL(String name) {
        char chr = '-';

        if (name != null) {
            String newName = name.replace(' ', chr);
            newName = newName.replace("'", "");
            newName = newName.replace("--", "");
            newName = newName.replace(":", "");
            newName = newName.replace(".", "");
            newName = newName.replace("™", "");
            newName = newName.replace("®", "");
            newName = newName.replace("©", "");
            String newURL = this.url + newName.toLowerCase();
            System.out.println(name);
            System.out.println(newURL);
        }
    }

}
