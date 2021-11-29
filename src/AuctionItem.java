import java.io.Serializable;

/**
 * this is the auction item class which will be used to make instances of auction items and store their information
 */

public class AuctionItem implements Serializable {
    public String highestBidderEmail;
    public String highestBidderName;
    public int highestBidderID;
    public int currentBid;
    private int userId;
    private final int clientId;
    private int reservePrice;
    private String itemTitle;
    private  String itemDescription;

    /**
     *
     * @param highestBidderName
     * @param highestBidderEmail
     * @param clientId
     * @param highestBidderID
     * @param reservePrice
     * @param currentBid
     * @param itemTitle
     * @param itemDescription
     */

    public AuctionItem(String highestBidderName, String highestBidderEmail,int clientId,int highestBidderID, int reservePrice, int currentBid,  String itemTitle, String itemDescription)
    {
        this.clientId = clientId;
        this.highestBidderID = highestBidderID;
        this.reservePrice = reservePrice;
        this.currentBid = currentBid;
        this.itemTitle = itemTitle;
        this.itemDescription = itemDescription;
        this.highestBidderName = highestBidderName;
        this.highestBidderEmail=highestBidderEmail;
    }

    public int getClientId() {
        return clientId;
    }
    public String getItemTitle() {
        return itemTitle;
    }
    public String getItemDescription() {
        return itemDescription;
    }
    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public void setItemName(String highestBidderName) {
        this.highestBidderName = highestBidderName;
    }

    public void setItemEmail(String highestBidderEmail) {
        this.highestBidderEmail = highestBidderEmail;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public int getHighestBidderID() {
        return highestBidderID;
    }

    public void setHighestBidderID(int highestBidder) {
        this.highestBidderID = highestBidderID;
    }

    public int getReservePrice() {
        return reservePrice;
    }

    public void setReservePrice(int reservePrice) {
        this.reservePrice = reservePrice;
    }

    public int getCurrentBid() {
        return currentBid;
    }

    public void setCurrentBid(int currentBid) {
        this.currentBid = currentBid;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getHighestBidderName() {
        return highestBidderName;
    }

    public String getHighestBidderEmail() {
        return highestBidderEmail;
    }
}

