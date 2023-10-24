package TP4;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Server  {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket(1234);
            byte[] buf = new byte[1024];
            while(true){
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);
            String received  = new String(packet.getData(),0, packet.getLength());
            String msg ="Bonjour"+received;
            byte[] bef1 = msg.getBytes();
            DatagramPacket envoie =new DatagramPacket(bef1,bef1.length, InetAddress.getByName("localhost"),1234);
            socket.send(envoie);
            }



        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
