package TP4.ACT4_3.clients;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Client1
{
    public static void main(String[] args) {
        try (DatagramSocket clientSocket = new DatagramSocket()) {
            System.out.println("Client de chat UDP démarré.");

            InetAddress serverAddress = InetAddress.getByName("localhost");
            int serverPort = 1234;
            Scanner scanner =new Scanner(System.in);
            System.out.println("please enter your name!:");
            String username = scanner.nextLine(); // Nom d'utilisateur du client

            while (true) {
                System.out.println(" message:\t");
                String message = scanner.nextLine();
                byte[] sendData = (username + ": " + message).getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, serverPort);
                clientSocket.send(sendPacket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


