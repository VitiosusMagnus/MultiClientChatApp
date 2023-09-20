package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatServer {
    private static final int SERVER_PORT = 9999;

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
            System.out.println("Server is running on port 9999");

            while (true){
                Socket clientSocket = serverSocket.accept();
                System.out.println("New user connected");
                ClientHandler clientHandler = new ClientHandler(clientSocket);
                ClientHandler.userList.add(clientHandler);

                //create a separate thread to handle each client.
                Thread clientThread = new Thread(clientHandler);
                clientThread.start();

                //removing disconnected user
                for (ClientHandler user: ClientHandler.userList){
                    if (user.isClosed()){
                        ClientHandler.userList.remove(user);
                    }
                }
                System.gc();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
