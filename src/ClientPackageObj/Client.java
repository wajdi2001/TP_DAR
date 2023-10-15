package ClientPackageObj;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
        System.out.println("je suis un client");
        Socket s =new Socket("localhost",1234);
        System.out.println("je suis connecté au serveur");
        int nb;
            System.out.println("donner un nombre ");
        Scanner scanner =new Scanner(System.in);
        nb =scanner.nextInt();

            OutputStream os =s.getOutputStream();

        InputStream is =s.getInputStream();
        os.write(nb);
        int rep = is.read();
            System.out.println("le produit de "+nb+"*5="+rep);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
