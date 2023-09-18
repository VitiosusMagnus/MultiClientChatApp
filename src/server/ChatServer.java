package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatServer {
    private static final int SERVER_PORT = 9999;
    protected static List<Socket> clientSockets = new ArrayList<>();
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
            System.out.println("Server is running on port 9999");

            while (true){
                Socket clientSocket = serverSocket.accept();
                clientSockets.add(clientSocket);

                //create a separate thread to handle each client.
                Thread clientThread = new Thread(new ClientHandler(clientSocket));
                clientThread.start();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
