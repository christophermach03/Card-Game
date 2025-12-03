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
    
    public void run(){
        
    }
    
    public Set<ServerThreadThread> getServerThreadThreads(){return serverThreadThreads;}
}
