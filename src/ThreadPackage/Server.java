package ThreadPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {
    public static void main(String[] args) {
        new Server().start();
    }

    @Override
    public void run() {
        System.out.println("Je suis un serveur");
        try {
            ServerSocket ss = new ServerSocket(1234);
            System.out.println("J'attends un client");
            int index = 1;

            while (true) {
                Socket clientSocket = ss.accept();
                new ClientProcess(clientSocket, index).start();
                System.out.println("Le client est connecté");
                index++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

class ClientProcess extends Thread {
    private Socket socket;
    private int index;

    public ClientProcess(Socket socket, int index) {
        this.socket = socket;
        this.index = index;
    }

    @Override
    public void run() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);

            String clientMessage = br.readLine();
            System.out.println("Message du client " + index + ": " + clientMessage);

            // Réponse au client
            String response = "Bienvenue, vous êtes le client numéro " + index + ". Votre @IP: " + socket.getRemoteSocketAddress();
            pw.println(response);

            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
