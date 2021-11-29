import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;

public interface MyInterface extends Remote {

    public HashMap<Integer, AuctionItem> createAuction(String name, String Email, int clientId, int highestBidderID, int reservePrice, int currentBid, String itemTitle, String itemDescription) throws RemoteException;

    public void removeAuction(int auctionID, int clientId) throws RemoteException;

    public HashMap<Integer, AuctionItem> newBid(String name, String Email, int auctionID, int bidderID, int currentBid) throws RemoteException;

    public HashMap<Integer, AuctionItem> getHashMapAuctionList() throws RemoteException;

}