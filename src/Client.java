import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client
{
    public static void main(String[] args) throws RemoteException
    {
        Client one = new Client();
        one.connectRemote();
    }

    private void connectRemote() throws RemoteException
    {
        try
        {
            Registry reg = LocateRegistry.getRegistry("localhost",1099);
            MyInterface inf = (MyInterface) reg.lookup ("hi_server");
            AuctionItem auctionItem1 = inf.getSpec(10,20 );
            System.out.println("item ID is:" + auctionItem1.getItemId() + " \nitem Title is: " + auctionItem1.getItemTitle() );
        }
        catch (NotBoundException|RemoteException e)
        {
            System.out.println("Exception:"+e);
        }
    }
}
