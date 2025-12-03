/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gametest1.gametest1.ClientServer;
import java.io.*;
import java.net.*;
/**
 *
 * @author rigaljoseph
 */

public class ClientHandler extends Thread {
    private Socket socket;
    private Server server;
    private PrintWriter out;
    private BufferedReader in;

    public ClientHandler(Socket socket, Server server) {
        this.socket = socket;
        this.server = server;
    }

    public void send(String msg) {
        out.println(msg);
    }

    @Override
    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            out.println("Welcome! Enter your username:");
            String username = in.readLine();
            server.broadcast(username + " joined the game!");

            String msg;
            while ((msg = in.readLine()) != null) {
                server.broadcast(username + ": " + msg);
            }

        } catch (IOException e) {
            System.out.println("Client disconnected");
        } finally {
            server.remove(this);
            try { socket.close(); } catch (IOException ignored) {}
        }
    }
}
