import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.Scanner;

/**
 * this is the buyer class which will be used to place bids on the auctions
 */

public class Buyer
{
    private static String name; // name of the buyer
    private static String email;//  email for the buyer
    private static MyInterface inf;// interface instance

    public static void main(String[] args) throws RemoteException
    {
        Buyer one = new Buyer();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter name.");// user is asked to enter the name
        String name = scanner.next();// user input is taken here

        System.out.println("Please enter Email address ");
        String email = scanner.next();// user input for email is taken here

        System.out.println("press 1 if you have a unique bidderID, 2 if you do not have a bidder ID?");
        int ans = scanner.nextInt(); // if one is entered user will have to add its own id, if 2 entered id automatically generated.

        int bidderID = 0;
        if(ans == 1){
            System.out.println("Please enter unique clientID");
            bidderID = scanner.nextInt();
        }
        else{
            bidderID = bidderID();// id is generated through BidderID method which generates a random number
        }

        System.out.println("bidderID is: " + bidderID);/// bidder id is printed

        try
        {
            Registry reg = LocateRegistry.getRegistry("localhost",1099);
            MyInterface inf = (MyInterface) reg.lookup ("hi_server");

            /*
             * the program stays in the while loop if the user enters with 1 or 2, if any other number is entered while loop s exited and buyer is closed.
             */
            int userCmd = 1;
            while (userCmd == 1 || userCmd ==2 )
            {
                System.out.println("Press 1 to display auction list \n 2 to bid \n 0 to exit  ");
                userCmd = scanner.nextInt();

                if(userCmd==1) {

                    HashMap<Integer, AuctionItem> auctionItemHashMap = new HashMap<Integer, AuctionItem>();
                    auctionItemHashMap = inf.getHashMapAuctionList();// get hashmap method from the interface used here

                    /*
                      this code underneath is used to print the auction hashmap in a presentable form by using a for loop and getter methods
                      while the print statement gives it a shape
                     */
                    System.out.println("| AuctionID  ||     ClientId    ||    Highest Bidder   ||     Current Bid  ||      Item Title    ||    Item description |");
                    for (Integer Index: auctionItemHashMap.keySet())
                    {
                        AuctionItem item = auctionItemHashMap.get(Index);

                        System.out.println("|" +Index+ "       ||        "+ item.getClientId()+ "        ||       "+ item.getHighestBidderID()+ "        ||       "+ item.getCurrentBid()+ "      ||       "+ item.getItemTitle()+ "       ||         "+item.getItemDescription()+"    |");
                    }
                }

                /*
                 * if user enters 2 he can bid then
                 */

                if(userCmd==2){

                    System.out.println("Please enter item id");
                    int auctionID = scanner.nextInt();// auction id input is taken here to match which auction item is the bid for

                    System.out.println("Please enter current bid");
                    int currentBid = scanner.nextInt();// bid is taken here and current bid is updated if it is bigger then the older bid.

                    HashMap<Integer, AuctionItem> bidForAuctionItem= inf.newBid(name,email,auctionID,bidderID,currentBid);// new bid method from the interface is used.
                }
            }
        }
        catch (NotBoundException|RemoteException e)
        {
            System.out.println("Exception:"+e);
        }
    }

    /**
     * this method generates a random number which is used to assign a unique ID.
     *
     */

    public static int bidderID() {
        return (int)Math.floor(Math.random()*(100000000-100000+1)+100000);
    }
}

//System.out.println(baos);
//baos.toByteArray();
//System.out.println(baos);

//byte[] secretKeyEncoded = secretKey.getEncoded();

 //   public static SecretKey readFile() throws FileNotFoundException {
////        ByteArrayOutputStream baos = null;
////        InputStream fis = null;
////        try {
////            byte[] buffer = new byte[4096];
////            baos = new ByteArrayOutputStream();
////            fis = new FileInputStream("L:\\Year3\\SCC311\\src\\secretKey.txt");
////            int read = 0;
////            while ((read = fis.read(buffer)) != -1) {
////                baos.write(buffer, 0, read);
////            }
////        } catch (IOException e) {
////            e.printStackTrace();
////            System.out.println(" error0");
////        } finally {
////            try {
////                if (baos != null)
////                    baos.close();
////            } catch (IOException e) {
////                System.out.println(" error1");
////            }
////
////            try {
////                if (fis != null)
////                    fis.close();
////            } catch (IOException e) {
////                System.out.println(" error2");
////            }
////        }
////        return (SecretKey) new SecretKeySpec(baos.toByteArray(), "AES");
////    }

//    private void connectRemote(int itemId, int currentBid, int buyerId) throws RemoteException
//    {
//        try
//        {
//            Registry reg = LocateRegistry.getRegistry("localhost",1099);
//            MyInterface inf = (MyInterface) reg.lookup ("hi_server");
//
//            ClientRequest client1 = new ClientRequest(1);
//            SecretKey decodedKey = readFile();
//
//            Cipher cipher = Cipher.getInstance("AES");
//
//            //create an ecrypt_mode cipher
//            cipher.init(Cipher.ENCRYPT_MODE, decodedKey);
//            //cipher = the created encrypt cipher
//            javax.crypto.SealedObject test = new SealedObject(client1,cipher);
//            SealedObject auctionItem1 = inf.getSpec(itemId, test);
//
//
//
//
//            cipher.init(Cipher.DECRYPT_MODE, decodedKey);
//            AuctionItem A = (AuctionItem)auctionItem1.getObject(cipher);
//
//            //to unsel you want an auctionitem variable to hold a casted auctionItem1.getObject() as auctionitem
//            //System.out.println("item ID is:" + "unsealed sealdedobject can be referenced here" + " \nitem Title is: " + auctionItem1 );
//        }
//        catch (NotBoundException|RemoteException e)
//        {
//            System.out.println("Exception:"+e);
//        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | IOException | BadPaddingException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }

//


//            ClientRequest client1 = new ClientRequest(1);
//            SecretKey decodedKey = readFile();
//
//            Cipher cipher = Cipher.getInstance("AES");
//
//            cipher.init(Cipher.ENCRYPT_MODE, decodedKey);
//            //cipher = the created encrypt cipher
//            javax.crypto.SealedObject test = new SealedObject(client1,cipher);
//            SealedObject auctionItem1 = inf.getSpec(itemId, test);
//
//            int newBidForItem = inf.newBid(itemId,bidderID,currentBid);
//
//            cipher.init(Cipher.DECRYPT_MODE, decodedKey);