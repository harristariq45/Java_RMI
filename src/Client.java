import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Client
{
    private Object SealedObject;

    public static void main(String[] args) throws RemoteException
    {
        Client one = new Client();
        //java client 1 2
        // this will pass am item id of 10 and a client id of 2
        int arg1 =  Integer.parseInt(args[0]);
        int arg2 =  Integer.parseInt(args[1]);
        one.connectRemote(arg1, arg2);
    }

    private void connectRemote(int itemId, int clientId) throws RemoteException
    {
        try
        {
            Registry reg = LocateRegistry.getRegistry("localhost",1099);
            MyInterface inf = (MyInterface) reg.lookup ("hi_server");



            ClientRequest client1 = new ClientRequest();
            SecretKey decodedKey = readFile();


            Cipher cipher = Cipher.getInstance("AES");

            //create an ecypt_mode cipher
            cipher.init(Cipher.ENCRYPT_MODE, decodedKey);
            //cipher = the created encrypt cipher
            javax.crypto.SealedObject test = new SealedObject(client1,cipher);
            SealedObject auctionItem1 = inf.getSpec(itemId, test);

            cipher.init(Cipher.DECRYPT_MODE, decodedKey);
             AuctionItem A = (AuctionItem)auctionItem1.getObject(cipher);

            //to unsel you want an auctionitem variable to hold a casted auctionItem1.getObject() as auctionitem
            System.out.println("item ID is:" + "unsealed sealdedobject can be referenced here" + " \nitem Title is: " + auctionItem1. );
        }
        catch (NotBoundException|RemoteException e)
        {
            System.out.println("Exception:"+e);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | IOException | BadPaddingException | ClassNotFoundException e) {
            e.printStackTrace();
        }
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
}




//System.out.println(baos);
//baos.toByteArray();
//System.out.println(baos);

//byte[] secretKeyEncoded = secretKey.getEncoded();