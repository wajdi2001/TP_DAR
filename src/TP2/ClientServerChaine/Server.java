package TP2.ClientServerChaine;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        System.out.println("Je suis un serveur");
        try {
            ServerSocket ss = new ServerSocket(1234);
            System.out.println("J'attends un client");
            Socket s = ss.accept();
            System.out.println("Un client est connecté");

            // Recevez l'opération du client
            InputStream is = s.getInputStream();
            InputStreamReader isr= new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String operation= br.readLine();
            System.out.println("J'ai reçu l'opération : " + operation);

            String[] parts = operation.split(" ");
            int operand1 = Integer.parseInt(parts[0]);
            String operator = parts[1];
            int operand2 = Integer.parseInt(parts[2]);
            int result = 0;

            switch (operator) {
                case "+":
                    result = operand1 + operand2;
                    break;
                case "-":
                    result = operand1 - operand2;
                    break;
                case "*":
                    result = operand1 * operand2;
                    break;
                case "/":
                    if (operand2 != 0) {
                        result = operand1 / operand2;
                    } else {
                        result = -1; // Indiquer une division par zéro
                    }
                    break;
                default:
                    System.out.println("Opérateur non pris en charge");
            }

            PrintWriter out = new PrintWriter(s.getOutputStream(), true);
            out.println(result);

            s.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
