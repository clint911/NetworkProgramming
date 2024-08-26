import java.net.*; 
import java.io.*;
public class Server {
    public static int SERVER_PORT = 5000; 
    private int counter = 0; 

    //Helper method to get the ServerSocket started 
    private ServerSocket goOnline() {
        ServerSocket serverSocket = null; 
        try {
        serverSocket = new ServerSocket(SERVER_PORT); 
        System.out.println("Server is online on port " + SERVER_PORT);
        } catch (IOException e) {
            System.out.println("SERVER: Error creating network connection"); 
    }
    return serverSocket;
}
//Handle all requests 
    private void handleRequests(ServerSocket serverSocket){
        while (true) {
            Socket socket = null; 
            BufferedReader bufferedReader = null; 
            PrintWriter printWriter = null; 

            try {
                //Wait for an incoming client request 
                socket = serverSocket.accept();
                //at this point a client connection has been established 
                bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                printWriter = new PrintWriter(socket.getOutputStream());
            } catch (IOException e) { 
                System.out.println("SERVER: Error connecting to client"); 
                System.exit(-1); 
        }
        //Read in the clients' request  
        try {
            String clientRequest = bufferedReader.readLine(); 
            System.out.println("SERVER: Client message received: " + clientRequest);
            if (clientRequest.equals("what time is it ?")) {
                System.out.println(new java.util.Date().toString());
                counter++;
            } else if (clientRequest.equals("How many requests have you handled?")) 
                System.out.println(counter++);
         else 
            System.out.println("SERVER: Unrecognized request");
            printWriter.flush();
            socket.close();
        } catch (IOException e) {
            System.out.println("SERVER: ERROR communicating with client");
    }
}
    }
    public static void main(String[] args) {
        Server server = new Server(); //server instance
        ServerSocket serverSocket = server.goOnline();//socket instance calling goOnline from server instance
        if (serverSocket != null) { 
            server.handleRequests(serverSocket);
    }
}
}


