package TP3;

import TP2.ClientServerobject.Operation;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("192.168.248.1", 1234);
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
            OutputStream os = socket.getOutputStream();

            // Obtient un flux d'entrée à partir de la socket pour recevoir des données
            InputStream is = socket.getInputStream();
            // Envoyer un message au serveur
            pw.println("Bonjour je suis un client dans ce serveur");

            // Lire la réponse du serveur
            String serverResponse = br.readLine();
            System.out.println(serverResponse);
            int nb1;
            int nb2;
            String op;
            double res = 0;

            // Crée un objet Scanner pour lire l'entrée utilisateur depuis la console
            Scanner scanner = new Scanner(System.in);

            // Demande à l'utilisateur de saisir le premier nombre
            System.out.print("Donnez un nombre1 : ");
            nb1 = scanner.nextInt();

            // Demande à l'utilisateur de saisir le deuxième nombre
            System.out.print("Donnez un nombre2 : ");
            nb2 = scanner.nextInt();

            // Capture la ligne vide après les nombres
            scanner.nextLine();

            // Demande à l'utilisateur de saisir l'opérateur (+, -, *, /)
            System.out.print("Donnez un opérateur (+, -, *, /) : ");
            op = scanner.nextLine();

            // Crée un objet d'opération avec les valeurs saisies
            TP2.ClientServerobject.Operation op1 = new Operation(nb1, nb2, op);
            ObjectOutputStream oos = new ObjectOutputStream(os);
            ObjectInputStream ois = new ObjectInputStream(is);

            // Envoie l'objet d'opération au serveur
            oos.writeObject(op1);

            // Le serveur renvoie un résultat sous forme de double
            Operation op2= (Operation) ois.readObject();

            // Affiche le résultat à l'utilisateur
            System.out.println("Résultat : " + op2.getRes());

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
