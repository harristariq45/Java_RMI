import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server extends UnicastRemoteObject implements MyInterface
{
    public Server() throws RemoteException
    {
        super();

    }

    public static void startServer(Server server)
    {
        try
        {
            // MyInterface stub = (MyInterface) UnicastRemoteObject.exportObject(s, 0);
            Registry reg = LocateRegistry.createRegistry(1099);
            reg.rebind("hi_server",server);
            System.out.println("server is ready.....");
        } catch (RemoteException e) {
            System.out.println("exception" +e);
        }
    }


    public static void main (String args[]) throws RemoteException
    {
        Server server = new Server();
        startServer(server);
    }

    @Override
    public AuctionItem getSpec(int itemId, int clientId) throws RemoteException {

        return new AuctionItem(itemId);
    }
}
