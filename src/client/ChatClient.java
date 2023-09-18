package client;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;


public class ChatClient {

    private ChatClientGUI gui;

    public ChatClient(ChatClientGUI gui){
        this.gui = gui;
    }

    public void start() {
        try {
            Socket clientSocket = new Socket(InetAddress.getLocalHost(), 9999);
            OutputStream outputStream = clientSocket.getOutputStream();

            Thread messageReceiverThread = new Thread(()->{
               try {
                   BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                   String serverMessage;
                   while ((serverMessage = reader.readLine()) != null){
                       gui.displayReceivedMessage(serverMessage);
                   }
               }catch (IOException e){
                   e.printStackTrace();
               }
            });
            messageReceiverThread.start();

            // Send message to server
            sendMessageToServer("Hello server!", outputStream);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void sendMessageToServer(String message, OutputStream outputStream) throws IOException {
        byte[] messageBytes = message.getBytes();
        outputStream.write(messageBytes);
        outputStream.flush();
    }
}
