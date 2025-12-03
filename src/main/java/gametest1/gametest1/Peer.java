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
public class Peer implements Runnable {

    private final Socket socket;
    private final NetworkManager manager;
    private BufferedReader in;
    private PrintWriter out;

    public Peer(Socket socket, NetworkManager manager) {
        this.socket = socket;
        this.manager = manager;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void send(String msg) {
        out.println(msg);
    }

    @Override
    public void run() {
        try {
            String line;
            while ((line = in.readLine()) != null) {
                manager.handleMessage(line, this);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
