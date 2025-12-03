/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gametest1.gametest1.ClientServer;
import java.io.*;
import java.net.*;
import java.util.*;
/**
 *
 * @author rigaljoseph
 */

public class Server {
    private ServerSocket serverSocket;
    private Set<ClientHandler> clients = new HashSet<>();

    public Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        System.out.println("Server started on port " + port);
    }

    public void start() {
        try {
            while (true) {
                Socket socket = serverSocket.accept();
                ClientHandler handler = new ClientHandler(socket, this);
                clients.add(handler);
                handler.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void broadcast(String msg) {
        for (ClientHandler client : clients) {
            client.send(msg);
        }
    }

    public synchronized void remove(ClientHandler handler) {
        clients.remove(handler);
    }

    public static void main(String[] args) throws Exception {
        Server server = new Server(1255);
        server.start();
    }
}
