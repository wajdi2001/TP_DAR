package TP1.ServerPackage;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        System.out.println("je suis un serveur");
        try {
            ServerSocket ss=new ServerSocket(1234);
            System.out.println("j'attend un client");
            Socket s=ss.accept();
            System.out.println("un client est connecté");
            OutputStream os =s.getOutputStream();
            InputStream is =s.getInputStream();
            int nb = is.read();
            System.out.println("j'ai reçu la valeur "+nb);
            int rep =nb*5;
            os.write(rep);



        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
