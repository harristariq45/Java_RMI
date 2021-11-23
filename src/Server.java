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

public class Server extends UnicastRemoteObject implements MyInterface
{
    public Server() throws RemoteException
    {
        super();

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

    public static void keyGenerator() throws NoSuchAlgorithmException, RemoteException {
        //super();
        KeyGenerator keyGen = null;

        try {
            keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(128);
           SecretKey secretKey = keyGen.generateKey();
            Path path = Paths.get("L:\\Year3\\SCC311\\src\\secretKey.txt");
            try {
                FileOutputStream fs = new FileOutputStream("L:\\Year3\\SCC311\\src\\secretKey.txt");
                byte[] secretKeyEncoded = secretKey.getEncoded();
                System.out.println(secretKeyEncoded +" 1 ");
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


    public static void main (String args[]) throws RemoteException, NoSuchAlgorithmException, FileNotFoundException {
        Server server = new Server();
        startServer(server);
        keyGenerator();
        readFile();
    }

//    @Override
//    public AuctionItem getSpec(int itemId, int clientId) throws RemoteException {
//
//        return new AuctionItem(itemId);
//    }

    public SealedObject getSpec(int itemId, SealedObject clientReq) throws IOException {
        return null;
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

        //System.out.println(baos);
        //baos.toByteArray();
        //System.out.println(baos);

        //byte[] secretKeyEncoded = secretKey.getEncoded();

        return (SecretKey) new SecretKeySpec(baos.toByteArray(), "AES");
    }
}
