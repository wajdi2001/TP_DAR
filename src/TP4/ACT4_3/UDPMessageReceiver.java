package TP4.ACT4_3;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPMessageReceiver implements Runnable {
    private DatagramSocket socket;

    public UDPMessageReceiver(DatagramSocket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

            while (true) {
                socket.receive(receivePacket);
                String message = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Message reçu: " + message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}