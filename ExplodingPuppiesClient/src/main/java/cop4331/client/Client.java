/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cop4331.client;
import java.io.*;
import java.net.*;
/**
 *
 * @author rigaljoseph
 */

public class Client {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 1255);

        BufferedReader serverIn = 
            new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter serverOut = 
            new PrintWriter(socket.getOutputStream(), true);
        BufferedReader userIn = 
            new BufferedReader(new InputStreamReader(System.in));

        // Listen to server
        new Thread(() -> {
            try {
                String line;
                while ((line = serverIn.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException ignored) {}
        }).start();

        // Send messages to server
        while (true) {
            String msg = userIn.readLine(); //USER INPUT
            serverOut.println(msg);//send it to server
        }
    }
}
