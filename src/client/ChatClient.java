package client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;


public class ChatClient {
        private ChatClientGUI gui;
        private Socket clientSocket;
        private PrintWriter out;
        private BufferedReader in;



    public ChatClient(ChatClientGUI gui){
        this.gui = gui;
    }

    public void start() {
        try {
            clientSocket = new Socket(InetAddress.getLocalHost(), 9999);
            out = new PrintWriter(clientSocket.getOutputStream(),true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            new Thread(()->{
               try {
                   String serverMessage;
                   while (clientSocket.isConnected()){
                       serverMessage = in.readLine();
                        gui.displayReceivedMessage(serverMessage);
                   }
               }catch (IOException e){
                   System.out.println("error in messageReceiverThread");
                   e.printStackTrace();
               }
            }).start();

            gui.sendButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String message = gui.textField.getText();
                    sendMessageToServer(message);
                    gui.textArea.append("You: " + message + "\n");
                    gui.textArea.setCaretPosition(gui.textArea.getDocument().getLength());//scrolling bottom
                    gui.textField.setText("");
                }
            });



        } catch (IOException e) {
            System.out.println("error in ChatClient start method");
            throw new RuntimeException(e);
        }
    }

    public  void sendMessageToServer(String message) {
        out.println(message);
    }


}
