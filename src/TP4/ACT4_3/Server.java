package TP4.ACT4_3;

import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.util.HashSet;

public class Server {
    private static final int SERVER_PORT = 1234;
    private static HashSet<InetAddress> clientAddresses = new HashSet<>();
    private static int clientPort = 9876;

    public static void main(String[] args) {
        try (DatagramSocket serverSocket = new DatagramSocket(SERVER_PORT)) {
            System.out.println("Serveur de chat UDP démarré sur le port " + SERVER_PORT);

            while (true) {
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);

                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();
                clientAddresses.add(clientAddress);

                String message = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Message reçu de " + clientAddress + ":" + clientPort + ": " + message);

                broadcastMessage(message, serverSocket, clientAddress);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void broadcastMessage(String message, DatagramSocket serverSocket, InetAddress senderAddress) {
        try {
            byte[] sendData = message.getBytes();
            for (InetAddress address : clientAddresses) {
                if (!address.equals(senderAddress)) {
                    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, address, clientPort);
                    serverSocket.send(sendPacket);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}