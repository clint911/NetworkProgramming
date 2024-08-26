package TCP.SimpleThreadedServer;

import java.io.IOException; 
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.Executors;
/**server program that accepts requests from clients to capitalize strings. when a client connects, a new thread is started to handle it. Receiving client data, capitalizing it & sending the response back is all done on the thread, allowing much greater throughput because more clients can be handled concurrently */
public class CapitalizeServer {
    /**
     * Runs the server. When a client connects, the server spawns a new thread to do
     * the servicing and immediately returns to listening. The application limits
     * the number of threads via a thread pool (otherwise millions of clients could
     * cause the server to run out of resources by allocating too many threads).
     */
  public static void main(String[] args) throws Exception {
    try (var listener = new ServerSocket(59098)) {
        System.out.println("Capitalization server is running...");
        var pool = Executors.newFixedThreadPool(20);
        while(true){
            pool.execute(new Capitalizer(listener.accept()));
        }
    }
    
  }
  private static class Capitalizer implements Runnable {
        private Socket socket;
         
        Capitalizer(Socket socket) {
            this.socket = socket;
  }

  @Override 
  public void run() {
    System.out.println("Connected:", + socket); 
    try {
        var in = new Scanner(socket.getInputStream());
        var out = new PrintWriter(socket.getOutputStream(), true); 
        while (in.hasNextLine()) {
            out.println(in.nextLine().toUpperCase());
        }
    } catch (Exception e) {
        // TODO: handle exception
        System.out.println("Error!" + socket);
    } finally {
        try {
            socket.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
        System.out.println("Closed:" + socket);
    }
  }
}
