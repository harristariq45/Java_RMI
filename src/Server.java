import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Scanner;

/**
 *this is the server class which is where the server is initialised.
 * also contains alot of the important methods like
 * create auction, remove auctions and new bid.
 * these methods are very important for the whole program to work.
 * @author Harris Bin Tariq
 * @version 1.0
 */
public class Server extends UnicastRemoteObject implements MyInterface {
    public static int clientId;
    HashMap<Integer, AuctionItem> auctionItemHashMap = new HashMap<Integer, AuctionItem>(); // Hashmap is initialised here which stores the list of all the auction items.
    private static String itemDescription;// description of the item
    private static String itemTitle; // title of the item

    public Server() throws RemoteException {
        super();
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in); // scanner initialised for the user inputs, was used for checking the server class.

        Server server = null;
        try {
            server = new Server();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        startServer(server);

        /**
         * this while loop was used to check if the hashmap was working
         * if user enters 1 it prints the list
         * if user enters anything else while loop is exited.
         */

        int usercmd = 1;
        while (usercmd == 1) {
            System.out.println("Please enter 1 to print Auction list and press 2 to exit");
            usercmd = scanner.nextInt();

            if (usercmd == 1) {
                assert server != null;
                System.out.println(server.auctionItemHashMap);
            }
        }
    }

    public static void startServer(Server server) {
        try {
            Registry reg = LocateRegistry.createRegistry(1099);// here the registry is created, any code can be used but i used 1099
            reg.rebind("hi_server", server);//
            System.out.println("server is ready.....");
        } catch (RemoteException e) {
            System.out.println("exception" + e);
        }
    }

    /**
     * this is the method used to create auctions.
     * this method is also declared in the interface
     * this creates the item and adds it to the hashmap
     * hashmap saves the index which is used as the unique auction id
     * secondly it saves the auction item with all of its details.
     * @param name name of the highest bidder
     * @param Email email of the highest bidder
     * @param clientId sellerId which is unique and is generated in the seller class
     * @param highestBidderID this is the highest bidder id
     * @param reservePrice this is the resreve price which is set by the seller in seller class
     * @param currentBid this is current highest bid
     * @param itemTitle this is the item title
     * @param itemDescription this is the one work item description
     * @return this method returns the auction item hashmap
     */
    @Override
    public synchronized HashMap<Integer, AuctionItem> createAuction(String name, String Email,int clientId,int highestBidderID, int reservePrice, int currentBid, String itemTitle, String itemDescription) {

        Server.clientId = clientId;
        Server.itemTitle = itemTitle;
        Server.itemDescription = itemDescription;
        AuctionItem aucItem = new AuctionItem(name,Email,clientId,highestBidderID, reservePrice, currentBid, itemTitle, itemDescription);

        auctionItemHashMap.put(auctionItemHashMap.size() + 1, aucItem);
        return auctionItemHashMap;
    }

    /**
     * this method is used to remove auctions from hashmap
     * @param auctionID this is the auction id that user will enter which needs removing
     * @param clientId this is the clientID which is automatically added and is used as a security feature so that the client can only remove it's own listings
     */
    @Override
    public synchronized void removeAuction(int auctionID, int clientId) {

        for (Integer Index : auctionItemHashMap.keySet()) {
            if (auctionID == Index) {
                AuctionItem item = auctionItemHashMap.get(Index);

                if (item.getClientId() == clientId){
                    auctionItemHashMap.remove(auctionID);
                }
            }
        }
    }

    /**
     * this method is used to add new bid by the buyer class
     * @param name this is the name of the bidder
     * @param Email this is the email of the bidder
     * @param auctionID this is teh auction id that the bidder is submitting the bid for
     * @param bidderID this is the unique bidder id
     * @param currentBid this is the highest current bid.
     */
    @Override
    public synchronized HashMap<Integer, AuctionItem> newBid(String name, String Email,int auctionID, int bidderID, int currentBid) {

        for (Integer Index : auctionItemHashMap.keySet()) {
            if (auctionID == Index) {
                AuctionItem item = auctionItemHashMap.get(Index);

                if (item.getCurrentBid() < currentBid)
                    item.setCurrentBid(currentBid);
                    item.setHighestBidderID(bidderID);
                    item.setItemEmail(Email);
                    item.setItemName(name);
            }
        }
        return auctionItemHashMap;
    }

    /**
     * this method returns the auction items hashmap.
     * @return
     */
    @Override
    public HashMap<Integer, AuctionItem> getHashMapAuctionList() {
        return auctionItemHashMap;
    }

}

//    public static SecretKey readFile() throws FileNotFoundException {
//        ByteArrayOutputStream baos = null;
//        InputStream fis = null;
//        try {
//            byte[] buffer = new byte[4096];
//            baos = new ByteArrayOutputStream();
//            fis = new FileInputStream("L:\\Year3\\SCC311\\src\\secretKey.txt");
//            int read = 0;
//            while ((read = fis.read(buffer)) != -1) {
//                baos.write(buffer, 0, read);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//            System.out.println(" error0");
//        } finally {
//            try {
//                if (baos != null)
//                    baos.close();
//            } catch (IOException e) {
//                System.out.println(" error1");
//            }
//
//            try {
//                if (fis != null)
//                    fis.close();
//            } catch (IOException e) {
//                System.out.println(" error2");
//            }
//        }
//        return (SecretKey) new SecretKeySpec(baos.toByteArray(), "AES");
//    }


//    public static void keyGenerator() throws RemoteException {
//
//        KeyGenerator keyGen = null;
//
//        try {
//            keyGen = KeyGenerator.getInstance("AES");
//            keyGen.init(128);
//           SecretKey secretKey = keyGen.generateKey();
//            Path path = Paths.get("L:\\Year3\\SCC311\\src\\secretKey.txt");
//            try {
//                FileOutputStream fs = new FileOutputStream("L:\\Year3\\SCC311\\src\\secretKey.txt");
//                byte[] secretKeyEncoded = secretKey.getEncoded();
//                //System.out.println(secretKeyEncoded +" 1 ");
//                fs.write(secretKeyEncoded);
//                fs.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        catch (Exception e){
//            System.out.println("error inside keygen");
//            e.printStackTrace();
//        }
//
//    }

//    @Override
//    public AuctionItem getSpec(int clientId, int clientId) throws RemoteException {
//
//        return new AuctionItem(clientId);
//    }


//    public static int rndRange (int start, int finish){
//
//        return new Random().nextInt(finish + 1 - start) + start;
//    }

//@Override
//    public SealedObject getSpec(int itemId, SealedObject clientReq) throws IOException {
//        return null;
//    }