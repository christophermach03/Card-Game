/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gametest1.gametest1.Peer;
import java.io.*;
import java.net.*;

/**
 *
 * @author rigaljoseph
 */
public class PeerThread extends Thread {
    private BufferedReader bufferedReader;

    public PeerThread(Socket socket) throws IOException {
        bufferedReader = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
    }

    public void run() {
        boolean flag = true;

        while (flag) {
            try {
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    System.out.println(line); 
            }
            } catch (Exception e) {
               interrupt();
            }
        }
    }
}

