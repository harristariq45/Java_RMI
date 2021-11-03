import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MyInterface extends Remote{
//    public int add(int itemIdNum, int clientIdNum) throws RemoteException ;
    public AuctionItem getSpec(int itemId, int clientId) throws RemoteException;
}
