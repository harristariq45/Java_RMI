import java.io.Serializable;

public class AuctionItem implements Serializable {
    private int userId;
    private final int itemId;
    private int startingPrice;
    private int reservePrice;
    private int currentBid;
    private String itemTitle;
    private  String itemDescription;

    public AuctionItem(int itemId,int userId, int startingPrice, int reservePrice, int currentBid,  String itemTitle, String itemDescription)
    {
        this.itemId = itemId;
        this.userId = userId;
        this.startingPrice = startingPrice;
        this.reservePrice = reservePrice;
        this.currentBid = currentBid;
        this.itemTitle = itemTitle;
        this.itemDescription = itemDescription;

    }

    public int getItemId() {
        return itemId;
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

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }


    public int getStartingPrice() {
        return startingPrice;
    }

    public void setStartingPrice(int startingPrice) {
        this.startingPrice = startingPrice;
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
}

