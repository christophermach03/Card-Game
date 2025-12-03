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
public class Peer {
    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("> enter username & port # for this peer:");
        String[] setupValues = bufferedReader.readLine().split(" ");
        ServerThread serverThread = new ServerThread(setupValues[1]);
        serverThread.start();
        new Peer().updateListenToPeers(bufferedReader,setupValues[0],serverThread);
    }
    public void updateListenToPeers(BufferedReader bufferedReader,
                                    String username,
                                    ServerThread serverThread) throws Exception {

        System.out.println("> enter (space separated) hostname:port#");
        System.out.println("peers to receive messages from (s to skip):");

        String input = bufferedReader.readLine();
        String[] inputValues = input.split(" ");

        if (!input.equals("s"))
            for (int i = 0; i < inputValues.length; i++) {
                String[] address = inputValues[i].split(":");
                Socket socket = null;
                try {
                    socket = new Socket(address[0], Integer.valueOf(address[1]));
                    new PeerThread(socket).start();
                } catch (Exception e) {
                    if (socket != null) socket.close();
                    else System.out.println("invalid input. skipping.");
                }
            }
        communicate(bufferedReader, username, serverThread);
    }

    public void communicate(BufferedReader bufferedReader, String username, ServerThread serverThread) {
        try {
            System.out.println("> you can now communicate (e to exit, c to change)");
            boolean flag = true;
            while (flag) {
                String message = bufferedReader.readLine();
                if (message.equals("e")) {
                    flag = false;
                    break;
                } else if (message.equals("c")) {
                    updateListenToPeers(bufferedReader, username, serverThread);
                } else {
                    serverThread.sendMessage(username + ": " + message);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
