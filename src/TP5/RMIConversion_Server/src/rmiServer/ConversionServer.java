package rmiServer;

import rmiService.ConversionImpl;
import rmiService.IConversion;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class ConversionServer   {

    public static void main(String[] args) {

        try {
            LocateRegistry.createRegistry(1099);
            ConversionImpl od = new ConversionImpl();
            System.out.println(od.toString());
            Naming.rebind("rmi://localhost:1099/ConversionObject",od);
        } catch (RemoteException | MalformedURLException e) {
            throw new RuntimeException(e);
        }


    }
}
