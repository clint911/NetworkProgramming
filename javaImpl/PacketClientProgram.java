import java.net.*; 
import java.io.*;
public class PacketClientProgram {
    private static int INPUT_BUFFER_LIMIT = 500; 
    private InetAddress localHost; 

    public PacketClientProgram(){
        try {
            localHost =  InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            // TODO: handle exception
            System.out.println("ERROR: CLIENT ERROR connecting to network"); 
            System.exit(-1);
        }
    }
    //ask server for current time 
    private void askForTime(){
    DatagramSocket datagramSocket = null; 
     try {
         datagramSocket = new DatagramSocket();

         byte[] sendBuffer = "what time is it?".getBytes();
         DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, localHost, PacketServer.SERVER_PORT);
         System.out.println("CLIENT: sending time request to server"); 
         datagramSocket.send(sendPacket);
     } catch (IOException e) {
        System.out.println("CLIENT: Error sending time request to server");
     }
     try {
        byte[] receiveBuffer = new byte[INPUT_BUFFER_LIMIT];
        DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
        datagramSocket.receive(receivePacket);
        System.out.println("CLIENT: The time is" + new String(
            receivePacket.getData(), 0 , 
            receivePacket.getLength())); 
     } catch (IOException e) { 
        System.out.println("CLIENT: Error receiving time response from server");
     } datagramSocket.close();
}
private void askForNumOfRequests() {
    DatagramSocket socket = null;
    try {
        socket = new DatagramSocket(); 
        byte[] sendBuffer = "how many requests have you handled?".getBytes(); 
        DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, localHost, PacketServer.SERVER_PORT);
        System.out.println("CLIENT: Sending request count to the server"); 
        socket.send(sendPacket);
    } catch (IOException e) {
        // TODO: handle exception
        System.err.println(e);
    }
    try {
        byte[] receiveBuffer = new byte[INPUT_BUFFER_LIMIT]; 
        DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length); 
        socket.receive(receivePacket);
        System.out.println("CLIENT: The number of requests are " + new String(receivePacket.getData(), 0,receivePacket.getLength()));
    } catch (IOException e) {
        // TODO: handle exception
        System.out.println("CLIENT:  cannot receive num requests from server");
    }
    socket.close();
}
//ask for pizza from server 
private void askForPizza() {
    DatagramSocket socket = null;
    try {
        socket = new DatagramSocket(); 
        byte[] sendBuffer = "order pizza".getBytes(); 
        DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, localHost, PacketServer.SERVER_PORT);
        System.out.println("CLIENT: Sending request for pizza to the server"); 
        socket.send(sendPacket);
    } catch (IOException e) {
        // TODO: handle exception
        System.out.println("CLIENT: Error sending request for pizza to server");
    }
    socket.close();
}
//implement a Delay function with 3000ms 
private static void delay() {
    try {
        Thread.sleep(3000);
    } catch (InterruptedException e) {
        // TODO: handle exception
        System.out.println("CLIENT: Error in delay function");
    }
}
//implement the main method 
public static void main(String[] args) {
    PacketClientProgram packetClientProgram = new PacketClientProgram();
    delay();
    packetClientProgram.askForTime();
    delay();
    packetClientProgram.askForNumOfRequests();
    delay();
    packetClientProgram.askForPizza();
    delay();
}

}
