import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.Scanner;

public class Seller
{
    private static String itemDescription;
    private static String itemTitle;
    private static int itemId;
    private static int clientID;
    private Object SealedObject;
    //private int itemId;
    private int bidderID;
    private int reservePrice;
    private int currentBid;

    public static void main(String[] args) throws RemoteException
    {

        Seller one = new Seller();
        //Server server1= new Server();
        Scanner scanner = new Scanner(System.in);




        try
        {
            System.out.println("---11");
            Registry reg = LocateRegistry.getRegistry("localhost",1099);
            MyInterface inf = (MyInterface) reg.lookup ("hi_server");
            System.out.println("press 1 if you have a unique clientID, 2 if you do not have a client ID?");
            int ans = scanner.nextInt();

            if(ans == 1){
                System.out.println("Please enter unique clientID");
                int clientID = scanner.nextInt();
            }
            else{
                System.out.println("Please enter your name");
                String clientName = scanner.next();
                int clientID = inf.clientId(clientName);
            }

            System.out.println("clientId is: " + clientID);

            System.out.println("To create listing ");
//this would instead request a users unique generated user id, if this does not exist they should create one, this would be a separate function that would take a name, email and return a random generated unique id for a user


            System.out.println("Please enter winner ID");
            int winnerID = scanner.nextInt();
            //System.out.println(bidderID);

            System.out.println("Please enter reserve price");
            int reservePrice = scanner.nextInt();
            //System.out.println(reservePrice);

            System.out.println("Please enter current bid");
            int currentBid = scanner.nextInt();
            // System.out.println(currentBid);

            System.out.println("Please enter itemTitle");
            String itemTitle = scanner.next();
            // System.out.println(itemTitle);

            System.out.println("Please enter itemDescription");
            String itemDescription = scanner.next();
            // System.out.println(itemDescription);

            //ClientRequest client1 = new ClientRequest(1);
            //SecretKey decodedKey = readFile();

            System.out.println("itemDescription2");

           // Cipher cipher = Cipher.getInstance("AES");

            //cipher.init(Cipher.ENCRYPT_MODE, decodedKey);
            //javax.crypto.SealedObject test = new SealedObject(client1,cipher);
            //SealedObject auctionItem1 = inf.getSpec(itemId, test);

            //AuctionItem idForMyAuction = inf.createAuction(itemId,winnerID,reservePrice,currentBid,itemTitle, itemDescription);

            //System.out.println(idForMyAuction);
            //cipher.init(Cipher.DECRYPT_MODE, decodedKey);
            //AuctionItem A = (AuctionItem)auctionItem1.getObject(cipher);


            //System.out.println("item ID is:" + "unsealed sealdedobject can be referenced here" + " \nitem Title is: " + auctionItem1 );
        }
        catch (NotBoundException|RemoteException e)
        {
            System.out.println("Exception:"+e);
        } catch (IOException e) {
            e.printStackTrace();
        }


        //int arg1 =  Integer.parseInt(args[0]);
        //int arg2 =  Integer.parseInt(args[1]);

        //connectRemote();
    }

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


}

//System.out.println(baos);
//baos.toByteArray();
//System.out.println(baos);

//byte[] secretKeyEncoded = secretKey.getEncoded();