package TP4.ACT4_3.clients;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try (DatagramSocket clientSocket = new DatagramSocket()) { // Crée un socket UDP pour le client.
            System.out.println("Client de chat UDP démarré.");

            InetAddress serverAddress = InetAddress.getByName("localhost"); // Adresse IP du serveur
            int serverPort = 1234; // Port de communication avec le serveur

            Scanner scanner = new Scanner(System.in);
            System.out.println("please enter your name!:"); // Invite l'utilisateur à saisir son nom.
            String username = scanner.nextLine(); // Nom d'utilisateur du client


            while (true) {
                System.out.println(" message:\t");
                String message = scanner.nextLine();

                // Prépare le message avec le nom d'utilisateur et le convertit en tableau de bytes.
                byte[] sendData = (username + ": " + message).getBytes();

                // Crée un DatagramPacket avec les données à envoyer, l'adresse du serveur et le port.
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, serverPort);

                // Envoie le packet au serveur.
                clientSocket.send(sendPacket);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Gestion basique des exceptions en imprimant la trace.
        }
    }
}
