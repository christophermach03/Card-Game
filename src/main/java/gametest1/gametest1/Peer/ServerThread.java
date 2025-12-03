/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gametest1.gametest1.Peer;
import java.io.*;
import java.net.*;
import java.util.*;

/**
 *
 * @author rigaljoseph
 */
public class ServerThread extends Thread{
    private ServerSocket serverSocket;
    private Set<ServerThreadThread> serverThreadThreads = new HashSet<ServerThreadThread>();
    public ServerThread(String portNumb) throws IOException {
    serverSocket = new ServerSocket (Integer.valueOf(portNumb));
    }
    
    @Override
    public void run() {
        try {
            while (true) {
                ServerThreadThread serverThreadThread =
                        new ServerThreadThread(serverSocket.accept(), this);
                serverThreadThreads.add(serverThreadThread);
                serverThreadThread.start();
            }
        } catch (Exception e) { 
            e.printStackTrace(); 
        }
    }

    void sendMessage(String message) {
        try {
            serverThreadThreads.forEach(t -> t.getPrintWriter().println(message));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public Set<ServerThreadThread> getServerThreadThreads(){return serverThreadThreads;}
}
