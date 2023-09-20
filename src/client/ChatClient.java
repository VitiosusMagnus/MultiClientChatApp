package client;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;


public class ChatClient {

        private Socket clientSocket;
        private OutputStream out;
        private BufferedReader in;



    public ChatClient(){

    }

    public void start() {
        try {
            clientSocket = new Socket(InetAddress.getLocalHost(), 9999);
            out = clientSocket.getOutputStream();

            Thread messageReceiverThread = new Thread(()->{
               try {
                   in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                   String serverMessage;
                   while ((serverMessage = in.readLine()) != null){
                        //TODO:
                   }
               }catch (IOException e){
                   System.out.println("error in messageReceiverThread");
                   e.printStackTrace();
               }
            });
            messageReceiverThread.start();

            // Send message to server
            sendMessageToServer("Hello server!");

        } catch (IOException e) {
            System.out.println("error in ChatClient start method");
            throw new RuntimeException(e);
        }
    }

    public  void sendMessageToServer(String message) throws IOException {
        byte[] messageBytes = message.getBytes();
        out.write(messageBytes);
        out.flush();
    }
}
