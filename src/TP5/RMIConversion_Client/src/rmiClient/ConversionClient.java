package rmiClient;

import rmiService.IConversion;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ConversionClient {
    public static void main(String[] args) {
        IConversion stub;

        {
            try {
                stub = (IConversion) Naming.lookup("rmi://localhost:1099/ConversionObject");
                double montantConv =stub.convertirMontant(15.3);
                System.out.println(montantConv);
            } catch (NotBoundException | MalformedURLException | RemoteException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
