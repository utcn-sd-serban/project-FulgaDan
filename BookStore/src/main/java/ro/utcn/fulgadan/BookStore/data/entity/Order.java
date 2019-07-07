package ro.utcn.fulgadan.BookStore.data.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="orders")
public class Order {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer orderId;

    @OneToOne
    @JoinColumn(name="customer_id")
    private Customer customer;

    @Column(name="price")
    private Float price;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "order_product",
            joinColumns = { @JoinColumn(name = "order_id")},
            inverseJoinColumns = { @JoinColumn(name = "product_id")})
    private List<Product> productsBuy = new ArrayList<>();

    public Order() {
    }

    public Order(Integer orderId, Customer customer, List<Product> productsBuy, Float price) {
        this.orderId = orderId;
        this.customer = customer;
        this.price = price;
        this.productsBuy = productsBuy;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Product> getProductsBuy() {
        return productsBuy;
    }

    public void setProductsBuy(List<Product> productsBuy) {
        this.productsBuy = productsBuy;
    }
}
