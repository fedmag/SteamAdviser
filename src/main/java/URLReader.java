import com.google.gson.Gson;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

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
}
