import com.google.gson.Gson;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

class URLReader{

        void parsePage(String s){
            Gson gson = new Gson();
            String inputLine;
            StringBuffer content = new StringBuffer();

            try {
                URL url = new URL(s);
                // read what I receive back from the URL
                BufferedReader read = new BufferedReader(
                        new InputStreamReader(url.openStream()));

                while ((inputLine = read.readLine()) != null)
                    // appending every line I read to the StringBuffer
                    content.append(inputLine);
                    read.close();
                // transforming in a Json file
                try {FileWriter fw = new FileWriter("games.json");
                    BufferedWriter bw = new BufferedWriter(fw);{
                        bw.write(content.toString());
                        bw.close();
                    }
                }catch (IOException i){
                    i.printStackTrace();
                }
            }
            catch(MalformedURLException e) {
                    System.out.println("Malformed URL: "+ e.getMessage());
            }
            catch(IOException e){
                System.out.println("I/O Error: "+ e.getMessage());
            }
        }


        public String googleSearchQuery(String searchName, String site) throws IOException {
            ArrayList<String> URLList = new ArrayList<>();

            // Creating the base URL from which I will make the query
            String googleURL = "https://www.google.com/search?q=" + site + "+" + searchName + "&num=1";
            Document doc = Jsoup.connect(googleURL).userAgent(
                    "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.16 Safari/537.36 Edg/79.0.309.15").get();

            // Getting the page as HTML String and parsing it
            String htlmDoc = doc.toString();
            Document parsedDoc = Jsoup.parse(htlmDoc);
            // Getting the class "r"
            Elements classHTML = parsedDoc.getElementsByClass("r");
            // Selecting only the links
            Elements parsedURLS = classHTML.select("a[href]");
            for(Element parsedURL : parsedURLS){
                // Appending the links to a list s.t. I can get only the first one
                String urlGoogle = parsedURL.attr("abs:href");
                URLList.add(urlGoogle);
            }
            return URLList.get(0);
        }
        }

