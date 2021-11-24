import javax.crypto.KeyGenerator;
import javax.crypto.SealedObject;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Random;

public class Server extends UnicastRemoteObject implements MyInterface
{
    HashMap<Integer, AuctionItem> auctionItemHashMap = new HashMap<Integer, AuctionItem>();
    HashMap<Integer, String> clientList = new HashMap<Integer, String>();
    private int clientId;
    private int bidderID;
    private int reservePrice;
    private int currentBid;
    private static String itemDescription;
    private static String itemTitle;
    private int winnerID;
    private String name;
    private String email;

    public Server() throws RemoteException
    {
        super();

    }

    public static void main (String args[]) throws RemoteException, NoSuchAlgorithmException, FileNotFoundException {
        Server server = new Server();
        startServer(server);
        keyGenerator();
        readFile();
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

    public static void keyGenerator() throws RemoteException {

        KeyGenerator keyGen = null;

        try {
            keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(128);
           SecretKey secretKey = keyGen.generateKey();
            Path path = Paths.get("L:\\Year3\\SCC311\\src\\secretKey.txt");
            try {
                FileOutputStream fs = new FileOutputStream("L:\\Year3\\SCC311\\src\\secretKey.txt");
                byte[] secretKeyEncoded = secretKey.getEncoded();
                //System.out.println(secretKeyEncoded +" 1 ");
                fs.write(secretKeyEncoded);
                fs.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        catch (Exception e){
            System.out.println("error inside keygen");
            e.printStackTrace();
        }

    }

//    @Override
//    public AuctionItem getSpec(int clientId, int clientId) throws RemoteException {
//
//        return new AuctionItem(clientId);
//    }

    public SealedObject getSpec(int clientId, SealedObject clientReq) throws IOException {
        return null;
    }

    @Override

    public AuctionItem createAuction(int clientId, int winnerID, int reservePrice, int currentBid, String itemTitle, String itemDescription) {
       //set item id on server so you can check for duplicates and ensure it is unique
        //pass client id instead of item id for this function, this will allow you to return the integer of the auction while using 'itemid'  which would now be clientid as a way to complete the constructor for auction item
        this.clientId = clientId;
        this.winnerID = winnerID;
        this.reservePrice = reservePrice;
        this.currentBid = currentBid;
        Server.itemTitle = itemTitle;
        Server.itemDescription = itemDescription;
        //the item id will be used as the key and as an ekement in the auction item, the itemid must be unique.
        AuctionItem aucItem = new AuctionItem(clientId,clientId,winnerID,reservePrice,currentBid,itemTitle,itemDescription);

        // return hashmap id = the auctions id
        return auctionItemHashMap.put(clientId,aucItem);
    }

    public int clientId(String name) {
        this.name = name;

        int randomNum = rndRange(1,10000000);
        clientList.put(randomNum, name);
        return randomNum;
    }

    public static int rndRange (int start, int finish){

        new Random().nextInt(finish + 1 - start);
        return start;
    }


    @Override
    public int newBid(int clientId, int bidderID, int currentBid) {
        this.clientId = clientId;
        this.bidderID = bidderID;
        this.currentBid = currentBid;
        return  0;
    }


    public static SecretKey readFile() throws FileNotFoundException {
        ByteArrayOutputStream baos = null;
        InputStream fis = null;
        try {
            byte[] buffer = new byte[4096];
            baos = new ByteArrayOutputStream();
            fis = new FileInputStream("L:\\Year3\\SCC311\\src\\secretKey.txt");
            int read = 0;
            while ((read = fis.read(buffer)) != -1) {
                baos.write(buffer, 0, read);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(" error0");
        } finally {
            try {
                if (baos != null)
                    baos.close();
            } catch (IOException e) {
                System.out.println(" error1");
            }

            try {
                if (fis != null)
                    fis.close();
            } catch (IOException e) {
                System.out.println(" error2");
            }
        }

        return (SecretKey) new SecretKeySpec(baos.toByteArray(), "AES");
    }

    public int getWinnerID() {
        return winnerID;
    }

    public void setWinnerID(int winnerID) {
        this.winnerID = winnerID;
    }

    public int getCurrentBid() {
        return currentBid;
    }

    public void setCurrentBid(int currentBid) {
        this.currentBid = currentBid;
    }

    public int getReservePrice() {
        return reservePrice;
    }

    public void setReservePrice(int reservePrice) {
        this.reservePrice = reservePrice;
    }

    public int getItemId() {
        return clientId;
    }

    public void setItemId(int clientId) {
        this.clientId = clientId;
    }

    public int getBidderID() {
        return bidderID;
    }

    public void setBidderID(int bidderID) {
        this.bidderID = bidderID;
    }

    public static String getItemTitle() {
    return itemTitle;
}

    public static void setItemTitle(String itemTitle) {
        Server.itemTitle = itemTitle;
    }

    public static String getItemDescription() {
        return itemDescription;
    }

    public static void setItemDescription(String itemDescription) {
        Server.itemDescription = itemDescription;
    }
}
