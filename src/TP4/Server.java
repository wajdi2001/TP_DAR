package TP4;

import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;

public class Server {
    public static void main(String[] args) {
        try {
            int serverPort = 1234;
            DatagramSocket serverSocket = new DatagramSocket(serverPort);

            System.out.println("Le serveur est en attente de connexions...");

            while (true) {
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);

                String clientMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();

                System.out.println("Message reçu du client " + clientAddress + ":" + clientPort + ": " + clientMessage);

                // Réponse au client
                String welcomeMessage = "Bienvenu " + clientMessage;
                byte[] sendData = welcomeMessage.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
                serverSocket.send(sendPacket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
