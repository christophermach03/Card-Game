/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cop4331.game;
import java.io.*;
import java.net.*;

/**
 *Networking code goes here!
 * Will probably only really communicate with the game class.
 */
public class GameCoordinator {
    private int port;
    private ServerSocket serverSocket;
    
    public GameCoordinator(int port) throws IOException {
        this.port = port;
        this.serverSocket = new ServerSocket(port);
    }
    
    public void start() throws IOException{
        System.out.println("Peer started on port " + port);
        while(true){
            Socket clientSocket = serverSocket.accept();
            new Thread(new ClientHandler(clientSocket)).start();
        }
    }
    
    
    public void connectToPeer(String host, int port) {
    try (
        Socket socket = new Socket(host, port);
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(
            new InputStreamReader(socket.getInputStream())
        )
    ) {
        out.println("Hello from Peer on port " + port);
        String response = in.readLine();
        System.out.println("Received from another Peer: " + response);
        
    } catch (IOException e) {
        e.printStackTrace();
    }
    }
    
    public static void main(String[] args){
        if (args.length != 1){
            System.out.println("Usage: java Peer <port>");
            return;
        }
        int port = Integer.parseInt(args[0]);
        try{
            GameCoordinator peer = new GameCoordinator(port);
            peer.start();
            peer.connectToPeer("localhost", 1232);
        } catch (IOException e){
            e.printStackTrace();
        }
        
    }
}
