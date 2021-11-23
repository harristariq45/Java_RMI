import java.io.Serializable;

public class ClientRequest implements Serializable {

    private int clientId;

    public ClientRequest(int clientId)
    {
        this.clientId = clientId;

    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }
}

