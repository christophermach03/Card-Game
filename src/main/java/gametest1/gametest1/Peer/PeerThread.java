/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gametest1.gametest1.Peer;
import java.io.*;
import java.net.*;
import javax.json.*;

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
                JsonObject jsonObject = Json.createReader(bufferedReader).readObject();

                if (jsonObject.containsKey("username")) {
                    System.out.println("[" + jsonObject.getString("username") + "]: "
                            + jsonObject.getString("message"));
                }

            } catch (Exception e) {
                flag = false;
                interrupt();
            }
        }
    }
}

