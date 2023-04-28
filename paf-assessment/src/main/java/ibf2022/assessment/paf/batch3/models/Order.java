package ibf2022.assessment.paf.batch3.models;

public class Order {
    private String beerId;
    private Integer quantity;
    
    public Order(String beerId, Integer quantity) {
        this.beerId = beerId;
        this.quantity = quantity;
    }
    
    public String getBeerId() {
        return beerId;
    }
    public void setBeerId(String beerId) {
        this.beerId = beerId;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Order [beerId=" + beerId + ", quantity=" + quantity + "]";
    }
    
}
