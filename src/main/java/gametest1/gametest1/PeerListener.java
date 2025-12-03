/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gametest1.gametest1;
import java.io.*;
import java.net.*;

/**
 *
 * @author chris
 */
public class PeerListener implements Runnable {

    private Socket socket;
    private PeerNetworkManager manager;

    public PeerListener(Socket socket, PeerNetworkManager manager) {
        this.socket = socket;
        this.manager = manager;
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));

            String line;
            while ((line = in.readLine()) != null) {
                manager.handleMessage(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
