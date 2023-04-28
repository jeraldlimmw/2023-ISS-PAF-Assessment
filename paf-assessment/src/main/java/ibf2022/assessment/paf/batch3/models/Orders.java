package ibf2022.assessment.paf.batch3.models;

import java.time.LocalDateTime;
import java.util.List;

public class Orders {
    private String orderId;
    private LocalDateTime date;
    private Integer breweryId;
    private List<Order> orders;

    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public LocalDateTime getDate() {
        return date;
    }
    public void setDate(LocalDateTime date) {
        this.date = date;
    }
    public Integer getBreweryId() {
        return breweryId;
    }
    public void setBreweryId(Integer breweryId) {
        this.breweryId = breweryId;
    }
    public List<Order> getOrders() {
        return orders;
    }
    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Orders [orderId=" + orderId + ", date=" + date + ", breweryId=" + breweryId + ", orders=" + orders
                + "]";
    }

        
}
