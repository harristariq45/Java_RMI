import javax.crypto.SealedObject;
import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MyInterface extends Remote{

    public SealedObject getSpec(int itemId, SealedObject clientReq) throws IOException;
    //input would be all required elements of auction item  the server would add a unique auvtion id to identify this auction


    public AuctionItem createAuction(int clientId, int winnerId, int reservePrice, int currentBid, String itemTitle, String itemDescription) throws RemoteException;

    public int newBid(int itemId,int bidderID, int currentBid) throws RemoteException;

    int clientId(String clientName) throws RemoteException;
}//elements like winner id would not be set in the initial call but would be an element in auction item that can be set to 0  in the actual createAuction function

