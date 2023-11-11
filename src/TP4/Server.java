package TP4;

import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Server {
    public static void main(String[] args) {
        try {
            int serverPort = 1234; // Définit le port sur lequel le serveur écoute
            DatagramSocket serverSocket = new DatagramSocket(serverPort); // Crée une socket pour le serveur

            System.out.println("Le serveur est en attente de connexions..."); // Affiche un message indiquant que le serveur est en attente de connexions

            while (true) { // Boucle infinie pour gérer les connexions entrantes
                byte[] receiveData = new byte[1024]; // Crée un tableau de bytes pour stocker les données reçues
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length); // Crée un paquet pour recevoir des données
                serverSocket.receive(receivePacket); // Attend la réception de données depuis un client

                String clientMessage = new String(receivePacket.getData(), 0, receivePacket.getLength()); // Convertit les données reçues en une chaîne de caractères
                InetAddress clientAddress = receivePacket.getAddress(); // Obtient l'adresse IP du client
                int clientPort = receivePacket.getPort(); // Obtient le port du client

                System.out.println("Message reçu du client " + clientAddress + ":" + clientPort + ": " + clientMessage); // Affiche le message reçu du client, ainsi que l'adresse et le port du client

                // Réponse au client
                String welcomeMessage = "Bienvenu " + clientMessage; // Crée un message de réponse
                byte[] sendData = welcomeMessage.getBytes(); // Convertit le message de réponse en tableau de bytes
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort); // Crée un paquet pour envoyer la réponse au client
                serverSocket.send(sendPacket); // Envoie la réponse au client

                //-----------------------------------------------TP4-2-----------------------------------
                // Heure
                byte[] receiveHeure = new byte[1024]; // Crée un tableau de bytes pour stocker les données reçues
                DatagramPacket receivePacketHeure = new DatagramPacket(receiveHeure, receiveHeure.length); // Crée un paquet pour recevoir des données
                serverSocket.receive(receivePacketHeure); // Attend la réception de données depuis un client

                String clientMessageHeure = new String(receivePacketHeure.getData(), 0, receivePacketHeure.getLength());
                System.out.println(clientMessageHeure);
                String heureResponse ="Heure:  "+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                byte[] sendHeure= heureResponse.getBytes(); // Convertit le message de réponse en tableau de bytes
                DatagramPacket sendPacket1 = new DatagramPacket(sendHeure, sendHeure.length, clientAddress, clientPort); // Crée un paquet pour envoyer la réponse au client
                serverSocket.send(sendPacket1); // Envoie la réponse au client
            }
        } catch (Exception e) {
            e.printStackTrace(); // En cas d'erreur, affiche l'exception
        }
    }
}
