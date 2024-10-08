//imports
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

//start of program
class Main {
    public static void main(String[] args) {

        //create reader
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String email="";
        try {

            //take id entered by user
            email = reader.readLine();
            reader.close();
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }

        //add the id to the url
        email="https://www.ecs.soton.ac.uk/people/"+email;
        InputStream data =null;

        try{
            //open the url
            URL url = new URL(email);
            URLConnection connection = url.openConnection();
            //scrape the data from the webpage
            data = connection.getInputStream();
            } catch(MalformedURLException e){
                System.out.println("Error: " + e);
            } catch(IOException e) {
                System.out.println("Error: " + e);
            }

        //create a reader for the scraped data
        BufferedReader cursor = 
            new BufferedReader(new InputStreamReader(data));
            try {
                String line="";
                boolean finished = false;
                //search for the line containing the name
                while (finished == false) {
                     line = cursor.readLine();
                    if (line.contains("people")) {
                        finished = true;
                    }
                }
                String name = line;
                //extract the name from the webpage
                name = name.substring(72, name.length() - 4);
                name= name.replace("-",  " ");
                //return the name
                System.out.println(name);
            } catch (IOException e) {
                System.out.println("Error: " + e);
            }
    }
}