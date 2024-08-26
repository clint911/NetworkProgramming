import java.net.*;
import java.io.*; 

class URLTestProgram {
    public static void main(String[] args) {
        URL wiki = null; 
        try {
            wiki = new URL("https://en.wikipedia.org/wiki/Computer_science");
            BufferedReader in = new BufferedReader(
                new InputStreamReader(wiki.openStream()));
                //Now read the webpage file 
                String lineOfWebpage;
                while ((lineOfWebpage = in.readLine()) != null)
                System.out.println(lineOfWebpage);
                in.close();//close the connection to the net 
            
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) { 
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
