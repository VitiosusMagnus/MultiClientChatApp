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

    protected static ArrayList<ClientHandler> userList = new ArrayList<>();




    public ClientHandler(Socket clientSocket){
        this.clientSocket = clientSocket;
        try {
            out = new PrintWriter(clientSocket.getOutputStream(),true);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String message;
            while ((message = reader.readLine()) != null){
                broadcastMessage(message);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void broadcastMessage(String message){
        for (ClientHandler user : userList) {
            user.out.println(message);
        }
    }
}
