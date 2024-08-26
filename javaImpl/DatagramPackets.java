import java.net.*; 
import java.io.*;
public class DatagramPackets {
    public static int SERVER_PORT = 5000; 
    private static int INPUT_BUFFER_LIMIT = 500;
    private int counter = 0; 

    //Helper method to get the Datagram socket started 
    private DatagramSocket goOnline(){
            DatagramSocket socket = null; 
            try {
                socket = new DatagramSocket(SERVER_PORT);
                System.out.println("SERVER online");
            } catch (SocketException e) {
                // TODO: handle exception
                System.out.println("SERVER offline, no network connection");
                System.exit(-1);
            }
            return socket;
    }

    //Handle all requests 
    private void handleRequests(DatagramSocket socket) {
        while (true) {
            try {
                //wait for an incoming client request 
                byte[] receiveBuffer = new byte[INPUT_BUFFER_LIMIT];
                DatagramPacket receivePacket; 
                receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                socket.receive(receivePacket);


                //Extract the packet data that contains th request
                InetAddress addr = receivePacket.getAddress(); 
                int clientPort = receivePacket.getPort();
                String clientRequest = new String(receivePacket.getData(), 0, receivePacket.getLength()); 
                System.out.println("SERVER: Packet Received: \""  + clientRequest + "\" from " + addr + ":" + clientPort);
                
                //Respond to client/ decide what to be sent back to client 
                byte[] sendBuffer; 
                if (clientRequest.equals("What time is it?")) {
                    System.out.println("SERVER: sending packet with time information");
                    sendResponse(socket, addr, clientPort, new java.util.Date().toString().getBytes());
                    counter++;
                } else if (clientRequest.equals("How many requests have you handled?")) {
                    System.out.println("SERVER: sending packet with request count");
                    sendResponse(socket, addr, clientPort, (counter + "").getBytes());
                } else 
                    System.out.println("Server: unrecognized client request");
            } catch (IOException e) {
                // TODO: handle exception
                System.out.println("Error receiving client requests"); 
            }
        }
    }
    //Helper method to send a given response back to the client 
    private void sendResponse(DatagramSocket socket, InetAddress addr, int clientPort, byte[] response) {
        try {
            //Now to create a packet to contain the response and send it 
            DatagramPacket sendPacket = new DatagramPacket(response, response.length, addr, clientPort);
            
            socket.send(sendPacket);
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Error sending response to client");
        }
    }
    public static void main(String[] args) {
        DatagramPackets packetServer = new DatagramPackets();
        DatagramSocket datagramSocket = packetServer.goOnline(); 
        if (datagramSocket != null) {
            packetServer.handleRequests(datagramSocket);
    }
}
}
