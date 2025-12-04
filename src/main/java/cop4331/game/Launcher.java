/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cop4331.game;
import java.io.IOException;
/**
 *
 * @author chris
 */
public class Launcher {

    public static void main(String[] args) {
        try {
            String jarPath = getJarPath();

            // Start the server in a new terminal window
            startInNewTerminal("Server", jarPath, "gametest1.gametest1.Server");

            // Small delay so server can bind port before clients connect
            Thread.sleep(1000);

            // Start two clients in new terminal windows
            startInNewTerminal("Client 1", jarPath, "gametest1.gametest1.Client");
            startInNewTerminal("Client 2", jarPath, "gametest1.gametest1.Client");

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static String getJarPath() {
        // Path to the current JAR
        return System.getProperty("java.class.path");
    }

    /**
     * Opens a new terminal window and runs a Java class from the JAR
     */
    private static void startInNewTerminal(String title, String jarPath, String mainClass) throws IOException {
        // Command for Windows cmd
        String cmd = String.format(
                "cmd /c start \"%s\" java -cp \"%s\" %s",
                title, jarPath, mainClass
        );

        Runtime.getRuntime().exec(cmd);
    }
}
