package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable{
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private String nickname;

    protected static ArrayList<ClientHandler> userList = new ArrayList<>();




    public ClientHandler(Socket clientSocket){
        this.clientSocket = clientSocket;
        try {
            out = new PrintWriter(clientSocket.getOutputStream(),true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            //setting nickname
            out.println("Write nickname");
            nickname = in.readLine();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        try {
            String message;
            while (clientSocket.isConnected()){
                message = in.readLine();
                broadcastMessage(message);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void broadcastMessage(String message){
        for (ClientHandler user : userList) {
            if (user != this){
                user.out.println(nickname + ": " + message);
            }
        }
    }
}
