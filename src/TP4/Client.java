package TP4; // Le code est dans le package TP4

import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            // Crée une nouvelle socket UDP pour le client
            DatagramSocket clientSocket = new DatagramSocket();

            // Adresse du serveur
            InetAddress serverAddress = InetAddress.getByName("localhost"); // Ici, "localhost" signifie que le serveur est sur la même machine
            int serverPort = 1234; // Port du serveur

            // Utilisation d'un scanner pour permettre à l'utilisateur de saisir son prénom
            Scanner scanner = new Scanner(System.in);
            System.out.println("Donnez votre prénom :\n");
            String message = scanner.nextLine();
            byte[] sendData = message.getBytes(); // Convertit la saisie de l'utilisateur en tableau de bytes

            // Crée un paquet contenant les données à envoyer, l'adresse du serveur et le port
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, serverPort);

            // Envoie le paquet au serveur
            clientSocket.send(sendPacket);

            // Réception de la réponse du serveur
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket); // Attends la réponse du serveur

            // Convertit la réponse en une chaîne de caractères
            String serverResponse = new String(receivePacket.getData(), 0, receivePacket.getLength());

            // Affiche la réponse du serveur ainsi que l'adresse et le port du serveur
            System.out.println("Message du serveur: " + serverResponse);
            System.out.println("Adresse du serveur: " + receivePacket.getAddress() + ", Port du serveur: " + receivePacket.getPort());
            String heureMessage = "quelle heure est-il";
            byte[] sendheureMessage = heureMessage.getBytes();
            DatagramPacket sendPacketHeure = new DatagramPacket(sendheureMessage, sendheureMessage.length, serverAddress, serverPort);
            clientSocket.send(sendPacketHeure);
            // Réception de la réponse du serveur
            byte[] receiveHeure = new byte[1024];
            DatagramPacket receivePacket1 = new DatagramPacket(receiveHeure, receiveHeure.length);
            clientSocket.receive(receivePacket1); // Attends la réponse du serveur
            // Convertit la réponse en une chaîne de caractères
            String serverResponse1 = new String(receivePacket1.getData(), 0, receivePacket1.getLength());
            System.out.println(serverResponse1);
            // Ferme la socket du client
            clientSocket.close();
        } catch (Exception e) {
            e.printStackTrace(); // En cas d'erreur, affiche l'exception
        }
    }
}
