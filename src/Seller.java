import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.Scanner;

public class Seller
{
    private static MyInterface inf; // initialises interface

    /** this is the main function of the class
     *
     */

    public static void main(String[] args)
    {

        Seller one = new Seller();
        //Server server1= new Server();
        Scanner scanner = new Scanner(System.in);

        /*
         * this is where the seller is connected with the Server
         */

        try
        {
            Registry reg = LocateRegistry.getRegistry("localhost",1099);
            inf = (MyInterface) reg.lookup ("hi_server");
            System.out.println("press 1 if you have a unique clientID, 2 if you do not have a client ID?");
            int ans = scanner.nextInt();

            int clientId;
            if(ans == 1){
                System.out.println("Please enter unique clientID");
                clientId = scanner.nextInt();
            }
            else{
                clientId = clientId();// id is generated through BidderID method which generates a random number
            }

            System.out.println("clientId is: " + clientId);

            /*
             * the program stays in the while loop if the user enters with 1 or 2 or 3, if any other number is entered while loop s exited and seller is closed.
             */

            int userCmd = 1;
            while (userCmd == 1 || userCmd ==2 || userCmd==3)
            {
                System.out.println("Press 1 to display auction list \n 2 to create new auction \n 3 to remove an auction \n 0 to exit  ");
                userCmd = scanner.nextInt();

                if(userCmd==1) {

                    HashMap<Integer, AuctionItem> auctionItemHashMap = new HashMap<Integer, AuctionItem>();
                    auctionItemHashMap = inf.getHashMapAuctionList();

                    /*
                      this code underneath is used to print the auction hashmap in a presentable form by using a for loop and getter methods
                      while the print statement gives it a shape
                     */

                    System.out.println("| AuctionID  ||     ClientId    ||    Highest Bidder     ||    Reserve price  ||  Current Bid  ||      Item Title    ||    Item description |");
                    for (Integer Index: auctionItemHashMap.keySet())
                    {
                        AuctionItem item = auctionItemHashMap.get(Index);

                        System.out.println("|" +Index+ "       ||        "+ item.getClientId()+ "        ||       "+ item.getHighestBidderID()+ "        ||       "+ item.getReservePrice()+ "          ||       "+ item.getCurrentBid()+ "      ||       "+ item.getItemTitle()+ "       ||         "+item.getItemDescription()+"    |");
                    }
                }

                /*
                to create a listing user enters 2
                this code then asks for reserve price, item description and item title
                 */

                if(userCmd==2){
                    System.out.println("To create listing ");

                    System.out.println("Please enter reserve price");
                    int reservePrice = scanner.nextInt();// reserve price is entered here, which if exceeded by the current bid will result n a winner or else no winner.

                    System.out.println("Please enter itemTitle");
                    String itemTitle = scanner.next();// item title is entered here

                    System.out.println("Please enter itemDescription");
                    String itemDescription = scanner.next();// item description is entered here

                    /*
                    create auction method which is from the interface and the method is initialised in the server
                    name, email, the highest bidder is kept empty which is added in the buyer class and the details are for the highest bidder
                    current id is also 0 because no one has bid yet
                    client ID automatically added this is like a security feature since this will....
                    .... prevent clients from deleting or creating auctions in anyone else's name but theirs
                     */
                    HashMap<Integer, AuctionItem> auctionItemHashMap = inf.createAuction("none","none", clientId, 0 , reservePrice, 0, itemTitle, itemDescription);
                }

                /*
                 *this code underneath is used to remove auction item from the auction list which is a hashmap in server
                 * this also announces the winner, with its details, of the auction closed if the current bid is higher than the reserve price
                 * if reserveprice not met then it will say reserve price not met.
                 */

                if(userCmd==3){

                    System.out.println("Please enter auctionId that you want to remove");
                    int auctionID = scanner.nextInt();

                    HashMap<Integer, AuctionItem> auctionItemHashMap = inf.getHashMapAuctionList();
                    AuctionItem item = auctionItemHashMap.get(auctionID);

                    inf.removeAuction(auctionID, clientId);

                    if(item.getReservePrice()<=item.getCurrentBid()) {
                        System.out.println("Highest bidder's name, Email and UniqueID is:  " + item.getHighestBidderName()+" , " + item.getHighestBidderEmail()+ " . ");
                    }
                    else{
                        System.out.println("Reserve price not met therefore no winner");
                    }
                }
            }
        }
        catch (NotBoundException|RemoteException e)
        {
            System.out.println("Exception:"+e);
        }
    }

    /**
     * this returns a random integer which is used to assign a unique ID.
     * @return an int is returned which is a random integer
     */
    public static int clientId() {
        return (int)Math.floor(Math.random()*(100000000-100000+1)+100000);
    }
}

//System.out.println(baos);
//baos.toByteArray();
//System.out.println(baos);

//byte[] secretKeyEncoded = secretKey.getEncoded();

// Cipher cipher = Cipher.getInstance("AES");

//cipher.init(Cipher.ENCRYPT_MODE, decodedKey);
//javax.crypto.SealedObject test = new SealedObject(client1,cipher);
//SealedObject auctionItem1 = inf.getSpec(itemId, test);

//AuctionItem idForMyAuction = inf.createAuction(itemId,winnerID,reservePrice,currentBid,itemTitle, itemDescription);

//System.out.println(idForMyAuction);
//cipher.init(Cipher.DECRYPT_MODE, decodedKey);
//AuctionItem A = (AuctionItem)auctionItem1.getObject(cipher);


//System.out.println("item ID is:" + "unsealed sealdedobject can be referenced here" + " \n item Title is: " + auctionItem1 );

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

//private void connectRemote(int itemId,int bidderID, int reservePrice, int currentBid,  String itemTitle, String itemDescription) throws RemoteException
//    private static void connectRemote()
//    {
////        this.itemId = itemId;
////        this.bidderID = bidderID;
////        this.reservePrice = reservePrice;
////        this.currentBid = currentBid;
////        this.itemTitle = itemTitle;
////        this.itemDescription = itemDescription;
//        try
//        {
//            Registry reg = LocateRegistry.getRegistry("localhost",1099);
//            MyInterface inf = (MyInterface) reg.lookup ("hi_server");
//
//            ClientRequest client1 = new ClientRequest(1);
//            SecretKey decodedKey = readFile();
//
//
//            Cipher cipher = Cipher.getInstance("AES");
//
//            cipher.init(Cipher.ENCRYPT_MODE, decodedKey);
//            //cipher = the created encrypt cipher
//            javax.crypto.SealedObject test = new SealedObject(client1,cipher);
//            SealedObject auctionItem1 = inf.getSpec(itemId, test);
//
//            int idForMyAuction = inf.createAuction(1,0,1000,100,"Ball", "a football");
//
//            cipher.init(Cipher.DECRYPT_MODE, decodedKey);
//            AuctionItem A = (AuctionItem)auctionItem1.getObject(cipher);
//
//
//            //System.out.println("item ID is:" + "unsealed sealdedobject can be referenced here" + " \nitem Title is: " + auctionItem1 );
//        }
//        catch (NotBoundException|RemoteException e)
//        {
//            System.out.println("Exception:"+e);
//        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | IOException | BadPaddingException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }

//ClientRequest client1 = new ClientRequest(1);
//SecretKey decodedKey = readFile();
