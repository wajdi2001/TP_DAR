package UDP_4_1;

import java.io.IOException;
import java.net.*;

public class Client {

    public static void main(String[] args) {


        try {
           DatagramSocket socket = new DatagramSocket();
           String msg ="Wajdi ben Moumen";
           byte[] bef = msg.getBytes();
            DatagramPacket packet =new DatagramPacket(bef,bef.length, InetAddress.getByName("localhost"),1234); 
            socket.send(packet);
            byte[] buf = new byte[1024];
            DatagramPacket packet1 = new DatagramPacket(buf, buf.length);
            socket.receive(packet1);
            String received  = new String(packet1.getData(),0, packet1.getLength());
            System.out.println(received);

        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
