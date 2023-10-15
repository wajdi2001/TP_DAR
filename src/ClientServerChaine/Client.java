package ClientServerChaine;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            System.out.println("Je suis un client");
            Socket s = new Socket("localhost", 1234);
            System.out.println("Je suis connecté au serveur");

            // Demandez à l'utilisateur de fournir une opération mathématique
            Scanner scanner = new Scanner(System.in);
            System.out.print("Entrez l'opération (ex. 34 * 55) : ");
            String operation = scanner.nextLine();

            // Envoyez l'opération au serveur
            OutputStream os = s.getOutputStream();
            PrintWriter pw = new PrintWriter(os, true);
            pw.println(operation);
            // os.write(operation.getBytes());

            // Recevez le résultat du serveur
            InputStream is = s.getInputStream();

            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            String result = in.readLine();
            System.out.println("Résultat : " + result);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
