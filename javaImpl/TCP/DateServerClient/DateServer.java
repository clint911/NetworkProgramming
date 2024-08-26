package TCP.DateServerClient;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date; 

/**Simple TCP server, when client connects, it sends the client the current datetime, then closes the connection arguably the simplest server you can write. Note that the client has to be completely served its date before the server will be able to handle another client.*/
public class DateServer {
    public static void main(String[] args) throws IOException {
        try (var listener = new ServerSocket(59090)){
            System.out.println("The date server is up, running & listening..."); 
            while (true) {
                try (var socket = listener.accept()){
                    var out = new PrintWriter(socket.getOutputStream(), true);
                    out.println(new Date().toString());
                }
            }
        } 
        }
}
