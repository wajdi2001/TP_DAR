package ThreadPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 1234);
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);

            // Envoyer un message au serveur
            pw.println("Bonjour je suis un client dans ce serveur");

            // Lire la réponse du serveur
            String serverResponse = br.readLine();
            System.out.println(serverResponse);

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
