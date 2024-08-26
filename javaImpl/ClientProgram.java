import java.net.*; 
import java.io.*; 
public class ClientProgram {
    private Socket socket; 
    private BufferedReader bufferedReader; 
    private PrintWriter printWriter; 

    //Make a connection to the server 
    private void connectToServer() {
            try {
                socket = new Socket(InetAddress.getLocalHost(), Server.SERVER_PORT);
                bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                printWriter = new PrintWriter(socket.getOutputStream());
            } catch (Exception e) {
                // TODO: handle exception
                System.out.println("CLIENT ERROR: " + e.getMessage() + "as a result client failed to connect to server");
                System.exit(-1); 
            }
    }
    //Disconnecting from the server 
    private void disconnectFromServer() {
        try {
            socket.close();
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Client failed to disconnect from server");
        }
}
//Ask the server for current time 
 private void askForTime() {
        connectToServer(); 
        printWriter.println("What time is it?"); 
        printWriter.flush();
        try {
            String time = bufferedReader.readLine();
            System.out.println("Client time is " + time);
        } catch (IOException e) {
            // TODO: handle exception
            System.out.println("Client failed to receive time from server");
        }
        disconnectFromServer();
        }
        //Ask the server for the number of requests handled 
        private void askForNumOfRequests(){
                connectToServer();
                printWriter.println("How many requests have you handled?");
                printWriter.flush();
                int count = 0; 
                try {
                    count = Integer.parseInt(bufferedReader.readLine());
                } catch (IOException e) {
                    // TODO: handle exception
                    System.out.println("Client failed to receive num requests from Server");
                }
                System.out.println("CLIENT: The number of requests are: " + count);
                disconnectFromServer();
        }
        //ask for the server to order a pizza 
        private void askForPizza() {
                connectToServer(); 
                printWriter.println("Give me a pizza");
                printWriter.flush();
                disconnectFromServer();
        }
        //a Delay function to avoid Deadlocks 
        private static void Delay() {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                // TODO: handle exception
                System.out.println("Interruped Exception");
            }
        }
        public static void main(String[] args) {
            ClientProgram clientProgram = new ClientProgram(); 
            Delay(); 
            clientProgram.askForTime();
            Delay(); 
            clientProgram.askForNumOfRequests();
            Delay();
            clientProgram.askForPizza();
            Delay(); 
            clientProgram.askForNumOfRequests();
        }
}
