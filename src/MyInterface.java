import javax.crypto.SealedObject;
import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MyInterface extends Remote{
//    public int add(int itemIdNum, int clientIdNum) throws RemoteException ;
   ///// public AuctionItem getSpec(int itemId, int clientId) throws RemoteException;

    public SealedObject getSpec(int itemId, SealedObject clientReq) throws IOException;

}

// key generator
//write the key generator to a file
// read that key from client and the server
// read using "secret key"