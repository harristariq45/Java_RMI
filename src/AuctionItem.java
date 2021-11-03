import java.io.Serializable;

public class AuctionItem implements Serializable {

    private int itemId;
    private String itemTitle = "One";
    private  String itemDescription = "Harris";

    public AuctionItem(int itemId)
    {
        this.itemId = itemId;

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
}

