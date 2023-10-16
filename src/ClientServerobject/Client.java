package ClientServerobject;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            System.out.println("Je suis un client");

            // Établissement d'une connexion avec un serveur local sur le port 1234
            Socket s = new Socket("localhost", 1234);
            System.out.println("Je suis connecté au serveur");

            // Déclaration de variables pour les nombres, l'opérateur et le résultat
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
            Operation op1 = new Operation(nb1, nb2, op);

            // Obtient un flux de sortie à partir de la socket pour envoyer des données
            OutputStream os = s.getOutputStream();

            // Obtient un flux d'entrée à partir de la socket pour recevoir des données
            InputStream is = s.getInputStream();

            // Crée des objets pour sérialiser (envoyer) et désérialiser (recevoir) des objets
            ObjectOutputStream oos = new ObjectOutputStream(os);
            ObjectInputStream ois = new ObjectInputStream(is);

            // Envoie l'objet d'opération au serveur
            oos.writeObject(op1);

            // Le serveur renvoie un résultat sous forme de double
            Operation op2= (Operation) ois.readObject();

            // Affiche le résultat à l'utilisateur
            System.out.println("Résultat : " + op2.getRes());

            // Ferme la connexion avec le serveur
            s.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
